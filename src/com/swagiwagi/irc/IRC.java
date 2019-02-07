package com.swagiwagi.irc;

/**
 * 
 * IRC Interface.
 * 
 * @author SwagiWagi
 * 
 */

public interface IRC
{
	
	/**
	 * Connecting to the IRC server.
	 */
	void connect();

	/**
	 * Disconnecting from the server.
	 * @param reason for disconnecting from the server.
	 */
	void disconnect(String reason);
	
	/**
	 * Setting the output interface "IRCLogger".
	 * @param logger The "IRCLogger" class.
	 */
	void setLogger(IRCLogger logger);

	/**
	 * Sending a message to the server.
	 * @param message The message to send.
	 */
	void sendMessage(String message);
	
	
	/**
	 * Sending a raw message to the server, usually used for commands.
	 * @param message The message to send.
	 */
	void sendRawMessage(String message);
	
}