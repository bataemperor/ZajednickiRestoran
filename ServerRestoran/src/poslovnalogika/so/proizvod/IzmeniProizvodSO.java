package poslovnalogika.so.proizvod;

import domen.Proizvod;
import baza.DatabaseBroker;
import poslovnalogika.so.OpstaSO;

public class IzmeniProizvodSO extends OpstaSO {
	
	@Override
	protected void proveriPreduslov(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		DatabaseBroker.getInstance().izmeni((Proizvod)obj);
	}
	
}
