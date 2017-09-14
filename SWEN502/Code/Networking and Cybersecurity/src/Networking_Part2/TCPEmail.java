import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Julian on 14/09/2017.
 */

//Note:

public class TCPEmail {

    public static void main(String[] args) throws IOException {

        int port = 25;

        //Create a clioent socket (this will connect to the welcome socket) and assign an available port (provide input 0 - can also define manually)
        Socket clientSocket = new Socket(InetAddress.getByName("mail.ecs.vuw.ac.nz"), port);


        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String message = "";
        String response = "";

        // Send Initial Command and await response
        message = "HELO bats.ecs.vuw.ac.nz\n";
        out.write(message);
        //Flush buffer to send message of any length
        out.flush();
        response = in.readLine();
        System.out.println(response);

        // Send Second Command and await response
        message = "MAIL FROM: <leesjuli@myvuw.ac.nz>\n";
        out.write(message);
        //Flush buffer to send message of any length
        out.flush();
        response = in.readLine();
        System.out.println(response);

        //Send Third Command and await response
        message = "RCPT TO: <leesjuli@myvuw.ac.nz>\n";
        out.write(message);
        //Flush buffer to send message of any length
        out.flush();
        response = in.readLine();
        System.out.println(response);

        //Send Data and await response
        message = "DATA\n";
        out.write(message);
        //Flush buffer to send message of any length
        out.flush();
        response = in.readLine();
        System.out.println(response);
        message = "This is an email from my Java program\n";
        out.write(message);

        //Terminate message with a fullstop on a newline
        out.write(".");
        out.newLine();
        //Flush buffer to send message of any length
        out.flush();
        response = in.readLine();
        System.out.println(response);

        //Quit
        out.write("QUIT\n");
        //Flush buffer to send message of any length
        out.flush();
        response = in.readLine();
        System.out.println(response);

        clientSocket.close();

    }

}
