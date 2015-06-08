package poslovnalogika.so.konobar;

import java.util.List;

import baza.DatabaseBroker;

import domen.Konobar;
import domen.OpstiDomenskiObjekat;
import poslovnalogika.so.OpstaSO;

public class PronadjiKonobareSO extends OpstaSO {
	private List<OpstiDomenskiObjekat> lista;
	private boolean provera;
	
	public boolean getProvera() {
		return provera;
	}

	public void setProvera(boolean provera) {
		this.provera = provera;
	}

	public List<OpstiDomenskiObjekat> getLista() {
		return lista;
	}

	public void setLista(List<OpstiDomenskiObjekat> lista) {
		this.lista = lista;
	}

	@Override
	protected void proveriPreduslov(Object obj) throws RuntimeException {

	}
	
	@Override
	protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
		lista = DatabaseBroker.getInstance().pronadjiListu((Konobar) obj);
		if (lista.isEmpty()) {
			provera=false;
		}else{
			provera=true;
		}
	}

}
