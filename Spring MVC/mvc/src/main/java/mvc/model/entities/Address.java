package mvc.model.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String addressline1;
	
	private String addressline2;

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}
	
	
}
