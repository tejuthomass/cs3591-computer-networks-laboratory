package exercise_04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DNSClient {

	public static void main(String[] args) throws IOException {
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress serverAddress = InetAddress.getByName("localhost");
		
		byte[] recieveData = new byte[1024];
		byte[] sendData = new byte[1024];
		
		String domain = "rajalakshmi.org";
		
		sendData = domain.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
		clientSocket.send(sendPacket);
		
		DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
		clientSocket.receive(recievePacket);
		
		String ip = new String(recievePacket.getData(), 0, recievePacket.getLength());
		System.out.println("Resolved IP for " + domain + " is: " + ip);
		
		clientSocket.close();
	}
}
