package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import BusinessLayer.BusinessLayer;
import Model.Order;
import Model.Product;

public class AddOrderView implements ActionListener {

	private JLabel addLbl, orderLbl;

	private JLabel orderIDLbl, dateTimeLbl, customerLbl, priceLbl, productsLbl;

//	private JTextArea products;

	private SimpleTable table, cartTable;
	private JTable dataTable, shoppingCart;

	private JPanel panel1;

	private JButton confirmAdd, moveToCart;

	

	private JTextField orderID, customer;

	private JFormattedTextField dateTime;

	private DesktopAppView desktop;

	private JFrame frame;

	private int line;
	private Order order;
	
	boolean newOrder;
	float price;

	/**
	 * @wbp.parser.entryPoint
	 */
	public AddOrderView(DesktopAppView desktop) {
		this.desktop = desktop;
		this.order = new Order(); // null indicates we are adding
		
		this.newOrder = true;
		
		buildFrame("add");
		
		price = 0;
		
		
	}

	public AddOrderView(DesktopAppView desktop, String[] s, int line) {
		this.desktop = desktop;
		// this.order = BusinessLayer.getOrder(id); //not null means editing
		this.newOrder = false;
		this.line = line;
		if (order == null) {
			// throw exception, Order not found
		}

		// all this information extracted from the Order retrieved from the data access
		int orderID = Integer.parseInt(s[0]);
		String name = s[1];
		String price = s[2];
		String customer = "customer name";

		buildFrame("edit");

		this.orderID.setText("" + orderID);
		this.dateTime.setText(LocalDateTime.now().toString());

		this.customer.setText(customer);
		
		

//		this.products.append("product1\n");
//		this.products.append("product2\n");
	}

	private void setRows(SimpleTable s) {
		String columns[] = { "ID", "Name", "Price" };

		for (String c : columns) {
			s.addColumn(c);
		}

		s.addRow(columns);
	}

	private void populateProducts() {

		List<Product> products = BusinessLayer.getProducts();
		for (Product p : (List<Product>) products) {
			table.addRow(p.getTableEntry());

		}
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

		this.dateTimeLbl = new JLabel("dateTime:");
		dateTimeLbl.setBounds(5, 130, 75, 16);

		this.customerLbl = new JLabel("customer:");
		customerLbl.setBounds(5, 186, 75, 16);

		this.priceLbl = new JLabel("total price: $0.00");
		priceLbl.setBounds(16, 613, 200, 16);

		this.productsLbl = new JLabel("shopping cart:");
		productsLbl.setBounds(16, 435, 109, 16);

		this.table = new SimpleTable();
		this.dataTable = new JTable(table);
		dataTable.setBounds(15, 214, 540, 144);

		dataTable.setDefaultRenderer(Object.class, (TableCellRenderer) new DefaultTableCellRenderer() {
			public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				int selected = table.getSelectedRow();
				c.setBackground(row == 0 ? Color.LIGHT_GRAY : (row == selected ? Color.RED : Color.WHITE));

				return c;
			}
		});

		this.setRows(table);
		this.populateProducts();

		this.cartTable = new SimpleTable();
		this.shoppingCart = new JTable(cartTable);
		shoppingCart.setBounds(15, 466, 540, 144);

		shoppingCart.setDefaultRenderer(Object.class, (TableCellRenderer) new DefaultTableCellRenderer() {
			public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				c.setBackground(row == 0 ? Color.LIGHT_GRAY : Color.WHITE);

				return c;
			}
		});

		this.setRows(cartTable);

		this.orderID = new JTextField();
		orderID.setBounds(92, 96, 168, 28);

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss");
		this.dateTime = new JFormattedTextField(df);
		dateTime.setBounds(92, 124, 168, 28);

		dateTime.setBounds(92, 151, 168, 28);
		this.customer = new JTextField();
		customer.setBounds(92, 180, 168, 28);
//		this.products = new JTextArea();
//		products.setBounds(336, 96, 168, 134);

		this.moveToCart = new JButton("move to cart");
		moveToCart.setBounds(92, 381, 378, 25);
		this.moveToCart.addActionListener(this);

		this.confirmAdd = new JButton("Confirm " + operation + "?");
		confirmAdd.setBounds(92, 660, 378, 25);
		this.confirmAdd.addActionListener(this);

		this.panel1 = new JPanel();
		panel1.setLayout(null);
		this.panel1.add(this.addLbl);
		this.panel1.add(this.orderLbl);

		this.panel1.add(this.orderIDLbl);
		this.panel1.add(this.dateTimeLbl);

		this.panel1.add(this.customerLbl);
		this.panel1.add(this.priceLbl);
		this.panel1.add(this.productsLbl);

		this.panel1.add(this.orderID);
		this.panel1.add(this.dateTime);
		this.panel1.add(this.customer);
		this.panel1.add(this.dataTable);
		this.panel1.add(this.shoppingCart);

		this.panel1.add(this.confirmAdd);
		this.panel1.add(this.moveToCart);
		
		

		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		frame.setBounds(280, 120, 578, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private String[] getRow(int row) {
		String rowArr[] = new String[dataTable.getColumnCount()];
		
		for(int a = 0; a < dataTable.getColumnCount(); a++) {
			rowArr[a] = (String) dataTable.getValueAt(row, a);
		}
		
		return rowArr;
	}
	

	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent event) {

		
		if (event.getSource() == this.confirmAdd) {

			int orderID;

			Date dateTime;
			int customerId = 0;
			int NoOfProduct = this.order.getProducts().size();
			try {

				// orderID = Integer.parseInt(this.orderID.getText());
				dateTime = (Date) this.dateTime.getValue();

				customerId = Integer.parseInt(this.customer.getText());
			} catch (Exception e) {
				System.out.println("bad format somewhere");
			}
			// use the above info to make Order and address
			if (newOrder == true) {

				// for testing purposes
				
				System.out.println("Calling adding order");
				
				BusinessLayer.addOrder(this.order, customerId);
			} else {

				// for testing purposes
				Order o = new Order();
				System.out.println("Calling editing order");
				
				BusinessLayer.editOrder(o, 0);
			}
			// close window
			this.frame.setVisible(false);
			this.frame.dispose();

		}
		else if (event.getSource() == this.moveToCart) {
			int selected = dataTable.getSelectedRow();
			
			String[] data = this.getRow(selected);
			
			this.cartTable.addRow(data);
			
			
			
			int tempProductId = Integer.parseInt(data[0]);
			
			String tempProductName = (String)data[1];
			
			Double tempProductPrice = Double.parseDouble(data[2]);
			Product p = new Product (tempProductId, tempProductName, tempProductPrice);
			
			this.order.addProduct(p);
			
			price += Double.parseDouble(data[2]);
			
			this.priceLbl.setText("total price is $" + price);
		}

	}

}
