package simplechat.common;

import java.io.Serializable;

/**
 *  A message object which will be passed between a client and server
 */
public class Message implements Serializable {

    /**
     * The message came from the server
     */
    public static final int ORIGIN_SERVER = 0;

    /**
     * The message came from the client
     */
    public static final int ORIGIN_CLIENT = 1;

    private final String message;
    private final int origin;

    /**
     * Creates an instance of the message which should be transfered between two endpoints
     *
     * @param message The body of the message
     * @param origin The origin of the message
     */
    public Message(String message, int origin) {
        this.message = message;
        this.origin = origin;
    }

    /**
     * Fetches the body of the message
     * @return The body of the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Fetches the origin of the message
     * @return The origin of hte message
     */
    public int getOrigin() {
        return origin;
    }
}
