package BusinessLayer;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Model.Address;
import Model.Customer;
import Model.Product;
import Model.ProductHistory;
import Model.Order;

public class CustomerManager {
	static SessionFactory factory;
	
	public CustomerManager() {
		factory = new Configuration().
		        configure("hibernate.cfg.xml").
		        addAnnotatedClass(Customer.class).
		        addAnnotatedClass(Address.class).
		        addAnnotatedClass(Product.class).
		        addAnnotatedClass(Order.class).
		        addAnnotatedClass(ProductHistory.class).
		        buildSessionFactory();
	}
	
	public static boolean addCustomer(Customer c) {
		
		 
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			System.out.println(c);
			session.save(c);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		System.out.println("Adding customer....");
		
		//connect to data access here and add customer
		
		return true; //if successful return true
	}
	
	public static boolean editCustomer(Customer c, int customerId) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			System.out.println(c);
			Customer oldC = session.find(Customer.class, customerId);
			
			
			System.out.println(oldC.toString());
			
			oldC.setFirstName(c.getFirstName());
			oldC.setLastName(c.getLastName());
			oldC.setAddress(c.getAddress());
			oldC.setBronco_id(c.getBronco_id());
			oldC.setPhone(c.getPhone());
			oldC.setDob(c.getDOB());
			
			
		
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		System.out.println("Modifying customer....");
		
		//connect to data access here and add customer
		
		return true; //if successful return true
	}
	
	public static boolean deleteCustomer(int ID) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			
			Customer c = new Customer();
			
			c.setId(ID);
			
			session.delete(c);
			session.getTransaction().commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		
		System.out.println("Removing customer....");
		
		//connect to data access here and add customer
		
		return true; //if successful return true
	}
	
	public static Customer getCustomer(int ID) {
		//fetch customer from data access		
		return new Customer();
	}
	
public List<Customer> getCustomers() {
		
		//get from data acccess layer
		Session session = factory.getCurrentSession();
		try {
			session = factory.openSession();
			session.beginTransaction();
			
			List result = session.createQuery("from Customer").list();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		return null;
		
	}
	protected void finalize()
	{
		factory.close();
	}
}
