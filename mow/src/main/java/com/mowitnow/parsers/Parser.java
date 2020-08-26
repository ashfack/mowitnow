package com.mowitnow.parsers;

import java.util.ArrayList;
import java.util.List;

import com.mowitnow.constants.MowItNowErrorMessages;
import com.mowitnow.exceptions.MowItNowParserException;
import com.mowitnow.models.MowItNowInstruction;
import com.mowitnow.models.Orientation;
import com.mowitnow.models.Tondeuse;

public class Parser {

	/**
	 * @param ligne
	 * @return xCoinDroitSuperieur de la pelouse
	 * @throws MowItNowParserException : si la ligne ne contient pas 2 chiffres
	 *                                 séparés par un espace
	 */
	public static int xCoinDroitSuperieurParser(String ligne) throws MowItNowParserException {

		String[] coordonneesString = ligne.split(" ");
		if (2 != ligne.split(" ").length) {
			throw new MowItNowParserException(MowItNowErrorMessages.FORMAT_PELOUSE_INCORRECT);
		}
		try {
			int xCoinDroitSuperieur = Integer.parseInt(coordonneesString[0]);
			return xCoinDroitSuperieur;
		} catch (NumberFormatException e) {
			throw new MowItNowParserException(MowItNowErrorMessages.FORMAT_PELOUSE_INCORRECT);
		}
	}

	/**
	 * @param ligne
	 * @return yCoinDroitSuperieur de la pelouse
	 * @throws MowItNowParserException : si la ligne ne contient pas 2 chiffres
	 *                                 séparés par un espace
	 */
	public static int yCoinDroitSuperieurParser(String ligne) throws MowItNowParserException {
		String[] coordonneesString = ligne.split(" ");
		if (2 != ligne.split(" ").length) {
			throw new MowItNowParserException(MowItNowErrorMessages.FORMAT_PELOUSE_INCORRECT);
		}
		try {
			int yCoinDroitSuperieur = Integer.parseInt(coordonneesString[1]);
			return yCoinDroitSuperieur;
		} catch (NumberFormatException e) {
			throw new MowItNowParserException(MowItNowErrorMessages.FORMAT_PELOUSE_INCORRECT);
		}
	}

	/**
	 * @param ligne
	 * @return true si le format de la ligne (nombre nombre orientation)
	 */
	public static boolean isTondeuseLigne(String ligne) {
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append(Orientation.N).append("|").append(Orientation.S).append("|").append(Orientation.E)
				.append("|").append(Orientation.W);
		return ligne.matches("(\\d+) (\\d+) (" + stringBuilder.toString() + ")");
	}

	/**
	 * @param ligne de données
	 * @param dim1  (de la pelouse)
	 * @param dim2  (de la pelouse)
	 * @return la tondeuse initialisée
	 * @throws MowItNowParserException : si on veut initialiser la tondeuse en
	 *                                 dehors de la pelouse
	 */
	public static Tondeuse tondeuseParser(String ligne, int dim1, int dim2) throws MowItNowParserException {
		if (isTondeuseLigne(ligne)) {
			String[] coordonneesString = ligne.split(" ");
			int x = Integer.parseInt(coordonneesString[0]);
			int y = Integer.parseInt(coordonneesString[1]);
			if (x < 0 || y < 0 || x >= dim1 || y >= dim2) {
				throw new MowItNowParserException(MowItNowErrorMessages.INIT_TONDEUSE_HORS_ZONE);
			}
			Orientation orientation = Enum.valueOf(Orientation.class, coordonneesString[2]);
			return new Tondeuse(x, y, orientation, dim1, dim2);
		} else {
			throw new MowItNowParserException(MowItNowErrorMessages.FORMAT_TONDEUSE_INCORRECT);
		}
	}

	/**
	 * @param ligne
	 * @return true si la ligne ne contient que des MowItNowInstruction valide
	 */
	public static boolean isInstructionsLigne(String ligne) {
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append("(").append(MowItNowInstruction.A).append("|").append(MowItNowInstruction.D).append("|")
				.append(MowItNowInstruction.G).append(")+");
		return ligne.matches(stringBuilder.toString());
	}

	/**
	 * @param ligne
	 * @return
	 * @throws MowItNowParserException : si le moindre caractère n'est pas une
	 *                                 MowItNowInstruction valide
	 */
	public static List<MowItNowInstruction> instructionsParser(String ligne) throws MowItNowParserException {
		List<MowItNowInstruction> instructions = new ArrayList<>();

		if (isInstructionsLigne(ligne)) {
			String[] instructionsStrArray = ligne.split("");
			for (String instructionStr : instructionsStrArray) {
				instructions.add(Enum.valueOf(MowItNowInstruction.class, instructionStr));
			}
			return instructions;

		} else {
			throw new MowItNowParserException(MowItNowErrorMessages.FORMAT_INSTRUCTIONS_INCORRECT);
		}
	}
}
