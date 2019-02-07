package com.swagiwagi.io;

import java.util.Scanner;

import com.swagiwagi.irc.IRCCore;
import com.swagiwagi.irc.IRCLogger;

/**
 * 
 * This class is a Runnable class which get the output from the server constantly.
 * 
 * @author SwagiWagi
 *
 */

public class OutputThread implements Runnable
{
	
	private Scanner inputStreamScanner;
	private IRCLogger logger;
	private IRCCore ircCore;

	public OutputThread(Scanner inputStreamScanner, IRCLogger logger, IRCCore ircCore)
	{
		this.inputStreamScanner = inputStreamScanner;
		this.logger = logger;
		this.ircCore = ircCore;
	}
	
	/**
	 * As long as the server has output (connected), print them out.
	 */
	@Override
	public void run()
	{
		while (inputStreamScanner.hasNext())
		{
			logger.ircMainFeed(inputStreamScanner.nextLine());
		}
		
		ircCore.disconnect("Disconnected");
	}

}
