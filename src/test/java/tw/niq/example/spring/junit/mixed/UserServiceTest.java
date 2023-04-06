package tw.niq.example.spring.junit.mixed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import tw.niq.example.spring.junit.repository.UserDatabase;
import tw.niq.example.spring.junit.repository.UserDatabaseMapImpl;
import tw.niq.example.spring.junit.service.UserService;
import tw.niq.example.spring.junit.service.UserServiceImpl;

@Order(1)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
	
	UserDatabase userDatabase;
	UserService userService;
	String createUserId = "";

	@BeforeAll
	void setup() {
		// Create & initialize database
		userDatabase = new UserDatabaseMapImpl();
		userDatabase.init();
		userService = new UserServiceImpl(userDatabase);
	}
	
	@AfterAll
	void cleanup() {
		// Close connection
		// Delete database
		userDatabase.close();
	}
	
	@Test
	@Order(1)
	@DisplayName("Create User")
	void testCreateUser_whenProvidedWithValidDetails_returnsUserId() {
		
		// Arrange
		Map<String, String> user = new HashMap<>();
		user.put("firstName", "John");
		user.put("lastName", "Doe");
		
		// Act
		createUserId = userService.createUser(user);
		
		// Assert
		assertNotNull(createUserId, () -> "User ID should not be null");
	}
	
	@Test
	@Order(2)
	@DisplayName("Update User")
	void testUpdateUser_whenProvidedWithValidDetails_returnsUpdatedUser() {
		
		// Arrange
		Map<String, String> newUser = new HashMap<>();
		newUser.put("firstName", "John");
		newUser.put("lastName", "Smith");
		
		// Act
		Map<String, String> updatedUser = userService.updateUser(createUserId, newUser);
		
		// Assert
		assertEquals(newUser.get("firstName"), updatedUser.get("firstName"), () -> "Returned value of user's first name is incorrect");
		assertEquals(newUser.get("lastName"), updatedUser.get("lastName"), () -> "Returned value of user's last name is incorrect");
	}
	
	@Test
	@Order(3)
	@DisplayName("Find User")
	void testFindUser_whenProvidedWithValidUserId_returnsUser() {
		
		// Act
		Map<String, String> foundUser = userService.getUserDetails(createUserId);
				
		// Assert
		assertNotNull(foundUser, () -> "User should not be null");
		assertEquals(createUserId, foundUser.get("userId"), () -> "Returned user contains incorrect user ID");
	}
	
	@Test
	@Order(4)
	@DisplayName("Delete User")
	void testDeleteUser_whenProvidedWithValidUserId_returnsUser() {
		
		// Act
		userService.deleteUser(createUserId);
				
		// Assert
		assertNull(userService.getUserDetails(createUserId), () -> "User should not be found");
	}
	
}
