package com.mowitnow.operators;

import java.util.ArrayList;
import java.util.List;

import com.mowitnow.exceptions.MowItNowAccesException;
import com.mowitnow.exceptions.MowItNowLectureFichierException;
import com.mowitnow.exceptions.MowItNowParserException;
import com.mowitnow.exceptions.MowItNowWorkException;
import com.mowitnow.io.DataFileLoader;
import com.mowitnow.models.MowItNowInstruction;
import com.mowitnow.models.Pelouse;
import com.mowitnow.models.PositionTondeuse;
import com.mowitnow.models.Tondeuse;
import com.mowitnow.parsers.Parser;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Ashfack
 * 
 *         Traitement bout en bout d'un fichier d'instruction
 */
@Slf4j
public class MowItNowOperator {

	/**
	 * Procède au chargement du nom de fichier passsé en paramètre et initialise la
	 * pelouse et traite les tondeuses une par une
	 * 
	 * @param nomFichierAvecChemin
	 * @return List<PositionTondeuse> positionTondeuses : la liste des positions des
	 *         tondeuses
	 * @throws MowItNowLectureFichierException
	 * @throws MowItNowWorkException
	 * @throws MowItNowParserException
	 * @throws MowItNowAccesException
	 */
	public List<PositionTondeuse> execution(String nomFichierAvecChemin) throws MowItNowLectureFichierException,
			MowItNowWorkException, MowItNowParserException, MowItNowAccesException {
		List<PositionTondeuse> positionTondeuses = new ArrayList<>();

		/* Chargement fichier */
		List<String> data = DataFileLoader.load(nomFichierAvecChemin);

		/* Instance de pelouse partagée */
		Pelouse pelouse = Pelouse.getInstance();

		initPelouse(data, pelouse);
		/* Gestion des lignes du fichier deux par deux */
		int nbTondeuses = (data.size() - 1) / 2;
		for (int numTondeuse = 0; numTondeuse < nbTondeuses; numTondeuse++) {
			Tondeuse tondeuse = initTondeuse(data, pelouse, numTondeuse);
			log.info("Position initiale ({}, {}, {})", tondeuse.getX(), tondeuse.getY(),
					tondeuse.getOrientationTondeuse());
			positionTondeuses.add(execInstructions(data, pelouse, numTondeuse));
			log.info("Position finale ({}, {}, {})", tondeuse.getX(), tondeuse.getY(),
					tondeuse.getOrientationTondeuse());
		}
		return positionTondeuses;
	}

	/**
	 * @param data    : liste des lignes
	 * @param pelouse
	 * @throws MowItNowWorkException   : peut échouer si on a problème de parsing ou
	 *                                 si la pelouse a déjà été initialisée
	 * @throws MowItNowParserException : si la ligne ne contient pas 2 chiffres
	 *                                 séparés par un espace
	 */
	private static void initPelouse(List<String> data, Pelouse pelouse)
			throws MowItNowWorkException, MowItNowParserException {
		/* Parsing première ligne et init pelouse */
		int xCoinDroitSuperieur = Parser.xCoinDroitSuperieurParser(data.get(0));
		int yCoinDroitSuperieur = Parser.yCoinDroitSuperieurParser(data.get(0));

		pelouse.initPelouse(xCoinDroitSuperieur, yCoinDroitSuperieur);
	}

	private Tondeuse initTondeuse(List<String> data, Pelouse pelouse, int numTondeuse)
			throws MowItNowWorkException, MowItNowParserException, MowItNowAccesException {
		/* Parsing lignes de tondeuse et init tondeuse */
		Tondeuse tondeuse = Parser.tondeuseParser(data.get(numTondeuse * 2 + 1), pelouse.getDim1(), pelouse.getDim2());
		pelouse.initTondeuse(tondeuse);
		return tondeuse;
	}

	private PositionTondeuse execInstructions(List<String> data, Pelouse pelouse, int numTondeuse)
			throws MowItNowAccesException, MowItNowParserException {

		/* Parsing lignes d'instructions et execution */
		String ligneInstructions = data.get((numTondeuse + 1) * 2);
		List<MowItNowInstruction> instructions = Parser.instructionsParser(ligneInstructions);
		PositionTondeuse positionTondeuse = pelouse.execInstructions(instructions);

		pelouse.setTondeuseEnCours(false);

		return positionTondeuse;
	}

}
