package fr.jmaniquet.poc.tools.user;

import org.joda.time.DateTime;

public class UserBuilder {

	private Long id;
	
	private String name;
	
	private String givenName;
	
	private DateTime birthDate;
	
	private UserBuilder() {
		super();
	}
	
	public static UserBuilder builder() {
		return new UserBuilder();
	}
	
	public UserBuilder id(long pId) {
		this.id = pId;
		return this;
	}
	
	public UserBuilder name(String pName) {
		this.name = pName;
		return this;
	}
	
	public UserBuilder givenName(String pGivenName) {
		this.givenName = pGivenName;
		return this;
	}

	public UserBuilder birthDate(DateTime pBirthDate) {
		this.birthDate = pBirthDate;
		return this;
	}
	
	public User build() {
		User user = new User();
		user.setId(this.id);
		user.setName(this.name);
		user.setGivenName(this.givenName);
		user.setBirthDate(this.birthDate);
		return user;
	}
}
