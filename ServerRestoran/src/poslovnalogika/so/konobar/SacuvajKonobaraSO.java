package poslovnalogika.so.konobar;

import domen.Konobar;
import baza.DatabaseBroker;
import poslovnalogika.so.OpstaSO;

public class SacuvajKonobaraSO extends OpstaSO{

	@Override
	protected void proveriPreduslov(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		DatabaseBroker.getInstance().sacuvaj((Konobar)obj);
	}

}
