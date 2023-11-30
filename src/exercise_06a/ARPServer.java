package exercise_06a;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class ARPServer {
	private static HashMap<String, String> dnsRecords;

	public static void main(String[] args) throws IOException {
		dnsRecords = new HashMap<>();
		dnsRecords.put("192.120.1.1", "6A:08:AA:C2");
		dnsRecords.put("192.120.1.2", "8A:BC:E3:FA");

		try (DatagramSocket serverSocket = new DatagramSocket(9876)) {
			byte[] recieveData = new byte[1024];
			byte[] sendData = new byte[1024];

			while (true) {
				DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
				serverSocket.receive(recievePacket);

				String ip = new String(recievePacket.getData(), 0, recievePacket.getLength());
				InetAddress clienIP = recievePacket.getAddress();
				int clientPort =  recievePacket.getPort();
				String mac = dnsRecords.getOrDefault(ip, "IP address not found!");
				sendData = mac.getBytes();
				
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clienIP, clientPort);
				serverSocket.send(sendPacket);
			}
		}
	}
}

