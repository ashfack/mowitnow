package com.mowitnow.models;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Tondeuse extends PositionTondeuse {
	private static final String DEPLACEMENT_IMPOSSIBLE = "Déplacement impossible";

	public Tondeuse(int x, int y, Orientation orientationTondeuse, int dim1, int dim2) {
		super(x, y, orientationTondeuse, dim1, dim2);
	}

	/**
	 * Effectue la rotation en changeant l'orientation dans la direction indiquée
	 * 
	 * @param direction
	 */
	public void rotation(MowItNowInstruction direction) {
		if (direction.equals(MowItNowInstruction.D)) {
			rotationDroite();
		} else if (direction.equals(MowItNowInstruction.G)) {
			rotationGauche();
		}
	}

	private void rotationGauche() {
		switch (orientationTondeuse) {
		case N:
			orientationTondeuse = Orientation.W;
			break;
		case E:
			orientationTondeuse = Orientation.N;
			break;
		case S:
			orientationTondeuse = Orientation.E;
			break;
		case W:
			orientationTondeuse = Orientation.S;
			break;
		}
	}

	private void rotationDroite() {
		switch (orientationTondeuse) {
		case N:
			orientationTondeuse = Orientation.E;
			break;
		case E:
			orientationTondeuse = Orientation.S;
			break;
		case S:
			orientationTondeuse = Orientation.W;
			break;
		case W:
			orientationTondeuse = Orientation.N;
			break;
		}
	}

	/**
	 * @return <b>true</b> s'il est possible d'avancer dans la direction courante
	 */
	public boolean avancer() {
		switch (orientationTondeuse) {
		case N:
			if (y + 1 < dim2) {
				y = y + 1;
			} else {
				// #RG 008
				log.warn(DEPLACEMENT_IMPOSSIBLE);
				return false;
			}
			break;
		case E:
			if (x + 1 < dim1) {
				x = x + 1;
			} else {
				// #RG 008
				log.warn(DEPLACEMENT_IMPOSSIBLE);
				return false;
			}
			break;
		case S:
			if (y > 0) {
				y = y - 1;
			} else {
				// #RG 008
				log.warn(DEPLACEMENT_IMPOSSIBLE);
				return false;
			}
			break;
		case W:
			if (x > 0) {
				x = x - 1;
			} else {
				// #RG 008
				log.warn(DEPLACEMENT_IMPOSSIBLE);
				return false;
			}
			break;
		}
		return true;
	}
}
