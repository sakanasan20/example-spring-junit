package tw.niq.example.spring.junit.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Test Math operations in Calculator class")
class CalculatorTest {

	Calculator calculator;
	
	@BeforeAll
	static void beforeAll() {
//		System.out.println("beforeAll");
	}
	
	@AfterAll
	static void afterAll() {
//		System.out.println("afterAll");
	}
	
	@BeforeEach
	void beforeEach() throws Exception {
//		System.out.println("beforeEach");
		calculator = new Calculator();
	}
	
	@AfterEach
	void afterEach() {
//		System.out.println("afterEach");
	}
	
	// test<System Under Test>_<Condition or State Change>_ <Expected Result>
	
	@DisplayName("Test 4/2=2")
	@Test
	void testIntegerDivision_WhenDividendIsDividedByValidInteger_ShouldReturnValidResult() {
		
		// Arrange (Given)
		int dividend = 4;
		int divisor = 2;
		int expectedResult = 2;
		
		// Act (When)
		int result = calculator.integerDivision(dividend,  divisor);
		
		// Assert (Then)
		assertEquals(expectedResult, result, 
				() -> dividend + "/" + divisor + " did not produce " + expectedResult);
	}
	
//	@Disabled("TODO")
	@DisplayName("Division by zero")
	@Test
	void testIntegerDivision_WhenDividendIsDividedByZero_ShouldThrowArithmeticException() {

		// Arrange (Given)
		int dividend = 4;
		int divisor = 0;
		String expectedExceptionMessage = "/ by zero"; 
		
		// Act (When) & Assert (Then)
		ArithmeticException arithmeticException = 
				assertThrows(ArithmeticException.class, ()-> {
					calculator.integerDivision(dividend,  divisor);
		}, "Division by zero should have thrown an Arithmetic exception");
		
		// Assert (Then)
		assertEquals(expectedExceptionMessage, arithmeticException.getMessage(), 
				"Unexpected exception message");
	}

	@DisplayName("Test integer subtraction [minuend, subtrahend, expectedResult]")
	@ParameterizedTest
//	@MethodSource()
//	@MethodSource("testIntegerSubtraction")
//	@CsvSource({
//		"33, 1, 32", 
//		"54, 1, 53", 
//		"24, 1, 23"
//	})
	@CsvFileSource(resources = "/integerSubtraction.csv")
	void testIntegerSubtraction(int minuend, int subtrahend, int expectedResult) {
		
		// Arrange (Given)

		// Act (When)
		int result = calculator.integerSubtraction(minuend,  subtrahend);
		
		// Assert (Then)
		assertEquals(expectedResult, result, 
				() -> minuend + "-" + subtrahend + " did not produce " + expectedResult);
	}
	
	@SuppressWarnings("unused")
	private static Stream<Arguments> testIntegerSubtraction() {
		return Stream.of(
				Arguments.of(33, 1, 32), 
				Arguments.of(54, 1, 53), 
				Arguments.of(24, 1, 23)
		);
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "John", "Kate", "Alice" })
	void testValueSource(String name) {
		assertNotNull(name);
	}
	
	@DisplayName("Test RepeatedTest")
	@RepeatedTest(
			value = 3, 
			name = "{displayName}. Repetition {currentRepetition} of {totalRepetitions}")
	void testRepeatedTest(RepetitionInfo repetitionInfo, TestInfo testInfo) {
		
		System.out.println("Running " + testInfo.getTestMethod().get().getName());
		
		System.out.println("Repetition #" + repetitionInfo.getCurrentRepetition() 
			+ " of " + repetitionInfo.getTotalRepetitions());
		
		assertNull(null);
	}
	
}
