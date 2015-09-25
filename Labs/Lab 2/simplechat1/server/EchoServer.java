// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package simplechat1.server;

//**** Changed for E49 MY; import InetAddress

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import simplechat1.common.Message;

import java.io.IOException;
import java.net.InetAddress;

/**
 * This class overrides some of the methods in the abstract
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer {

    //Constructors ****************************************************

    /**
     * Constructs an instance of the echo server.
     *
     * @param port The port number to connect on.
     */
    public EchoServer(int port) {
        super(port);
    }


    //Instance methods ************************************************

    /****
     * Changed for E49 MY
     * /**
     * This method overrides the implementation found in AbstractServer
     */
    protected void clientConnected(ConnectionToClient client) {
        InetAddress newClientIP = client.getInetAddress();
        Message msg = new Message("Welcome client at " + newClientIP.getHostAddress() + " !!", Message.ORIGIN_SERVER);
        this.sendToAllClients(msg);
    }

    synchronized protected void clientDisconnected(ConnectionToClient client) {
        InetAddress oldClientIP = client.getInetAddress();
        Message msg = new Message("Goodbye client at " + oldClientIP.toString() + " !!", Message.ORIGIN_SERVER);
        this.sendToAllClients(msg);
    }


    /****
     * Changed for E50 MY
     * /**
     * This method handles any messages received from the client.
     *
     * @param msg    The message received from the client.
     * @param client The connection from which the message originated.
     */
    public void handleMessageFromClient(Object msg, ConnectionToClient client) {
        String message = msg.toString();
        if (message.startsWith("#")) {
            String[] params = message.substring(1).split(" ");
            if (params[0].equalsIgnoreCase("login") && params.length > 1) {
                if (client.getInfo("username") == null) {
                    client.setInfo("username", params[1]);
                } else {
                    try {
                        client.sendToClient(new Message("Your username has already been set!", Message.ORIGIN_SERVER));
                    } catch (IOException e) {
                    }
                }

            }
        } else {
            if (client.getInfo("username") == null) {
                try {
                    client.sendToClient(new Message("Please set a username before messaging the server!", Message.ORIGIN_SERVER));
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Message received: " + msg + " from " + client);
                this.sendToAllClients(new Message(client.getInfo("username") + ": " + message, Message.ORIGIN_CLIENT));
            }
        }
    }

    /**
     * This method overrides the one in the superclass.  Called
     * when the server starts listening for connections.
     */
    protected void serverStarted() {
        System.out.println
                ("Server listening for connections on port " + getPort());
    }

    /**
     * This method overrides the one in the superclass.  Called
     * when the server stops listening for connections.
     */
    protected void serverStopped() {
        System.out.println
                ("Server has stopped listening for connections.");
    }

    public void handleMessageFromServerConsole(String message) {
        //*** E50: Implement client side commands
        if (message.startsWith("#")) {
            String[] parameters = message.split(" ");
            String command = parameters[0];
            switch (command) {
                case "#quit":
                    //closes the server and then exits it
                    try {
                        this.close();
                    } catch (IOException e) {
                        System.exit(1);
                    }
                    System.exit(0);
                    break;
                case "#stop":
                    this.stopListening();
                    break;
                case "#close":
                    try {
                        this.close();
                    } catch (IOException e) {
                    }
                    break;
                case "#setport":
                    if (!this.isListening() && this.getNumberOfClients() < 1) {
                        this.setPort(Integer.parseInt(parameters[1]));
                    } else {
                        System.out.println("Can't do that now. Server is connected.");
                    }
                    break;
                case "#start":
                    if (!this.isListening()) {
                        try {
                            this.listen();
                        } catch (IOException e) {
                            //error listening for clients
                        }
                    } else {
                        System.out.println("We are already started and listening for clients!.");
                    }
                    break;
                case "#getport":
                    System.out.println("Current port is " + this.getPort());
                    break;
            }

        } else {
            this.sendToAllClients(new Message(message, Message.ORIGIN_SERVER));
        }
    }
}
//End of EchoServer class
