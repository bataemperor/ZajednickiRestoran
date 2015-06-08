package poslovnalogika.so.narudzbina;


import java.util.List;

import baza.DatabaseBroker;
import domen.Narudzbina;
import domen.OpstiDomenskiObjekat;
import poslovnalogika.so.OpstaSO;

public class PretraziNarudzbineSO extends OpstaSO{
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
		lista = DatabaseBroker.getInstance().pronadjiListu((Narudzbina)obj);

	}

	@Override
	protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		
	}

}
