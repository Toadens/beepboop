/**
 * @author Johnny Edgett
 * @version 1.01
 * This is the server side of a small client-server application. 
 * When the user beeps, the server boops!
 * Also it uses threads because I am trying to brush up on my programming...
 */
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    public static void main(String[] args){
        int portNum = Integer.parseInt("1234");
        PrintWriter out = null;
        BufferedReader in = null;
        
        
        try {
            ServerSocket socket = new ServerSocket(portNum);
            Socket clientSocket = socket.accept(); // Wait for an incoming connection
            
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        String clientInput = null;
        String serverOutput = null;
        
        try {
            clientInput = in.readLine();
            System.out.println(clientInput);
            if(clientInput.equals("beep"))
            {
                out.println("boop");
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(clientInput);
    }
}
