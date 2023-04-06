package tw.niq.example.spring.junit.service;

import java.util.Map;
import java.util.UUID;

import tw.niq.example.spring.junit.repository.UserDatabase;

public class UserServiceImpl implements UserService {

	UserDatabase userDatabase;

	public UserServiceImpl(UserDatabase userDatabase) {
		this.userDatabase = userDatabase;
	}

	@Override
	public String createUser(Map<String, String> userDetails) {
		String userId = UUID.randomUUID().toString();
		userDetails.put("userId", userId);
		userDatabase.save(userId, userDetails);
		return userId;
	}

	@Override
	public Map<String, String> updateUser(String userId, Map<String, String> userDetails) {
		Map<String, String> existingUser = userDatabase.find(userId);
		if (existingUser == null)
			throw new IllegalArgumentException("User not found");

		existingUser.put("firstName", userDetails.get("firstName"));
		existingUser.put("lastName", userDetails.get("lastName"));

		return userDatabase.update(userId, existingUser);
	}

	@Override
	public Map<String, String> getUserDetails(String userId) {
		return userDatabase.find(userId);
	}

	@Override
	public void deleteUser(String userId) {
		Map<String, String> existingUser = userDatabase.find(userId);
		if (existingUser == null)
			throw new IllegalArgumentException("User not found");

		userDatabase.delete(userId);
	}
}
