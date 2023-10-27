package exercise_03c;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileClient {
	private static String serverAddress = "localhost";
	private static final int serverPort = 7777;

	public static void main(String[] args) throws UnknownHostException, IOException {
		try (Socket socket = new Socket(serverAddress, serverPort)) {
			System.out.println("Connected to the Server...");
			
			FileInputStream fileInputStream = new FileInputStream(new File("test.txt"));
			OutputStream outputStream = socket.getOutputStream();
			
			byte[] buffer = new byte[8192];
			int bytesRead;
			while ((bytesRead = fileInputStream.read(buffer)) != -1) {
			    outputStream.write(buffer, 0, bytesRead);
			}
		}
		System.out.println("File sent successfully.");
	}

}
