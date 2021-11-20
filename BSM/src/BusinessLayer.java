

public class BusinessLayer {
	

	public String getProducts() {
		
		//get from data acccess layer
		
		String s = "";
		
		//product 1
		s += "ID = 123\t Name = name1\t Price = $1.00\n";
		//product 2
		s += "ID = 345\t Name = name2\t Price = $2.00\n";
		//product 3
		s += "fgh46euy4 5y\n";
		
		return s;
		
	}
	
	public String getOrders() {
		
		//get from data acccess layer
		
		String orders[] = new String[5];
		
		for(int a = 0; a < orders.length; a++) {
			orders[a] = "ID = " + a + "\tTotal = $" + a + ".00";
		}
		String s = "";
		for (String o : orders) {
			s += o + "\n";
		}
		return s;
	}
	
	public String getCustomers() {
		
		//get from data acccess layer
		
		String customers[] = new String[5];
		for(int a = 0; a < 5; a++) {
			customers[a] = "ID = " + a + "\tname" + a + "\taddress" + a;
		}
		
		String s = "";
		
		for(String c : customers) {
			s += c;
			s += "\n";
		}
		
		return s;
	}

	//
	//customer 
	//
	public static boolean addCustomer(Customer c) {
		
		System.out.println("Adding customer....");
		
		//connect to data access here and add customer
		
		return true; //if successful return true
	}
	
	public static boolean editCustomer(Customer c, int ID) {
		
		System.out.println("Modifying customer....");
		
		//connect to data access here and add customer
		
		return true; //if successful return true
	}
	
	public static boolean deleteCustomer(int ID) {
		
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
		
		//connect to data access here and add Product
		
		return true; //if successful return true
	}
	
	public static boolean editProduct(Product p, int ID) {
		
		System.out.println("Modifying Product....");
		
		//connect to data access here and add Product
		
		return true; //if successful return true
	}
	
	public static boolean deleteProduct(int ID) {
		
		System.out.println("Removing Product....");
		
		//connect to data access here and add Product
		
		return true; //if successful return true
	}
	
	public static Product getProduct(int ID) {
		//fetch product from data access		
		return new Product();
	}
	//
	//order
	//
	public static boolean addOrder(Order o) {
		
		System.out.println("Adding Order....");
		
		//connect to data access here and add Order
		
		return true; //if successful return true
	}
	
	public static boolean editOrder(Order o, int ID) {
		
		System.out.println("Modifying Order....");
		
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
}
