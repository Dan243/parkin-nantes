package com.example.dmonunu.parkinnantes.utilities;


import android.widget.ImageView;

public final class ParkingParser {

	public static boolean isCreditCardAvailable(final String options) {
		return options.contains("CB en borne de sortie");
	}

	public static boolean isCashAvailable(final String options) {
		return options.contains("Espèces");
	}

	public static boolean isTotalGRAvailable(final String options) {
		return options.contains("Total GR");
	}

	public static boolean isChequeAvailable(final String options) {
		return options.contains("chèque");
	}

	public static boolean isLigneOneNear(final String publicsTransports) {
		return publicsTransports.contains("Ligne 1") || publicsTransports.contains("Lignes 1") ||
				publicsTransports.contains("Lignes 1-2") || publicsTransports.contains("Lignes 1-2-3");
	}

	public static boolean isLigneTwoNear(final String publicsTransports) {
		return publicsTransports.contains("Ligne 2") || publicsTransports.contains("Lignes 2") ||
				publicsTransports.contains("Lignes 2-3") || publicsTransports.contains("Lignes 1-2-3");
	}

	public static boolean isLigneThreeNear(final String publicsTransports) {
		return publicsTransports.contains("Ligne 3") || publicsTransports.contains("Lignes 3") ||
				publicsTransports.contains("Lignes 2-3") || publicsTransports.contains("Lignes 1-2-3");
	}

	public static boolean isLigneFourNear(final String publicsTransports) {
		return publicsTransports.contains("Ligne 4") || publicsTransports.contains("Lignes 4") ||
				publicsTransports.contains("Lignes 3-4");
	}

	public static void setImageVisibility(final boolean isServiceAvailable, final ImageView
			imageView) {
		int result = ImageView.GONE;
		if (isServiceAvailable) {
			result = ImageView.VISIBLE;
		}
		imageView.setVisibility(result);
	}

}
