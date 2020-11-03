import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author hossam
 *
 */  
public class Server {
	private int port = 2351;
    private ServerSocket serverSocket;
    
    
    public void acceptConnections()throws Exception{
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
     	
    	
    	
    	
    	try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Server socket failed to instantiate .");
            e.printStackTrace();
            System.exit(0);}
    while(true)
    {
    	
    
    	Socket newSocket =null;
    	
        try {
    
          
        	newSocket = serverSocket.accept();
        	//System.out.println("If you see this message then you are seeing what the server is printing. Press terminate then remove launch to see what happened with the client");
        	SocketThread st = new SocketThread(newSocket); //put the socket in a sockethread
            new Thread(st).start();
            
        	
        } catch (IOException e) {
            System.out.println("caught exception");
        	e.printStackTrace();
        
        	
        }}}
    private void Server() {	}
	public static void main(String[] args) throws Exception
	{
    	Server ss=new Server();
    	ss.acceptConnections();
	}

}



  


