package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;

/**
 * FibonacciClient is a client that sends requests to the FibonacciServer
 * and interprets its replies.
 * A new FibonacciClient is "open" until the close() method is called,
 * at which point it is "closed" and may not be used further.
 */
public class YelpDBClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    // Rep invariant: socket, in, out != null
    
    /**
     * Make a FibonacciClient and connect it to a server running on
     * hostname at the specified port.
     * @throws IOException if can't connect
     */
    public YelpDBClient(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    
    /**
     * Send a request to the server. Requires this is "open".
     * @param x to find Fibonacci(x)
     * @throws IOException if network or server failure
     */
    public void sendRequest(String x) throws IOException {
        out.print(x + "\n");
        out.flush(); // important! make sure x actually gets sent
    }
    
    /**
     * Get a reply from the next request that was submitted.
     * Requires this is "open".
     * @return the server response
     * @throws IOException if network or server failure
     */
    public String getReply() throws IOException {
        String reply = in.readLine();
        if (reply == null) {
            throw new IOException("connection terminated unexpectedly");
        }
        
        try {
            return reply;
        } catch (NumberFormatException nfe) {
            throw new IOException("misformatted reply: " + reply);
        }
    }

    /**
     * Closes the client's connection to the server.
     * This client is now "closed". Requires this is "open".
     * @throws IOException if close fails
     */
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
    
    
    
    
    private static final int N = 100;
    
    /**
     * Use a FibonacciServer to find the first N Fibonacci numbers.
     */
    public static void main(String[] args) {
        try {
            YelpDBClient client = new YelpDBClient("localhost", YelpDBServer.PORT_NUM);

           Scanner sc = new Scanner(System.in);
           String input = sc.nextLine();
           while(input != "end") {
        	   client.sendRequest(input);
        	   // collect the replies
        	   String y = client.getReply();
        	   System.out.println(y);
        	   input = sc.nextLine();
           }
           client.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
