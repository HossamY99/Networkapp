import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;




public class GUI implements ActionListener {

	
	boolean loggedin=false;
	private static JLabel label;
	private static JLabel passwordLabel;
	private static JLabel label2;
	private static JLabel totalprice;
	private static JTextField userText;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JButton logout;
	private static JButton quit;
	private static JButton checkout;
	private static JButton backvp;
	private static JButton gotocart;
	private static JButton loginbutton;
	private static JFrame frame;
	private static JPanel panel;
	private static JButton supbutton;
	private static JButton backnl;
	private static JLabel suplabel;
	private static JLabel suppasswordLabel;
	private static JLabel suplabelle;
	private static JTextField supuserText;
	private static JPasswordField suppasswordText;
	private static JButton suppbutton;
	private static JButton vp;
	private static JButton vphome;
	private static JButton lback;
	private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;
   // private static JButton[] btns= new JButton[30];
    private static ArrayList<JButton> btns=new ArrayList<JButton>();
    private static ArrayList<JButton> prodbtns= new ArrayList<JButton>();
    private static ArrayList<Integer> pids=new ArrayList<Integer>();
    private static ArrayList<String> products=new ArrayList<String>();
    
    public GUI(){
    	System.out.println("new socket");
    s = null;
	try {
		   s = new Socket("127.0.0.1", 2351);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		System.out.println("caught");
		e1.printStackTrace();
	}
	
	try {
		dos = new DataOutputStream(s.getOutputStream());
		dis = new DataInputStream(s.getInputStream());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("nothing yet");
		e.printStackTrace();
	}
   
    }
	
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub

		
		
		
		   
	
		
		
		GUI gui= new GUI();
		frame= new JFrame();
		frame.setSize(800,500 );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		frame.add(panel);
		
		panel.setLayout(null);
		
		label=new JLabel("Welcome to the E-shop!");
		label.setBounds(10, 10, 200, 45);
		panel.add(label);
		
		
		button = new JButton("Login");
		button.setBounds(50,50,170,50);
		button.addActionListener(gui);
		panel.add(button);
		
		
		
		
		supbutton = new JButton("Sign up");
		supbutton.setBounds(50,110,170,50);
		supbutton.addActionListener(gui);
		panel.add(supbutton);
		
		
		vp=new JButton("View products");
		vp.setBounds(50, 180, 180, 50);
		vp.addActionListener(gui);
		panel.add(vp);
		
		
		
		label2=new JLabel("");
		label2.setBounds(50, 120, 100, 100);
		panel.add(label2);
		
