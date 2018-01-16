package Übung_1;
import java.net.*;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class TCPEchoClient {

    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException uhEx) {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        accessServer();
    }

    private static void accessServer() {
        Socket link = null;    //Step 1
        try {
            link = new Socket(host, PORT); //Step 1
            //Step 2
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            //Set up stream for keyboard entry
            Scanner userEntry = new Scanner(System.in);

            int firstInt, secondInt, answer;
            do {
                System.out.print("Please input the first number: ");
                firstInt = userEntry.nextInt();
                System.out.print("Please input the second number: ");
                secondInt = userEntry.nextInt();

                //send the numbers
                output.println(firstInt);
                output.println(secondInt);
                answer = input.nextInt(); //getting the answer from the server
                System.out.println("\nSERVER> " + answer);
            } while (firstInt != 0 || secondInt != 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (NoSuchElementException ne){   //This exception may be raised when the server closes connection
            System.out.println("Connection closed");
        }
        finally {
            try {
                System.out.println("\n* Closing connection… *");
                link.close(); //Step 4.
            } catch (IOException ioEx) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}