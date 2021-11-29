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

public class ProductManager {
	static SessionFactory factory;
	
	public ProductManager() {
		factory = new Configuration().
				configure("hibernate.cfg.xml").
		        addAnnotatedClass(Customer.class).
		        addAnnotatedClass(Address.class).
		        addAnnotatedClass(Product.class).
		        addAnnotatedClass(Order.class).
		        addAnnotatedClass(ProductHistory.class).
		        buildSessionFactory();
	}
	
	public static boolean addProduct(Product p) {
		
		System.out.println("Adding Product....");
		
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			System.out.println(p);
			session.save(p);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		
		return true; //if successful return true
	}
	
	public static boolean editProduct(Product p, int ID) {
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			
			Product oldP = session.find(Product.class, ID);
			
			oldP.setName(p.getName());
			oldP.setPrice(p.getPrice());
			
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		System.out.println("Modifying Product....");
		
		//connect to data access here and add Product
		
		return true; //if successful return true
	}
	
	public static boolean deleteProduct(int ID) {
		Session session = factory.getCurrentSession();
		
		
		try {
			session.beginTransaction();
			
			Product p = new Product();
			p.setId(ID);
			
			session.delete(p);
			session.getTransaction().commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		
		System.out.println("Removing Product....");
		
		//connect to data access here and add Product
		
		return true; //if successful return true
	}
	
	public static Product getProduct(int ID) {
		//fetch product from data access	
		
		Session session = factory.getCurrentSession();
		try {
			session = factory.openSession();
			session.beginTransaction();
			
			Product p = (Product) session.load(Product.class, ID);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		return null;
	}
	
	public static List<Product> getProducts() {
		//get from data acccess layer
		Session session = factory.getCurrentSession();
		try {
			session = factory.openSession();
			session.beginTransaction();
			
			List result = session.createQuery("from Product").list();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		return null;
	}
	
	public static List<ProductHistory> getHistorialPrice(int productId){
		//get from data acccess layer
				Session session = factory.getCurrentSession();
				try {
					session = factory.openSession();
					session.beginTransaction();
					
					Product product = getProduct(productId);
					return product.getProductHistory();
					
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
