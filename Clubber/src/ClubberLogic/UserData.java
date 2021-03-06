package ClubberLogic;

import java.util.Date;


/*
 * Maayan : 24/4/2014
 * 			* added filed 'password'
 * 			* field gender changed according to DB
 * 			* the set and get functions were private changed to protected
 */

public abstract class UserData {
	private Integer id;
	private String password;
	private String firstName;
	private String lastName;
	private Gender gender;
	private String phoneNumber;
	private String email;
	private Date birthDate; 
	
	
	public UserData(){
		
	}
	/*
	 * Maayan : 24/4/2014
	 * 			added this constructor 
	 */
	public UserData(String email, String password) {
	}
	
	/*
	 * Maayan : 24/4/2014
	 * 			added set to filed password
	 */
	
	public UserData(String lastName, String firstName, String gender, String phoneNumber, String email, Date birthDate, String password) 
    {
        this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setGender(gender);
		this.setPhoneNumber(phoneNumber);
		this.setEmail(email);
		this.setBirthDate(birthDate);	
		this.setPassword(password);
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender.equals(Gender.Male.toString())? Gender.Male : Gender.Female;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
