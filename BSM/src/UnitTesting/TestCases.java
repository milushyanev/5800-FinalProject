package UnitTesting;
import java.awt.AWTException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.text.BadLocationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import View.DesktopAppView;

public class TestCases {

	Robot bot;
	
	int mask = InputEvent.BUTTON1_DOWN_MASK;
	
	private void click(int x, int y) {
		try{Thread.sleep(250);}catch(InterruptedException e){}
		bot.mouseMove(x, y);
		try{Thread.sleep(250);}catch(InterruptedException e){}
		bot.mousePress(mask);
		try{Thread.sleep(100);}catch(InterruptedException e){}
		bot.mouseRelease(mask);
	}
	
	public void type(String keys) {
		try{Thread.sleep(250);}catch(InterruptedException e){}
	    for (char c : keys.toCharArray()) {
	        int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
	        if (KeyEvent.CHAR_UNDEFINED == keyCode) {
	            throw new RuntimeException(
	                "Key code not found for character '" + c + "'");
	        }
	        bot.keyPress(keyCode);
	        bot.delay(10);
	        bot.keyRelease(keyCode);
	        bot.delay(10);
	    }
	}

	
	public void clickType(int x, int y, String s) {
		try{Thread.sleep(250);}catch(InterruptedException e){}
		click(x, y);
		
		try{Thread.sleep(250);}catch(InterruptedException e){}
		type(s);
	}
	
	public int getLastYCoord(DesktopAppView view) {
		int rows = view.rowCount();
		
		return 90 + (rows) * 15;
	}
	

	
	//test ID display
	@Test
	public void testFailure1() throws AWTException{
		bot = new Robot();
		
		DesktopAppView view = new DesktopAppView();
		
		
		//add product
		click(460, 50);		
		click(280, 280);	
		clickType(160, 140, "product1");		
		clickType(160, 170, "1");	
		click(130, 215);	
		
		//delete product
		int y = this.getLastYCoord(view);
		click(640, y);	
		
		int row = view.selectedRow();
		
		String ID = view.getItem(row, 0);
		
		try{Thread.sleep(3000);}catch(InterruptedException e){}
		
		assertTrue(!ID.equals("0"));
		
	}
	
	
	@Test
	public void testFailure2() throws AWTException{
		bot = new Robot();
		
		DesktopAppView view = new DesktopAppView();
		
		//add order
		click(580, 50);		
		click(280, 280);
		clickType(175, 140, "1");		
		clickType(175, 225, "3");	
		click(250, 270);
		click(295, 425);
		click(295, 700);
		

		int y = this.getLastYCoord(view);
		click(640, y);	
		
		int row = view.selectedRow();
		
		String ID = view.getItem(row, 0);
		
		try{Thread.sleep(3000);}catch(InterruptedException e){}
		
		assertTrue(!ID.equals("0"));
		
	}
	
	//cover as much code as possible
	@Test
	public void coverageTest() throws AWTException, BadLocationException {
		bot = new Robot();
		
		DesktopAppView view = new DesktopAppView();
		
		//add customer
		click(340, 50);		
		click(280, 280);		
		clickType(175, 140, "name1");		
		clickType(175, 160, "address1");		
		clickType(175, 190, "1/11/1111");		
		clickType(175, 220, "1111111111");		
		clickType(430, 140, "street1");		
		clickType(430, 160, "123");		
		clickType(430, 190, "456");		
		clickType(430, 220, "city1");		
		clickType(430, 250, "state1");
		click(260, 320);
		
		//report customer
		int y = this.getLastYCoord(view);
		
		click(640, y);
		click(420, 320);
		click(145, 205);
		
		//delete customer
		y = this.getLastYCoord(view);
		click(640, y);	
		click(640, 280);	
		
		try{Thread.sleep(1000);}catch(InterruptedException e){}
		
		//add product
		click(460, 50);		
		click(280, 280);	
		clickType(160, 140, "product1");		
		clickType(160, 170, "1");	
		click(130, 215);	
		
		//delete product
		y = this.getLastYCoord(view);
		click(640, y);	
		click(640, 280);	
		
		try{Thread.sleep(1000);}catch(InterruptedException e){}
		
		//add order
		click(580, 50);		
		click(280, 280);
		clickType(175, 140, "1");		
		clickType(175, 225, "3");	
		click(250, 270);
		click(295, 425);
		click(295, 700);
		
		//delete order
		y = this.getLastYCoord(view);
		click(640, y);	
		click(640, 280);	
		
		try{Thread.sleep(1000);}catch(InterruptedException e){}
		
		//date report
		click(350, 350);
		click(145, 205);
		
		try{Thread.sleep(3000);}catch(InterruptedException e){}
		
		
		
	}


	//didnt close with button
	@Test
	public void testError1() throws AWTException {
		
		ByteArrayOutputStream errContent = new ByteArrayOutputStream();
		PrintStream originalErr = System.err;
		
	    System.setErr(new PrintStream(errContent));
		
		bot = new Robot();
		
		new DesktopAppView();
		//add order
		click(580, 50);		
		click(280, 280);
		clickType(175, 140, "1");		
		clickType(175, 225, "3");	
		click(295, 425);		
		click(295, 700);
		
		String s1 = errContent.toString();
		
	    System.setErr(originalErr);
	    
	    //System.out.println(s);
	    System.out.println(s1);
	    
	    try{Thread.sleep(3000);}catch(InterruptedException e){}
	    
	    if(s1.contains("java.lang.ArrayIndexOutOfBoundsException")) {
	    	throw new IndexOutOfBoundsException();
	    }


	}
	
