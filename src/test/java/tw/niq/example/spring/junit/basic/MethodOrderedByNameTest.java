package tw.niq.example.spring.junit.basic;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class MethodOrderedByNameTest {

	@Test
	void testA() {
		System.out.println("testA");
	}
	
	@Test
	void testB() {
		System.out.println("testB");
	}

	@Test
	void testC() {
		System.out.println("testC");
	}

	@Test
	void testD() {
		System.out.println("testD");
	}

}
