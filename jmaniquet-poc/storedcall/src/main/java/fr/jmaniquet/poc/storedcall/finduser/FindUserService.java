package fr.jmaniquet.poc.storedcall.finduser;

import fr.jmaniquet.poc.tools.user.User;

public interface FindUserService {
	
	FindAllUsersResult findAllUsers();
	
	FindAllUsersAndPropertyResult findAllUsersAndProperty();
	
	User findUserById(long id);
}
