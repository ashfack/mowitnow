package com.mowitnow.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class PositionTondeuse {
	/**
	 * Position x (relative à dim1)
	 */
	protected int x;

	/**
	 * Position y (relative à dim2)
	 */
	protected int y;

	/**
	 * Orientation tondeuse (N, E, W, S)
	 */
	protected Orientation orientationTondeuse;

	/**
	 * cf Tondeuse
	 */
	protected int dim1;

	/**
	 * cf Tondeuse
	 */
	protected int dim2;

}
