package com.mowitnow.utils;

import com.mowitnow.constants.Symbols;

public class MowItNowUtils {

	public static String completerAvecEspacesPourTotalLongueur8(String initial) {
		return completerAvecEspacesPourTotalLongueurN(initial, 8);
	}

	public static String completerAvecEspacesPourTotalLongueurN(String initial, int n) {
		int currentSize = initial.length();
		StringBuilder sb = new StringBuilder();
		sb.append(initial);
		for (int i = 0; i < n - currentSize; i++) {
			sb.append(Symbols.SPACE);
		}
		return sb.toString();
	}
}
