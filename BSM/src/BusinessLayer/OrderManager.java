package BusinessLayer;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Model.Address;
import Model.Customer;
import Model.Order;
import Model.Product;
import Model.ProductHistory;

public class OrderManager {
	static SessionFactory factory;
	
	public OrderManager() {
		factory = new Configuration().
		        configure("hibernate.cfg.xml").
		        addAnnotatedClass(Customer.class).
		        addAnnotatedClass(Address.class).
		        addAnnotatedClass(Product.class).
		        addAnnotatedClass(Order.class).
		        addAnnotatedClass(ProductHistory.class).
		        buildSessionFactory();
	}
	
public static boolean addOrder(Order o, int customerID) {

		
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			

			
			
			//Enter Cusomter ID at UI 
			Customer c = session.get(Customer.class, customerID);
			
			o.CalculateTotalPriceWithDiscount();
			c.addOrder(o);
			session.save(o);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		
		
		System.out.println("Adding Order....");
		
		//connect to data access here and add Order
		
		return true; //if successful return true
	}
	
	public static boolean editOrder(Order o, int ID) {
		
		
		
		
		
		
		
		System.out.println("Modifying Ordeewr....");
		
		//connect to data access here and add Order
		
		return true; //if successful return true
	}
	
	public static boolean deleteOrder(int ID) {
		Session session = factory.getCurrentSession();
		
		
		try {
			session.beginTransaction();
			
			Order o = new Order();
			o.setId(ID);
			
			session.delete(o);
			session.getTransaction().commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		System.out.println("Removing Order....");
		
		//connect to data access here and add Order
		
		return true; //if successful return true
	}
	
	public static Order getOrder(int ID) {
		//fetch order from data access		
		return new Order();
	}
	
	public static List<Order> getOrders() {
		
		//get from data acccess layer
		Session session = factory.getCurrentSession();
		try {
			session = factory.openSession();
			session.beginTransaction();
			
			List result = session.createQuery("from Order").list();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		return null;
	}
	
	public static Order CreateOrderFromWeb(String broncoId, String ProductName)
	{
		Customer c = CustomerManager.getCustomerWithBronco(Integer.parseInt(broncoId));
		Product p = ProductManager.getProductWithName(ProductName);
		
		Order Order = new Order(c,p);
		return Order;
	}
	
	protected void finalize()
	{
		factory.close();
	}
}
