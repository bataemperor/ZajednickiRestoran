package poslovnalogika.so.narudzbina;

import domen.Narudzbina;
import baza.DatabaseBroker;
import poslovnalogika.so.OpstaSO;

public class VratiIDNarudzbineSO extends OpstaSO{
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	protected void proveriPreduslov(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		id = DatabaseBroker.getInstance().kreirajSifru((Narudzbina)obj);
	}

}
