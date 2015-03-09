package fr.jmaniquet.poc.storedcall.finduser;

import java.util.List;

import fr.jmaniquet.poc.tools.user.User;

public class FindAllUsersAndPropertyResult {

	private List<User> orderedAsc;
	private List<User> orderedDesc;
	private String property;
	
	public FindAllUsersAndPropertyResult(List<User> ascOrdered, List<User> descOrdered, String property) {
		super();
		this.orderedAsc = ascOrdered;
		this.orderedDesc = descOrdered;
		this.property = property;
	}
	
	public List<User> getOrderedAsc() {
		return orderedAsc;
	}
	public List<User> getOrderedDesc() {
		return orderedDesc;
	}
	public String getProperty() {
		return property;
	}
}
