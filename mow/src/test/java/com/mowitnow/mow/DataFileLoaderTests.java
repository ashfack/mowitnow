package com.mowitnow.mow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.mowitnow.constants.MowItNowErrorMessages;
import com.mowitnow.exceptions.MowItNowLectureFichierException;
import com.mowitnow.io.DataFileLoader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataFileLoaderTests {

	@Test
	void lectureFichierCheminAbsolu() {
		log.info("Chargement fichier chemin absolu");
		List<String> instructions;
		try {
			instructions = DataFileLoader
					.load("C:\\Users\\Ashfack\\Documents\\github\\mowitnow\\mow\\src\\main\\resources\\xebia.txt");
			assertEquals(5, instructions.size());
		} catch (MowItNowLectureFichierException e) {
			log.error(e.getMessage());
		}

	}

	@Test
	void lectureFichierCheminRelatif() throws MowItNowLectureFichierException {
		log.info("Chargement fichier chemin relatif");
		List<String> instructions = DataFileLoader.load(".\\src\\main\\resources\\xebia.txt");
		assertEquals(5, instructions.size());

	}

	@Test
	void lectureFichierVide() {
		log.info("Chargement fichier vide");
		MowItNowLectureFichierException exception = assertThrows(MowItNowLectureFichierException.class, () -> {
			DataFileLoader.load(".\\src\\main\\resources\\xebiaFichierVide.txt");
		});
		assertEquals(exception.getMessage(), MowItNowErrorMessages.FICHIER_VIDE);
	}

	@Test
	void lectureFichierMalFormatteNbLignesPair() {
		log.info("Chargement fichier avec nb lignes pair");
		MowItNowLectureFichierException exception = assertThrows(MowItNowLectureFichierException.class, () -> {
			DataFileLoader.load(".\\src\\main\\resources\\xebiaFichierMalFormatteNbLignesPair.txt");
		});
		assertEquals(exception.getMessage(), MowItNowErrorMessages.NB_LIGNES_INCORRECT);
	}

	@Test
	void lectureFichierInexistant() {
		log.info("Chargement fichier inexistant");
		MowItNowLectureFichierException exception = assertThrows(MowItNowLectureFichierException.class, () -> {
			DataFileLoader.load(".\\src\\main\\resources\\inexistant.test");
		});
		assertThat(exception.getMessage()).contains(MowItNowErrorMessages.ERREUR_LECTURE_FICHIER);
	}

}
