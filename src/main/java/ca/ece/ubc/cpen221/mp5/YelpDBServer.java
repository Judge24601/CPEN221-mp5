package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * 
 * Rep Invariant: 
 * serverSocket is not null
 * database is not null
 * restLatch and userLatch are not null and are only closed when 
 * restaurants or Users are being modified.
 * 
 * Abstraction Function:
 * Wraps a YelpDB<Business> in a server framework.
 *
 */
public class YelpDBServer {
	//Default Port Number
	public static final int PORT_NUM = 4949;
	private ServerSocket serverSocket;
	private YelpDB database;
	
	private CountDownLatch restLatch;
	private CountDownLatch userLatch;
	
	
	
	/**
	 * Make a YelpDbServer that listens for connections on port.
	 * 
	 * @param port
	 *            port number, requires 0 <= port <= 65535
	 */
	public YelpDBServer(int port) throws IOException, BindException{
		this.restLatch = new CountDownLatch(0);
		this.userLatch = new CountDownLatch(0);
		serverSocket = new ServerSocket(port);
		database = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
	}
	
	public void serve() throws IOException{
		while(true) {
			final Socket socket = serverSocket.accept();
			
			Thread handler = new Thread(new Runnable() {
				public void run() {
					try {
						try {
							handle(socket);
						}finally {
							socket.close();
						}
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			});
			handler.start();
		}
	}
	/**
	 * Takes in a query, if it is one of the four base queries, or the structured Query
	 * pass it to the database, otherwise return an error.
	 * Handles concurrency through countdownlatches - if writing is happening, 
	 * cannot read or write to that area of the database.
	 * @param socket The client connection
	 * @throws IOException
	 * @modifies database
	 */
	public void handle(Socket socket) throws IOException{
		System.err.println("client connected");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(
				socket.getOutputStream()), true);
		try {
			for(String line = in.readLine(); line != null; line = in.readLine()) {
				System.err.println("Query: "+ line);
				String queryShort  = line.split(" ")[0];
				String result;
       			switch(queryShort) {
	       			case "GETRESTAURANT": 
	       				try {
	       					restLatch.await();
	       					result = database.getRestaurant(line.replaceAll("GETRESTAURANT ", "")); //don't knock it if it works
	       				}catch(InterruptedException e) {
	       					//something
	       					result = "oops";
	       				}
	       				break;
	       			case "ADDRESTAURANT": 
	       				try {
	       					restLatch.await();
	       					System.out.println("Past the lock");
	       					restLatch = new CountDownLatch(1);
	       					result = database.addRestaurant(line.replaceAll("ADDRESTAURANT ", ""));
	       					restLatch.countDown();
	       				}catch(InterruptedException e) {
	       					restLatch.countDown();
	       					result = "oops";
	       				}	
	       				break;
	       			case "ADDUSER":
	       				try {
	       					userLatch.await();
	       					userLatch = new CountDownLatch(1);
	       					result = database.addUser(line.replaceAll("ADDUSER ", ""));
	       					userLatch.countDown();
	       				}catch(InterruptedException e) {
	       					userLatch.countDown();
	       					result = "oops";
	       				}	
	       				break;
	       			case "ADDREVIEW":
	       				try {
	       					restLatch.await();
	       					restLatch = new CountDownLatch(1);
	       					userLatch.await();
	       					userLatch = new CountDownLatch(1);
	       					result = database.addReview(line.replaceAll("ADDREVIEW ", ""));
	       					restLatch.countDown();
	       					userLatch.countDown();
	       				}catch(InterruptedException e) {
	       					restLatch.countDown();
	       					userLatch.countDown();
	       					result = "oops";
	       				}	
	       				break;
	       			case "QUERY":
	       				try {
	       					restLatch.await();
	       					Set<Business> matched = database.getMatches(line.replaceAll("QUERY " , ""));
	       					System.err.println(matched);
	       					if(matched.isEmpty()) {
	       						result = "ERR: NO_MATCH";
	       					}else if(matched.contains(null)) {
	       						result = "ERR: INVALID_QUERY";
	       					}else {
	       						result = matched.toString();
	       					}
	       				}catch(InterruptedException e) {
	       					result = "oops";
	       				}
	       				break;
	       			default:
	       				result = "ERR: ILLEGAL_REQUEST";
       			}
				out.println(result);
			}
		}finally {
			in.close();
			out.close();
		}
	}
	/**
	 * for testing only
	 * @throws BindException
	 */
	public static void test() throws BindException{
		try {
			YelpDBServer server = new YelpDBServer(PORT_NUM);
			server.serve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			YelpDBServer server = new YelpDBServer(PORT_NUM);
			server.serve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
