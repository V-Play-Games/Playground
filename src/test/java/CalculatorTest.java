import net.vpg.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class CalculatorTest {
    @Test
    void test() {
        Calculator calc = new Calculator();
        Map.of(
            "2^2^3", 256.0,
            "20+2^(2-2)", 21.0,
            "2+2^(2-2)+2", 5.0,
            "(2+2^(2-2)+2+2^(2-2))*2", 12.0,
            "(2+2^(2-2)+2+2^(2-2))/2", 3.0,
            "pi", 3.14159265358979323846,
            "e", 2.718281828459045,
            "sin(pi/4)^2 + cos(pi/4)^2", 1.0,
            "log10", 2.302585092994046
        ).forEach((input, expected) -> Assertions.assertEquals(expected, calc.evaluate(input)));
    }
}
