package Controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BusinessLayer.ProductManager;
import Model.Order;
import Model.Product;
import Model.MessageException;
public class ProductController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Product> products = ProductManager.getProducts();
		
		String address = "/view/ProductView.jsp";
		response.setContentType(address);
		 RequestDispatcher rd = request.getRequestDispatcher(address);
			rd.forward(request, response);
}
}
