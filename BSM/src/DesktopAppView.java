
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
//import javax.swing.JTextPane;
import javax.swing.text.Highlighter;

import org.hibernate.mapping.Component;

//import javax.swing.JEditorPane;
//import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;


@SuppressWarnings("serial")
public class DesktopAppView extends JFrame implements ActionListener {

	private JPanel panel1, panel2, panel3;
	
	private BusinessLayer business;
	
	//private JTextArea dataArea;
	
	private SimpleTable table;
	private JTable  dataTable;
	
	private JButton getCustomers, getProducts, getOrders;
	
	private JButton addButton, editButton, deleteButton, reportButton, dateReport;
	
	private JTextField startDate, endDate;
	
	private int selectedLine;
	
	//0 = customers
	//1 = products
	//2 = orders
	private int view;
	
	public DesktopAppView() {

		this.initializeComponents();

		this.buildUI();
		this.business = getBusiness();
		
	}

	private void initializeComponents() {
		
		view = -1;
		
		this.panel1 = new JPanel();
		this.panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.panel2 = new JPanel();

		this.panel3 = new JPanel();
		this.panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//my stuff 
		this.getCustomers = new JButton("Get Customers");
		this.getCustomers.addActionListener(this);
		this.getProducts = new JButton("Get Products");
		this.getProducts.addActionListener(this);
		this.getOrders = new JButton("Get Orders");
		this.getOrders.addActionListener(this);
		
//		this.dataArea = new JTextArea();
//		dataArea.setBounds(30, 11, 500, 190);
//		dataArea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//	
//		dataArea.addMouseListener(this);
//		dataArea.setText("click a button to start...");
		
		this.table = new SimpleTable();
		this.dataTable = new JTable(table);
		dataTable.setBounds(6, 11, 872, 190);
		

		
		dataTable.setDefaultRenderer(Object.class, (TableCellRenderer) new DefaultTableCellRenderer() {
            public java.awt.Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       	
            	java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            	int selected = table.getSelectedRow();
            	c.setBackground(row == 0 ? Color.LIGHT_GRAY : (row == selected ? Color.RED : Color.WHITE));

                
                return c;
            }
           });
		
		



		
		this.addButton = new JButton("...");
		this.addButton.setBounds(198, 202, 160, 28);
		this.addButton.setVisible(false);
		this.addButton.addActionListener(this);
		
		this.editButton = new JButton("...");
		this.editButton.setBounds(370, 202, 160, 28);
		this.editButton.setVisible(false);
		this.editButton.addActionListener(this);
		
		this.deleteButton = new JButton("...");
		this.deleteButton.setBounds(542, 202, 160, 28);
		this.deleteButton.setVisible(false);
		this.deleteButton.addActionListener(this);
		
		this.reportButton = new JButton("...");
		this.reportButton.setBounds(208, 234, 500, 28);
		this.reportButton.setVisible(false);
		this.reportButton.addActionListener(this);
		
		this.dateReport = new JButton("date report");
		this.dateReport.addActionListener(this);
		
