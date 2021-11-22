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

@Entity
@Table(name="customer")
public class Customer extends Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int id; 
	
	//TODO
	//needs column int database
	//private int bronco_id;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="dob")
	private Date dob;
	
	@Column(name="phone")
	private String phone;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;
	
	
	//just temporary, we don't want to store all data as string in final
	//private String customerData;

	public Customer()
	{
		
	}
	public Customer(int bronco_id, String firstName, String lastName, Date dob, String phone, Address address) {
		//this.bronco_id = bronco_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
	}
	
//	public Customer(Address address) {
//		this.address = address;
//		this.customerData = "";
//	}
//	
//	public Customer(String data) {
//		this.customerData = data;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public Date getDOB() {
		return dob;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public Address getAddress()
	{
		return address;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id +", firstName=" + firstName + ",lastName=" + lastName + ",DOB=" + dob + ",phone=" + phone + address.toString();
	}
	
	public String[] getTableEntry() {
		return new String[] {"" + id, firstName, lastName, dob.toString(), "" + phone, address.toString()};
		//return "Customer [id=" + id +", firstName=" + firstName + ",lastName=" + lastName + ",DOB=" + dob + ",phone=" + phone + address.toString();
	}
}
