package exercise_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class WebPageDownloader {

	public static void main(String[] args) throws URISyntaxException {
		String url = "https://www.google.com";
		
		try {
            // Parse the URL to get the host, port and path
			URI uri = new URI(url);
			URL parsedURL = uri.toURL();
			
			String host = parsedURL.getHost();
			int port = parsedURL.getPort() == -1 ? 80: parsedURL.getPort();
	        String path = parsedURL.getPath() == "" ? "/": parsedURL.getPath();

            // Establish a TCP connection to the web server
			Socket socket = new Socket(host, port);
		
            // Create an output stream to send the HTTP request
			OutputStream outputStream = socket.getOutputStream();
	        PrintWriter requestWriter = new PrintWriter(outputStream, true);


	        // Send an HTTP GET request to the server
	        requestWriter.println("GET " + path + " HTTP/1.1");
	        requestWriter.println("Host: " + host);
	        requestWriter.println("Connection: close\n"); // Close the connection after the response
	
            // Create an input stream to read the response
	        InputStream inputStream = socket.getInputStream();
	        BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream));
	        
	        // Read and print the server's response
	        String line;
	        while ((line = responseReader.readLine()) != null) {
	            System.out.println(line);
	        }
	        
	        socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
