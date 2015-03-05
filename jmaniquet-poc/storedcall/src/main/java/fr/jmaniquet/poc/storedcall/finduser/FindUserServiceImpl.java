package fr.jmaniquet.poc.storedcall.finduser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.jmaniquet.poc.storedcall.core.StoredCall;
import fr.jmaniquet.poc.storedcall.core.StoredCallResult;
import fr.jmaniquet.poc.tools.user.User;
import fr.jmaniquet.poc.tools.user.UserBuilder;

@Service
public class FindUserServiceImpl implements FindUserService {
	
	@Autowired
	@Qualifier("findUserById")
	private StoredCall findUserById;
	
	@Override
	public User findUserById(long id) {
		StoredCallResult result = findUserById.execute(id);
		User u = UserBuilder.builder()
					.id(result.getLong("ID_P"))
					.name(result.getString("NAME"))
					.givenName(result.getString("GIVEN_NAME"))
					.birthDate(result.getDateTime("BIRTH_DATE"))
					.build();
		return u;
	}
}
