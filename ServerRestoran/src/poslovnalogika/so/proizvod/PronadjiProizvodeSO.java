package poslovnalogika.so.proizvod;

import java.util.List;

import domen.OpstiDomenskiObjekat;
import domen.Proizvod;
import baza.DatabaseBroker;
import poslovnalogika.so.OpstaSO;

public class PronadjiProizvodeSO extends OpstaSO {
	private List<OpstiDomenskiObjekat> lista;
	
	public List<OpstiDomenskiObjekat> getLista() {
		return lista;
	}

	public void setLista(List<OpstiDomenskiObjekat> lista) {
		this.lista = lista;
	}

	@Override
	protected void proveriPreduslov(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		lista = DatabaseBroker.getInstance().pronadjiListu((Proizvod)obj);
	}

}
