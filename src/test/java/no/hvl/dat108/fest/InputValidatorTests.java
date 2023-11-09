package no.hvl.dat108.fest;

import no.hvl.dat108.fest.Util.InputValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputValidatorTests {
	// TESTER NAVN

	// TESTER NAVN

	@ParameterizedTest
	@ValueSource(strings = {"Anne", "Åse", "Kjell-Åge",  "Anne Marie"}) // valid
	public void validateNavnValidPasses(String navn) {
		assertTrue(InputValidator.erGyldigNavn(navn));
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "A", "ThisNameIsWayTooLongToBeConsideredValid", "Name!", "123", "Invalid_Name"}) // invalid names
	public void validateNavnInvalidPasses(String navn) {
		assertFalse(InputValidator.erGyldigNavn(navn));
	}


	// TESTER PASSORD

	@ParameterizedTest
	@ValueSource(strings = {"ieurhuihuigprg", "hbhblhjkblh32", "passord123456", "erdetteetbrapassord?"})
	public void validatePassordValidPasses(String passord) {
		assertFalse(InputValidator.erGyldigPassord(passord));
	}

	@ParameterizedTest
	@ValueSource(strings = {"passord", " ", "pelle1", ""})
	public void validatePassordInvalidPasses(String passord) {
		assertFalse(InputValidator.erGyldigPassord(passord));
	}

	@Test
	public void validateReperertPasswordValidPasses() {
		// EksempelArray med gyldige
		String[] validPasswords = {
				"Passord12345", // valid
				"Blabla123A",   // valid
				"Secret1s",     // valid
				"GoodPw123",    // valid
		};

		for (String password : validPasswords)
			assertTrue(InputValidator.erGyldigPassord(password));
	}

	@Test
	public void validateRepetertPasswordInvalidFails() {
		// Example array of invalid passwords
		String[] invalidPasswords = {
				"short1",           // For kort
				"nouppercase123",   // Ingen store bokstaver
				"NOLOWERCASE123",   // Ingen smÃ¥ bokstaver
				"NoDigitsHere!",    // Ingen tall
				"12345678",         // Ingen bokstaver
				"abcdefgh",         // Ingen tall
		};

		for (String password : invalidPasswords)
			assertFalse(InputValidator.erGyldigPassord(password));
	}


	// TESTER MOBILNUMMER

	@ParameterizedTest
	@ValueSource(strings = {"12345678", "12938765", "00000001"})
	public void validateMobilValidPasses(String mobil) {
		assertTrue(InputValidator.erGyldigMobil(mobil));
	}

	@ParameterizedTest
	@ValueSource(strings = {"abcdefgh", "1293876S", "0000000|", "123456789", "1234567"})
	public void validateMobilInvalidPasses(String mobil) {

		assertFalse(InputValidator.erGyldigPassord(mobil));
	}

}


