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

class MowApplicationScenario3CheckNonSortiePelouseTests {

	@Test
	void scenario3() throws MowItNowLectureFichierException, MowItNowWorkException, MowItNowParserException,
			MowItNowAccesException {
		// On a une seule, tondeuse, le coin supérieur droit est 3,3 (donc pelouse
		// taille 4,4)
		// On commence avec la tondeuse en (0,0, E)
		// On avance tout droit pendant 7 fois (donc on veut vérifier qu'on ne sort pas
		// du plateau
		// on faite ensuite une rotation gauche pour aller vers le Nord et on avance
		// La position attendue est (3, 1, N)

		String scenario3 = ".\\src\\main\\resources\\scenario3.txt";
		MowItNowOperator operator = new MowItNowOperator();
		List<PositionTondeuse> positionTondeuses = operator.execution(scenario3);
		assertEquals(1, positionTondeuses.size());
		PositionTondeuse expectedPosition1 = new PositionTondeuse(3, 1, Orientation.N, 4, 4);
		assertEquals(expectedPosition1, positionTondeuses.get(0));
	}
}
