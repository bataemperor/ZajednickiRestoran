package forme.modeltabele;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domen.Proizvod;

public class ModelTabeleProizvodi extends AbstractTableModel {
	private List<Proizvod> listaProizvoda;
	private String[] naziviKolona = { "Naziv", "Cena", "Kolicina", "Tip",
			"Opis" };

	public List<Proizvod> getListaProizvoda() {
		return listaProizvoda;
	}

	public ModelTabeleProizvodi(List<Proizvod> listaProizvoda) {
		this.listaProizvoda = listaProizvoda;
	}

	public void setListaProizvoda(List<Proizvod> listaProizvoda) {
		this.listaProizvoda = listaProizvoda;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return naziviKolona.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaProizvoda.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Proizvod p = listaProizvoda.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getNazivProizvoda();
		case 1:
			return p.getCenaProizvoda();
		case 2:
			return p.getKolicinaNaStanju();
		case 3:
			return p.getTipProizvoda();
		case 4:
			return p.getOpisProizvoda();

		default:
			return "Greska";
		}
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return naziviKolona[column];
	}
	public Proizvod vratiSelektovaniProizvod(int red){
		return listaProizvoda.get(red);
	}
	public void obrisiRed(int red){
		listaProizvoda.remove(red);
		fireTableDataChanged();
	}
}
