
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class BusinessLayer {
	static SessionFactory factory;

	public BusinessLayer()
	{
		factory = new Configuration().
		        configure("hibernate.cfg.xml").
		        addAnnotatedClass(Customer.class).
		        addAnnotatedClass(Address.class).
		        addAnnotatedClass(Product.class).
		        addAnnotatedClass(Order.class).
		        buildSessionFactory();

	}


	public List<Order> getOrders() {
		
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
		
//		String customers[] = new String[5];
//		for(int a = 0; a < 5; a++) {
//			customers[a] = "ID = " + a + "\tname" + a + "\taddress" + a;
//		}
//		
//		String s = "";
//		
//		for(String c : customers) {
//			s += c;
//			s += "\n";
//		}
//		
//		return s;
	}

	//
	//customer 
	//
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
	
	//
	//product 
	//
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
	//
	//order
	//
	public static boolean addOrder(Order o, int customerID) {

		
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			
			
			o.DetermineDiscount();
			
			
			//Enter Cusomter ID at UI 
			Customer c = session.get(Customer.class, customerID);
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
		
		System.out.println("Removing Order....");
		
		//connect to data access here and add Order
		
		return true; //if successful return true
	}
	
	public static Order getOrder(int ID) {
		//fetch order from data access		
		return new Order();
	}
	

	
	protected void finalize()
	{
		factory.close();
	}
}
