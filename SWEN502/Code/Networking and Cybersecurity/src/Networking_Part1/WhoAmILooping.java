package Networking_Part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by Julian on 12/09/2017.
 */
public class WhoAmILooping {

    /*Simple server application*/

    public static void main(String[] args) throws IOException {
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

            boolean serving = true;

            while (serving) {

//                try {
//                    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//                    System.out.println("Continue server, Y/N...?");
//                    String user_input = input.readLine();
//                    if (user_input.equals("N")) {
//                        serving = false;
//                        break;
//                    } else if (!user_input.equals("Y")) {
//                        throw new IOException();
//                    }
//                } catch (IOException e) {
//                    System.out.println("Sorry, your response was not recognised, please try again");
//                    continue;
//                }

                udp.receive(packet);

                String request = new String(packet.getData(), 0, packet.getLength());

                System.out.println(request);

                if (request.substring(request.length() - 3).equals("100")) {
                    serving = false;
                }

            }



            udp.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

}
