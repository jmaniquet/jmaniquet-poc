package fr.jmaniquet.poc.storedcall.insertuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.jmaniquet.poc.storedcall.core.StoredCall;

@Service
public class InsertUserServiceImpl implements InsertUserService {
	
	@Autowired
	@Qualifier("insertUser")
	private StoredCall inserUser;
	
	@Override
	public void insertUser(long id) {
		inserUser.execute(id);
	}
}