		this.startDate = new JTextField(8);
		this.startDate.setText("1/1/1111");
		this.endDate = new JTextField(8);
		this.endDate.setText("2/2/2222");
		
		
	}

	private void buildUI() {
		
		this.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

		
		this.panel1.add(getCustomers);
		this.panel1.add(getProducts);
		this.panel1.add(getOrders);
		
		
		
		panel2.setLayout(null);
		

		this.panel2.add(dataTable);

		this.panel3.add(dateReport);
		this.panel3.add(this.startDate);
		this.panel3.add(this.endDate);

		this.getContentPane().add(panel1, BorderLayout.NORTH);
		this.getContentPane().add(panel2, BorderLayout.CENTER);
		this.getContentPane().add(panel3, BorderLayout.SOUTH);

		panel2.add(addButton);
		panel2.add(editButton);
		panel2.add(deleteButton);
		panel2.add(reportButton);
		


		this.setTitle("BSM Desktop Application");
		this.setBounds(350, 140, 900, 383);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) throws BadLocationException {
		new DesktopAppView();
	}

	public void actionPerformed(ActionEvent event) {
		
		
		this.business = this.getBusiness();
		
		if (event.getSource() == this.getCustomers) {
			
			table.setColumnCount(0);
			table.setRowCount(0);
			

			String columns[] = {"ID", "First Name", "Last Name", "DoB", "Phone", "Address"};
			
			for(String c : columns) {
				table.addColumn(c);
			}
			
			table.addRow(columns);
			
			List<Customer> customers = this.business.getCustomers();
			for(Customer c : (List<Customer>) customers) {
				table.addRow(c.getTableEntry());
			}
			
			view = 0;
			
			updateButton(addButton, "Add Customer");
			updateButton(editButton, "Edit Customer");
			updateButton(deleteButton, "Delete Customer");
			updateButton(reportButton, "Show Customer's Revenue Report");
			
			selectedLine = -1;
		}
		else if (event.getSource() == this.getProducts) {

			table.setColumnCount(0);
			table.setRowCount(0);
			

			String columns[] = {"ID", "Name", "Price"};
			
			for(String c : columns) {
				table.addColumn(c);
			}
			
			table.addRow(columns);
			
			List<Product> products = BusinessLayer.getProducts();
			for (Product p : (List<Product>)products)
			{
				table.addRow(p.getTableEntry());
						
			}
			
			view = 0;
			
			updateButton(addButton, "Add Customer");
			updateButton(editButton, "Edit Customer");
			updateButton(deleteButton, "Delete Customer");
			updateButton(reportButton, "Show Customer's Revenue Report");
			
			selectedLine = -1;
			view = 1;
			
			updateButton(addButton, "Add Product");
			updateButton(editButton, "Edit Product");
			updateButton(deleteButton, "Delete Product");
			updateButton(reportButton, "Show Historical Price");
			
			selectedLine = -1;
			
		}
		else if (event.getSource() == this.getOrders) {
			
			
			//TODO
			table.setColumnCount(0);
			table.setRowCount(0);
			

			String columns[] = {"Order_ID", "Customer_ID", "Date Time Ordered", "Total Price", "Percentage of Discount"};
			
			for(String c : columns) {
				table.addColumn(c);
			}
			
			table.addRow(columns);
			
			List<Order> orders = this.business.getOrders();
		
			for(Order o : (List<Order>) orders) {
				table.addRow(o.getTableEntry());
			}
			
			
			
			updateButton(addButton, "Add Order");
			updateButton(editButton, "Edit Order");
			updateButton(deleteButton, "Delete Order");
			reportButton.setVisible(false);
			
			view = 2;
			selectedLine = -1;
		}
		else if(event.getSource() == this.addButton) {
			if(view == 0) {
				new AddCustomerView(this);
			}
			else if(view == 1) {
				new AddProductView(this);	
			}
			else if(view == 2) {
				new AddOrderView(this);
			}
		}
		else if(event.getSource() == this.editButton) {
			
			selectedLine = dataTable.getSelectedRow();
			
			if(selectedLine == -1) {
				//throw exception
				JOptionPane.showMessageDialog (null, new MessageException("bad selection"));
				return;
			}
			
			
			String[] row = getRow(selectedLine);
			
			System.out.println(event);
			
			if(view == 0) {
				new AddCustomerView(this, row, selectedLine);			
			}
			else if(view == 1) {
				new AddProductView(this, row, selectedLine);		
			}
			else if(view == 2) {
				new AddOrderView(this, row, selectedLine);			
			}
		}
		else if(event.getSource() == this.deleteButton) {
			
			selectedLine = dataTable.getSelectedRow();
			int id =  Integer.parseInt(getRow(selectedLine)[0]);
			
			if(selectedLine == -1) {
				//throw exception
				JOptionPane.showMessageDialog (null, new MessageException("bad selection"));
				return;
			}

			
			//extract the ID to delete from  this line
			@SuppressWarnings("unused")
			String lineID = getRow(selectedLine)[0];
			
			deleteLine(this.selectedLine);
			selectedLine = -1;
			if(view == 0) {				
				
				BusinessLayer.deleteCustomer(id);
			}
			else if(view == 1) {
				
				BusinessLayer.deleteProduct(id);
			}
			else if(view == 2) {
				
				BusinessLayer.deleteOrder(id);
			}
		}
		else if(event.getSource() == this.reportButton) {
			
			if(selectedLine == -1) {
				//throw exception
				JOptionPane.showMessageDialog (null, new MessageException("bad selection"));
				return;
			}

			

			String lineID = getRow(selectedLine)[0];
			String header = "displaying report for " + lineID;
			String report = "";
			if(view == 0) {				
				report = "total spending: $11.11";
			}
			else if(view == 1) {
				report = "date1 price1\ndate2 price2";
			}
			new ReportView(header, report);
		}
		else if(event.getSource() == this.dateReport) {
			
			String header = "displaying orders from " + startDate.getText() + " to " + endDate.getText();
			String report = "date1 order1 profit1\ndate2 order2 profit2\ndate3 order3 profit3\ndate4 order4 profit4\n";
			report += "total is $0.00";
			
			new ReportView(header, report);

		}
	}
	
	private String[] getRow(int row) {
		String rowArr[] = new String[dataTable.getColumnCount()];
		
		for(int a = 0; a < dataTable.getColumnCount(); a++) {
			rowArr[a] = (String) dataTable.getValueAt(row, a);
		}
		
		return rowArr;
	}
	
	public void addLine(Item item) {
		this.table.addRow(item.getTableEntry());
	}
	
	private void updateButton(JButton button, String text) {
		button.setVisible(true);
		button.setText(text);
	}
	
	public void updateLine(int line, Item item) {
		
		String row[] = item.getTableEntry();
		
		int index = 0;
		for(String s : row) {
			this.table.setValueAt(s, line, index++);
		}

	}
	

	
	private void deleteLine(int line) {
		this.table.removeRow(line);
	    
	}
	
	//singleton pattern
	private BusinessLayer getBusiness() {
		if(this.business == null) {
			this.business = new BusinessLayer();
		}
		
		return this.business;
	}

}