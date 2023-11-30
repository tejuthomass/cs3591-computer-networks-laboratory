package exercise_06b;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class RARPClient {
	public static void main(String[] args) throws IOException {
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress serverAddress = InetAddress.getByName("localhost");
		
		byte[] recieveData = new byte[1024];
		byte[] sendData = new byte[1024];
		String mac = "6A:08:AA:C2";
		
		sendData = mac.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
		clientSocket.send(sendPacket);
		
		DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
		clientSocket.receive(recievePacket);
		
		String ip = new String(recievePacket.getData(), 0, recievePacket.getLength());
		System.out.println("Resolved IP Address for " + mac + " is: " + ip);
		
		clientSocket.close();
	}
}