package poslovnalogika.so.narudzbina;

import domen.Narudzbina;
import baza.DatabaseBroker;
import poslovnalogika.so.OpstaSO;

public class ObrisiNarudzbinuSO extends OpstaSO{

	@Override
	protected void proveriPreduslov(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		DatabaseBroker.getInstance().obrisi((Narudzbina)obj);
	}

}
