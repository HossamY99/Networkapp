import java.io.*;
import java.net.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.*;

public class SocketThread implements Runnable {
        private Socket socket;
        private DataInputStream datain;
        private DataOutputStream dataOutputStream;
        String username="";
        String product;
        double price;
        int pid;

        public SocketThread(Socket socket){
            this.socket = socket;   }
        
        public void run(){
           
        	Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        	
        	
          try {
                  datain = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                  dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            } catch (IOException e) {
                  System.err.println("Failed to get input/output streams");
                  e.printStackTrace();
              }
String clientcmnd="a-";
     //         try {
      //           clientcmnd = datain.readUTF();
       //          
       //       } catch (IOException e) {
 // System.out.println("caught");
  //            }
    //          System.out.println(clientcmnd);
              
  
              
              
              
              
              boolean cnxnopen = true;
              while (cnxnopen){
                  
           	  try {
                      clientcmnd = datain.readUTF();
                      
                   } catch (IOException e) {
       System.out.println("caught2");
                   }
            	  
                  ArrayList<String> Arguments = new ArrayList<>(Arrays.asList(clientcmnd.split("-")));
                  String result = Arguments.get(0);
                  
                  
                  
                  
                  if (Arguments.get(0).equals("LOGIN")){
                  	try {
                  		
  						Statement statement=connection.createStatement();
  						
  						ResultSet r = statement.executeQuery("SELECT * FROM userpass WHERE email = '"+Arguments.get(1)+"' ");
  						
  						if(!r.next()) {
  							result="invalid username";
  						}
  						else { //use r.beforefirst() instead or check if beforefirst is false
  							ResultSet r2 = statement.executeQuery("SELECT * FROM userpass WHERE pass = '"+Arguments.get(2)+"' AND email = '"+Arguments.get(1)+"'");
  							result="here";
  							if(!r2.next()) {
  								result="invalid password";
  							}
  							else {
  								result ="verified";
  								
  								
  								username = r2.getString("email");
  								System.out.println("Username: "+r2.getString("email"));
  								//result=username;
  							}
  						}
  						
  					} catch (SQLException e) {
  						// TODO Auto-generated catch block
  						e.printStackTrace();
  					}
                  
                  }
                  
                  
                  
                  
                  
                  
                  	else if(Arguments.get(0).equals("ADDTOCART")) {
                  	try {
                  		result = "";
                  		Statement statement=connection.createStatement();
                  		
                  		ResultSet r = statement.executeQuery("SELECT * FROM products WHERE product = '"+Arguments.get(1)+"'");
                  		if(!r.next())
                  		{
                  			result="error occurred";
                  		}
                  		else {
                  			 
                  			 product=r.getString("product");
                  			 price=r.getDouble("price");
                  			
                  			// or make a synchronized method to increment purchase id
                  			statement.executeUpdate("INSERT INTO cart (user,product,price) VALUE ('"+username+"','"+product+"','"+price+"')");
  							result="done";
  						
                  	}
                  	}
                  	catch(SQLException e) {
                  		System.out.println("Exception "+e);
                  		e.printStackTrace();
                  		
                  	}
                  	
                  }
                  
                  	else if(Arguments.get(0).equals("CREATE")){
                  		
                  		if(!Arguments.get(1).endsWith("@mail.aub.edu"))
        				{
        					result="domainexception";
        				}
        				else {
                  		
                  		Statement statement = null;
            			try {
            				statement = connection.createStatement();
            			
            			
    	        				ResultSet g = statement.executeQuery("SELECT * FROM userpass WHERE email = '"+Arguments.get(1)+"'");
    	        			
    	        				if(!g.next()) {
    	                  			statement.executeUpdate("INSERT INTO userpass (email,pass) VALUES ('"+Arguments.get(1)+"','"+Arguments.get(2)+"')");
    	  						
    	                  			//ResultSet r = statement.executeQuery("SELECT * FROM userpass WHERE email = '"+Arguments.get(1)+"'");
    	                  			//while(r.next()) {
          							//	result=r.getString("email")+" "+r.getString("pass");
      								//}
    	                  			
    	                  			result="done";
    	        						
    	        				username=Arguments.get(1);
    	        				}
            			else {
        					result="email-already-in-use";
        			}}
            			catch(SQLException e) {}
    	        			
        				}
                  	}
                  
                  else if(Arguments.get(0).equals("Viewproducts")) {
                  	try {
                  		result = "";
                  		Statement statement=connection.createStatement();
  						ResultSet r = statement.executeQuery("SELECT * FROM products ");
  						
  							
  							while(r.next()) {
  								result = result + r.getString("product") + "-" + r.getDouble("price")  +"-";
  								}
  								
  						
  							 
                  	}
                  	catch (SQLException e) {
                  		e.printStackTrace();
                  		} 
                  }
                  
    
                  else if(Arguments.get(0).equals("Closeconvo")) {  	
                	  cnxnopen=false;
                    }
                    
                  else if(Arguments.get(0).equals("Computetotal")) {
                  	try {
                  		result = "";
                  		Statement statement=connection.createStatement();
  						ResultSet r = statement.executeQuery("SELECT * FROM cart WHERE user ='"+username+"'  ");
  							
  							int id=0;
  							price=0;
  							String res="";
  							int thisprice=0;
  							while(r.next()) {
  								//System.out.println("count");
  								res= r.getString("product");
  								thisprice=r.getInt("price");
  								id=r.getInt("purchaseId");
  								price=price+thisprice;
  								//result=String.valueOf(price);
  	  						//	result=result+ "-" + res;
  								
  	  							result=result+res+"-"+String.valueOf(thisprice)+"-"+String.valueOf(id)+"-";
  							
  							}
  						//	result=String.valueOf(price);
  							result=result + String.valueOf(price);
  								
  						
  							 
                  	}
                  	catch (SQLException e) {
                  		e.printStackTrace();
                  		} 
                  }
                  
                  
                  else if(Arguments.get(0).equals("Deleteitem")) {
                  	try {
                  		result = "Hey";
                  		Statement statement=connection.createStatement();
                  		//String item = Arguments.get(1);
                  		int pid= Integer.parseInt(Arguments.get(1));
                  		
  							
  								//to not delete all rows with same data pass in an id or something from the client for the row you want deleted
  								statement.executeUpdate("DELETE FROM cart WHERE user='"+username+"'and purchaseId='"+pid+"' "); 
  								result = "success";
  							}
                  	catch(SQLException e) {
                		System.out.println("Exception "+e);
                		e.printStackTrace();
                		
                	}
          
                  	}
                  
                  else if(Arguments.get(0).equals("Quit")) {
                	  cnxnopen=false;
                  }
                  
                  
                  
                  else if(Arguments.get(0).equals("Deleteall")) {
                    	try {
                    		result = "Hey";
                    		Statement statement=connection.createStatement();
                    		//String item = Arguments.get(1);
                    		//int pid= Integer.parseInt(Arguments.get(1));
                    		
    							
    								//to not delete all rows with same data pass in an id or something from the client for the row you want deleted
    								statement.executeUpdate("DELETE FROM cart WHERE user='"+username+"' "); 
    								result = "success";
    							}
                    	catch(SQLException e) {
                  		System.out.println("Exception "+e);
                  		e.printStackTrace();
                  		
                  	}
            
                    	}
                  
                  
                  
                 
                  
				
                  clientcmnd = "";
                  try {
                      dataOutputStream.writeUTF(result);
                      
                      dataOutputStream.flush();
                  } catch (IOException e) {
                      //cnxnopen = false;
                      e.printStackTrace();
                  }
              }
              try {
                  dataOutputStream.close();
                  datain.close();
                  socket.close();
              } catch (IOException e) {
              }
              finally{
                  try {
                      connection.close();
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
              }
       
      }
}
          
        

           
            
            
            
            
              


