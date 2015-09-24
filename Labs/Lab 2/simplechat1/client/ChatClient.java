// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import ocsf.client.*;
import common.*;

import java.io.*;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
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
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  
  //Instance methods ************************************************
  /**
  * 
  */
  protected void connectionClosed() {
	  System.out.println("Connection Closed to Server.");
  }
  
  /**
   * Handles errors in connection
   */
  protected void connectionException(Exception exception) {
	  System.out.println("Lost Connection to Server. Exiting.");
  }
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
    clientUI.display(msg.toString());
  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message)
  {
	  if(message.toString().startsWith("#")){
			String[] command = message.toString().split(" ");
			switch(command[0]){
			case "#exit": 	quit();
							break;
			case "#logoff": try{
								closeConnection();
							}catch(IOException e){}
							break;
			case "#sethost": if(this.isConnected()){
								System.out.println("Can't do that now. Already connected.");
							}else{
								this.setHost(command[1]);
							}
							break;
			case "#setport": if(this.isConnected()){
								System.out.println("Can't do that now. Already connected.");
							}else{
								this.setPort(Integer.parseInt(command[1]));
							}
							break;
			case "#login": 	if(this.isConnected()){
								System.out.println("Can't do that now. Already connected.");
							}else{
								try {
									this.openConnection();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							break;
			case "#gethost": System.out.println(this.getHost());
							break;
			case "#getport": System.out.println(this.getPort());
							break;
			}
			
		}else{
		    try
		    {
		      sendToServer(message);
		    }
		    catch(IOException e)
		    {
		      clientUI.display
		        ("Could not send message to server.  Terminating client.");
		      quit();
		    }
		}
  }
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
