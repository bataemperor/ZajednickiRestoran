package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StavkaNarudzbine implements Serializable, OpstiDomenskiObjekat {
	private Narudzbina narudzbina;
	private int rbStavke;
	private int kolicina;
	private int iznos;
	private String napomena;
	private Proizvod proizvod;

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}

	public StavkaNarudzbine(Narudzbina narudzbina, int rbStavke, int kolicina,
			int iznos, String napomena, Proizvod proizvod) {
		this.narudzbina = narudzbina;
		this.rbStavke = rbStavke;
		this.kolicina = kolicina;
		this.iznos = iznos;
		this.napomena = napomena;
		this.proizvod = proizvod;
	}

	public StavkaNarudzbine() {

	}

	public StavkaNarudzbine(int narudzbinaID) {
		this.kolicina = narudzbinaID;
	}

	public Narudzbina getNarudzbina() {
		return narudzbina;
	}

	public void setNarudzbina(Narudzbina narudzbina) {
		this.narudzbina = narudzbina;
	}

	public int getRbStavke() {
		return rbStavke;
	}

	public void setRbStavke(int rbStavke) {
		this.rbStavke = rbStavke;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public int getIznos() {
		return iznos;
	}

	public void setIznos(int iznos) {
		this.iznos = iznos;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}
	@Override
	public String toString() {
		return proizvod.getNazivProizvoda()+" x "+kolicina;
	}

	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "Proizvod INNER JOIN StavkaNarudzbine ON Proizvod.ProizvodID = StavkaNarudzbine.ProizvodID";
	}

	@Override
	public String vratiParametreZaInsert() {
		// TODO Auto-generated method stub
		return "" + narudzbina.getNarudzbinaID() + ", " + rbStavke + ", "
				+ kolicina + ", " + iznos + ", " + proizvod.getProizvodID()
				+ " , '" + napomena + "'";
	}

	@Override
	public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		List<OpstiDomenskiObjekat> lista = new ArrayList();
		while (rs.next()) {
			int proizvodID = rs.getInt("PROIZVODID");
			String nazivProizvoda = rs.getString("NAZIVPROIZVODA");
			int cenaProizvoda = rs.getInt("CENAPROIZVODA");
			int kolicinaNaStanju = rs.getInt("KOLICINANASTANJU");
			String tipProizvoda = rs.getString("TIPPROIZVODA");
			String opisProizvoda = rs.getString("OPISPROIZVODA");
			Proizvod proizvod = new Proizvod(proizvodID, nazivProizvoda,
					cenaProizvoda, kolicinaNaStanju, tipProizvoda,
					opisProizvoda);
			int narudzbinaID = rs.getInt("NarudzbinaID");
			Narudzbina narudzbina = new Narudzbina();
			narudzbina.setNarudzbinaID(narudzbinaID);
			int rbStavke = rs.getInt("RbStavke");
			int kolicina = rs.getInt("Kolicina");
			int iznos = rs.getInt("Iznos");
			String napomena = rs.getString("Napomena");

			StavkaNarudzbine stavka = new StavkaNarudzbine(narudzbina,
					rbStavke, kolicina, iznos, napomena, proizvod);

			lista.add(stavka);
		}
		return lista;
	}

	@Override
	public String vratiNazivKolonePrimarnogKljuca() {
		// TODO Auto-generated method stub
		return "NarudzbinaID";
	}

	@Override
	public String vratiSifru() {
		// TODO Auto-generated method stub
		return String.valueOf(narudzbina.getNarudzbinaID());
	}

	@Override
	public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String vratiParametreZaUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String vratiNazivTabeleZaBrisanje() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String vratiNazivTabeleZaInsert() {
		// TODO Auto-generated method stub
		return "StavkaNarudzbine";
	}

	@Override
	public String vratiNazivTabeleZaUpdate() {
		// TODO Auto-generated method stub
		return "StavkaNarudzbine";
	}

	@Override
	public String uslov() {
		// TODO Auto-generated method stub
		return " WHERE NarudzbinaID = ";
	}

	@Override
	public String uslov2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String uslov3() {
		// TODO Auto-generated method stub
		return String.valueOf(kolicina);
	}

}
