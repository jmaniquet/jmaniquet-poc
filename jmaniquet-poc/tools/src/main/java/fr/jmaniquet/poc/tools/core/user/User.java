package fr.jmaniquet.poc.tools.core.user;

import org.joda.time.DateTime;

public class User {

	private Long id;
	
	private String name;
	
	private String givenName;
	
	private DateTime birthDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public DateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(DateTime birthDate) {
		this.birthDate = birthDate;
	}
	
	@Override
	public String toString() {
		String birthDateStr = (birthDate != null ? birthDate.toString("dd/MM/yyyy HH:mm:ss") : null);
		StringBuilder sb = new StringBuilder()
			.append("[id=").append(id).append("\n")
			.append(" name=").append(name).append("\n")
			.append(" givenName=").append(givenName).append("\n")
			.append(" birthDate=").append(birthDateStr)
			.append("]\n");
		return sb.toString();
	}
}
