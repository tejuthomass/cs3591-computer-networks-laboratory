package exercise_03b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	private static final int port = 7777;

	public static void main(String[] args) throws IOException {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Chat Server is running on port " + serverSocket.getLocalPort());
			
			Socket clientSocket = serverSocket.accept();
			System.out.println("Client connected: " + clientSocket.getRemoteSocketAddress());
			
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			
			String message;
			
			while (true) {
				if ((message = in.readLine()) != null) {
					System.out.println("Client: " + message);
					if (message.equals("bye")) {
						break;
					}
				}
				
				System.out.print("You: ");
				String userMessage = userInput.readLine();
				out.println(userMessage);
				
				if (userMessage.equals("bye")) {
					break;
				}
			}
			System.out.println("Client Disconnected...");
		}
	}
}
