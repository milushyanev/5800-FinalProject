import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int id;

	// just temporary, we don't want to store all data as string in final
	// private String AddressData;

	@Column(name = "street")
	private String street;

	@Column(name = "number")
	private int number;

	@Column(name = "zip")
	private int zip;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

//	@OneToOne(cascade= {CascadeType.ALL})
//	private Customer customer;


	public Address(String street, int number, int zip, String city, String state) {
		// this.AddressData = "";
		this.street = street;
		this.number = number;
		this.zip = zip;
		this.city = city;
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}

	public int getNumber() {
		return number;
	}
	
	public int getZip() {
		return zip;
	}
	
	@Override
	public String toString() {
		return "Address: " + street + " " + number + " " + zip + " " + city + " " + state;
	}

	
//	public Address(String data) {
//		this.AddressData = data;
//	}
//	
//	public String getData() {
//		return AddressData;
//	}
	
	public static Address splitString(String s) {
		String split[] = s.split(" ");
		
		return new Address(split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]), split[4], split[5]);
	}
}
