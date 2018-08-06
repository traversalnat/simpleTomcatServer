package tomcat.serverThread;

import java.net.Socket;

import tomcat.request.Request;
import tomcat.response.Response;

public class ServerThread extends Thread {
	private Socket client;
	public ServerThread(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		Request request = new Request(client);
		Response response = new Response(client);
		response.forward(request.getUrl());
	}
}
