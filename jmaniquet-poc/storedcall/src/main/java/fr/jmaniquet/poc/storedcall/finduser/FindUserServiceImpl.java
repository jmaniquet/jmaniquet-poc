package fr.jmaniquet.poc.storedcall.finduser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.jmaniquet.poc.storedcall.core.StoredCall;
import fr.jmaniquet.poc.storedcall.core.StoredCallResult;
import fr.jmaniquet.poc.tools.user.User;
import fr.jmaniquet.poc.tools.user.UserBuilder;

@Service
class FindUserServiceImpl implements FindUserService {
	
	@Autowired
	@Qualifier("findAllUsers")
	private StoredCall findAllUsers;
	
	@Autowired
	@Qualifier("findAllUsersAndProperty")
	private StoredCall findAllUsersAndProperty;
	
	@Autowired
	@Qualifier("findUserById")
	private StoredCall findUserById;
	
	@Override
	public FindAllUsersResult findAllUsers() {
		StoredCallResult result = findAllUsers.execute();
		List<User> orderedAsc = result.getList("USERS_ASC");
		List<User> orderedDesc = result.getList("USERS_DESC");
		return new FindAllUsersResult(orderedAsc, orderedDesc);
	}

	@Override
	public FindAllUsersAndPropertyResult findAllUsersAndProperty() {
		StoredCallResult result = findAllUsersAndProperty.execute();
		List<User> orderedAsc = result.getList("USERS_ASC");
		List<User> orderedDesc = result.getList("USERS_DESC");
		String property = result.getString("PROPERTY");
		return new FindAllUsersAndPropertyResult(orderedAsc, orderedDesc, property);
	}
	
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
