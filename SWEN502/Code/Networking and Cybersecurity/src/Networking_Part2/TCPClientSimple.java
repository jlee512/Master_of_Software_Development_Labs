package Networking_Part2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Julian on 14/09/2017.
 */
public class TCPClientSimple {

    public static void main(String[] args) throws IOException {

        int port = -1;

        try {
            System.out.println("Enter the server port #: ");
            BufferedReader user_input = new BufferedReader(new InputStreamReader(System.in));
            port = Integer.parseInt(user_input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create a clioent socket (this will connect to the welcome socket) and assign an available port (provide input 0 - can also define manually)
        Socket clientSocket = new Socket(InetAddress.getByName("10.140.115.158"), port);

            String message = "";

            try {
                System.out.println("Enter a message to send to the server: ");
                BufferedReader user_input = new BufferedReader(new InputStreamReader(System.in));
                message = user_input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            out.write(message);
            //Flush buffer to send message of any length
            out.flush();

        clientSocket.close();

    }

}
