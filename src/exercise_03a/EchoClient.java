package exercise_03a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String serverAddress = "localhost";
		int serverPort = 1234;
		
		try (Socket socket = new Socket(serverAddress, serverPort)) {
			System.out.println("Connected to Echo Server...");
			
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String userInputLine;
			
			do {
				System.out.print("Enter message: ");
				userInputLine = userInput.readLine();
				
				out.println(userInputLine);
				if (!userInputLine.equals("over")) {
					String serverResponse = in.readLine();
					System.out.println("Server Response: " + serverResponse);
				}				
			} while(!userInputLine.equals("over"));
			
			System.out.println("Connection Terminated...");
		}	
	}
}
