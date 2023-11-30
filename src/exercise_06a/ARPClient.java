package exercise_06a;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ARPClient {
	public static void main(String[] args) throws IOException {
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress serverAddress = InetAddress.getByName("localhost");
		
		byte[] recieveData = new byte[1024];
		byte[] sendData = new byte[1024];
		String ip = "192.120.1.1";
		
		sendData = ip.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
		clientSocket.send(sendPacket);
		
		DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
		clientSocket.receive(recievePacket);
		
		String mac = new String(recievePacket.getData(), 0, recievePacket.getLength());
		System.out.println("Resolved MAC/Physical Address for " + ip + " is: " + mac);
		
		clientSocket.close();
	}
}