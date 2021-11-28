<<<<<<< HEAD:BSM/src/AddCustomerView.java
<<<<<<< HEAD
=======
package View;
>>>>>>> parent of ae5bdab (changes):BSM/src/View/AddCustomerView.java

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCustomerView implements ActionListener {

	private JLabel addLbl, personLbl, addressLbl;
	
	private JLabel broncoIDLbl, fnameLbl, lnameLbl, dobLbl, phoneLbl;
	
	private JLabel streetLbl, numberLbl, zipLbl, cityLbl, stateLbl;

	private JPanel panel1;
	
	private JButton confirmAdd;

	private String[] customer;
	
	private JTextField broncoID, fname, lname, phone;
	
	private JFormattedTextField dob;
	
	private JTextField street, number, zip, city, state;
	
	private DesktopAppView desktop;
	
	private JFrame frame;
	
	private int line;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public AddCustomerView(DesktopAppView desktop) {
		this.desktop = desktop;
		this.customer = null; //null indicates we are adding
		buildFrame("add");
	}
	
	public AddCustomerView(DesktopAppView desktop, String[] c, int line) {
		this.desktop = desktop;
		//this.customer = BusinessLayer.getCustomer(id); //not null means editing
		this.customer = c;
		this.line = line;
		if (customer == null) {
			//throw exception, customer not found
		}
		
		//all this information extracted from the customer retrieved from the data access
		int broncoID = Integer.parseInt(c[0]); //change later
		String fname = c[1];
		String lname = c[2];
		String dob = c[3].split(" ")[0];
		dob = dob.replace('-', '/');
		String phone = c[4];
		
		Address a = Address.splitString(c[5]);
		
		String street = a.getStreet();
		int number = a.getNumber();
		int zip = a.getZip();
		String city = a.getCity();
		String state = a.getState();
		
		buildFrame("edit");
		
		this.broncoID.setText("" + broncoID);
		this.fname.setText(fname);
		this.lname.setText(lname);
		this.dob.setText(dob);
		this.phone.setText("" + phone);
		

		this.street.setText(street);
		this.number.setText("" + number);
		this.zip.setText("" + zip);
		this.city.setText(city);
		this.state.setText(state);
	
		
		
		
	}


	private void buildFrame(String operation) {
		this.frame = new JFrame();
		
		
		this.addLbl = new JLabel(operation + " customer?");		
		addLbl.setBounds(5, 2, 168, 30);
		this.addLbl.setFont(new Font("Arial", Font.PLAIN, 25));
		
		this.personLbl = new JLabel("person info:");		
		personLbl.setBounds(5, 54, 168, 30);
		this.personLbl.setFont(new Font("Arial", Font.PLAIN, 25));
		
		this.addressLbl = new JLabel("address info:");		
		addressLbl.setBounds(268, 54, 168, 30);
		this.addressLbl.setFont(new Font("Arial", Font.PLAIN, 25));
		
		
		
		this.broncoIDLbl = new JLabel("bronco id:");
		broncoIDLbl.setBounds(5, 102, 75, 16);
		
		this.fnameLbl = new JLabel("first name:");
		fnameLbl.setBounds(5, 130, 75, 16);
		
		this.lnameLbl = new JLabel("last name:");
		lnameLbl.setBounds(5, 158, 75, 16);
		
		this.dobLbl = new JLabel("date of birth:");
		dobLbl.setBounds(5, 186, 75, 16);
		
		this.phoneLbl = new JLabel("phone:");
		phoneLbl.setBounds(5, 214, 75, 16);
		
		
		
		
		
		this.streetLbl = new JLabel("street:");
		streetLbl.setBounds(268, 102, 75, 16);
		
		this.numberLbl = new JLabel("number:");
		numberLbl.setBounds(268, 130, 53, 16);
		
		this.zipLbl = new JLabel("zip:");
		zipLbl.setBounds(268, 158, 75, 16);
		
		this.cityLbl = new JLabel("city:");
		cityLbl.setBounds(268, 186, 75, 16);
		
		this.stateLbl = new JLabel("state:");
		stateLbl.setBounds(268, 214, 75, 16);


		
		
		this.broncoID = new JTextField();
		broncoID.setBounds(92, 96, 168, 28);
		this.fname = new JTextField();
		fname.setBounds(92, 124, 168, 28);
		this.lname = new JTextField();
		lname.setBounds(92, 151, 168, 28);
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		this.dob = new JFormattedTextField(df);
		dob.setBounds(92, 180, 168, 28);
		
		this.phone = new JTextField();
		phone.setBounds(92, 208, 168, 28);
		
		this.street = new JTextField();
		street.setBounds(340, 96, 168, 28);
		this.number = new JTextField();
		number.setBounds(340, 124, 168, 28);
		this.zip = new JTextField();
		zip.setBounds(340, 152, 168, 28);
		this.city = new JTextField();
		city.setBounds(340, 180, 168, 28);
		this.state = new JTextField();
		state.setBounds(340, 208, 168, 28);
		
		this.confirmAdd = new JButton("Confirm " + operation + "?");
		confirmAdd.setBounds(58, 278, 378, 25);
		this.confirmAdd.addActionListener(this);
		
		this.panel1 = new JPanel();
		panel1.setLayout(null);
		this.panel1.add(this.addLbl);
		this.panel1.add(this.personLbl);
		this.panel1.add(this.addressLbl);
		
		this.panel1.add(this.broncoIDLbl);
		this.panel1.add(this.fnameLbl);
		this.panel1.add(this.lnameLbl);
		this.panel1.add(this.dobLbl);
		this.panel1.add(this.phoneLbl);
		
		this.panel1.add(this.streetLbl);
		this.panel1.add(this.numberLbl);
		this.panel1.add(this.zipLbl);
		this.panel1.add(this.cityLbl);
		this.panel1.add(this.stateLbl);
		
		this.panel1.add(this.broncoID);
		this.panel1.add(this.fname);
		this.panel1.add(this.lname);
		this.panel1.add(this.dob);
		this.panel1.add(this.phone);
		
		this.panel1.add(this.street);
		this.panel1.add(this.number);
		this.panel1.add(this.zip);
		this.panel1.add(this.city);
		this.panel1.add(this.state);
		
		this.panel1.add(this.confirmAdd);
		
		

		
		

		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		frame.setBounds(280, 120, 526, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent event) {
		
if(event.getSource() == this.confirmAdd) {
			
			int broncoID = 0 ;
			String phone = "" ;
			int addressID = 0 ;
			int number = 0 ; 
			int zip = 0 ;
			
			String fname ="";
			String lname ="";
			Date dob = null ;
			String street ="";
			String city ="";
			String state = "";
			
			try {
			
				broncoID = Integer.parseInt(this.broncoID.getText());
				fname = this.fname.getText();
				lname = this.lname.getText();
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				dob = df.parse(this.dob.getText());
				phone = this.phone.getText();
				
				street = this.street.getText();
				number = Integer.parseInt(this.number.getText());
				zip = Integer.parseInt(this.zip.getText());
				city = this.city.getText();
				state = this.state.getText();
			
			}
			catch (Exception e) {
				System.out.println("bad format somewhere");
				System.out.println(e);
			}
			//use the above info to make customer and address
			if(customer == null) {
				
				//for testing purposes
				if (fname != "" && lname != "" && dob != null && street != "" && city != "" && state != "" && phone != "" && number != 0 && zip != 0 && broncoID != 0 ) 

				{
					Address a = new Address( street, number, zip,city, state);
					Customer c = new Customer(broncoID, fname, lname, dob, phone, a );
					desktop.addLine(c);
					BusinessLayer.addCustomer(c);
				}

			}
			else {
				
				if (fname != "" && lname != "" && dob != null && street != "" && city != "" && state != "" && phone != "" && number != 0 && zip != 0 && broncoID != 0 ) 

				{
					int id = Integer.parseInt(customer[0]);
					Address a = new Address( street, number, zip,city, state);
					Customer c = new Customer(broncoID, fname, lname, dob, phone, a );
					desktop.updateLine(line, c);
					BusinessLayer.editCustomer(c, id);
				}
			}
			//close window
			this.frame.setVisible(false);
			this.frame.dispose();
			
			
		}

	}
	



}
<<<<<<< HEAD:BSM/src/AddCustomerView.java
=======

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCustomerView implements ActionListener {

	private JLabel addLbl, personLbl, addressLbl;
	
	private JLabel broncoIDLbl, fnameLbl, lnameLbl, dobLbl, phoneLbl;
	
	private JLabel streetLbl, numberLbl, zipLbl, cityLbl, stateLbl;

	private JPanel panel1;
	
	private JButton confirmAdd;

	private String[] customer;
	
	private JTextField broncoID, fname, lname, phone;
	
	private JFormattedTextField dob;
	
	private JTextField street, number, zip, city, state;
	
	private DesktopAppView desktop;
	
	private JFrame frame;
	
	private int line;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public AddCustomerView(DesktopAppView desktop) {
		this.desktop = desktop;
		this.customer = null; //null indicates we are adding
		buildFrame("add");
	}
	
	public AddCustomerView(DesktopAppView desktop, String[] c, int line) {
		this.desktop = desktop;
		//this.customer = BusinessLayer.getCustomer(id); //not null means editing
		this.customer = c;
		this.line = line;
		if (customer == null) {
			//throw exception, customer not found
		}
		
		//all this information extracted from the customer retrieved from the data access
		int broncoID = Integer.parseInt(c[0]); //change later
		String fname = c[1];
		String lname = c[2];
		String dob = c[3].split(" ")[0];
		dob = dob.replace('-', '/');
		String phone = c[4];
		
		Address a = Address.splitString(c[5]);
		
		String street = a.getStreet();
		int number = a.getNumber();
		int zip = a.getZip();
		String city = a.getCity();
		String state = a.getState();
		
		buildFrame("edit");
		
		this.broncoID.setText("" + broncoID);
		this.fname.setText(fname);
		this.lname.setText(lname);
		this.dob.setText(dob);
		this.phone.setText("" + phone);
		

		this.street.setText(street);
		this.number.setText("" + number);
		this.zip.setText("" + zip);
		this.city.setText(city);
		this.state.setText(state);
	
		
		
		
	}


	private void buildFrame(String operation) {
		this.frame = new JFrame();
		
		
		this.addLbl = new JLabel(operation + " customer?");		
		addLbl.setBounds(5, 2, 168, 30);
		this.addLbl.setFont(new Font("Arial", Font.PLAIN, 25));
		
		this.personLbl = new JLabel("person info:");		
		personLbl.setBounds(5, 54, 168, 30);
		this.personLbl.setFont(new Font("Arial", Font.PLAIN, 25));
		
		this.addressLbl = new JLabel("address info:");		
		addressLbl.setBounds(268, 54, 168, 30);
		this.addressLbl.setFont(new Font("Arial", Font.PLAIN, 25));
		
		
		
		this.broncoIDLbl = new JLabel("bronco id:");
		broncoIDLbl.setBounds(5, 102, 75, 16);
		
		this.fnameLbl = new JLabel("first name:");
		fnameLbl.setBounds(5, 130, 75, 16);
		
		this.lnameLbl = new JLabel("last name:");
		lnameLbl.setBounds(5, 158, 75, 16);
		
		this.dobLbl = new JLabel("date of birth:");
		dobLbl.setBounds(5, 186, 75, 16);
		
		this.phoneLbl = new JLabel("phone:");
		phoneLbl.setBounds(5, 214, 75, 16);
		
		
		
		
		
		this.streetLbl = new JLabel("street:");
		streetLbl.setBounds(268, 102, 75, 16);
		
		this.numberLbl = new JLabel("number:");
		numberLbl.setBounds(268, 130, 53, 16);
		
		this.zipLbl = new JLabel("zip:");
		zipLbl.setBounds(268, 158, 75, 16);
		
		this.cityLbl = new JLabel("city:");
		cityLbl.setBounds(268, 186, 75, 16);
		
		this.stateLbl = new JLabel("state:");
		stateLbl.setBounds(268, 214, 75, 16);


		
		
		this.broncoID = new JTextField();
		broncoID.setBounds(92, 96, 168, 28);
		this.fname = new JTextField();
		fname.setBounds(92, 124, 168, 28);
		this.lname = new JTextField();
		lname.setBounds(92, 151, 168, 28);
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		this.dob = new JFormattedTextField(df);
		dob.setBounds(92, 180, 168, 28);
		
		this.phone = new JTextField();
		phone.setBounds(92, 208, 168, 28);
		
		this.street = new JTextField();
		street.setBounds(340, 96, 168, 28);
		this.number = new JTextField();
		number.setBounds(340, 124, 168, 28);
		this.zip = new JTextField();
		zip.setBounds(340, 152, 168, 28);
		this.city = new JTextField();
		city.setBounds(340, 180, 168, 28);
		this.state = new JTextField();
		state.setBounds(340, 208, 168, 28);
		
		this.confirmAdd = new JButton("Confirm " + operation + "?");
		confirmAdd.setBounds(58, 278, 378, 25);
		this.confirmAdd.addActionListener(this);
		
		this.panel1 = new JPanel();
		panel1.setLayout(null);
		this.panel1.add(this.addLbl);
		this.panel1.add(this.personLbl);
		this.panel1.add(this.addressLbl);
		
		this.panel1.add(this.broncoIDLbl);
		this.panel1.add(this.fnameLbl);
		this.panel1.add(this.lnameLbl);
		this.panel1.add(this.dobLbl);
		this.panel1.add(this.phoneLbl);
		
		this.panel1.add(this.streetLbl);
		this.panel1.add(this.numberLbl);
		this.panel1.add(this.zipLbl);
		this.panel1.add(this.cityLbl);
		this.panel1.add(this.stateLbl);
		
		this.panel1.add(this.broncoID);
		this.panel1.add(this.fname);
		this.panel1.add(this.lname);
		this.panel1.add(this.dob);
		this.panel1.add(this.phone);
		
		this.panel1.add(this.street);
		this.panel1.add(this.number);
		this.panel1.add(this.zip);
		this.panel1.add(this.city);
		this.panel1.add(this.state);
		
		this.panel1.add(this.confirmAdd);
		
		

		
		

		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		frame.setBounds(280, 120, 526, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent event) {
		
if(event.getSource() == this.confirmAdd) {
			
			int broncoID = 0 ;
			String phone = "" ;
			int addressID = 0 ;
			int number = 0 ; 
			int zip = 0 ;
			
			String fname ="";
			String lname ="";
			Date dob = null ;
			String street ="";
			String city ="";
			String state = "";
			
			try {
			
				broncoID = Integer.parseInt(this.broncoID.getText());
				fname = this.fname.getText();
				lname = this.lname.getText();
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				dob = df.parse(this.dob.getText());
				phone = this.phone.getText();
				
				street = this.street.getText();
				number = Integer.parseInt(this.number.getText());
				zip = Integer.parseInt(this.zip.getText());
				city = this.city.getText();
				state = this.state.getText();
			
			}
			catch (Exception e) {
				System.out.println("bad format somewhere");
				System.out.println(e);
			}
			//use the above info to make customer and address
			if(customer == null) {
				
				//for testing purposes
				if (fname != "" && lname != "" && dob != null && street != "" && city != "" && state != "" && phone != "" && number != 0 && zip != 0 && broncoID != 0 ) 

				{
					Address a = new Address( street, number, zip,city, state);
					Customer c = new Customer(broncoID, fname, lname, dob, phone, a );
					desktop.addLine(c);
					BusinessLayer.addCustomer(c);
				}

			}
			else {
				
				if (fname != "" && lname != "" && dob != null && street != "" && city != "" && state != "" && phone != "" && number != 0 && zip != 0 && broncoID != 0 ) 

				{
					int id = Integer.parseInt(customer[0]);
					Address a = new Address( street, number, zip,city, state);
					Customer c = new Customer(broncoID, fname, lname, dob, phone, a );
					desktop.updateLine(line, c);
					BusinessLayer.editCustomer(c, id);
				}
			}
			//close window
			this.frame.setVisible(false);
			this.frame.dispose();
			
			
		}

	}
	



}
>>>>>>> parent of 696d6c5 (Add files via upload)
=======
>>>>>>> parent of ae5bdab (changes):BSM/src/View/AddCustomerView.java
