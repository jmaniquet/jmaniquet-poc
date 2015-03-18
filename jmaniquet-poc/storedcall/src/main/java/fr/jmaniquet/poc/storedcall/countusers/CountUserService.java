package fr.jmaniquet.poc.storedcall.countusers;

public interface CountUserService {

	Integer countUsers();
	
	Integer countUsersWithIn(int incrementIn);
}
