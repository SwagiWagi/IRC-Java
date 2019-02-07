package com.swagiwagi.irc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.swagiwagi.io.OutputThread;

/**
 * 
 * The main IRC instance.
 * 
 * @author SwagiWagi
 *
 */

public class IRCCore implements IRC
{
	
	private static int threadCount;

	private final String ip;
	private final int port;
	private final String nick, user;
	private String realName, channel;
	
	private Socket connection;
	private OutputStream outputStream;
	private InputStream inputStream;
	
	private IRCLogger logger;
	
	private Scanner inputStreamScanner;

	/**
	 * Finalizing everything so we can connect to the IRC server.
	 * @param ip The IP address to connect to.
	 * @param port The port to connect to.
	 * @param user What username to use when connecting to the IRC server.
	 * @param nick What nickname to use when connecting to the IRC server.
	 */
	public IRCCore(String ip, int port, String user, String nick)
	{
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.nick = nick;
		
		this.logger = new IRCLogger() {
			
			@Override
			public void ircMainFeed(String feed) {
				System.out.println(feed);
			}
			
			@Override
			public void connectionStatus(String status) {
				System.out.println(status);
			}
		};
	}

	/**
	 * Setting What "real name" to use when connecting to the IRC server.
	 * Optional.
	 * @param realName The real name.
	 */
	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	/**
	 * Setting What channel to use when connecting to the IRC server.
	 * Optional.
	 * @param channel What channel to connect to.
	 */
	public void setChannel(String channel)
	{
		this.channel = channel;
	}

	/**
	 * Connecting to the IRC server.
	 */
	@Override
	public void connect()
	{
		try {
			connection = new Socket(ip, port);
			outputStream = connection.getOutputStream();
			inputStream = connection.getInputStream();
			inputStreamScanner = new Scanner(inputStream);
			
			sendRawMessage((realName != null) ? "USER " + user + " 0 * : " + realName : "USER " + user + " 0 * : " + nick);
			sendRawMessage("NICK " + nick);
			
		}
		catch (UnknownHostException e)
		{
			logger.connectionStatus("Sorry, can't find this server.");
			return;
		}
		catch (IOException e)
		{
			logger.connectionStatus("An error has occured.");
			return;
		}
		
		threadCount++;
		
		if (channel != null)
			sendRawMessage("JOIN " + channel);
				
		logger.connectionStatus("Connected to server!");

		getConstOutput();
	}
	
	/**
	 * Setting the output interface "IRCLogger".
	 */
	@Override
	public void setLogger(IRCLogger logger)
	{
		this.logger = logger;
	}
	
	/**
	 * Disconnecting from the IRC server.
	 */
	@Override
	public void disconnect(String reason)
	{
		try
		{
			sendRawMessage("QUIT " + reason);
			this.connection.close();;
			this.outputStream = null;
			this.inputStream = null;
			this.inputStreamScanner = null;
			threadCount = 0;
		}
		catch (IOException e)
		{
			logger.connectionStatus("An error has occured.");
		}
		
		logger.connectionStatus("Disconnected from server.");
	}
	
	/**
	 * Sending a message to the server.
	 * @param message The message to send.
	 */
	@Override
	public void sendMessage(String message)
	{
		try
		{
			outputStream.write(("PRIVMSG " + channel + " " + message + System.lineSeparator()).getBytes());
			logger.ircMainFeed(message);
		}
		catch (IOException e)
		{
			logger.ircMainFeed("An error has occured, your message was not sent.");
		}
	}
	
	/**
	 * Sending a raw message to the server, usually used for commands.
	 * @param message The message to send.
	 */
	@Override
	public void sendRawMessage(String message)
	{
		try
		{
			outputStream.write((message + System.lineSeparator()).getBytes());
			logger.ircMainFeed(message);
		}
		catch (IOException e)
		{
			logger.ircMainFeed("An error has occured, your message was not sent.");
		}
	}
	
	/**
	 * Creating a new thread that constantly receives output from the server.
	 */
	private void getConstOutput()
	{
		Thread t = new Thread(new OutputThread(inputStreamScanner, logger, this), "OutputThread ID: " + threadCount);
		t.start();
	}
	
	/**
	 * Checks whether the connection is opened or not.
	 * @return true if the connection is open, false if closed.
	 */
	public boolean isConnected()
	{
		return connection.isClosed();
	}
	
	/**
	 * @return the threads amount in this program.
	 * Used to number the threads.
	 */
	public int getThreadCount()
	{
		return threadCount;
	}

}