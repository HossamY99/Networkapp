import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

//THIS CLASS is just for testing functions and not for gui no need to run it

public class Client {

	
	public static void main(String[] args) throws Exception
	{
		String ip="localhost";
		int port=2351; //port it goes to
		boolean login=false;
		
		Socket s9 = new Socket("127.0.0.1", 2351);
	    DataOutputStream dos9 = new DataOutputStream(s9.getOutputStream());
	    DataInputStream dis9 = new DataInputStream(s9.getInputStream());
	    
	    System.out.println("Press 1 to login and 2 to create");
	    Scanner scanner = new Scanner(System.in);
	    int a=scanner.nextInt();
	    int x=0;
	    if (a==1)
	    
	    {
	    	
	    	System.out.println("enter username then enter password");
	    	
		    	Scanner scan = new Scanner(System.in);
		    	
		    	String eml=scan.nextLine();
		    	
		    	System.out.println("you entered "+eml);
		    	String pass=scan.nextLine();
		    	System.out.println("you entered "+pass);
		  
		    	dos9.writeUTF("LOGIN-"+eml+"-"+pass+"-");
		    	
		    	String res9 = dis9.readUTF();
		    	ArrayList<String> replyArguments2 = new ArrayList<>(Arrays.asList(res9.split("-")));
		    	System.out.println("C:");
		    	for(String st:replyArguments2)
		    	{
		    		System.out.println(st);
		    	}
		    	
	    		
	    
	    }
	    
	    else if (a==2)
	    {
	    
	    	System.out.println("enter email then enter password to create");
	    	
	    	Scanner scan = new Scanner(System.in);
	    	
	    	String eml=scan.nextLine();
	    	
	    	System.out.println("you entered "+eml);
	    	String pass=scan.nextLine();
	    	//System.out.println("you entered "+pass);
	  
	    	dos9.writeUTF("CREATE-"+eml+"-"+pass+"-");
	    	String res9 = dis9.readUTF();
	    	//ArrayList<String> replyArguments2 = new ArrayList<>(Arrays.asList(res9.split("-")));
	    	System.out.println(res9);
	    	//for(String st:replyArguments2)
	    	//{
	    	//	System.out.println(st);
	    	//}
	    	
	    	}
		
		
		
		
		
		
		
		Socket s = new Socket("127.0.0.1", 2351);
	    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
	    DataInputStream dis = new DataInputStream(s.getInputStream());
		dos.writeUTF("Viewproducts-");
   	 
	String res22 = dis.readUTF();
	ArrayList<String> replyArguments = new ArrayList<>(Arrays.asList(res22.split("-")));
	System.out.println("C:");
	for(String st:replyArguments)
	{
		System.out.println(st);
	}
	
	
	
	
	
	
	Socket s2 = new Socket("127.0.0.1", 2351);
    DataOutputStream dos2 = new DataOutputStream(s2.getOutputStream());
    DataInputStream dis2 = new DataInputStream(s2.getInputStream());
	dos2.writeUTF("LOGIN-abc@mail.aub.edu-abc123-");
	 
String res3 = dis2.readUTF();
ArrayList<String> replyArguments2 = new ArrayList<>(Arrays.asList(res3.split("-")));
System.out.println("C:");
for(String st:replyArguments2)
{
	System.out.println(st);
}



Socket s3 = new Socket("127.0.0.1", 2351);
DataOutputStream dos3 = new DataOutputStream(s3.getOutputStream());
DataInputStream dis3 = new DataInputStream(s3.getInputStream());
dos3.writeUTF("LOGIN-def@mail.aub.edu-def123-");
String res8 = dis3.readUTF();


dos3.writeUTF("ADDTOCART-lettuce-");
 
String res4 = dis3.readUTF();
ArrayList<String> replyArguments3 = new ArrayList<>(Arrays.asList(res4.split("-")));
System.out.println("C:");
for(String st:replyArguments3)
{
System.out.println(st);
}


//DataOutputStream dos22 = new DataOutputStream(s.getOutputStream());
//DataInputStream dis22 = new DataInputStream(s.getInputStream());



dos2.writeUTF("ADDTOCART-lettuce-");	
String res2 = dis2.readUTF();
ArrayList<String> replyArguments22 = new ArrayList<>(Arrays.asList(res2.split("-")));
System.out.println("C:");
for(String st:replyArguments22)
{
	System.out.println(st+"\n");
}



dos2.writeUTF("Computetotal-");	
String res5 = dis2.readUTF();
ArrayList<String> replyArguments5 = new ArrayList<>(Arrays.asList(res5.split("-")));
System.out.println("C:");
int to=replyArguments5.size()-1;
System.out.println(replyArguments5.get(to)+"$");
int i=0;
System.out.println("Products: ");
String g="";
while ((i+3)<replyArguments5.size())
{
	g=g+replyArguments5.get(i)+"\n";
	i=i+3;
}
System.out.println(g);



//dos2.writeUTF("Deleteitem-4");
//String res6 = dis2.readUTF();
//System.out.println(res6);



		while(true) {
			
		}
	}}
