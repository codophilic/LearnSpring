package mvc.model.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity(name = "student_form_details")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="unique_id")
	private int uniqueId;
	
	@Column(name="student_name")
	private String stdname;

	@Column(name="college_roll_number")
	private long stdrollnum;
	
	@DateTimeFormat(pattern="dd-MMMM-yyyy")
	@Column(name="date_of_birth")
	private Date stddatedob;
	

    @ElementCollection
    @CollectionTable(name="student_subjects_data", joinColumns = @JoinColumn(name="student_subjects_id"))
    @Column(name="foreign_key_student_subjects_id")
    private List<String> stdsubjects;
	
	@Column(name="gender")
	private String gender;

	@Column(name="type")
	private String stdtype;

	@Column(name="foreign_key_address_id")
	@Embedded
	private Address studentAddress;

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getStdname() {
		return stdname;
	}

	public void setStdname(String stdname) {
		this.stdname = stdname;
	}

	public long getStdrollnum() {
		return stdrollnum;
	}

	public void setStdrollnum(long stdrollnum) {
		this.stdrollnum = stdrollnum;
	}

	public Date getStddatedob() {
		return stddatedob;
	}

	public void setStddatedob(Date stddatedob) {
		this.stddatedob = stddatedob;
	}

	public List<String> getStdsubjects() {
		return stdsubjects;
	}

	public void setStdsubjects(List<String> stdsubjects) {
		this.stdsubjects = stdsubjects;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStdtype() {
		return stdtype;
	}

	public void setStdtype(String stdtype) {
		this.stdtype = stdtype;
	}

	public Address getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(Address studentAddress) {
		this.studentAddress = studentAddress;
	}
	
	
	
}
