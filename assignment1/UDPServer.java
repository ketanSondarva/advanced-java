package assignment1;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        
        try {
            DatagramSocket socket = new DatagramSocket(8000);

            byte[] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);

            socket.receive(packet);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packet.getData());
            FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt");

            byte[] fileBytes = new byte[1024];
            int byteRead;

            while((byteRead = byteArrayInputStream.read(fileBytes)) != -1) {
                fileOutputStream.write(fileBytes, 0, byteRead);
            }

            System.out.println("File received and write into received_file.txt");

            byteArrayInputStream.close();
            fileOutputStream.close();
            socket.close();
        } catch(IOException ie) {
            ie.printStackTrace();
        }        
    }
}
