package com.mowitnow.mow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.mowitnow.models.MowItNowInstruction;
import com.mowitnow.models.Orientation;
import com.mowitnow.models.Tondeuse;

class TondeuseTests {

	// Rappel si dim1 = z alors xCoinDroitSuperieur = z-1
	// dim1 et dim2 ne sont pas les coordonnées du coin supérieur

	private boolean comparePositionTondeuse(Tondeuse tondeuse, int x, int y) {
		return tondeuse.getX() == x && tondeuse.getY() == y;
	}

	@Test
	void rotationsDroite() {
		int dim1 = 5;
		int dim2 = 5;
		int xInitial = 3;
		int yInitial = 3;
		Tondeuse tondeuse = new Tondeuse(xInitial, yInitial, Orientation.N, dim1, dim2);
		/*
		 * On effectue 4 rotations droite et la position doit rester inchangée à chaque
		 * étape, on doit à la fin revenir à l'orientation initiale
		 */

		tondeuse.rotation(MowItNowInstruction.D);
		assertEquals(Orientation.E, tondeuse.getOrientationTondeuse());
		assertTrue(comparePositionTondeuse(tondeuse, xInitial, yInitial));

		tondeuse.rotation(MowItNowInstruction.D);
		assertEquals(Orientation.S, tondeuse.getOrientationTondeuse());
		assertTrue(comparePositionTondeuse(tondeuse, xInitial, yInitial));

		tondeuse.rotation(MowItNowInstruction.D);
		assertEquals(Orientation.W, tondeuse.getOrientationTondeuse());
		assertTrue(comparePositionTondeuse(tondeuse, xInitial, yInitial));

		tondeuse.rotation(MowItNowInstruction.D);
		assertEquals(Orientation.N, tondeuse.getOrientationTondeuse());
		assertTrue(comparePositionTondeuse(tondeuse, xInitial, yInitial));
	}

	@Test
	void rotationsGauche() {
		int dim1 = 5;
		int dim2 = 5;

		int xInitial = 3;
		int yInitial = 3;
		Tondeuse tondeuse = new Tondeuse(xInitial, yInitial, Orientation.N, dim1, dim2);
		/*
		 * On effectue 4 rotations gauche et la position doit rester inchangée à chaque
		 * étape, on doit à la fin revenir à l'orientation initiale
		 */

		tondeuse.rotation(MowItNowInstruction.G);
		assertEquals(Orientation.W, tondeuse.getOrientationTondeuse());
		assertTrue(comparePositionTondeuse(tondeuse, xInitial, yInitial));

		tondeuse.rotation(MowItNowInstruction.G);
		assertEquals(Orientation.S, tondeuse.getOrientationTondeuse());
		assertTrue(comparePositionTondeuse(tondeuse, xInitial, yInitial));

		tondeuse.rotation(MowItNowInstruction.G);
		assertEquals(Orientation.E, tondeuse.getOrientationTondeuse());
		assertTrue(comparePositionTondeuse(tondeuse, xInitial, yInitial));

		tondeuse.rotation(MowItNowInstruction.G);
		assertEquals(Orientation.N, tondeuse.getOrientationTondeuse());
		assertTrue(comparePositionTondeuse(tondeuse, xInitial, yInitial));
	}

	// Vis à vis de la tondeuse, elle n'a pas connaissance directe de la pelouse
	// mais uniquement ses dimensions
	@Test
	void avancerOk() {

		/* Etat initial */
		final int dim1 = 5;
		final int dim2 = 5;
		final int xInitial = 3;
		final int yInitial = 3;

		/* Test1 : North */
		Tondeuse tondeuse = new Tondeuse(xInitial, yInitial, Orientation.N, dim1, dim2);

		boolean statutAvancement = tondeuse.avancer();
		assertTrue(statutAvancement);

		int xExpected = 3;
		int yExpected = 4;
		// #RG 009
		assertTrue(comparePositionTondeuse(tondeuse, xExpected, yExpected));

		/* Test2 : East */
		tondeuse = new Tondeuse(xInitial, yInitial, Orientation.E, dim1, dim2);

		statutAvancement = tondeuse.avancer();
		assertTrue(statutAvancement);

		xExpected = 4;
		yExpected = 3;
		assertTrue(comparePositionTondeuse(tondeuse, xExpected, yExpected));

		/* Test3 : West */
		tondeuse = new Tondeuse(xInitial, yInitial, Orientation.W, dim1, dim2);

		statutAvancement = tondeuse.avancer();
		assertTrue(statutAvancement);

		xExpected = 2;
		yExpected = 3;
		assertTrue(comparePositionTondeuse(tondeuse, xExpected, yExpected));

		/* Test4 : South */
		tondeuse = new Tondeuse(xInitial, yInitial, Orientation.S, dim1, dim2);

		statutAvancement = tondeuse.avancer();
		assertTrue(statutAvancement);

		xExpected = 3;
		yExpected = 2;
		assertTrue(comparePositionTondeuse(tondeuse, xExpected, yExpected));

	}

	@Test
	void avancerKo() {
		// #RG 008

		/* Etat initial */
		final int dim1 = 3;
		final int dim2 = 3;
		final int xInitial = 3;
		final int yInitial = 3;

		Tondeuse tondeuse = new Tondeuse(xInitial, yInitial, Orientation.N, dim1, dim2);

		/* On cherche à sortir de la pelouse vers le haut */
		boolean statutAvancement = tondeuse.avancer();
		assertFalse(statutAvancement);

		int xExpected = 3;
		int yExpected = 3;
		/* on s'attend à ce que la position n'ait pas changée */
		assertTrue(comparePositionTondeuse(tondeuse, xExpected, yExpected));
	}

}
