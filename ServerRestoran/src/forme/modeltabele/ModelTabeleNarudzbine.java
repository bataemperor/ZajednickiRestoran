package forme.modeltabele;

import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import domen.Narudzbina;
import domen.Proizvod;
import domen.StavkaNarudzbine;

public class ModelTabeleNarudzbine extends AbstractTableModel {
	private Narudzbina narudzbina;

	public ModelTabeleNarudzbine(Narudzbina narudzbina) {
		this.narudzbina = narudzbina;
	}

	public Narudzbina getNarudzbina() {
		return narudzbina;
	}

	public void setNarudzbina(Narudzbina narudzbina) {
		this.narudzbina = narudzbina;
	}

	private String[] naziviKolona = { "NarudzbinaID", "Broj Stavke",
			"Proizvod", "Kolicina", "Iznos", "Napomena" };

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return naziviKolona.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return narudzbina.getListaStavki().size();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return naziviKolona[column];
	}

	public void dodajStavku() {
		int rbStavke = narudzbina.getListaStavki().size() + 1;
		narudzbina.getListaStavki().add(
				new StavkaNarudzbine(narudzbina, rbStavke, 0, 0, "",
						new Proizvod()));
		fireTableDataChanged();
	}

	public void obrisiStavku(int red) {
		narudzbina.getListaStavki().remove(red);
		//pri brisanju ponovno sortiranje stavki od 1 do kraja liste
		int brStavke = 1;
		for (StavkaNarudzbine stavkaNar : narudzbina.getListaStavki()) {
			stavkaNar.setRbStavke(brStavke);
			brStavke++;
		}
		fireTableDataChanged();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 2 || columnIndex == 3 || columnIndex == 5) {
			return true;
		}
		return false;

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		StavkaNarudzbine stavka = narudzbina.getListaStavki().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return narudzbina.getNarudzbinaID();
		case 1:
			return stavka.getRbStavke();
		case 2:
			return stavka.getProizvod();
		case 3:
			return stavka.getKolicina();
		case 4:
			return stavka.getIznos();
		case 5:
			return stavka.getNapomena();
		default:
			return "Greska";
		}

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		StavkaNarudzbine stavka = narudzbina.getListaStavki().get(rowIndex);
		switch (columnIndex) {

		case 2:
			stavka.setProizvod((Proizvod) aValue);
			break;
		case 3:
			try {
				stavka.setKolicina(Integer.valueOf((String) aValue));
				
				stavka.setIznos(stavka.getProizvod().getCenaProizvoda()
						* stavka.getKolicina());
				fireTableRowsDeleted(rowIndex, 4);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Morate uneti broj kod kolièine", "Pogresan unos kolièine", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case 4:
			break;
		case 5:
			stavka.setNapomena(String.valueOf(aValue));
			break;
		default:
			break;
		}
	}
//	@Override
//	public Class<?> getColumnClass(int columnIndex) {
//		// TODO Auto-generated method stub
//		switch (columnIndex) {
//		case 0:
//		case 1:
//		case 2:
//		case 3: return String.class;
//
//		}
//	}
	
}
