package tomcat.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Response {
	private Socket client;
	private PrintStream ps;
	private String path = null;
	private String classpath = 
			this.getClass().getClassLoader().getResource(".").getPath();

	public Response() {

	}

	public Response(Socket client) {
		super();
		this.client = client;

		try {
			ps = new PrintStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void forward() throws FileNotFoundException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(path));
			ps.println("HTTP/1.1 200 OK");
			ps.println();
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) != -1) {
				ps.write(buf, 0, len);
				ps.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (client != null) {
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void forward(String url) {
		if (url.equals("/")) {
			path = classpath + "source/2.jpg";
		} else {
			path = classpath + "source" + url;
			File file = new File(path);
			if (!file.exists()) {
				path = "src/source/error.html";
			}
		}

		try {
			forward();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
