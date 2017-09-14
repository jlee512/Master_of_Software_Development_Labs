package Networking_Part2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Julian on 14/09/2017.
 */

//Note:

public class TCPHTTPClient {

    public static void main(String[] args) throws IOException {

        int port = 80;

        //Create a clioent socket (this will connect to the welcome socket) and assign an available port (provide input 0 - can also define manually)
        Socket clientSocket = new Socket(InetAddress.getByName("google.com"), port);


        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String message_line = "";
        String response = "";

        // Send HTTP Headers
        message_line = "GET /robots.txt HTTP/1.1\r\n\r\n";
        out.write(message_line);
        message_line = "Host: google.com\r\n\r\n";
        out.write(message_line);

        //Flush buffer to send message of any length
        out.flush();

        response = in.readLine();

        while(!response.equals("Sitemap: https://www.google.com/sitemap.xml")) {
            System.out.println(response);
            response = in.readLine();
        }

        System.out.println(in.readLine());

        clientSocket.close();

    }

}
