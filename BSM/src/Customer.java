
public class Customer {
	
	//just temporary, we don't want to store all data as string in final
	private String customerData;
	
	@SuppressWarnings("unused")
	private Address address;
	
	public Customer() {
		
	}
	
	public Customer(Address address) {
		this.address = address;
		this.customerData = "";
	}
	
	public Customer(String data) {
		this.customerData = data;
	}
	
	public String getData() {
		return customerData;
	}
}
