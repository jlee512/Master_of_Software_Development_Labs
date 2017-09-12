package Networking_Part1;

import java.io.*;
import java.net.*;

/**
 * Created by Julian on 12/09/2017.
 */
public class Extension_Server {

    public static void main(String[] args) {
        Extension_Server extension_server = new Extension_Server();
        extension_server.printIPandPort();
        extension_server.receive_message();
        extension_server.setDestination();
        extension_server.setDestination_port();
        extension_server.server_message();
    }

    private DatagramSocket udp;
    private int my_port;
    private InetAddress destination;
    private int destination_port;
    private String most_recent_message;
    byte[] data;
    DatagramPacket packet;

    public Extension_Server() {
        try {
            udp = new DatagramSocket();
            my_port = udp.getLocalPort();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void receive_message() {
        data = new byte[256];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            udp.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Final packet received, gracefully terminate client/server");
        }
    }

    public void server_message() {
        int random_line = (int) (4 * Math.random() + 1);
        String message = "";
        File random_responses = new File("random_response.txt");
        try(BufferedReader input = new BufferedReader(new FileReader(random_responses))) {
            for (int i = 0; i < random_line; i++) {
                message = input.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] message_bytes = message.getBytes();
        DatagramPacket send_packet = new DatagramPacket(message_bytes, message_bytes.length, destination, destination_port);
        try {
            udp.send(send_packet);
            System.out.println("Sent successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        udp.close();
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
