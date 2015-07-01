package poslovnalogika.so.narudzbina;

import baza.DatabaseBroker;
import domen.Narudzbina;
import poslovnalogika.so.OpstaSO;

public class NaplatiNarudzbinuSO extends OpstaSO{

	@Override
	protected void proveriPreduslov(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		DatabaseBroker.getInstance().naplati((Narudzbina)obj);
	}

}
