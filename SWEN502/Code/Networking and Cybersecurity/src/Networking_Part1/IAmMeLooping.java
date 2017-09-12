package Networking_Part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by Julian on 12/09/2017.
 */
public class IAmMeLooping {

    public static void main(String[] args) throws IOException {

        /*Use InetAddress to get the client hostname*/
        try {
            System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            //Create a UDP socket - no differentiation between client and server
            InetAddress udp_server = InetAddress.getByName("10.140.115.158");
            InetAddress my_client = InetAddress.getLocalHost();

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter the server my_port number: ");
            int destination_port = Integer.parseInt(input.readLine());
            DatagramSocket clientSocket = new DatagramSocket();

            boolean connected_to_client = true;
            int loop_index = 1;

            while (connected_to_client && loop_index < 101) {

//                try {
//                    System.out.println("Continue client, Y/N...?");
//                    String user_input = input.readLine();
//                    if (user_input.equals("N")) {
//                        break;
//                    } else if (!user_input.equals("Y")) {
//                        throw new IOException();
//                    }
//                } catch (IOException e) {
//                    System.out.println("Sorry, your response was not recognised, please try again");
//                    continue;
//                }

//                System.out.println("Please enter your message: ");
//                String message = input.readLine() + " " + loop_index;

                String message = "hello " + loop_index;

                byte[] message_bytes = message.getBytes();

                DatagramPacket send_packet = new DatagramPacket(message_bytes, message_bytes.length, udp_server, destination_port);

                clientSocket.send(send_packet);

                System.out.println("Sent successfully");

                loop_index ++;

            }

            clientSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

}
