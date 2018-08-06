package tomcat.request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

class GetParm {
	public GetParm() {
		
	}
}

public class Request {
	private Socket client;
	private BufferedReader br;
	private String url;
	private String method;
	private String protocal;
	private Map<String, String> map;
	private GetParm getParm;

	public Request() {

	}
	
	public Request(Socket client) {
		this.client = client;
		map = new HashMap<String, String>();
		getParm = new GetParm();
		try {
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String firstLine = br.readLine();
			String[] split = firstLine.split(" ");
			method = split[0];
			url = split[1];
			System.out.println(firstLine);
			setProtocal(split[2]);

			if (method.equalsIgnoreCase("get")) {
				
			} else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getProtocal() {
		return protocal;
	}

	public void setProtocal(String protocal) {
		this.protocal = protocal;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
}
