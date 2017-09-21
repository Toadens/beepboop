/**
 * @author Johnny Edgett
 * @version 1.02
 * This is the server side of a small client-server application. 
 * When the user beeps, the server boops!
 * Also it uses threads because I am trying to brush up on my programming...
 */
package Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    
    public static void main(String[] args){
        int portNum = Integer.parseInt("1234"); // Change to take input from args[]
        
        String curDirectory = System.getProperty("user.dir");
        File directory = new File(curDirectory.concat("/serverlogs/"));
        if(!directory.exists()){
            directory.mkdir();
        }
        
        // Datetime for logging
        SimpleDateFormat dF = new SimpleDateFormat("MM-dd-yy HH:mm:ss");


        
        // Create the ServerSocket
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(portNum);
        } catch (IOException ex) {
            throw new RuntimeException("Could not open socket", ex);
        }
        
        boolean running = true;
        
        while(running){
            
            Socket clientSocket = null;
            try {
                clientSocket = socket.accept(); // Wait for an incoming connection
            } 
            catch (IOException ex) {
                throw new RuntimeException("Could not accept incoming connection", ex);
            }
            
            new Thread(new booper(clientSocket, dF, new Date(), directory)).start();
            
        }
    }
}

class booper implements Runnable {

    public Socket clientSocket;
    public SimpleDateFormat dF;
    public Date date;
    public File directory;
    
    public booper(Socket clientSocket, SimpleDateFormat dF, Date date, File directory){
        this.clientSocket = clientSocket;
        this.dF = dF;
        this.date = date;
        this.directory = directory;
    }
    
    // Implement logging
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.println("Received: \"" + in.readLine() + "\" at " + dF.format(date));
            out.println("boop");
        } catch (Exception ex){
            throw new RuntimeException("Unable to boop", ex);
        }
    }
}