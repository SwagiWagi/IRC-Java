package com.swagiwagi.irc;

/**
 * 
 * Building the IRC instance for you to use.
 * 
 * @author SwagiWagi
 *
 */

public class IRCBuilder
{
	
	private IRCCore irc;

	/**
	 * Building the IRC connection command.
	 * @param ip The IP address to connect to.
	 * @param port The port to connect to.
	 * @param user What username to use when connecting to the IRC server.
	 * @param nick What nickname to use when connecting to the IRC server.
	 */
	public IRCBuilder(String ip, int port, String user, String nick)
	{
		irc = new IRCCore(ip, port, user, nick);
	}

	/**
	 * Setting the output interface "IRCLogger".
	 * @param logger The "IRCLogger" class.
	 * @return this to continue building the command.
	 */
	public IRCBuilder setLogger(IRCLogger logger)
	{
		irc.setLogger(logger);
		return this;
	}
	
	/**
	 * Setting What "real name" to use when connecting to the IRC server.
	 * Optional.
	 * @param realName The real name.
	 * @return this to continue building the command.
	 */
	public IRCBuilder setRealName(String realName)
	{
		this.irc.setRealName(realName);
		return this;
	}

	/**
	 * Setting What channel to use when connecting to the IRC server.
	 * Optional.
	 * @param channel What channel to connect to.
	 * @return this to continue building the command.
	 */
	public IRCBuilder setChannel(String channel)
	{
		this.irc.setChannel(channel);
		return this;
	}

	/**
	 * Finishing the command building.
	 * @return type "IRC" for connection.
	 */
	public IRC build()
	{
		return irc;
	}

}