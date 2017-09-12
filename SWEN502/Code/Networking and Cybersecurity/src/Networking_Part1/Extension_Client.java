package Networking_Part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by Julian on 12/09/2017.
 */
public class Extension_Client {

    public static void main(String[] args) {
        Extension_Client extension_client = new Extension_Client();
        extension_client.setDestination();
        extension_client.setDestination_port();
        extension_client.client_message();
        extension_client.printIPandPort();
        extension_client.receive_message();
    }

    private DatagramSocket udp;
    private int my_port;
    private InetAddress destination;
    private int destination_port;
    private byte[] data;

    public Extension_Client() {
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
        udp.close();
    }

    public void client_message() {
        byte[] message_bytes = "Send a response please".getBytes();
        DatagramPacket send_packet = new DatagramPacket(message_bytes, message_bytes.length, destination, destination_port);
        try {
            udp.send(send_packet);
            System.out.println("Sent successfully");
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
