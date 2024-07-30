package mvc.model;

public class UserClass {

	/**
	 * Instance variable names are same as form input field 
	 * name attributes
	 * 
	 * <input name="fieldEmail">
	 * <input name="fieldUserName">
	 * <input name="fieldPassword">
	 * <input name="fieldSecurityCode">
	 * 
	 */
	private String fieldEmail;
	private String fieldUserName;
	private String fieldPassword;
	private String fieldSecurityCode;
	
	
	public String getFieldEmail() {
		return fieldEmail;
	}
	public void setFieldEmail(String fieldEmail) {
		this.fieldEmail = fieldEmail;
	}
	public String getFieldUserName() {
		return fieldUserName;
	}
	public void setFieldUserName(String fieldUserName) {
		this.fieldUserName = fieldUserName;
	}
	public String getFieldPassword() {
		return fieldPassword;
	}
	public void setFieldPassword(String fieldPassword) {
		this.fieldPassword = fieldPassword;
	}
	public String getFieldSecurityCode() {
		return fieldSecurityCode;
	}
	public void setFieldSecurityCode(String fieldSecurityCode) {
		this.fieldSecurityCode = fieldSecurityCode;
	}
	
	
}
