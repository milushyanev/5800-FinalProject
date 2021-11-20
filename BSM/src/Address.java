public class Address {
	
	//just temporary, we don't want to store all data as string in final
	private String AddressData;
	
	
	public Address() {
		this.AddressData = "";
	}
	
	public Address(String data) {
		this.AddressData = data;
	}
	
	public String getData() {
		return AddressData;
	}
}
