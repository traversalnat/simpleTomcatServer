package tomcat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import tomcat.serverThread.ServerThread;

public class Server {
	@SuppressWarnings("unused")
	private ServerSocket server;
	private int port;

	public void init() {
		try {
			port = 8888;
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void receive() {
		try {
			Socket client = server.accept();
			ServerThread thread = new ServerThread(client);
			thread.start();
			thread.join();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Server() {
		
	}

	public String Path() {
		return this.getClass().getClassLoader().getResource(".").getPath();
	}
	
	public static void main(String[] args) {
		
		Server server2 = new Server();

		server2.init();
		while (true) {
			server2.receive();
		}
		
		
		
	}
}
