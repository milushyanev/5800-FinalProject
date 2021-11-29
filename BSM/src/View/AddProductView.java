package View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BusinessLayer.ProductManager;
import Model.Product;

public class AddProductView implements ActionListener {

	private JLabel addLbl, orderLbl;

	private JLabel nameLbl, priceLbl;

	private JPanel panel1;

	private JButton confirmAdd;

	private String[] product;

	private JTextField name, price;

	private DesktopAppView desktop;

	private JFrame frame;

	private int line;

	/**
	 * @wbp.parser.entryPoint
	 */
	public AddProductView(DesktopAppView desktop) {
		this.desktop = desktop;
		this.product = null; // null indicates we are adding
		buildFrame("add");
	}

	public AddProductView(DesktopAppView desktop, String[] s, int line) {
		this.desktop = desktop;
		this.product = s;
		this.line = line;
		if (product == null) {
			// throw exception, Order not found
		}

		buildFrame("edit");


		this.name.setText(s[1]);
		this.price.setText("" + s[2]);
	}

	private void buildFrame(String operation) {
		this.frame = new JFrame();

		this.addLbl = new JLabel(operation + " product?");
		addLbl.setBounds(5, 2, 168, 30);
		this.addLbl.setFont(new Font("Arial", Font.PLAIN, 25));

		this.orderLbl = new JLabel("order info:");
		orderLbl.setBounds(5, 54, 168, 30);
		this.orderLbl.setFont(new Font("Arial", Font.PLAIN, 25));



		this.nameLbl = new JLabel("name:");
		nameLbl.setBounds(5, 102, 75, 16);
		this.priceLbl = new JLabel("price:");
		priceLbl.setBounds(5, 130, 75, 16);

		this.name = new JTextField();
		name.setBounds(92, 96, 168, 28);
		this.price = new JTextField();
		price.setBounds(92, 130, 168, 28);

		this.confirmAdd = new JButton("Confirm " + operation + "?");
		confirmAdd.setBounds(5, 170, 255, 25);
		this.confirmAdd.addActionListener(this);

		this.panel1 = new JPanel();
		panel1.setLayout(null);
		this.panel1.add(this.addLbl);
		this.panel1.add(this.orderLbl);

		this.panel1.add(this.nameLbl);
		this.panel1.add(this.priceLbl);

		this.panel1.add(this.name);
		this.panel1.add(this.price);

		this.panel1.add(this.confirmAdd);

		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		frame.setBounds(280, 120, 292, 247);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(0,0);
		frame.setVisible(true);
	}

	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == this.confirmAdd) {

			int productID = 0;

			String name = "";
			double price = 0.0;

			try {

				
				name = this.name.getText();
				System.out.println(name);
				price = Double.parseDouble(this.price.getText());
				
				System.out.println(price);

			} catch (Exception e) {
				System.out.println("bad format somewhere");
			}

			// use the above info to make product
			if (product == null) {

				// for testing purposes
				Product p = new Product(name, price);
				System.out.println(p.toString());
				
				desktop.addLine(p);
				ProductManager.addProduct(p);
			} else {

				int id = Integer.parseInt(product[0]);
				Product p = new Product(name, price);

				desktop.updateLine(line, p);
				ProductManager.editProduct(p, id); // need an id to edit product
			}
			// close window
			this.frame.setVisible(false);
			this.frame.dispose();

		}

	}

}
