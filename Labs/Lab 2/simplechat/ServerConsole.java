// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package simplechat;

import simplechat.common.ChatIF;
import simplechat.common.Message;
import simplechat.server.EchoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class constructs the UI for a chat client.  It implements the
 * chat interface in order to activate the display() method.
 * Warning: Some of the code here is cloned in ServerConsole
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */
public class ServerConsole implements ChatIF {
    //Class variables *************************************************

    /**
     * The default port to connect on.
     */
    final public static int DEFAULT_PORT = 5555;

    //Instance variables **********************************************

    /**
     * The instance of the client that created this ConsoleChat.
     */
    EchoServer server;


    //Constructors ****************************************************

    /**
     * Constructs an instance of the ServerConsole UI.
     *
     * @param port The port to connect on.
     */
    public ServerConsole(int port) {
        server = new EchoServer(port);
        try {
            server.listen();
        } catch (IOException e) {
            System.out.println("ERROR - Could not listen for clients!");
        }
    }


    //Instance methods ************************************************

    /**
     * This method waits for input from the console.  Once it is
     * received, it sends it to all of the clients
     */
    public void accept() {
        try {
            BufferedReader fromConsole =
                    new BufferedReader(new InputStreamReader(System.in));
            String message;

            while (true) {
                message = fromConsole.readLine();
                server.handleMessageFromServerConsole(message);
                this.display(message, Message.ORIGIN_SERVER);
            }
        } catch (Exception ex) {
            System.out.println
                    ("Unexpected error while reading from console!");
        }
    }

    /**
     * This method overrides the method in the ChatIF interface.  It
     * displays a message onto the screen.
     *
     * @param message The string to be displayed.
     */
    public void display(String message, int origin) {
        if (message.startsWith("#")) {
            return;
        }
        //We know that all messages through here will be from the console hence a server message, so there's no need to check the origin
        System.out.println("SERVER MSG> " + message);
    }


    //Class methods ***************************************************

    /**
     * This method is responsible for the creation of the Client UI.
     *
     * @param args The port to use for connection
     */
    public static void main(String[] args) {
        int port = 0; //Port to listen on

        try {
            port = Integer.parseInt(args[0]); //Get port from command line
        } catch (Throwable t) {
            port = DEFAULT_PORT; //Set port to 5555
        }

        ServerConsole serv = new ServerConsole(port);
        serv.accept();
    }

}
//End of ConsoleChat class
