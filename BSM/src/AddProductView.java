

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddProductView implements ActionListener {

	private JLabel addLbl, orderLbl;
	
	private JLabel productIDLbl, nameLbl;
	
	private JPanel panel1;
	
	private JButton confirmAdd;

	private Product product;
	
	private JTextField productID, name;
	
	private DesktopAppView desktop;
	
	private JFrame frame;
	
	private int line;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public AddProductView(DesktopAppView desktop) {
		this.desktop = desktop;
		this.product = null; //null indicates we are adding
		buildFrame("add");
	}
	
	public AddProductView(DesktopAppView desktop, int id, int line) {
		this.desktop = desktop;
		this.product = BusinessLayer.getProduct(id); //not null means editing
		this.line = line;
		if (product == null) {
			//throw exception, Order not found
		}
		
		//all this information extracted from the Order retrieved from the data access
		int productID = 123;
		String name = "somethign";


		

		buildFrame("edit");
		
		this.productID.setText("" + productID);
		this.name.setText(name);

		

	}


	private void buildFrame(String operation) {
		this.frame = new JFrame();
		
		
		this.addLbl = new JLabel(operation + " order?");		
		addLbl.setBounds(5, 2, 168, 30);
		this.addLbl.setFont(new Font("Arial", Font.PLAIN, 25));
		
		this.orderLbl = new JLabel("order info:");		
		orderLbl.setBounds(5, 54, 168, 30);
		this.orderLbl.setFont(new Font("Arial", Font.PLAIN, 25));
		
		this.productIDLbl = new JLabel("product id:");
		productIDLbl.setBounds(5, 102, 75, 16);
		
		this.nameLbl = new JLabel("name:");
		nameLbl.setBounds(5, 130, 75, 16);

		
		this.productID = new JTextField();
		productID.setBounds(92, 96, 168, 28);
		this.name = new JTextField();
		name.setBounds(92, 124, 168, 28);


		this.confirmAdd = new JButton("Confirm " + operation + "?");
		confirmAdd.setBounds(5, 158, 255, 25);
		this.confirmAdd.addActionListener(this);
		
		this.panel1 = new JPanel();
		panel1.setLayout(null);
		this.panel1.add(this.addLbl);
		this.panel1.add(this.orderLbl);
		
		this.panel1.add(this.productIDLbl);
		this.panel1.add(this.nameLbl);

		
		
		this.panel1.add(this.productID);
		this.panel1.add(this.name);

		
		this.panel1.add(this.confirmAdd);

		

		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		frame.setBounds(280, 120, 292, 232);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent event) {
		
if(event.getSource() == this.confirmAdd) {
			
			int productID;
			
			String name;
			
			try {
			
				productID = Integer.parseInt(this.productID.getText());
				name = this.name.getText();

			}
			catch (Exception e) {
				System.out.println("bad format somewhere");
			}
			
			//use the above info to make product
			if(product == null) {
				
				//for testing purposes
				Product p = new Product("test product");
				
				desktop.addLine(p.getData());
				BusinessLayer.addProduct(p);
			}
			else {
				
				//for testing purposes
				Product p = new Product("edited product");
				
				desktop.updateLine(line, p.getData());
				BusinessLayer.editProduct(p, 0); // need an id to edit product
			}
			//close window
			this.frame.setVisible(false);
			this.frame.dispose();
			
			
		}

	}
	



}
