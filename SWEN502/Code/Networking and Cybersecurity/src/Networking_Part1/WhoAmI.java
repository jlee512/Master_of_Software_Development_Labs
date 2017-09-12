package Networking_Part1;

import java.io.IOException;
import java.net.*;

/**
 * Created by Julian on 12/09/2017.
 */
public class WhoAmI {

    /*Simple server application*/

    public static void main(String[] args) throws IOException {

        //Test with Intellij input of command line arguments
        System.out.println(args[0]);

        //Use InetAddress to get the hostname and print to the console
        try {
            System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            //Create a UDP socket - no differentiation between client and server
            DatagramSocket udp = new DatagramSocket();
            int localPort = udp.getLocalPort();
            System.out.println(localPort);

            byte[] data = new byte[256];

            DatagramPacket packet = new DatagramPacket(data, data.length);

            udp.receive(packet);

            String request = new String(packet.getData(), 0, packet.getLength());

            System.out.println(request);

            udp.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

}
