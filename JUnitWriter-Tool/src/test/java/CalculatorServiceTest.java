import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.t13.app.service.CalculatorService;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CalculatorService Unit Tests")
class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @BeforeEach
    void setUp() {
        // No specific setup needed for CalculatorService as it has no dependencies.
        // @InjectMocks handles the instantiation.
    }

    @Test
    @DisplayName("Test add method with positive numbers")
    void testAddPositiveNumbers() {
        assertEquals(5, calculatorService.add(2, 3));
        assertEquals(100, calculatorService.add(70, 30));
    }

    @Test
    @DisplayName("Test add method with negative numbers")
    void testAddNegativeNumbers() {
        assertEquals(-5, calculatorService.add(-2, -3));
        assertEquals(-100, calculatorService.add(-70, -30));
    }

    @Test
    @DisplayName("Test add method with mixed positive and negative numbers")
    void testAddMixedNumbers() {
        assertEquals(1, calculatorService.add(-2, 3));
        assertEquals(-1, calculatorService.add(2, -3));
        assertEquals(0, calculatorService.add(-5, 5));
    }

    @Test
    @DisplayName("Test add method with zero")
    void testAddWithZero() {
        assertEquals(5, calculatorService.add(0, 5));
        assertEquals(5, calculatorService.add(5, 0));
        assertEquals(0, calculatorService.add(0, 0));
        assertEquals(-5, calculatorService.add(0, -5));
    }

    @Test
    @DisplayName("Test sub method with positive numbers")
    void testSubPositiveNumbers() {
        assertEquals(1, calculatorService.sub(3, 2));
        assertEquals(-1, calculatorService.sub(2, 3));
        assertEquals(0, calculatorService.sub(5, 5));
    }

    @Test
    @DisplayName("Test sub method with negative numbers")
    void testSubNegativeNumbers() {
        assertEquals(-1, calculatorService.sub(-2, -1)); // -2 - (-1) = -1
        assertEquals(1, calculatorService.sub(-1, -2));  // -1 - (-2) = 1
    }

    @Test
    @DisplayName("Test sub method with mixed positive and negative numbers")
    void testSubMixedNumbers() {
        assertEquals(5, calculatorService.sub(2, -3)); // 2 - (-3) = 5
        assertEquals(-5, calculatorService.sub(-2, 3)); // -2 - 3 = -5
    }

    @Test
    @DisplayName("Test sub method with zero")
    void testSubWithZero() {
        assertEquals(5, calculatorService.sub(5, 0));
        assertEquals(-5, calculatorService.sub(0, 5));
        assertEquals(0, calculatorService.sub(0, 0));
        assertEquals(5, calculatorService.sub(0, -5));
    }

    @Test
    @DisplayName("Test multiply method with positive numbers")
    void testMultiplyPositiveNumbers() {
        assertEquals(6, calculatorService.multiply(2, 3));
        assertEquals(100, calculatorService.multiply(10, 10));
    }

    @Test
    @DisplayName("Test multiply method with negative numbers")
    void testMultiplyNegativeNumbers() {
        assertEquals(6, calculatorService.multiply(-2, -3));
        assertEquals(-6, calculatorService.multiply(-2, 3));
        assertEquals(-6, calculatorService.multiply(2, -3));
    }

    @Test
    @DisplayName("Test multiply method with zero")
    void testMultiplyWithZero() {
        assertEquals(0, calculatorService.multiply(0, 5));
        assertEquals(0, calculatorService.multiply(5, 0));
        assertEquals(0, calculatorService.multiply(0, 0));
    }

    @Test
    @DisplayName("Test multiply method with one")
    void testMultiplyWithOne() {
        assertEquals(5, calculatorService.multiply(5, 1));
        assertEquals(5, calculatorService.multiply(1, 5));
        assertEquals(-5, calculatorService.multiply(-5, 1));
    }

    @Test
    @DisplayName("Test divide method with positive numbers")
    void testDividePositiveNumbers() {
        assertEquals(2, calculatorService.divide(6, 3));
        assertEquals(3, calculatorService.divide(10, 3)); // Integer division
    }

    @Test
    @DisplayName("Test divide method with negative numbers")
    void testDivideNegativeNumbers() {
        assertEquals(2, calculatorService.divide(-6, -3));
        assertEquals(-2, calculatorService.divide(-6, 3));
        assertEquals(-2, calculatorService.divide(6, -3));
        assertEquals(-3, calculatorService.divide(-10, 3)); // Integer division
        assertEquals(-3, calculatorService.divide(10, -3)); // Integer division
    }

    @Test
    @DisplayName("Test divide method with zero numerator")
    void testDivideZeroNumerator() {
        assertEquals(0, calculatorService.divide(0, 5));
        assertEquals(0, calculatorService.divide(0, -5));
    }

    @Test
    @DisplayName("Test divide method by one")
    void testDivideByOne() {
        assertEquals(5, calculatorService.divide(5, 1));
        assertEquals(-5, calculatorService.divide(-5, 1));
    }

    @Test
    @DisplayName("Test divide method with division by zero")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculatorService.divide(1, 0),
                "Expected ArithmeticException for division by zero");
        assertThrows(ArithmeticException.class, () -> calculatorService.divide(-1, 0),
                "Expected ArithmeticException for division by zero");
        assertThrows(ArithmeticException.class, () -> calculatorService.divide(0, 0),
                "Expected ArithmeticException for division by zero");
    }
}