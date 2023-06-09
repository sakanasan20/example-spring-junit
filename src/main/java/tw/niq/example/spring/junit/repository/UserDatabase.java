package tw.niq.example.spring.junit.repository;

import java.util.Map;

public interface UserDatabase {
	
	void init();

	void close();

	Map<String, String> save(String userId, Map<String, String> userDetails);

	Map<String, String> update(String userId, Map<String, String> user);

	Map<String, String> find(String userId);

	void delete(String userId);
	
}
