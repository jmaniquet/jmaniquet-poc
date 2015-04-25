package fr.jmaniquet.poc.tools.core.user;

import fr.jmaniquet.poc.tools.core.user.User;

public interface UserUtils {
	
	void assertEquals(User expectedUser, User actualUser);
	
	User findUserById(long id);
}
