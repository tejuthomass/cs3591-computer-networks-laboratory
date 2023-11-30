package exercise_06b;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class RARPServer {
	private static HashMap<String, String> dnsRecords;

	public static void main(String[] args) throws IOException {
		dnsRecords = new HashMap<>();
		dnsRecords.put("6A:08:AA:C2", "192.120.1.1");
		dnsRecords.put("8A:BC:E3:FA", "192.120.1.2");

		try (DatagramSocket serverSocket = new DatagramSocket(9876)) {
			byte[] recieveData = new byte[1024];
			byte[] sendData = new byte[1024];

			while (true) {
				DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
				serverSocket.receive(recievePacket);

				String mac = new String(recievePacket.getData(), 0, recievePacket.getLength());
				InetAddress clienIP = recievePacket.getAddress();
				int clientPort =  recievePacket.getPort();
				String ip = dnsRecords.getOrDefault(mac, "MAC/Physical address not found!");
				sendData = ip.getBytes();
				
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clienIP, clientPort);
				serverSocket.send(sendPacket);
			}
		}
	}
}

