package simplechat1.common;

import java.io.Serializable;

public class Message implements Serializable {

    public static final int ORIGIN_SERVER = 0;
    public static final int ORIGIN_CLIENT = 1;

    private final String message;
    private final int origin;

    public Message(String message, int origin) {
        this.message = message;
        this.origin = origin;
    }

    public String getMessage() {
        return message;
    }

    public int getOrigin() {
        return origin;
    }
}
