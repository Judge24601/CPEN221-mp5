package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Abstraction function: YelpDBClient makes requests to the server in order to get replies.
 */
public class YelpDBClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    // Rep invariant: socket, in, out != null
    
    /**
     * Make a YelbDBClient and connect it to a server running on
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
    
    public static String tester(String input) {
    	try {
           YelpDBClient client = new YelpDBClient("localhost", YelpDBServer.PORT_NUM);
    	   client.sendRequest(input);
    	   String y = client.getReply();
           client.close();
           return y;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return "ERR";
        }
    }
    
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
           sc.close();
           client.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