		quit= new JButton("quit");
		quit.setBounds(500, 400, 70, 20);
		quit.addActionListener(gui);
		panel.add(quit);
		
		
		frame.setVisible(true);
		System.out.println("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//((e.getSource())).getScene().getWindow().hide();
		
		
	   
		
		
		System.out.println(e.getSource());
		if (e.getSource()==button) {
			System.out.println("");
			frame.hide();
			frame = new JFrame();
			frame.setSize(800,500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			panel=new JPanel();
			frame.add(panel);
			
			panel.setLayout(null);
			
			label=new JLabel("User");
			label.setBounds(10, 50, 80, 25);
			panel.add(label);
			
			
			label2=new JLabel("");
			label2.setBounds(50, 120, 100, 100);
			panel.add(label2);
			
			userText=new JTextField();
			userText.setBounds(100,50,80,25);
			panel.add(userText);
			
			
			passwordLabel= new JLabel("Password");
			passwordLabel.setBounds(10, 80, 80, 25);
			panel.add(passwordLabel);
			
			passwordText= new JPasswordField();
			passwordText.setBounds(100, 80, 80, 25);
			panel.add(passwordText);
		
			
			loginbutton = new JButton("Login");
			loginbutton.setBounds(100,120,70,20);
			loginbutton.addActionListener(this);
			panel.add(loginbutton);
			
			quit= new JButton("quit");
			quit.setBounds(400, 400, 70, 20);
			quit.addActionListener(this);
			panel.add(quit);
			
			
			
			
			frame.setVisible(true);
			
			

			
		}
		
		
		
		
		else if (e.getSource()==loginbutton) {
			
			String eml=userText.getText();
			String pass=passwordText.getText();
			
			try {
				dos.writeUTF("LOGIN-"+eml+"-"+pass+"-");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String res="aa";
			try {
				res = dis.readUTF();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("C:");
			System.out.println(res);
			System.out.println(res== "invalid username");
			if (res.equals("invalid username"))
			{
				label2.setText(res);
			}
			else if (res.equals("invalid password"))
			{
				label2.setText(res);
			}
			else {
				loggedin=true;
				frame.hide();
				frame = new JFrame();
				frame.setSize(800,500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				panel=new JPanel();
				frame.add(panel);
				
				panel.setLayout(null);
				
				JLabel wlc=new JLabel("Welcome "+userText.getText());
				wlc.setBounds(10, 30, 200, 100);
				panel.add(wlc);
				
			     vp=new JButton("View Products");
			     vp.setBounds(30,200, 200, 80);
			     vp.addActionListener(this);
			     panel.add(vp);
			     
			     gotocart = new JButton("cart");
				 gotocart.setBounds(50,400,70,50);
				 gotocart.addActionListener(this);
				 panel.add(gotocart);
				 
				 
				 logout= new JButton("logout");
				 logout.setBounds(500, 10, 100, 20);
				 logout.addActionListener(this);
				 panel.add(logout);
				 
				quit= new JButton("quit");
				quit.setBounds(500, 400, 70, 20);
				quit.addActionListener(this);
				panel.add(quit);
				 
			}
			//ArrayList<String> replyArguments2 = new ArrayList<>(Arrays.asList(res.split("-")));
	    	//System.out.println("C:");
	    	//for(String st:replyArguments2)
	    //	{
	    //		System.out.println(st);
	    //	}
			
			
			frame.setVisible(true);
			
			
		}
		
		
		
		
		
		else if (e.getSource()==supbutton){
		frame.hide();
		System.out.println("");
		label2.setText("OK");
		frame=new JFrame();
		frame.setSize(800,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		frame.add(panel);
		
		panel.setLayout(null);
		

		suplabel=new JLabel("New email");
		suplabel.setBounds(10, 50, 150, 25);
		panel.add(suplabel);
		
		
		
		
		supuserText=new JTextField();
		supuserText.setBounds(100,50,80,25);
		panel.add(supuserText);
		
		
		suppasswordLabel= new JLabel("Password");
		suppasswordLabel.setBounds(10, 80, 80, 25);
		panel.add(suppasswordLabel);
		
	
		
		suppasswordText= new JPasswordField();
		suppasswordText.setBounds(100, 80, 80, 25);
		panel.add(suppasswordText);
	
		
		
		suppbutton = new JButton("Signup");
		suppbutton.setBounds(100,120,140,20);
		suppbutton.addActionListener(this);
		panel.add(suppbutton);
		
		
		suplabelle=new JLabel("");
		suplabelle.setBounds(30, 100, 400, 100);
		panel.add(suplabelle);
		
		quit= new JButton("quit");
		quit.setBounds(500, 400, 70, 20);
		quit.addActionListener(this);
		panel.add(quit);
		
		
		frame.setVisible(true);
		}
		
		else if (e.getSource()==suppbutton) {
			
			System.out.println("here");
			try {
				dos.writeUTF("CREATE-"+supuserText.getText()+"-"+suppasswordText.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String res9="";
			try {
				 res9=dis.readUTF();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			if(res9.equals("done"))
			{
				frame.hide();
				frame = new JFrame();
				frame.setSize(800,500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				panel=new JPanel();
				frame.add(panel);
				
				panel.setLayout(null);
				
				JLabel wlc=new JLabel("Welcome "+supuserText.getText());
				wlc.setBounds(10, 30, 200, 100);
				panel.add(wlc);
				
			     vp=new JButton("View Products");
			     vp.setBounds(30,200, 200, 80);
			     vp.addActionListener(this);
			     panel.add(vp);
			     
			     gotocart = new JButton("cart");
				 gotocart.setBounds(50,400,70,50);
				 gotocart.addActionListener(this);
				 panel.add(gotocart);
					
				    quit= new JButton("quit");
					quit.setBounds(500, 400, 70, 20);
					quit.addActionListener(this);
					panel.add(quit);
					
				 
				 frame.setVisible(true);
				
			}
			
			else if(res9.equals("domainexception"))
			{
				System.out.println("here3");
				suplabelle.setText("should be real email ending with @mail.aub.edu");
			}
			else //we have only 1 case which is email already in use
			{
				System.out.println("here2");
				suplabelle.setText("Email already in use");
			}
			System.out.println("res: "+res9);
				
				
			
			
			
		}
		
		
		else if (e.getSource()==logout)
		{
			loggedin=false;
			frame.hide();
			frame= new JFrame();
			frame.setSize(800,500 );
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			panel=new JPanel();
			frame.add(panel);
			
			panel.setLayout(null);
			
			label=new JLabel("Welcome to the E-shop!");
			label.setBounds(10, 10, 200, 45);
			panel.add(label);
			
			
			button = new JButton("Login");
			button.setBounds(50,50,170,50);
			button.addActionListener(this);
			panel.add(button);
			
			
			
			
			supbutton = new JButton("Sign up");
			supbutton.setBounds(50,110,170,50);
			supbutton.addActionListener(this);
			panel.add(supbutton);
			
			
			vp=new JButton("View products");
			vp.setBounds(50, 180, 180, 50);
			vp.addActionListener(this);
			panel.add(vp);
			
			
			
			label2=new JLabel("");
			label2.setBounds(50, 120, 100, 100);
			panel.add(label2);
			
			quit= new JButton("quit");
			quit.setBounds(500, 400, 70, 20);
			quit.addActionListener(this);
			panel.add(quit);
			
			
			frame.setVisible(true);
			
		}
		
		
		
		else if (e.getSource()==backnl &&loggedin==true) {
			//logged in page
			frame.hide();
			frame = new JFrame();
			frame.setSize(800,500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			panel=new JPanel();
			frame.add(panel);
			
			panel.setLayout(null);
			
			JLabel wlc=new JLabel("Welcome "+userText.getText());
			wlc.setBounds(10, 30, 200, 100);
			panel.add(wlc);
			
		     vp=new JButton("View Products");
		     vp.setBounds(30,200, 200, 80);
		     vp.addActionListener(this);
		     panel.add(vp);
		     
		     gotocart = new JButton("cart");
			 gotocart.setBounds(50,400,70,50);
			 gotocart.addActionListener(this);
			 panel.add(gotocart);
			
			 
			 
			 quit= new JButton("quit");
				quit.setBounds(500, 400, 70, 20);
				quit.addActionListener(this);
				panel.add(quit);
				
			 
			 frame.setVisible(true);
		}
		
		else if (e.getSource()==backnl &&loggedin==false) {
			//home screen
			frame.hide();
			frame= new JFrame();
			frame.setSize(800,500 );
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			panel=new JPanel();
			frame.add(panel);
			
			panel.setLayout(null);
			
			label=new JLabel("Welcome to the E-shop!");
			label.setBounds(10, 10, 200, 45);
			panel.add(label);
			
			
			button = new JButton("Login");
			button.setBounds(50,50,170,50);
			button.addActionListener(this);
			panel.add(button);
			
			
			
			
			supbutton = new JButton("Sign up");
			supbutton.setBounds(50,110,170,50);
			supbutton.addActionListener(this);
			panel.add(supbutton);
			
			
			vp=new JButton("View products");
			vp.setBounds(50, 180, 180, 50);
			vp.addActionListener(this);
			panel.add(vp);
			
			quit= new JButton("quit");
			quit.setBounds(500, 400, 70, 20);
			quit.addActionListener(this);
			panel.add(quit);
			
			
			
			label2=new JLabel("");
			label2.setBounds(50, 120, 100, 100);
			panel.add(label2);
			
			
			frame.setVisible(true);
		}
		
		else if (e.getSource()==vp)
		{
			
				
			
			
			
			frame.hide();
			frame=new JFrame();
			frame.setSize(800,500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			panel=new JPanel();
			frame.add(panel);
			
			panel.setLayout(null);
			
			JLabel wlclabel=new JLabel("Products: ");
			wlclabel.setBounds(30, 0, 70,70 );
			panel.add(wlclabel);
			
			
			try {
				dos.writeUTF("Viewproducts-");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   	 
			String res22="";
			try {
				res22 = dis.readUTF();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ArrayList<String> replyArguments = new ArrayList<>(Arrays.asList(res22.split("-")));
			System.out.println("C:");
			float toint=0;
			int i=0;
			int to=replyArguments.size();
			int ycoord=30;
			while((i+1)<to)
			{
				//System.out.println(st);
				JLabel prod=new JLabel(replyArguments.get(i));
				prod.setBounds(30, ycoord, 70,30 );
				panel.add(prod);
				
				products.add(replyArguments.get(i));
				

				//toint=Float.valueOf(replyArguments.get(i+1));
				JLabel prodprice=new JLabel(replyArguments.get(i+1)+"$");
				prodprice.setBounds(100, ycoord, 100,30 );
				panel.add(prodprice);

				
				prodbtns.add(new JButton("Add to cart"));
				prodbtns.get(i/2).setBounds(200, ycoord, 150, 30);
				prodbtns.get(i/2).addActionListener(this);
				panel.add(prodbtns.get(i/2));
				
				
				ycoord=ycoord+30;
			i=i+2;
			
			}

			gotocart=new JButton("Cart");
			gotocart.setBounds(500, 200, 60, 20);
			gotocart.addActionListener(this);
			panel.add(gotocart);
			
			backnl=new JButton("back");
			backnl.setBounds(10, 10, 80, 20);
			backnl.addActionListener(this);
			panel.add(backnl);
		
			//quit= new JButton("quit");
			//quit.setBounds(500, 400, 70, 20);
			//quit.addActionListener(this);
			panel.add(quit);
			
			logout=new JButton("logout");
			logout.setBounds(400, 20, 80, 10);
			panel.add(logout);
			
			
			frame.setVisible(true);
			
			
			
		
		}
		
		
		else if(e.getSource()==quit)
		{
			frame.hide();
			System.out.println("EXIT");
			try {
				dos.writeUTF("Quit");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				dis.readUTF();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			 try {
               dos.close();
                dis.close();
                 s.close();
             } catch (IOException ee) {
             }
             finally{
                
             }
		}

		
		else if(e.getSource()==gotocart)
		{
			if (loggedin==true)
			{
			System.out.println("I'm here");
			frame.hide();
			frame=new JFrame();
			frame.setSize(800,500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			panel=new JPanel();
			frame.add(panel);
			
			panel.setLayout(null);
			
			JLabel wlclabel=new JLabel("Your Cart: ");
			wlclabel.setBounds(30, 0, 70,70 );
			panel.add(wlclabel);
			

			String res="";
			System.out.println(res);
			try {
				
				dos.writeUTF("Computetotal-");
				dos.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		    try {
				res = dis.readUTF();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    System.out.println("res: "+res);
			ArrayList<String> replyArguments5 = new ArrayList<>(Arrays.asList(res.split("-")));
			System.out.println("C:");
			int to=replyArguments5.size()-1;
			String total=replyArguments5.get(to)+"$";
			System.out.println("Total= "+replyArguments5.get(to)+"$");
			int i=0;
			System.out.println("Products: ");
			System.out.println("size: "+replyArguments5.size());
			String g="";
			
			
			JLabel tempprod=new JLabel("");
			int ycoor=30;
			JLabel tempprice=new JLabel("");
		//	ArrayList
			while ((i+3)<replyArguments5.size())
			{
			//	System.out.println("i'm here");
				tempprod=new JLabel(replyArguments5.get(i));
				tempprod.setBounds(40, ycoor, 100, 30);
				panel.add(tempprod);
				
				
				
				tempprice=new JLabel("price: "+replyArguments5.get(i+1)+" $");
				tempprice.setBounds(100, ycoor, 100, 30);
				panel.add(tempprice);
				
			    
			    int id=Integer.valueOf(replyArguments5.get(i+2)); //the purchase if
			    pids.add(id);
				
			    
				
			    
			//	btns[i-1]=new JButton("delete");
			//	btns[i-1].setBounds(200, ycoor, 100, 15); //since i starts from 1 not 0
			//	btns[i-1].addActionListener(this);
			//	panel.add(btns[i-1]);
				
				
				
				btns.add(new JButton("delete"));
				btns.get(i/3).setBounds(200, ycoor, 100, 30); //since i starts from 1
				btns.get(i/3).addActionListener(this);
				panel.add(btns.get(i/3));
				
				
				
				ycoor=ycoor+30;
				g=g+replyArguments5.get(i)+"\n";
				i=i+3;
				//System.out.println(i);
			}
		
			String t="Total: "+replyArguments5.get(to)+"$";
			totalprice=new JLabel(t);
			totalprice.setBounds(300, ycoor, 100, 50);
			panel.add(totalprice);
			
			System.out.println(t);
			System.out.println(totalprice);
			
			backnl=new JButton("back");
			backnl.setBounds(10, 10, 80, 20);
			backnl.addActionListener(this);
			panel.add(backnl);
		
			 logout= new JButton("logout");
			 logout.setBounds(500, 10, 100, 20);
			 logout.addActionListener(this);
			 panel.add(logout);
			
			
			 checkout=new JButton ("check out");
			 checkout.setBounds(300, 200, 100, 20);
			 checkout.addActionListener(this);
			 panel.add(checkout);
			 
			 panel.add(quit);
			 
			 
			frame.setVisible(true);
			}
			else {
				prodbtns.get(0).doClick();
			}
			
			}
		
		else if(e.getSource()==checkout)
		{
			frame.hide();
			frame=new JFrame();
			frame.setSize(800,500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			panel=new JPanel();
			frame.add(panel);
			
			panel.setLayout(null);
			
			try {
				dos.writeUTF("Deleteall");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				dis.readUTF();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			JLabel lalabel=new JLabel("Checkout successful and total deducted from balance ");
			lalabel.setBounds(30, 100, 700,70 );
			panel.add(lalabel);
			
			
			lback= new JButton("back");
			lback.setBounds(20, 10, 80, 20);
			lback.addActionListener(this);
			panel.add(lback);
			
			panel.add(quit);
			
			frame.setVisible(true);
		}
	
		
		else if(e.getSource()==lback)
		{
			gotocart.doClick();
		}
		
		
		else if (e.getSource()==backvp) {
		
			vp.doClick();
			
		}
		
		
		
		
		
		else if(btns.contains((e.getSource())))
		{
			//System.out.println("hey");
			//System.out.println(e.getSource());
		    //	System.out.println(e.getSource().toString());
		
			JButton tem= new JButton();
			tem=(JButton) e.getSource();
			int y=tem.getY();
			System.out.println(y);
			
		
			
			
		int index=(y/30)-1; //since it already starts at y=30
		System.out.println("id:"+pids.get(index));
		String d= "Deleteitem"+"-"+pids.get(index);
		System.out.println(d);
		try {
			dos.writeUTF(d);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dis.readUTF();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//back to cart
		gotocart.doClick();

		
		}
	
		else if(prodbtns.contains(e.getSource()))
		{
			
			if(loggedin==true)
			{
			
			
			System.out.println("worked");
			JButton tem= new JButton();
			tem=(JButton) e.getSource();
			int y=tem.getY();
			System.out.println(y);
			
			for (String t:products) {
				System.out.println(t);
			}
			
		int index=(y/30)-1; //since it already starts at y=30
		System.out.println("products:"+products.get(index));
		String d= "ADDTOCART"+"-"+products.get(index);
		System.out.println(d);
		try {
			dos.writeUTF(d);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dis.readUTF();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		}
			
		
			else {
				frame.hide();
				frame=new JFrame();
				frame.setSize(800,500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				panel=new JPanel();
				frame.add(panel);
				
				panel.setLayout(null);
				
					JLabel l=new JLabel("You need to log in to be able to use this feature");
					l.setBounds(30,30,500,200);
					panel.add(l);
				
				button=new JButton("login");
				button.setBounds(30, 70, 100, 50);
				button.addActionListener(this);
				panel.add(button);
				
				supbutton=new JButton("signup");
				supbutton.setBounds(30, 140, 100, 50);
				supbutton.addActionListener(this);
				panel.add(supbutton);
				
				backvp=new JButton("back");
				backvp.setBounds(10, 10, 80, 20);
				backvp.addActionListener(this);
				panel.add(backvp);
			
				
				
			frame.setVisible(true);
			}
			
			
		}
		
		
		}
		
	}




