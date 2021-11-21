import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private int id;
	
	//just temporary, we don't want to store all data as string in final
	//private String AddressData;
	
	@Column(name="street")
	private String street;
	
	@Column(name="number")
	private int number;
	
	@Column(name="zip")
	private int zip;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state; 
	
//	@OneToOne(cascade= {CascadeType.ALL})
//	private Customer customer;
	
	public Address()
	{
		
	}
	public Address(String street, int number, int zip, String city, String state) {
		//this.AddressData = "";
		this.street = street;
		this.number = number;
		this.zip = zip;
		this.city = city;
		this.state = state;
	}
	
//	public Address(String data) {
//		this.AddressData = data;
//	}
//	
//	public String getData() {
//		return AddressData;
//	}
	
//	public void setCustomer(Customer customer)
//	{
//		this.customer = customer;
//	}
//	
	@Override
	public String toString() {
		return "Address: " + number + " " + street + " , " + city + " " + state ;
	}
}
