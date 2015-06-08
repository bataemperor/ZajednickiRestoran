package poslovnalogika.so.konobar;

import domen.Konobar;
import baza.DatabaseBroker;

import poslovnalogika.so.OpstaSO;

public class IzmeniKonobaraSO extends OpstaSO {

	@Override
	protected void proveriPreduslov(Object obj) throws RuntimeException {

	}

	@Override
	protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
		DatabaseBroker.getInstance().izmeni((Konobar) obj);
	}

}
