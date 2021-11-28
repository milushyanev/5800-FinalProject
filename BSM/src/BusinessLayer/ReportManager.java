package BusinessLayer;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Model.Product;
import Model.ProductHistory;

public class ReportManager {
	static SessionFactory factory;
	public ReportManager()
	{
		factory = new Configuration().
		        configure("hibernate.cfg.xml").
		        addAnnotatedClass(Product.class).
		        addAnnotatedClass(ProductHistory.class).
		        buildSessionFactory();
	}
	
//	public Double GetHistorialPriceOfProduct(int product_id)
//	
//	{
//		
//	}
}
