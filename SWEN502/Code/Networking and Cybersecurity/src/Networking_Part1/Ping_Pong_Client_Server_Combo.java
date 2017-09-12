package Networking_Part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by Julian on 12/09/2017.
 */
public class Ping_Pong_Client_Server_Combo {

    private DatagramSocket udp;
    private int my_port;
    private InetAddress destination;
    private int destination_port;
    private int loop_index;
    private String most_recent_message;
    byte[] data;
    DatagramPacket packet;

    public Ping_Pong_Client_Server_Combo() {
        try {
            udp = new DatagramSocket();
            my_port = udp.getLocalPort();
            loop_index = 1;
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void receive_message() {
        data = new byte[256];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            udp.receive(packet);
            most_recent_message = new String(packet.getData(), 0, packet.getLength());
            loop_index = Integer.parseInt(most_recent_message) + 1;
            System.out.println(loop_index);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Final packet received, gracefully terminate client/server");
        }
    }

    //Method to be used before sending next message (i.e. interpreting a received message)
    public int check_loop_index() {
        if (loop_index > 29) {
            System.out.println("Index has reached 30, closing socket and other client/server");
            termination_message();
            udp.close();
            return 0;
        } else if (most_recent_message.equals("FIN")) {
            System.out.println("FIN received");
            udp.close();
            return 0;
        } else {
            //Return 1 to continue
            return 1;
        }
    }

    public void client_message() {
        byte[] message_bytes = Integer.toString(loop_index).getBytes();
        DatagramPacket send_packet = new DatagramPacket(message_bytes, message_bytes.length, destination, destination_port);
        try {
            udp.send(send_packet);
            System.out.println("Sent successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void termination_message() {
        byte[] message_bytes = "FIN".getBytes();
        DatagramPacket final_packet = new DatagramPacket(message_bytes, message_bytes.length, destination, destination_port);
        try {
            udp.send(final_packet);
            System.out.println("Sent final packet successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDestination() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the client/server ip-address: ");
        try {
            destination = InetAddress.getByName(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDestination_port() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the server my_port number: ");
        try {
            destination_port = Integer.parseInt(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printIPandPort() {
        try {
            System.out.println("IP Address: " + InetAddress.getLocalHost().getCanonicalHostName() + ", Port: " + my_port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
