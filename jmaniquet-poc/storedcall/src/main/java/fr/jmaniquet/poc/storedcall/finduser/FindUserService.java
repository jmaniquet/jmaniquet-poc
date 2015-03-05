package fr.jmaniquet.poc.storedcall.finduser;

import fr.jmaniquet.poc.tools.user.User;

public interface FindUserService {
	
	User findUserById(long id);
}
