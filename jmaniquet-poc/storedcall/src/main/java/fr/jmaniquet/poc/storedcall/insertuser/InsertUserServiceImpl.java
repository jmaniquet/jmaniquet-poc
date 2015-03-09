package fr.jmaniquet.poc.storedcall.insertuser;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.jmaniquet.poc.storedcall.core.StoredCall;

@Service
public class InsertUserServiceImpl implements InsertUserService {
	
	@Autowired
	@Qualifier("inserUserOverload1")
	private StoredCall inserUserOverload1;
	
	@Autowired
	@Qualifier("insertUserOverload2")
	private StoredCall inserUserOverload2;
	
	@Override
	public void insertUser(long id) {
		inserUserOverload1.execute(id);
	}
	
	@Override
	public void insertUser(long id, DateTime birthDate) {
		inserUserOverload2.execute(id, birthDate);
	}
}
