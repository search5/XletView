
package net.beiker.xletview.net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

import net.beiker.xletview.io.OutputPrinter;

/**
 * 
 * @author Unknown 
 */
public class OutputServer implements OutputPrinter{

    private PrintStream original;

    // a ServerSocket for accepting new connections
    private ServerSocket ss;

    // for mapping sockets to DataOutputStreams
    private Hashtable outputStreams = new Hashtable();

    // Constructor and while-accept loop all in one.
    public OutputServer(int port, PrintStream original) throws IOException {
        this. original = original;
        // All we have to do is listen
        listen(port);
    }

    private void listen(int port) throws IOException {

        ss = new ServerSocket(port);
        System.out.println("Listening on " + ss);

        // accepting forever
        while (true) {

            // accept incoming
            Socket s = ss.accept();
            System.out.println("Connection from " + s);

            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            // Save stream 
            outputStreams.put(s, dout);

            // a new thread for this connection
            //new OutputServerThread(this, s);
        }
    }

    // Get an enumeration of all the OutputStreams, one for each client
    // connected to us
    private Enumeration getOutputStreams() {
        return outputStreams.elements();
    }

    // Send a message to all clients (utility routine)
    void sendToAll(String message) {

        // We synchronize on this because another thread might be
        // calling removeConnection() and this would screw us up
        // as we tried to walk through the list
        synchronized (outputStreams) {

            // For each client ...
            for (Enumeration e = getOutputStreams(); e.hasMoreElements();) {

                // ... get the output stream ...
                DataOutputStream dout = (DataOutputStream) e.nextElement();

                // ... and send the message
                try {
                    original.print(message);
                    dout.writeUTF(message);
                }
                catch (IOException ie) {
                    System.out.println(ie);
                }
            }
        }
    }

    void removeConnection(Socket s) {

        // Synchronize so we don't mess up sendToAll() while it walks
        // down the list of all output streamsa
        synchronized (outputStreams) {

            System.out.println("Removing connection to " + s);

            // Remove it from our hashtable/list
            outputStreams.remove(s);

            // Make sure it's closed
            try {
                s.close();
            }
            catch (IOException ie) {
                System.out.println("Error closing " + s);
                ie.printStackTrace();
            }
        }
    }

    /* (non-Javadoc)
     * @see net.beiker.xletview.io.OutputPrinter#print(java.lang.String)
     */
     static int i = 0;
    public void print(String s) {
        // TODO Auto-generated method stub
        if(i < 4){
            System.out.println("echo");
            i++;
        }
        sendToAll(s);
        
    }

}

