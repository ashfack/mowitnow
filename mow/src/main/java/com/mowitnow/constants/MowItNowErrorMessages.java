package com.mowitnow.constants;

/**
 * @author Ashfack
 * 
 *         Contient les différents messages d'erreur centralisé
 *
 */
public class MowItNowErrorMessages {
	/* Erreur de paramètre de lancement */
	public static final String PARAM_LANCEMENT_KO = "Lancer avec le chemin (relatif ou absolu) vers le fichier d'instructions MowItNow";

	/* Erreurs pour les fichiers [MowItNowLectureFichierException] */
	public static final String FICHIER_VIDE = "Fichier vide";
	public static final String NB_LIGNES_INCORRECT = "Format incorrect : nombre de lignes pair";
	public static final String ERREUR_LECTURE_FICHIER = "Erreur lecture fichier ";

	/* Erreurs de format de ligne [MowItNowParserException] */
	public static final String FORMAT_PELOUSE_INCORRECT = "Format taille pelouse incorrect";
	public static final String FORMAT_TONDEUSE_INCORRECT = "Format ligne tondeuse incorrect (attendu: x y Orientation)";
	public static final String FORMAT_INSTRUCTIONS_INCORRECT = "Format ligne instruction incorrect (attendu GAD)";
	public static final String PROBLEME_PARSING_FICHIER = "Problème parsing fichier ";

	/* Erreurs d'accès à la pelouse [MowItNowAccesException] */
	public static final String INIT_TONDEUSE_HORS_ZONE = "Problème init tondeuse coordonnées hors zone";
	public static final String PELOUSE_ACCES_HORS_BORNE = "Accès hors bornes,";

	/* Erreurs liée à l'instance de pelouse [MowItNowWorkException] */
	public static final String TENTATIVE_NOUVELLE_PELOUSE = "Pelouse déjà initialisée";
	public static final String INSTRUCTIONS_NON_TERMINEES = "Tondeuse en cours d'exécution";

	/* Erreur principale */
	public static final String ECHEC_EXECUTION_POUR_MOTIF = "Echec exécution pour motif ";

}
