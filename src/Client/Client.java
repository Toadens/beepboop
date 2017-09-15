/*
 * Johnny Edgett
 * This is the client side of a small client-server application. 
 * When the user beeps, the server boops!
 * Also it uses threads because I am trying to brush up on my programming...
 */
package Client;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    
    public static void main(String[] args){
        if(args.length != 3){
            System.out.println("Usage: beep [server socket] [beepcount] [# threads]");
            System.exit(-1);
        }
        
        for(int i = 1; i <= args.length; i++){
            new BeepThread(Integer.parseInt(args[0]), Integer.parseInt(args[1]), i).start();
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