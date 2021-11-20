
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.swing.JTextPane;
import javax.swing.text.Highlighter;

//import javax.swing.JEditorPane;
//import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



@SuppressWarnings("serial")
public class DesktopAppView extends JFrame implements ActionListener, MouseListener {

	private JPanel panel1, panel2, panel3;
	
	private BusinessLayer business;
	
	private JTextArea dataArea;
	
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
	}

	private void initializeComponents() {
		
		view = -1;
		
		this.panel1 = new JPanel();
		this.panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.panel2 = new JPanel();

		this.panel3 = new JPanel();
		this.panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//my stuff 
		this.getCustomers = new JButton("Enumerate Customers");
		this.getCustomers.addActionListener(this);
		this.getProducts = new JButton("Enumerate Products");
		this.getProducts.addActionListener(this);
		this.getOrders = new JButton("Enumerate Orders");
		this.getOrders.addActionListener(this);
		
		this.dataArea = new JTextArea();
		dataArea.setBounds(30, 11, 500, 190);
		dataArea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	
		dataArea.addMouseListener(this);
		dataArea.setText("click a button to start...");


		
		this.addButton = new JButton("...");
		this.addButton.setBounds(30, 202, 160, 28);
		this.addButton.setVisible(false);
		this.addButton.addActionListener(this);
		
		this.editButton = new JButton("...");
		this.editButton.setBounds(202, 202, 160, 28);
		this.editButton.setVisible(false);
		this.editButton.addActionListener(this);
		
		this.deleteButton = new JButton("...");
		this.deleteButton.setBounds(370, 202, 160, 28);
		this.deleteButton.setVisible(false);
		this.deleteButton.addActionListener(this);
		
		this.reportButton = new JButton("...");
		this.reportButton.setBounds(30, 242, 500, 28);
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
		

		this.panel2.add(dataArea);

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
		this.setBounds(350, 140, 560, 383);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) throws BadLocationException {
		new DesktopAppView();
	}

	public void actionPerformed(ActionEvent event) {
		
		this.business = getBusiness();
		
		if (event.getSource() == this.getCustomers) {
			dataArea.setText("");
			dataArea.append(business.getCustomers());
			
			view = 0;
			
			updateButton(addButton, "Add Customer");
			updateButton(editButton, "Edit Customer");
			updateButton(deleteButton, "Delete Customer");
			updateButton(reportButton, "Show Customer's Revenue Report");
			
			selectedLine = -1;
		}
		else if (event.getSource() == this.getProducts) {
			dataArea.setText("");
			dataArea.append(business.getProducts());
			
			view = 1;
			
			updateButton(addButton, "Add Product");
			updateButton(editButton, "Edit Product");
			updateButton(deleteButton, "Delete Product");
			updateButton(reportButton, "Show Historical Price");
			
			selectedLine = -1;
			
		}
		else if (event.getSource() == this.getOrders) {
			dataArea.setText("");
			dataArea.append(business.getOrders());
			
			view = 2;
			
			updateButton(addButton, "Add Order");
			updateButton(editButton, "Edit Order");
			updateButton(deleteButton, "Delete Order");
			reportButton.setVisible(false);
			
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
			
			if(selectedLine == -1) {
				//throw exception
				JOptionPane.showMessageDialog (null, new MessageException("bad selection"));
				return;
			}
			
			if(view == 0) {
				new AddCustomerView(this, 0, selectedLine);			
			}
			else if(view == 1) {
				new AddProductView(this, 0, selectedLine);		
			}
			else if(view == 2) {
				new AddOrderView(this, 0, selectedLine);			
			}
		}
		else if(event.getSource() == this.deleteButton) {
			
			if(selectedLine == -1) {
				//throw exception
				JOptionPane.showMessageDialog (null, new MessageException("bad selection"));
				return;
			}

			
			//extract the ID to delete from  this line
			@SuppressWarnings("unused")
			String line = getLine(selectedLine);
			
			deleteLine(this.selectedLine);
			selectedLine = -1;
			if(view == 0) {				
				int id = 0;
				BusinessLayer.deleteCustomer(id);
			}
			else if(view == 1) {
				int id = 0;
				BusinessLayer.deleteProduct(id);
			}
			else if(view == 2) {
				int id = 0;
				BusinessLayer.deleteOrder(id);
			}
		}
		else if(event.getSource() == this.reportButton) {
			
			if(selectedLine == -1) {
				//throw exception
				JOptionPane.showMessageDialog (null, new MessageException("bad selection"));
				return;
			}

			

			String line = getLine(selectedLine);
			String header = "displaying report for " + line;
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
	
	public void addLine(String text) {
		this.dataArea.append(text+ "\n");
	}
	
	private void updateButton(JButton button, String text) {
		button.setVisible(true);
		button.setText(text);
	}
	
	public void updateLine(int line, String text) {
		try {
			int start = dataArea.getLineStartOffset(line);
			int end = dataArea.getLineEndOffset(line);
			
			
			String s = "";
			s += dataArea.getText().substring(0, start);
			s += text;
			s += "\n";
			s += dataArea.getText().substring(end, dataArea.getText().length());
			
			dataArea.setText(s);
			
			
			
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	    
	}
	
	private String getLine(int line) {
		try {
			int start = dataArea.getLineStartOffset(line);
			int end = dataArea.getLineEndOffset(line);
			
			return dataArea.getText().substring(start, end);
		} catch (BadLocationException e) {
			e.printStackTrace();
			return "";
		}

	}
	
	private void deleteLine(int line) {
		try {
			int start = dataArea.getLineStartOffset(line);
			int end = dataArea.getLineEndOffset(line);
			
			
			String s = "";
			s += dataArea.getText().substring(0, start);
			s += dataArea.getText().substring(end, dataArea.getText().length());
			
			dataArea.setText(s);
			
			
			
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	    
	}
	
	//singleton pattern
	private BusinessLayer getBusiness() {
		if(this.business == null) {
			this.business = new BusinessLayer();
		}
		
		return this.business;
	}

	public void mouseClicked(MouseEvent event) {
		
		//for highlighting elements in the data area 
		if(event.getSource() == this.dataArea) {
		    Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
		    
		    int y = event.getY();
		    
		    
		    int line = y/15;
		    selectedLine = line;
		    
		    int start;
		    int end;
			try {
				dataArea.getHighlighter().removeAllHighlights();
				start = dataArea.getLineStartOffset(line);
			    end = dataArea.getLineEndOffset(line);
			    dataArea.getHighlighter().addHighlight(start, end-1, painter);	//subtract 1 so we don't include next line 	
			    
			    
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			dataArea.getCaret().setVisible(false);

		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
