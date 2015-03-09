package fr.jmaniquet.poc.storedcall.insertuser;

import org.joda.time.DateTime;


public interface InsertUserService {

	void insertUser(long id);
	
	void insertUser(long id, DateTime birthDate);
}
