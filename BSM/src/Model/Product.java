<<<<<<< HEAD:BSM/src/Product.java
=======
package Model;
>>>>>>> parent of ae5bdab (changes):BSM/src/Model/Product.java
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
<<<<<<< HEAD:BSM/src/Product.java
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
=======
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
>>>>>>> parent of ae5bdab (changes):BSM/src/Model/Product.java
=======
>>>>>>> parent of aff98a2 (fixed some errors)
=======
>>>>>>> parent of aff98a2 (fixed some errors)
=======
>>>>>>> parent of aff98a2 (fixed some errors)
import java.util.List;

@Entity
@Table(name="product")
public class Product extends Item  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int id; 
	
	@Column(name="name")
	private String name;
	
	@Column(name ="price")
	private double price;
	
	@ManyToMany(cascade= {CascadeType.PERSIST})
	@JoinTable(
			name="customer_order_product",
			joinColumns=@JoinColumn(name="product_id"),
			inverseJoinColumns=@JoinColumn(name="order_id")
			)
<<<<<<< HEAD:BSM/src/Product.java
			
	
	private List<Order> orders;
	
=======
	private List<Order> orders;
	
	@OneToMany(mappedBy="product"
	,cascade = CascadeType.PERSIST)
	private List<ProductHistory> producthistory;
	
	
>>>>>>> parent of ae5bdab (changes):BSM/src/Model/Product.java
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	


	public Product(){

	}
	
	
	public Product(String name, Double price) {
		this.name = name;
		this.price = price;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:BSM/src/Product.java
=======
		this.addProductPriceHistory(price);
		
>>>>>>> parent of ae5bdab (changes):BSM/src/Model/Product.java
=======
>>>>>>> parent of aff98a2 (fixed some errors)
=======
>>>>>>> parent of aff98a2 (fixed some errors)
=======
>>>>>>> parent of aff98a2 (fixed some errors)
	}
	
	public Product(int Id, String name, Double price) {
		this.id = Id;
		this.name = name;
		this.price = price;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:BSM/src/Product.java
=======
		this.addProductPriceHistory(price);
>>>>>>> parent of ae5bdab (changes):BSM/src/Model/Product.java
=======
>>>>>>> parent of aff98a2 (fixed some errors)
=======
>>>>>>> parent of aff98a2 (fixed some errors)
=======
>>>>>>> parent of aff98a2 (fixed some errors)
	}
	
	


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrder(Order order) {
		this.orders.add(order);
	}

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:BSM/src/Product.java
=======
	
	public void addProductPriceHistory(double price ) {
		if (productHistory == null)
		{	
			productHistory = new ArrayList<>();
		}
		
		ProductHistory ph = new ProductHistory (price, LocalDate.now());
		ph.setProduct(this);
		productHistory.add(ph);
		
		
	}

	public List<ProductHistory> getProductHistory() {
		return productHistory;
	}


	public void setProductHistory(List<ProductHistory> productHistory) {
		this.productHistory = productHistory;
	}

>>>>>>> parent of ae5bdab (changes):BSM/src/Model/Product.java
=======
>>>>>>> parent of aff98a2 (fixed some errors)
=======
>>>>>>> parent of aff98a2 (fixed some errors)
=======
>>>>>>> parent of aff98a2 (fixed some errors)

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
	public String[] getTableEntry() {
		return new String[] {"" + id, name, "" + price};
	}
	
	
	
	
//	public String getData() {
//		return data;
//	}

}
