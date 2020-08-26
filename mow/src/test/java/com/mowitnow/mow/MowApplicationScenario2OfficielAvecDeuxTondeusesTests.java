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

class MowApplicationScenario2OfficielAvecDeuxTondeusesTests {

	@Test
	void scenario2() throws MowItNowLectureFichierException, MowItNowWorkException, MowItNowParserException,
			MowItNowAccesException {
		// Cas avec 2 tondeuses, on check uniquement les positions finales et le
		// nombre de résultats
		String scenario2 = ".\\src\\main\\resources\\scenario2.txt";
		MowItNowOperator operator = new MowItNowOperator();
		List<PositionTondeuse> positionTondeuses = operator.execution(scenario2);
		assertEquals(2, positionTondeuses.size());
		PositionTondeuse expectedPosition1 = new PositionTondeuse(1, 3, Orientation.N, 6, 6);
		assertEquals(expectedPosition1, positionTondeuses.get(0));
		PositionTondeuse expectedPosition2 = new PositionTondeuse(5, 1, Orientation.E, 6, 6);
		assertEquals(expectedPosition2, positionTondeuses.get(1));
	}
}
