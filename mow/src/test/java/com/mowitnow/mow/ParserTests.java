package com.mowitnow.mow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.mowitnow.constants.MowItNowErrorMessages;
import com.mowitnow.exceptions.MowItNowParserException;
import com.mowitnow.parsers.Parser;

class ParserTests {

	@Test
	void isValidTondeuseLineOk() {
		assertTrue(Parser.isTondeuseLigne("3 3 S"));
	}

	@Test
	void isValidTondeuseLineKo() {
		assertFalse(Parser.isTondeuseLigne("33S"));
		assertFalse(Parser.isTondeuseLigne("S 3 3 S"));
		assertFalse(Parser.isTondeuseLigne("3 3 S S"));
	}

	@Test
	void tondeuseParserRaisesException() {
		MowItNowParserException exception = assertThrows(MowItNowParserException.class, () -> {
			Parser.tondeuseParser("S 3 3 S", 5, 5);
		});
		assertEquals(exception.getMessage(), MowItNowErrorMessages.FORMAT_TONDEUSE_INCORRECT);
	}

	@Test
	void isValidInstructionsLineOk() {
		assertTrue(Parser.isInstructionsLigne("AAD"));
	}

	@Test
	void isValidInstructionsLineKo() {
		assertFalse(Parser.isInstructionsLigne(("G A D")));
		assertFalse(Parser.isInstructionsLigne("GDDE"));
		assertFalse(Parser.isInstructionsLigne(""));
	}

	@Test
	void instructionsParserRaisesException() {
		MowItNowParserException exception = assertThrows(MowItNowParserException.class, () -> {
			Parser.instructionsParser("G A D");
		});
		assertEquals(exception.getMessage(), MowItNowErrorMessages.FORMAT_INSTRUCTIONS_INCORRECT);

		exception = assertThrows(MowItNowParserException.class, () -> {
			Parser.instructionsParser("GDDE");
		});
		assertEquals(exception.getMessage(), MowItNowErrorMessages.FORMAT_INSTRUCTIONS_INCORRECT);
	}

}
