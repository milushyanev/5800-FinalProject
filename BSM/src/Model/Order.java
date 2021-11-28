package Model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Entity
@Table(name="customer_order")
public class Order extends Item{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private int id; 
	
	@Column(name="datetimeordered")
	private LocalDateTime dateTimeOrdered; 
	
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Column(name="total_price")
	private Double totalPrice;
	
	@Column(name="discountpercentage")
	private Double discountpercentage;
	
	@ManyToMany(cascade= {CascadeType.PERSIST})
	@JoinTable(
			name="customer_order_product",
			joinColumns=@JoinColumn(name="order_id"),
			inverseJoinColumns=@JoinColumn(name="product_id")
			)
	private List<Product> products;

	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	
	
	public Order()
	{
		this.dateTimeOrdered  = LocalDateTime.now();
		this.totalPrice = .0;
	}
	
	public Order( LocalDateTime  dateTimeOrder, Double totalPrice, Double discount) {
		super();
		this.dateTimeOrdered  = dateTimeOrder;
		this.totalPrice = totalPrice;
		this.discountpercentage = discount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDateTimeOrder() {
		return dateTimeOrdered;
	}

	public void setDateTimeOrder(LocalDateTime dateTimeOrder) {
		this.dateTimeOrdered = dateTimeOrder;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getDiscount() {
		return discountpercentage;
	}

	public void setDiscount(Double discount) {
		this.discountpercentage = discount;
	}
	
	public void addProduct(Product tempProduct)
	{
		if (products == null)
		{
			products = new ArrayList<>();
		}
		
		
		this.products.add(tempProduct);
		
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void DetermineDiscount()
	{
		this.discountpercentage= 0.3;
	}


	public String toString() {
		return "Order [id=" + id + ", dateTimeOrder=" + dateTimeOrdered + ", totalPrice=" + totalPrice + ", discount="
				+ discountpercentage + "]";
	}
	
	public String[] getTableEntry() {
		return new String[] {"" + id, "" + customer.getId(), dateTimeOrdered.toString(), "" + totalPrice, "" + discountpercentage,};

	}



	
	
}
