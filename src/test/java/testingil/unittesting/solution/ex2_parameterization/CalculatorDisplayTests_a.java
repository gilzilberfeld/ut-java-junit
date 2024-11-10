package testingil.unittesting.solution.ex2_parameterization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

//  1. Add parameterized tests
public class CalculatorDisplayTests_a {

	private CalculatorDisplay cd;

	@BeforeEach
	public void setup(){
		cd = new CalculatorDisplay();
	}

	@Test
	public void at_start_display_0() {
		assertThat(cd.getDisplay()).isEqualTo("0");
	}

	@ParameterizedTest
	@CsvSource({
			"1,1",
			"12,12"
	})
	public void pressing_keys_and_displays_csv(String keys, String result) throws Exception{
		pressSequence(keys);
		assertThat(cd.getDisplay()).isEqualTo(result);
	}


	@ParameterizedTest
	@MethodSource ("press_displays")
	public void pressing_keys_and_displays(String keys, String result) throws Exception{
		pressSequence(keys);
		assertThat(cd.getDisplay()).isEqualTo(result);
	}

	static Stream<Arguments> press_displays(){
		return Stream.of(
				arguments("1", "1"),
				arguments("12", "12"));
	};


	private void pressSequence(String sequence) throws Exception {
		sequence.chars().mapToObj(i -> (char) i).forEach(c -> {
				cd.press(c.toString());
		});
	}
}
