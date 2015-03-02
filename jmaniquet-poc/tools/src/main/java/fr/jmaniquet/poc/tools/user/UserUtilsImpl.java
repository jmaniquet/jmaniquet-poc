package fr.jmaniquet.poc.tools.user;

import org.junit.Assert;
import org.springframework.stereotype.Component;

@Component
class UserUtilsImpl implements UserUtils {
	
	@Override
	public void assertEquals(User expectedUser, User actualUser) {
		Assert.assertNotNull(actualUser);
		Assert.assertEquals(expectedUser.getId(), actualUser.getId());
		Assert.assertEquals(expectedUser.getName(), actualUser.getName());
		Assert.assertEquals(expectedUser.getGivenName(), actualUser.getGivenName());
		Assert.assertEquals(expectedUser.getBirthDate(), actualUser.getBirthDate());
	}
}
