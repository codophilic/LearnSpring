package mvc.model.entities;

import javax.persistence.*;

@Entity(name = "customer_table")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customer_id")
	private int custId;
	
	@Column(name="first_name")
	private String custfirtName;

	@Column(name="last_name")
	private String custlastName;
	
	@Column(name="user_name")
	private String custuserName;
	
	@Column(name="password")
	private String custpassword;
	
	@Column(name="address")
	private String custaddress;
	
	@Column(name="contact")
	private String custcontact;

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustfirtName() {
		return custfirtName;
	}

	public void setCustfirtName(String custfirtName) {
		this.custfirtName = custfirtName;
	}

	public String getCustlastName() {
		return custlastName;
	}

	public void setCustlastName(String custlastName) {
		this.custlastName = custlastName;
	}

	public String getCustuserName() {
		return custuserName;
	}

	public void setCustuserName(String custuserName) {
		this.custuserName = custuserName;
	}

	public String getCustpassword() {
		return custpassword;
	}

	public void setCustpassword(String custpassword) {
		this.custpassword = custpassword;
	}

	public String getCustaddress() {
		return custaddress;
	}

	public void setCustaddress(String custaddress) {
		this.custaddress = custaddress;
	}

	public String getCustcontact() {
		return custcontact;
	}

	public void setCustcontact(String custcontact) {
		this.custcontact = custcontact;
	}
	
	
}
