import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ReportView implements ActionListener {

	private JLabel top;

	private JPanel panel1;

	private JButton done;
	
	private JTextArea reportArea;

	private JFrame frame;

	private String header;

	private String report;

	/**
	 * @wbp.parser.entryPoint
	 */
	public ReportView(String header, String report) {
		this.header = header;
		this.report = report;
		
		buildFrame();
	}

	private void buildFrame() {
		this.frame = new JFrame();
		
		this.top = new JLabel(header);
		top.setBounds(6,6,264,22);
		
		this.reportArea = new JTextArea();
		reportArea.setText(report);
		reportArea.setBounds(6,29,264,124);

		this.done = new JButton("done?");
		done.setBounds(6,165,264,22);
		done.addActionListener(this);

		this.panel1 = new JPanel();
		panel1.setLayout(null);
		this.panel1.add(this.top);
		this.panel1.add(this.reportArea);
		this.panel1.add(this.done);


		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		frame.setBounds(280, 120, 292, 232);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(0,0);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == this.done) {

			// close window
			this.frame.setVisible(false);
			this.frame.dispose();

		}

	}

}