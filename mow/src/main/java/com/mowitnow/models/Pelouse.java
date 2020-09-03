package com.mowitnow.models;

import java.util.List;

import com.mowitnow.constants.MowItNowErrorMessages;
import com.mowitnow.constants.Symbols;
import com.mowitnow.exceptions.MowItNowAccesException;
import com.mowitnow.exceptions.MowItNowWorkException;
import com.mowitnow.utils.MowItNowUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Ashfack
 * 
 *         Pelouse principale qui peut piloter une tondeuse
 */
@Slf4j
public class Pelouse {

	/**
	 * La liste des cellules qui doivent par défaut être avec HERBE, pas de getter
	 * pour éviter une modification externe
	 * 
	 * #RG 001
	 */
	private Cellule[][] cellules;

	/**
	 * première dimension de la pelouse
	 * 
	 * ex: si taille 2*4 alors
	 * 
	 * <pre>
	 * xx 
	 * xx 
	 * xx 
	 * xx
	 * </pre>
	 */
	@Getter
	private int dim1;

	/**
	 * deuxième dimension de la pelouse
	 * 
	 * ex: si taille 1*4 alors
	 * 
	 * <pre>
	 * xxxx
	 * </pre>
	 */
	@Getter
	private int dim2;

	/**
	 * Tondeuse courante
	 */
	private Tondeuse tondeuse;

	/**
	 * Si la pelouse a été initialisée avec des dimensions
	 */
	private boolean isInitialisee = false;

	/**
	 * Constructeur privé pour faire un Singleton
	 */
	private Pelouse() {
	};

	private static class PelouseHolder {
		private static final Pelouse INSTANCE = new Pelouse();
	}

	public static Pelouse getInstance() {
		return PelouseHolder.INSTANCE;
	}

	@Setter
	private boolean isTondeuseEnCours = false;

	/**
	 * @param xCoinDroitSuperieur : abscisse du coinDroitSupérieur
	 * @param yCoinDroitSuperieur : ordonnée du coinDroitSupérieur
	 * @throws MowItNowWorkException : si on veut initialiser une pelouse déjà
	 *                               initialisee
	 */
	public void initPelouse(int xCoinDroitSuperieur, int yCoinDroitSuperieur) throws MowItNowWorkException {
		if (isInitialisee) {
			throw new MowItNowWorkException(MowItNowErrorMessages.TENTATIVE_NOUVELLE_PELOUSE);
		}
		this.dim1 = xCoinDroitSuperieur + 1;
		this.dim2 = yCoinDroitSuperieur + 1;
		this.cellules = new Cellule[dim1][dim2];
		for (int i = 0; i < dim1; i++) {
			for (int j = 0; j < dim2; j++) {
				this.cellules[i][j] = Cellule.HERBE;
			}
		}
		isInitialisee = true;
	}

	/**
	 * @param tondeuse
	 * @throws MowItNowWorkException  : si une tondeuse est déjà en cours
	 *                                d'exécution
	 * @throws MowItNowAccesException : si on cherche à initialiser la tondeuse en
	 *                                dehors de la pelouse
	 */
	public void initTondeuse(Tondeuse tondeuse) throws MowItNowWorkException, MowItNowAccesException {
		if (null != this.tondeuse) {
			this.cellules[this.tondeuse.getX()][this.tondeuse.getY()] = Cellule.TONDEUSE;
		}
		// #RG 014
		if (isTondeuseEnCours) {
			throw new MowItNowWorkException(MowItNowErrorMessages.INSTRUCTIONS_NON_TERMINEES);
		}
		this.tondeuse = tondeuse;
		try {
			this.cellules[tondeuse.getX()][tondeuse.getY()] = Cellule.TONDEUSE;
			this.isTondeuseEnCours = true;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new MowItNowAccesException(MowItNowErrorMessages.INIT_TONDEUSE_HORS_ZONE);
		}
	}

	/**
	 * @throws MowItNowAccesException : si on cherche à accéder à une mauvaise
	 *                                case
	 */
	public void draw() throws MowItNowAccesException {
		this.draw(false);
	}

	/**
	 * @param isCoordonneesAffichees : pour voir les coordonnées entre parenthèses
	 * @throws MowItNowAccesException : si on cherche à accéder à une mauvaise
	 *                                case
	 */
	public void draw(boolean isCoordonneesAffichees) throws MowItNowAccesException {
		// #RG 005, #RG 009
		try {
			StringBuilder sb = new StringBuilder("\n");
			for (int j = dim2 - 1; j >= 0; j--) {
				for (int i = 0; i < dim1; i++) {
					if (this.cellules[i][j].equals(Cellule.TONDEUSE)) {
						if (tondeuse != null && tondeuse.getX() == i && tondeuse.getY() == j) {
							sb.append(celluleFormatter(tondeuse.getOrientationTondeuse()));
						} else {
							sb.append(celluleFormatter(Cellule.TONDEUSE));
						}
					} else {
						sb.append(celluleFormatter(this.cellules[i][j]));
					}
					if (isCoordonneesAffichees) {
						sb.append(" (");
						sb.append(i);
						sb.append(", ");
						sb.append(j);
						sb.append(") ");
					} else {
						sb.append(Symbols.SPACE);
					}
				}
				sb.append(Symbols.NEWLINE);
			}
			log.debug(sb.toString());
		} catch (ArrayIndexOutOfBoundsException exception) {
			throw new MowItNowAccesException(MowItNowErrorMessages.PELOUSE_ACCES_HORS_BORNE + " dimensions réelles "
					+ dim1 + Symbols.SPACE + dim2 + " [" + exception.getMessage() + "]");
		}

	}

