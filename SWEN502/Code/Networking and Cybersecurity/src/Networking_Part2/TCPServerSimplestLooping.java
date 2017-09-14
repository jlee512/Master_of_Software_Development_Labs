package Networking_Part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Julian on 14/09/2017.
 */
public class TCPServerSimplestLooping {

    public static void main(String[] args) throws IOException {

        //Get IP address for the Server Socket
        System.out.println(InetAddress.getLocalHost().getCanonicalHostName());

        //Create a server socket (this is the welcome socket) and assign an available port (provide input 0 - can also define manually)
        ServerSocket serverSocket = new ServerSocket(0);

        System.out.println(serverSocket.getLocalPort());

        // Create the worker socket once the server socket has accepted a connection (pauses on this line until there is a client connection request).
        Socket worker = serverSocket.accept();
        System.out.println("Connected");

        //Wrap input stream inside an input stream reader and a Buffered Reader.
        BufferedReader in = new BufferedReader(new InputStreamReader(worker.getInputStream()));

        boolean looping = true;
        int i = 0;

        while(i < 9) {

            String message = in.readLine();

            if (message.contains("close server")) {
                looping = false;
            }

            System.out.println(message);

            i++;

        }
        worker.close();
        serverSocket.close();
    }

}
