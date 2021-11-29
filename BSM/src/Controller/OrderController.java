package Controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLayer.OrderManager;
import Model.Order;
import Model.Product;
import Model.MessageException;
public class OrderController {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try 
		{
			String broncoId = request.getParameter("broncoId");
			String productName = request.getParameter("productName");
			if (broncoId.equals("")) {
				throw new MessageException("Bronco_Id  not informed.");
			} else if (productName.equals("")) {
				throw new MessageException("Product Name not informed.");
			}
			else
			{
				Order order = OrderManager.CreateOrderFromWeb(broncoId, productName);
			}
		}
		catch (MessageException e)
		{
			System.out.println(e);
		}
}
	
	
}