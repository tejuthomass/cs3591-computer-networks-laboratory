package exercise_03b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
	private static String serverAddress = "localhost";
	private static final int serverPort = 7777;

	public static void main(String[] args) throws UnknownHostException, IOException {
		try (Socket socket = new Socket(serverAddress, serverPort)) {
			System.out.println("Connected to the Chat Server...");
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			
			String message;
			
			while (true) {
				System.out.print("You: ");
				String userMessage = userInput.readLine();
				out.println(userMessage);
				
				if (userMessage.equals("bye")) {
					break;
				}
				
				if ((message = in.readLine()) != null) {
					System.out.println("Server: " + message);
					if (message.equals("bye")) {
						break;
					}
				}
			}
			System.out.println("Server Disconnected...");
		}
	}
}
