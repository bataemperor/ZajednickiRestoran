package forme.modeltabele;

import java.util.List;

import javax.swing.AbstractListModel;

import domen.Narudzbina;

public class ModelListeNarudzbine extends AbstractListModel<Narudzbina> {
	private List<Narudzbina> lista;

	public ModelListeNarudzbine(List<Narudzbina> lista) {
		this.lista = lista;
	}

	@Override
	public Narudzbina getElementAt(int rowIndex) {
		// TODO Auto-generated method stub

		return lista.get(rowIndex);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return lista.size();
	}

}
