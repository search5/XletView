package xjavax.tv.xlet;


/**
 * Signa ls that a requested Xlet state change failed.
 * 
 * Signals that a requested Xlet state change failed. This exception is thrown
 * in response to state change calls in the <code>Xlet</code> interface.
 * <HR>
 * 
 * @author Martin Sveden
 * @statuscode 4
 */
public class XletStateChangeException extends Exception {
    /**
     * Constructs an exception with no specified detail message.
     * </DL>
     * 
     */
    public XletStateChangeException() {
	super();
    }

    /**
     * Constructs an exception with the specified detail message.
     * 
     * @param s -
     *                the detail message
     */
    public XletStateChangeException(java.lang.String s) {
	super(s);
    }

}
