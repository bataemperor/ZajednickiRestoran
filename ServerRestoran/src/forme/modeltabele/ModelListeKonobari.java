package forme.modeltabele;

import java.util.List;

import javax.swing.AbstractListModel;

import domen.Konobar;

public class ModelListeKonobari extends AbstractListModel<Konobar> {
	private List<Konobar> listaKonobara;

	public ModelListeKonobari(List<Konobar> listaKonobara) {
		this.listaKonobara = listaKonobara;
	}

	@Override
	public Konobar getElementAt(int rowIndex) {
		// TODO Auto-generated method stub
		return listaKonobara.get(rowIndex);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return listaKonobara.size();
	}
	
	public void obrisiKonobara(int red){
		listaKonobara.remove(red);
		
	}

}
