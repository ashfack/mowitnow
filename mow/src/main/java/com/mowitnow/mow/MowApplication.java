package com.mowitnow.mow;

import com.mowitnow.constants.MowItNowErrorMessages;
import com.mowitnow.exceptions.MowItNowAccesException;
import com.mowitnow.exceptions.MowItNowLectureFichierException;
import com.mowitnow.exceptions.MowItNowParserException;
import com.mowitnow.exceptions.MowItNowWorkException;
import com.mowitnow.operators.MowItNowOperator;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Ashfack
 * 
 *         Application principale, lancer avec en paramètre de JVM le chemin
 *         vers le fichier d'instructions MowItNow
 */
@Slf4j
public class MowApplication {

	public static void main(String[] args) {
		if (args.length != 1) {
			log.warn(MowItNowErrorMessages.PARAM_LANCEMENT_KO);
			throw new IllegalArgumentException();
		} else {
			try {
				MowItNowOperator operator = new MowItNowOperator();
				operator.execution(args[0]);
			} catch (MowItNowLectureFichierException | MowItNowWorkException | MowItNowParserException
					| MowItNowAccesException e) {
				log.error(MowItNowErrorMessages.ECHEC_EXECUTION_POUR_MOTIF + e.getMessage());
			}
		}
	}

}
