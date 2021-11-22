import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.text.SimpleDateFormat;

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
	
	


	public Product(){

	}
	
	
	public Product(int id, String name, Double price) {
		this.id = id;
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
