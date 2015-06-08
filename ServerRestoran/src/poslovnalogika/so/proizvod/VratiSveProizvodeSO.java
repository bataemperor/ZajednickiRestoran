package poslovnalogika.so.proizvod;

import java.util.List;

import baza.DatabaseBroker;
import domen.OpstiDomenskiObjekat;
import domen.Proizvod;
import poslovnalogika.so.OpstaSO;

public class VratiSveProizvodeSO extends OpstaSO {
	private List<OpstiDomenskiObjekat> lista;

	@Override
	protected void proveriPreduslov(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void izvrsiKonkretnuOperaciju(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		lista = DatabaseBroker.getInstance().vratiListu((Proizvod)obj);
	}
    public List<OpstiDomenskiObjekat> getLista() {
        return lista;
    }

    public void setLista(List<OpstiDomenskiObjekat> lista) {
        this.lista = lista;
    }
}
