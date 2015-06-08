package poslovnalogika.so.proizvod;

import domen.Proizvod;
import baza.DatabaseBroker;
import poslovnalogika.so.OpstaSO;

public class ObrisiProizvodSO extends OpstaSO {

	@Override
	protected void proveriPreduslov(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		DatabaseBroker.getInstance().obrisi((Proizvod)obj);
	}

}
