package com.mowitnow.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.mowitnow.constants.MowItNowErrorMessages;
import com.mowitnow.exceptions.MowItNowLectureFichierException;

/**
 * @author Ashfack
 * 
 *         Gère le chargement de fichier et effectue quelques contrôles de
 *         surface
 *
 */
public class DataFileLoader {

	/**
	 * @param nomFichierAvecChemin
	 * @return List<String> : le contenu du fichier d'instructions
	 * @throws MowItNowLectureFichierException : si fichier vide ou nb ligne
	 *                                         incorrect ou pas de fichier
	 */
	public static List<String> load(String nomFichierAvecChemin) throws MowItNowLectureFichierException {
		try {
			FileReader fileReader = new FileReader(nomFichierAvecChemin);

			BufferedReader br = new BufferedReader(fileReader);
			List<String> result = br.lines().collect(Collectors.toList());
			if (result.isEmpty()) {
				String msg = MowItNowErrorMessages.FICHIER_VIDE;
				br.close();
				throw new MowItNowLectureFichierException(msg);
			}
			if (result.size() % 2 == 0) {
				String msg = MowItNowErrorMessages.NB_LIGNES_INCORRECT;
				br.close();
				throw new MowItNowLectureFichierException(msg);
			}
			br.close();
			fileReader.close();
			return result;
		} catch (IOException e) {
			String msg = MowItNowErrorMessages.ERREUR_LECTURE_FICHIER + e.getMessage();
			throw new MowItNowLectureFichierException(msg);
		}

	}
}
