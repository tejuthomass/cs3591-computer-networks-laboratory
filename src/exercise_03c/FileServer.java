package exercise_03c;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
	private static final int PORT = 7777;
	public static void main(String[] args) throws IOException {
		 
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server is running on port " + serverSocket.getLocalPort());
			
			Socket clientSocket = serverSocket.accept();
			System.out.println("Client connected: " + clientSocket.getRemoteSocketAddress());
			
			InputStream inputStream = clientSocket.getInputStream();
			try (FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt")) {
				byte[] buffer = new byte[8192];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					fileOutputStream.write(buffer, 0, bytesRead);
				}
			}
		}
		System.out.println("File received successfully.");
		
	}

}
