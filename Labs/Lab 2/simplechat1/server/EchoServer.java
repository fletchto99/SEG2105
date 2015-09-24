// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package simplechat1.server;

//**** Changed for E49 MY; import InetAddress

import java.net.InetAddress;

import ocsf.server.*;

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
        Object msg = "Welcome client at " + newClientIP.toString() + " !!";
        System.out.println(msg);
        this.sendToAllClients(msg);
    }

    synchronized protected void clientDisconnected(ConnectionToClient client) {
        InetAddress oldClientIP = client.getInetAddress();
        Object msg = "Goodbye client at " + oldClientIP.toString() + " !!";
        System.out.println(msg);
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
    public void handleMessageFromClient
    (Object msg, ConnectionToClient client) {
        System.out.println("Message received: " + msg + " from " + client);
        this.sendToAllClients(msg);
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
}
//End of EchoServer class