	/**
	 * Au sein d'une pelouse, on a une cellule de type Cellule, mais quand on veut
	 * dessiner/afficher le contenu d'une cellule avec tondeuse alors on a besoin de
	 * l'orientation. D'où la méthode qui gère l'orientation si tondeuse et on
	 * veut que chaque "cellule" soit de même taille à l'affichage.
	 * 
	 * @param o : Orientation ou Cellule
	 * @return Le contenu de la cellule à un format exploitable
	 */
	private String celluleFormatter(Object o) {
		if (o instanceof Cellule) {
			Cellule c = (Cellule) o;
			switch (c) {
			case HERBE:
				return MowItNowUtils.completerAvecEspacesPourTotalLongueur8("HERBE");
			case TONDEUSE:
				return MowItNowUtils.completerAvecEspacesPourTotalLongueur8("TONDEUSE");
			case TONDUE:
				return MowItNowUtils.completerAvecEspacesPourTotalLongueur8("TONDUE");
			default:
				return MowItNowUtils.completerAvecEspacesPourTotalLongueur8("INCONNU");
			}
		} else {
			return MowItNowUtils.completerAvecEspacesPourTotalLongueur8(o.toString());
		}
	}

	/**
	 * Commande à la tondeuse d'effectuer une rotation dans la direction voulue
	 * 
	 * @param direction
	 */
	private void rotation(MowItNowInstruction direction) {
		this.tondeuse.rotation(direction);

	}

	/**
	 * @return true si le déplacement est possible et effectué d'un point de vue
	 *         pelouse et tondeuse
	 */
	private boolean avancer() {
		// Si contrôle OK, il faut passer la case courante à TONDUE, et mettre la
		// nouvelle case à TONDEUSE
		switch (this.tondeuse.getOrientationTondeuse()) {
		case N:
			if (this.tondeuse.getY() + 1 < dim2) {
				if (Cellule.TONDEUSE == this.cellules[tondeuse.getX()][tondeuse.getY() + 1]) {
					log.warn("Case occupée");
					return false;
				} else {
				this.cellules[tondeuse.getX()][tondeuse.getY()] = Cellule.TONDUE;
				this.cellules[tondeuse.getX()][tondeuse.getY() + 1] = Cellule.TONDEUSE;
				}
			} else {
				// #RG 008
				log.warn(Symbols.DEPLACEMENT_IMPOSSIBLE);
				return false;
			}
			break;
		case E:
			if (this.tondeuse.getX() + 1 < dim1) {
				this.cellules[tondeuse.getX()][tondeuse.getY()] = Cellule.TONDUE;
				this.cellules[tondeuse.getX() + 1][tondeuse.getY()] = Cellule.TONDEUSE;
			} else {
				// #RG 008
				log.warn(Symbols.DEPLACEMENT_IMPOSSIBLE);
				return false;
			}
			break;
		case S:
			if (this.tondeuse.getY() > 0) {
				this.cellules[tondeuse.getX()][tondeuse.getY()] = Cellule.TONDUE;
				this.cellules[tondeuse.getX()][tondeuse.getY() - 1] = Cellule.TONDEUSE;
			} else {
				// #RG 008
				log.warn(Symbols.DEPLACEMENT_IMPOSSIBLE);
				return false;
			}
			break;
		case W:
			if (this.tondeuse.getX() > 0) {
				this.cellules[tondeuse.getX()][tondeuse.getY()] = Cellule.TONDUE;
				this.cellules[tondeuse.getX() - 1][tondeuse.getY()] = Cellule.TONDEUSE;
			} else {
				// #RG 008
				log.warn(Symbols.DEPLACEMENT_IMPOSSIBLE);
				return false;
			}
			break;
		}
		return this.tondeuse.avancer();
	}

	/**
	 * @param instruction
	 * @return le statut de l'éxécution (peut être à false si déplacement hors
	 *         zone)
	 */
	private boolean execInstruction(MowItNowInstruction instruction) {
		if (instruction != MowItNowInstruction.A) {
			this.rotation(instruction);
			return true;
		} else {
			return this.avancer();
		}
	}

	/**
	 * #RG 015
	 * 
	 * Après chaque instruction on dessine l'état du plateau
	 * 
	 * @param instructions
	 * @return PositionTondeuse à la fin de l'exécution
	 */
	public PositionTondeuse execInstructions(List<MowItNowInstruction> instructions) {
		instructions.forEach(instruction -> {
			execInstruction(instruction);
			try {
				this.draw();
			} catch (MowItNowAccesException e) {
				log.error(MowItNowErrorMessages.PELOUSE_ACCES_HORS_BORNE);
			}
		});
		return new PositionTondeuse(tondeuse.getX(), tondeuse.getY(), tondeuse.getOrientationTondeuse(),
				tondeuse.getDim1(), tondeuse.getDim2());
	}

	/**
	 * Pour résoudre le problème du singleton avec les tests unitaires
	 */
	public void endPelouse() {
		this.isInitialisee = false;
		this.isTondeuseEnCours = false;
		this.tondeuse = null;
	}
}
