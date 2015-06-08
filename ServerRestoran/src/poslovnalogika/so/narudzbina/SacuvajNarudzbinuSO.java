package poslovnalogika.so.narudzbina;

import domen.Narudzbina;
import domen.StavkaNarudzbine;
import baza.DatabaseBroker;
import poslovnalogika.so.OpstaSO;

public class SacuvajNarudzbinuSO extends OpstaSO{

	@Override
	protected void proveriPreduslov(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		Narudzbina narudzbina = (Narudzbina)obj;
		DatabaseBroker.getInstance().sacuvaj(narudzbina);
		for (StavkaNarudzbine sn : narudzbina.getListaStavki()) {
			DatabaseBroker.getInstance().sacuvaj(sn);
		}
	}

}
