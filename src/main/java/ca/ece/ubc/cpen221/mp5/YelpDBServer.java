package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * Rep Invariant: 
 * serverSocket is not null
 * database is not null
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
	/**
	 * Make a YelpDbServer that listens for connections on port.
	 * 
	 * @param port
	 *            port number, requires 0 <= port <= 65535
	 */
	public YelpDBServer(int port, YelpDB db) throws IOException{
		serverSocket = new ServerSocket(port);
		database = db;
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
	
	public void handle(Socket socket) throws IOException{
		System.err.println("client connected");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(
				socket.getOutputStream()), true);
		try {
			for(String line = in.readLine(); line != null; line = in.readLine()) {
				System.err.println("Query: "+ line);
				String result = database.request(line);
				out.println(result);
			}
		}finally {
			in.close();
			out.close();
		}
	}
	
	public static void main(String[] args) {
		try {
			YelpDBServer server = new YelpDBServer(PORT_NUM, new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json"));
			server.serve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
