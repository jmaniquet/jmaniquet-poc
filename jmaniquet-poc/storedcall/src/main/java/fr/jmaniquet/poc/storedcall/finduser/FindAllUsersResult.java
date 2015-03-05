package fr.jmaniquet.poc.storedcall.finduser;

import java.util.List;

import fr.jmaniquet.poc.tools.user.User;

public class FindAllUsersResult {

	private List<User> orderedAsc;
	private List<User> orderedDesc;
	
	FindAllUsersResult(List<User> ascOrdered, List<User> descOrdered) {
		super();
		this.orderedAsc = ascOrdered;
		this.orderedDesc = descOrdered;
	}
	
	public List<User> getOrderedAsc() {
		return orderedAsc;
	}
	public List<User> getOrderedDesc() {
		return orderedDesc;
	}
}
