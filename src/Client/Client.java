/**
 * @author Johnny Edgett
 * @version 1.02
 * This is the client side of a small client-server application. 
 * When the user beeps, the server boops!
 * Also it uses threads because I am trying to brush up on my programming...
 */
package Client;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    
    /**
     * 
     */
    public static void main(String[] args) throws IOException{
        // End if there are not enough arguments
        //if(args.length != 3){
          //  System.out.println("Usage: beep [server socket]");
            //System.exit(-1);
        //}
        BufferedReader in = null;
        PrintWriter out = null;
        
        int num1 = 1234;

        String hostName= "localhost";
        //int portNum = Integer.parseInt(args[0]);
        
        // Open up the socket to begin client-server communication
        try{
            Socket socket = new Socket(hostName, num1);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        new BeepThread(num1, in, out).start();
    }
}

class BeepThread extends Thread {
    public int socket;
    public BufferedReader in;
    public PrintWriter out;
    
    public BeepThread(int socket, BufferedReader in, PrintWriter out){
        this.socket = socket;
        this.in = in;
        this.out = out;
    }
    
    @Override
    public void run(){
        String output;
        
        out.println("beep");
        
        try {
            // Handle server response here
            while((output = in.readLine()) != null){
                System.out.println(output);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error: ", ex);
        }
    }
}