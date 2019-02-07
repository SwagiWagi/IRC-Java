package com.swagiwagi.irc;

/**
 * 
 * The output defining interface.
 * 
 * @author SwagiWagi
 *
 */

public interface IRCLogger {

	/**
	 * The method which outputs the status of the connection.
	 * E.g: "Connected", "Disconnected".
	 * @param status The messages one by one where status is each message.
	 */
	void connectionStatus(String status);
	
	/**
	 * The method which outputs the main feed of the connection, meaning the messages.
	 * @param feed The messages one by one where feed is each message.
	 */
	void ircMainFeed(String feed);
	
}