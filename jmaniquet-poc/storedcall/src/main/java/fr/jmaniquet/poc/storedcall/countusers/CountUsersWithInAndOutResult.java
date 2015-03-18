package fr.jmaniquet.poc.storedcall.countusers;

public class CountUsersWithInAndOutResult {

	private Integer nbUsers;

	private Integer incrementOut;
	
	CountUsersWithInAndOutResult(Integer nbUsers, Integer incrementOut) {
		super();
		this.nbUsers = nbUsers;
		this.incrementOut = incrementOut;
	}

	public Integer getIncrementOut() {
		return incrementOut;
	}

	public Integer getNbUsers() {
		return nbUsers;
	}
}
