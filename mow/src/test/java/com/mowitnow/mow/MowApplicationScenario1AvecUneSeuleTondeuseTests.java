package com.mowitnow.mow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.mowitnow.exceptions.MowItNowAccesException;
import com.mowitnow.exceptions.MowItNowLectureFichierException;
import com.mowitnow.exceptions.MowItNowParserException;
import com.mowitnow.exceptions.MowItNowWorkException;
import com.mowitnow.models.Orientation;
import com.mowitnow.models.PositionTondeuse;
import com.mowitnow.operators.MowItNowOperator;

class MowApplicationScenario1AvecUneSeuleTondeuseTests {

	@Test
	void scenario1() throws MowItNowLectureFichierException, MowItNowWorkException, MowItNowParserException,
			MowItNowAccesException {
		// Cas avec 1 seule tondeuse, on check uniquement la position finale et le
		// nombre de résultats

		String scenario1 = ".\\src\\main\\resources\\scenario1.txt";
		MowItNowOperator operator = new MowItNowOperator();
		List<PositionTondeuse> positionTondeuses = operator.execution(scenario1);
		assertEquals(1, positionTondeuses.size());
		// #RG 015
		PositionTondeuse expected = new PositionTondeuse(1, 3, Orientation.N, 6, 6);
		assertEquals(expected, positionTondeuses.get(0));
	}
}
