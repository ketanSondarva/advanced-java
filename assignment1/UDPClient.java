package assignment1;

import java.io.File;
import java.io.FileInputStream;
// import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            DatagramSocket socket = new DatagramSocket();

            File file = new File("D:\\Advanced Java\\assignment1\\mytext.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] fileBytes = new byte[(int) file.length()];

            fileInputStream.read(fileBytes);
            DatagramPacket packet = new DatagramPacket(fileBytes, fileBytes.length, inetAddress, 8000);

            socket.send(packet);

            System.out.println("File sent...");
            fileInputStream.close();
            socket.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
