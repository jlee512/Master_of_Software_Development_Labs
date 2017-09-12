package Networking_Part1;

import java.io.IOException;
import java.net.*;

/**
 * Created by Julian on 12/09/2017.
 */
public class IAmMe {

    public static void main(String[] args) throws IOException {

        /*Use InetAddress to get the client hostname*/
        try {
            System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            //Create a UDP socket - no differentiation between client and server
            InetAddress udp_server = InetAddress.getByName("10.140.117.189");
            InetAddress my_client = InetAddress.getLocalHost();

            int destination_port = 64843;
            DatagramSocket clientSocket = new DatagramSocket();

            String message = "Hi there, I am from: " + my_client.getHostName() + ", Port: " + clientSocket.getLocalPort();

            byte[] message_bytes = message.getBytes();

            DatagramPacket send_packet = new DatagramPacket(message_bytes, message_bytes.length, udp_server, destination_port);

            clientSocket.send(send_packet);

            System.out.println("Sent successfully");

            clientSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

}
