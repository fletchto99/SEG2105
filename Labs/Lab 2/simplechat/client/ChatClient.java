// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package simplechat.client;

import ocsf.client.AbstractClient;
import simplechat.common.ChatIF;
import simplechat.common.Message;

import java.io.IOException;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient {
    //Instance variables **********************************************

    /**
     * The interface type variable.  It allows the implementation of
     * the display method in the client.
     */
    ChatIF clientUI;


    //Constructors ****************************************************

    /**
     * Constructs an instance of the chat client.
     *
     * @param host     The server to connect to.
     * @param port     The port number to connect on.
     * @param clientUI The interface type variable.
     */

    public ChatClient(String username, String host, int port, ChatIF clientUI)
            throws IOException {
        super(host, port); //Call the superclass constructor
        this.clientUI = clientUI;
        this.openConnection();

        //Modified for E51: Automatically login
        this.sendToServer("#login " + username);
    }


    //Instance methods ************************************************

    /**
     * Modified for E49
     * An event to fire when the connection to the server has been closed
     */
    @Override
    public void connectionClosed() {
        System.out.println("Connection Closed to Server.");
    }

    /**
     * Handles errors in connection
     */
    @Override
    public void connectionException(Exception exception) {
        System.out.println("WARNING - The server has stopped listening for connections\n" +
                "SERVER SHUTTING DOWN! DISCONNECTING!");
        quit();
    }

    /**
     * This method handles all data that comes in from the server.
     *
     * @param msg The message from the server.
     */
    public void handleMessageFromServer(Object msg) {
        //*** E50: Listen for proper message object
        if (msg instanceof Message) {
            Message message = (Message) msg;
            clientUI.display(message.getMessage(), message.getOrigin());
        } else {
            System.out.println("RAW MSG>" + msg.toString());
        }
    }

    /**
     * Implemented for E50: Client side commands
     *
     * This method handles all data coming from the UI
     * @param message The message from the UI.
     */
    public void handleMessageFromClientConsole(String message) {
        if (message.startsWith("#")) {
            String[] parameters = message.split(" ");
            String command = parameters[0];
            switch (command) {
                case "#exit":
                    quit();
                    break;
                case "#logoff":
                    try {
                        closeConnection();
                    } catch (IOException e) {
                        System.out.println("Error closing connection!!!");
                    }
                    break;
                case "#sethost":
                    if (this.isConnected()) {
                        System.out.println("Can't do that now. Already connected.");
                    } else {
                        this.setHost(parameters[1]);
                    }
                    break;
                case "#setport":
                    if (this.isConnected()) {
                        System.out.println("Can't do that now. Already connected.");
                    } else {
                        this.setPort(Integer.parseInt(parameters[1]));
                    }
                    break;
                case "#login":
                    if (this.isConnected()) {
                        System.out.println("Can't do that now. Already connected.");
                    } else {
                        try {
                            this.openConnection();
                        } catch (IOException e) {
                            System.out.println("Error opening connection to server. Perhaps the server is not running!");
                        }
                    }
                    break;
                case "#gethost":
                    System.out.println("Current host is " + this.getHost());
                    break;
                case "#getport":
                    System.out.println("Current port is " + this.getPort());
                    break;
                default:
                    System.out.println("Invalid command: '" + command+ "'");
                    break;
            }
        } else {
            try {
                sendToServer(message);
            } catch (IOException e) {
                clientUI.display
                        ("Could not send message to server.  Terminating client.", Message.ORIGIN_CLIENT);
                quit();
            }
        }
    }

    /**
     * This method terminates the client.
     */
    public void quit() {
        try {
            this.closeConnection();
        } catch (IOException e) {
            //Ignored since the application is exiting anyways
        }
        System.exit(0);
    }
}
//End of ChatClient class
