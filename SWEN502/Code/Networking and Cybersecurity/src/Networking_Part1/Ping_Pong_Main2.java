package Networking_Part1;

/**
 * Created by Julian on 12/09/2017.
 */
public class Ping_Pong_Main2 {

    public static void main(String[] args) {
        Ping_Pong_Client_Server_Combo combo2 = new Ping_Pong_Client_Server_Combo();
        //At this point the 1st client server 1 is await the first client message, set the combo2 destination and port based
        //on the printed output from client server 1
        combo2.setDestination();
        combo2.setDestination_port();
        combo2.printIPandPort();

        boolean incrementing = true;

        while (incrementing) {
            combo2.client_message();
            combo2.receive_message();
            incrementing = combo2.check_loop_index() == 1;
        }
    }
}
