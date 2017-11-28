package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;
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
	private ServerSocket serverSocket;
	private YelpDB<Business> database;
	/**
	 * Make a YelpDbServer that listens for connections on port.
	 * 
	 * @param port
	 *            port number, requires 0 <= port <= 65535
	 */
	public YelpDBServer(int port, YelpDB<Business> db) throws IOException{
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
		
	}
}
