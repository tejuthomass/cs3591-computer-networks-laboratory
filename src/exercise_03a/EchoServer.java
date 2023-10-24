package exercise_03a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	private static int port = 1234;

	public static void main(String[] args) throws IOException {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Echo Server is running on port " + serverSocket.getLocalPort());
			
			Socket clientSocket = serverSocket.accept();
			System.out.println("Client Connected: " + clientSocket.getRemoteSocketAddress());
			
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			
			String inputLine;
			while (!(inputLine = in.readLine()).equals("over")) {
				System.out.println("Recieved: " + inputLine);
				out.println(inputLine);
			}
			
			System.out.println("Recieved: " + inputLine);
			System.out.println("Client Disconnected...");
		}
	}
}
