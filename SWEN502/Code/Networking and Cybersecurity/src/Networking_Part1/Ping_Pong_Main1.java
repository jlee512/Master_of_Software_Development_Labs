package Networking_Part1;

/**
 * Created by Julian on 12/09/2017.
 */
public class Ping_Pong_Main1 {

    public static void main(String[] args) {
        Ping_Pong_Client_Server_Combo combo1 = new Ping_Pong_Client_Server_Combo();
        combo1.printIPandPort();
        combo1.receive_message();
        //At this point the 1st client server will await the first client message, once received, the message will be
        //printed and destination IP and Port will be required
        combo1.setDestination();
        combo1.setDestination_port();

        boolean incrementing = true;

        while (incrementing) {
            combo1.client_message();
            combo1.receive_message();
            incrementing = combo1.check_loop_index() == 1;
        }

    }

}
