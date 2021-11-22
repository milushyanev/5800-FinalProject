import java.util.Date;

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
import java.time.LocalDateTime;

@Entity
@Table(name="order")
public class Order extends Item{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private int id; 
	
	@Column(name="dateTimeOrder")
	private LocalDateTime dateTimeOrder; 
	
	
	@Column(name="totalPrice")
	private Double totalPrice;
	
	@Column(name="discount")
	private Double discount;

	public Order()
	{
		
	}
	
	public Order(Date dateOrder, LocalDateTime  dateTimeOrder, Double totalPrice, Double discount) {
		super();
		this.dateTimeOrder  = dateTimeOrder;
		this.totalPrice = totalPrice;
		this.discount = discount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDateTimeOrder() {
		return dateTimeOrder;
	}

	public void setDateTimeOrder(LocalDateTime dateTimeOrder) {
		this.dateTimeOrder = dateTimeOrder;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", dateTimeOrder=" + dateTimeOrder + ", totalPrice=" + totalPrice + ", discount="
				+ discount + "]";
	}
	
	
}
