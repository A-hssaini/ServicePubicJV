package Metier;

import com.sun.org.apache.bcel.internal.generic.NEW;

public enum Etat {
    ENCORE("Encore"),
    MISEAJOUR("Mise à jour"),
    REJETE("Rejeté"),
    ACCEPTE("Accepté"),
	NEW("New");
	
    Etat(String str) {
    }
}
