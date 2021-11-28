import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
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
			
	
	private List<Order> orders;
	
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
	}
	
	


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrder(Order order) {
		this.orders.add(order);
	}


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
