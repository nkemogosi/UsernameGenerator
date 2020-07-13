package u_nameGen.main;

public class Student {
	private String fName;
	private String sName;
	private String house;
	private String form;
	private int yearGroup;
	private String yearOfAddition;
	private String userName = "";
	private String email = "";

	public Student(String sName, String fName, String house,
			String yearOfAddition, int yearGroup) {
		this.fName = fName;
		this.sName = sName;
		this.house = house;
		this.yearOfAddition = yearOfAddition;
		if (yearGroup == 7) {
			this.form = house.substring(0, 1);
		}

	}

	public Student(String sName, String fName, String email) {
		this.fName = fName;
		this.sName = sName;
		this.email = email;

	}

	public String getFName() {
		return fName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSName() {
		return sName;
	}

	public String getHouse() {
		return house;
	}

	public int getYearGroup() {
		return yearGroup;
	}

	public String getYearOfAddition() {
		return yearOfAddition;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setHouse(String house) {
		this.house = house;
	}
}