	//didnt enter date
	@Test
	public void testError2() throws AWTException {
		bot = new Robot();

		
		DesktopAppView view = new DesktopAppView();
		
		String fname = "Jon";
		
		//add customer
		click(340, 50);		
		click(280, 280);		
		clickType(175, 140, fname);		
		clickType(175, 160, "Doe");		
		clickType(175, 220, "19098697659");		
		clickType(430, 140, "Kellogg Dr");		
		clickType(430, 160, "60");		
		clickType(430, 190, "91768");		
		clickType(430, 220, "Pomona");		
		clickType(430, 250, "CA");
		click(260, 320);
		
		int col = 1;
		
		int rowCount = view.rowCount();
		
		boolean found = false;
		for(int a = 0; a < rowCount; a++) {
			String s = view.getItem(a, col);
			if (s.equalsIgnoreCase(fname)){
				found = true;
			}
		}
		
		try{Thread.sleep(3000);}catch(InterruptedException e){}
		
		assertTrue(!found);
		

	}
	
	
	//normal customer entry
	@Test
	public void blackBoxTest1() throws AWTException {
		bot = new Robot();
		
		DesktopAppView view = new DesktopAppView();
		
		String fname = "John";
		
		//add customer
		click(340, 50);		
		click(280, 280);		
		clickType(175, 140, fname);		
		clickType(175, 160, "Doe");		
		clickType(175, 190, "11/27/2021");		
		clickType(175, 220, "19098697659");		
		clickType(430, 140, "Kellogg Dr");		
		clickType(430, 160, "60");		
		clickType(430, 190, "91768");		
		clickType(430, 220, "Pomona");		
		clickType(430, 250, "CA");
		click(260, 320);

		try{Thread.sleep(3000);}catch(InterruptedException e){}
		
		int col = 1;
		
		int rowCount = view.rowCount();
		
		boolean found = false;
		for(int a = 0; a < rowCount; a++) {
			String s = view.getItem(a, col);
			if (s.equalsIgnoreCase(fname)){
				found = true;
			}
		}
		
		try{Thread.sleep(3000);}catch(InterruptedException e){}
		
		assertTrue(found);
		
		
	}
	
	//normal product entry
	@Test
	public void blackBoxTest2() throws AWTException {
		bot = new Robot();
		
		DesktopAppView view = new DesktopAppView();
		
		
		String itemName = "Textbook";
		
		//add product
		click(460, 50);		
		click(280, 280);	
		clickType(160, 140, "Textbook");		
		clickType(160, 170, "1200.00");	
		click(130, 215);	
		
		int col = 1;
		
		int rowCount = view.rowCount();
		
		boolean found = false;
		for(int a = 0; a < rowCount; a++) {
			String s = view.getItem(a, col);
			if (s.equalsIgnoreCase(itemName)){
				found = true;
			}
		}
		
		try{Thread.sleep(3000);}catch(InterruptedException e){}
		
		assertTrue(found);
	}
	
	//extra space on number and zip	
	@Test
	public void whiteBoxTest1() throws AWTException {
		bot = new Robot();
		
		DesktopAppView view = new DesktopAppView();
		
		String fname = "James";
		
		//add customer
		click(340, 50);		
		click(280, 280);		
		clickType(175, 140, fname);		
		clickType(175, 160, "Doe");		
		clickType(175, 190, "11/27/2021");		
		clickType(175, 220, "19098697659");		
		clickType(430, 140, "Kellogg Dr");		
		clickType(430, 160, "60 ");		
		clickType(430, 190, " 91768");		
		clickType(430, 220, "Pomona");		
		clickType(430, 250, "CA");
		click(260, 320);
		
	
		int col = 1;
		
		int rowCount = view.rowCount();
		
		boolean found = false;
		for(int a = 0; a < rowCount; a++) {
			String s = view.getItem(a, col);
			if (s.equalsIgnoreCase(fname)){
				found = true;
			}
		}
		
		try{Thread.sleep(3000);}catch(InterruptedException e){}
		
		assertTrue(found);
		
		
	}
	
	//put dollar sign on price
	@Test
	public void whiteBoxTest2() throws AWTException {
		bot = new Robot();
		
		DesktopAppView view = new DesktopAppView();
		
		
		String price = "1250.00.";
		
		//add product
		click(460, 50);		
		click(280, 280);	
		clickType(160, 140, "Textbook");		
		clickType(160, 170, price);	
		click(130, 215);	
		
		int col = 2;
		
		int rowCount = view.rowCount();
		
		String priceDouble = "1250.00";
		
		boolean found = false;
		for(int a = 0; a < rowCount; a++) {
			String s = view.getItem(a, col);
			if (s.equalsIgnoreCase(priceDouble)){
				found = true;
			}
		}
		
		try{Thread.sleep(3000);}catch(InterruptedException e){}
		
		assertTrue(found);
	}
	
}