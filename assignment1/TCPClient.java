package assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost",888);
            // InputStream is = s.getInputStream();
            PrintWriter writer = new PrintWriter(s.getOutputStream(), true);

            Scanner scn = new Scanner(System.in);
            System.out.print("Enter message: ");
            String message = scn.nextLine();

            writer.println(message);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String response = reader.readLine();
            System.out.println("Server response: "+response);

            reader.close();
            writer.close();
            s.close();
            scn.close();
        } catch(IOException ie) {
            System.out.println(ie);
        }
    }
}
