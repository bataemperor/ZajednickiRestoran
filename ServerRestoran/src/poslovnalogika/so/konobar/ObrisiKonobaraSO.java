package poslovnalogika.so.konobar;

import baza.DatabaseBroker;

import domen.Konobar;
import poslovnalogika.so.OpstaSO;

public class ObrisiKonobaraSO extends OpstaSO {

	@Override
	protected void proveriPreduslov(Object obj) throws RuntimeException {

	}

	@Override
	protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
		DatabaseBroker.getInstance().obrisi((Konobar) obj);
	}

}
