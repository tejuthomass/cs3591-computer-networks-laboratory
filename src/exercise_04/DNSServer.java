package exercise_04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class DNSServer {
	private static HashMap<String, String> dnsRecords;

	public static void main(String[] args) throws IOException {
		dnsRecords = new HashMap<>();
		dnsRecords.put("ritchennai.org", "192.120.1.1");
		dnsRecords.put("rajalakshmi.org" ,"192.120.1.2");

		try (DatagramSocket serverSocket = new DatagramSocket(9876)) {
			byte[] recieveData = new byte[1024];
			byte[] sendData = new byte[1024];

			while (true) {
				DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
				serverSocket.receive(recievePacket);

				String domain = new String(recievePacket.getData(), 0, recievePacket.getLength());
				InetAddress clienIP = recievePacket.getAddress();
				int clientPort =  recievePacket.getPort();
				String ip = dnsRecords.getOrDefault(domain, "Domain not found!");
				sendData = ip.getBytes();
				
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clienIP, clientPort);
				serverSocket.send(sendPacket);
			}
		}
	}
}
