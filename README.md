# IRC-Java
A simple Java library for IRC, developed by SwagiWagi.

This is mainly a showcase for a simple OOP design pattern.

# Usage
Create a new "IRC" object, and initialize it using the "IRCBuilder" class.<br />
The parameters are:<br />

String ip - The IP address to connect to.<br />
int port - The port to connect to.<br />
String user - What username to use when connecting to the IRC server.<br />
String nick - What nickname to use when connecting to the IRC server.<br />

After that, you can use the "setLogger()" method to set the output stream, the "setRealName()" method to set the real name, and the "setChannel()" method to set the channel which you want to connect to (you must put the #).<br />
Finally, use the "build()" method to build it.<br />
E.g: IRC irc = new IRCBuilder("your.server", 6667, "YourUsername", "YourNickName").setChannel("#abc").build();<br />

Now you can connect using the "connect()" method.<br />

To send a message use the "sendMessage()" method.<br />

To send a command use the "sendRawMessage()" method.<br />

To disconnect from the server use the "disconnect()" method.<br />

You can set the logger again by using the "setLogger()" method.<br />
