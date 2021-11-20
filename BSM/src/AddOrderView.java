

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddOrderView implements ActionListener {

	private JLabel addLbl, orderLbl;
	
	private JLabel orderIDLbl, dateLbl, timeLbl, customerLbl, priceLbl, productsLbl;
	
	private JTextArea products;
	
	private JPanel panel1;
	
	private JButton confirmAdd;

	private Order order;
	
	private JTextField orderID, date, time, customer;
	
	private DesktopAppView desktop;
	
	private JFrame frame;
	
	private int line;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public AddOrderView(DesktopAppView desktop) {
		this.desktop = desktop;
		this.order = null; //null indicates we are adding
		buildFrame("add");
	}
	
	public AddOrderView(DesktopAppView desktop, int id, int line) {
		this.desktop = desktop;
		this.order = BusinessLayer.getOrder(id); //not null means editing
		this.line = line;
		if (order == null) {
			//throw exception, Order not found
		}
		
		//all this information extracted from the Order retrieved from the data access
		int orderID = 123;
		String date = "1/1/1111";
		String time = "11:11";
		String customer = "customer name";

		

		buildFrame("edit");
		
		this.orderID.setText("" + orderID);
		this.date.setText(date);
		this.time.setText(time);
		this.customer.setText(customer);
		
		this.products.append("product1\n");
		this.products.append("product2\n");
	}


	private void buildFrame(String operation) {
		this.frame = new JFrame();
		
		
		this.addLbl = new JLabel(operation + " order?");		
		addLbl.setBounds(5, 2, 168, 30);
		this.addLbl.setFont(new Font("Arial", Font.PLAIN, 25));
		
		this.orderLbl = new JLabel("order info:");		
		orderLbl.setBounds(5, 54, 168, 30);
		this.orderLbl.setFont(new Font("Arial", Font.PLAIN, 25));
		

		
		
		this.orderIDLbl = new JLabel("order id:");
		orderIDLbl.setBounds(5, 102, 75, 16);
		
		this.dateLbl = new JLabel("date:");
		dateLbl.setBounds(5, 130, 75, 16);
		
		this.timeLbl = new JLabel("time:");
		timeLbl.setBounds(5, 158, 75, 16);
		
		this.customerLbl = new JLabel("customer:");
		customerLbl.setBounds(5, 186, 75, 16);
		
		this.priceLbl = new JLabel("total price: $0.00");
		priceLbl.setBounds(5, 214, 75, 16);
		
		this.productsLbl = new JLabel("products: ");
		productsLbl.setBounds(272, 102, 75, 16);
		
		
		
		

		
		
		this.orderID = new JTextField();
		orderID.setBounds(92, 96, 168, 28);
		this.date = new JTextField();
		date.setBounds(92, 124, 168, 28);
		this.time = new JTextField();
		time.setBounds(92, 151, 168, 28);
		this.customer = new JTextField();
		customer.setBounds(92, 180, 168, 28);
		this.products = new JTextArea();
		products.setBounds(336, 96, 168, 134);

		

		
		this.confirmAdd = new JButton("Confirm " + operation + "?");
		confirmAdd.setBounds(58, 278, 378, 25);
		this.confirmAdd.addActionListener(this);
		
		this.panel1 = new JPanel();
		panel1.setLayout(null);
		this.panel1.add(this.addLbl);
		this.panel1.add(this.orderLbl);
		
		this.panel1.add(this.orderIDLbl);
		this.panel1.add(this.dateLbl);
		this.panel1.add(this.timeLbl);
		this.panel1.add(this.customerLbl);
		this.panel1.add(this.priceLbl);
		this.panel1.add(this.productsLbl);
		
		
		this.panel1.add(this.orderID);
		this.panel1.add(this.date);
		this.panel1.add(this.time);
		this.panel1.add(this.customer);
		this.panel1.add(this.products);
		
		
		this.panel1.add(this.confirmAdd);
		
		

		
		

		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		frame.setBounds(280, 120, 526, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent event) {
		
if(event.getSource() == this.confirmAdd) {
			
			int orderID;
			
			String date, time, customer;
			
			try {
			
				orderID = Integer.parseInt(this.orderID.getText());
				date = this.date.getText();
				time = this.time.getText();
				customer = this.customer.getText();		
			}
			catch (Exception e) {
				System.out.println("bad format somewhere");
			}
			//use the above info to make Order and address
			if(order == null) {
				
				//for testing purposes
				Order o = new Order("test Order");
				
				desktop.addLine(o.getData());
				BusinessLayer.addOrder(o);
			}
			else {
				
				//for testing purposes
				Order o = new Order("edited Order");
				
				desktop.updateLine(line, o.getData());
				BusinessLayer.editOrder(o, 0);
			}
			//close window
			this.frame.setVisible(false);
			this.frame.dispose();
			
			
		}

	}
	



}
