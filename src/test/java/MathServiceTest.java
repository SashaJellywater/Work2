import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MathServiceTest {
    // Параметризованный тест
    @ParameterizedTest
    @CsvSource({
            "1, 2, -3, 16",
            "1, -4, 4, 0",
            "1, 1, 1, -3"
    })
    public void testGetD(int a, int b, int c, int expectedD) {
        MathService mathService = new MathService();

        int result = mathService.getD(a, b, c);
        Assertions.assertEquals(expectedD, result);
    }
    // Тест testGetAnswer() будет провален, так как в формулах предоставленного метода
    // (Double x1 = (-b + Math.sqrt(d)) / (2 + a); Double x2 = (-b - Math.sqrt(d)) / (2 + a);
    // допущена ошибка(возможно намеренно). Правильными в этом случае будут
    // (Double x1 = (-b + Math.sqrt(d)) / (2 * a); Double x2 = (-b - Math.sqrt(d)) / (2 * a);
    // При исправлении формулы тест проходит.

    @Test
    public void testGetAnswer() throws NotFoundAnswerException {
        MathService mathService = new MathService();

        // Тест для разных корней
        Pair expectedPair = new Pair(1.0, -3.0);
        Pair result = mathService.getAnswer(1, 2, -3);
        Assertions.assertEquals(expectedPair.first, result.first, 0.001);
        Assertions.assertEquals(expectedPair.second, result.second, 0.001);

        //Тест для одинаковых корней
        Pair expectedPair2 = new Pair(2.0, 2.0);
        Pair result2 = mathService.getAnswer(1, -4, 4);
        Assertions.assertEquals(expectedPair2.first, result2.first, 0.001);
        Assertions.assertEquals(expectedPair2.second, result2.second, 0.001);

    }

    // Негативный тест
    @Test
    public void testNegativeRootsScenario() throws NotFoundAnswerException{
        MathService mathService = new MathService();
        // Тест для отсутствия корней
        try {
            mathService.getAnswer(1, 1, 1);
            Assertions.fail("Expected NotFoundAnswerException was not thrown");
        } catch (NotFoundAnswerException e) {
            Assertions.assertEquals("Корни не могут быть найдены", e.getMessage());
        }
    }
}
