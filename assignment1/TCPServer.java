package assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TCPServer {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(888);
            System.out.println("Server started...");

            while(true) {
                Socket clientSocket = ss.accept();
                System.out.println("new client connected: "+clientSocket);
                executorService.execute(new ClientHandler(clientSocket));
                // ss.close();
            }

        } catch(IOException ie) {
            ie.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);

                String message = reader.readLine();
                System.out.println("Message from client: "+message);

                int charCount = message.length();
                System.out.println("Character count: "+charCount);
                pw.println("Total Character count in '"+message+"' is "+charCount);

                reader.close();
                pw.close();
                clientSocket.close();
            } catch(IOException ie) {
                System.out.println(ie);
            }
        }
    }
}