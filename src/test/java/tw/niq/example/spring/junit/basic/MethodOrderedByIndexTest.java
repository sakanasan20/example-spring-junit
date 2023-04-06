package tw.niq.example.spring.junit.basic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodOrderedByIndexTest {
	
	StringBuilder completed = new StringBuilder("");
	
	@AfterEach
	void afterEach() {
		System.out.println(completed);
	}

	@Order(1)
	@Test
	void testA() {
//		System.out.println("testA");
		completed.append("1");
	}
	
	@Order(2)
	@Test
	void testB() {
//		System.out.println("testB");
		completed.append("2");
	}

	@Order(3)
	@Test
	void testC() {
//		System.out.println("testC");
		completed.append("3");
	}

	@Order(4)
	@Test
	void testD() {
//		System.out.println("testD");
		completed.append("4");
	}

}
