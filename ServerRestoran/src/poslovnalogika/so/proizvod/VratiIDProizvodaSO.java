package poslovnalogika.so.proizvod;

import domen.Proizvod;
import baza.DatabaseBroker;
import poslovnalogika.so.OpstaSO;

public class VratiIDProizvodaSO extends OpstaSO {
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
		id = DatabaseBroker.getInstance().kreirajSifru((Proizvod) obj);
	}

}
