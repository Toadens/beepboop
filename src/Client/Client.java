/**
 * @author Johnny Edgett
 * @version 1.01
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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    
    /**
     * 
     */
    public static void main(String[] args) throws IOException{
        // End if there are not enough arguments
        //if(args.length != 3){
          //  System.out.println("Usage: beep [server socket] [beepcount] [# threads]");
            //System.exit(-1);
        //}
        BufferedReader in = null;
        PrintWriter out = null;
        
        // Test Block
        int num1 = 1234;
        int num2 = 2;
        int num3 = 3;
        // Test Block
        
        // Define the hostName here
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
        out.println("beep");
        
        // Create BeepThreads
        //for(int i = 1; i <= args.length; i++){
        //    new BeepThread(num1, num2, 1).start();
        //}
        String response = null;
        
        while((response = in.readLine()) != null){
            System.out.println(response);
        }
    }
}

class BeepThread extends Thread{
    int socket;
    int beepCount;
    int threadNum;
    
    public BeepThread(int beepCount, int socket, int threadNum){
        this.socket = socket;
        this.beepCount = beepCount;
        this.threadNum = threadNum;
        
        System.out.println("Beep thread " + threadNum + " created");
    }
    
    @Override
    public void run(){
        while(true){
            try {
                System.out.println("Hello I am thread number " + threadNum + " :)");
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(BeepThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}