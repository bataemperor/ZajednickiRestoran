package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Narudzbina implements Serializable, OpstiDomenskiObjekat {
	private int narudzbinaID;
	private Date datumNarudzbine;
	private int brojStola;
	private int ukupanIznos;
	private String status;
	private Konobar konobar;

	private List<StavkaNarudzbine> listaStavki;

	public List<StavkaNarudzbine> getListaStavki() {
		return listaStavki;
	}

	public void setListaStavki(List<StavkaNarudzbine> listaStavki) {
		this.listaStavki = listaStavki;
	}

	public Narudzbina(int narudzbinaID, Date datumNarudzbine, int brojStola,
			int ukupanIznos, String status, Konobar konobar) {
		this.narudzbinaID = narudzbinaID;
		this.datumNarudzbine = datumNarudzbine;
		this.brojStola = brojStola;
		this.ukupanIznos = ukupanIznos;
		this.status = status;
		this.konobar = konobar;
		this.listaStavki = new ArrayList<StavkaNarudzbine>();
	}

	public Narudzbina() {
		this.listaStavki = new ArrayList<StavkaNarudzbine>();
		konobar = new Konobar();
		konobar.setUsername("neki user");
		status = "placen";
	}

	public void izracunajUkupanIznos() {
		ukupanIznos = 0;
		for (StavkaNarudzbine stavka : listaStavki) {
			ukupanIznos += stavka.getIznos();
		}
	}

	public int getNarudzbinaID() {
		return narudzbinaID;
	}

	public void setNarudzbinaID(int narudzbinaID) {
		this.narudzbinaID = narudzbinaID;
	}

	public Date getDatumNarudzbine() {
		return datumNarudzbine;
	}

	public void setDatumNarudzbine(Date datumNarudzbine) {
		this.datumNarudzbine = datumNarudzbine;
	}

	public int getBrojStola() {
		return brojStola;
	}

	public void setBrojStola(int brojStola) {
		this.brojStola = brojStola;
	}

	public int getUkupanIznos() {
		return ukupanIznos;
	}

	public void setUkupanIznos(int ukupanIznos) {
		this.ukupanIznos = ukupanIznos;
	}

	public String isStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Konobar getKonobar() {
		return konobar;
	}

	public void setKonobar(Konobar konobar) {
		this.konobar = konobar;
	}

	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "Narudzbina";
	}

	@Override
	public String vratiParametreZaInsert() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datumNar = sdf.format(datumNarudzbine);
		return "" + narudzbinaID + ", '" + datumNar + "', " + brojStola + ", "
				+ ukupanIznos + ", '" + status + "', '" + konobar.getUsername()
				+ "'";
	}

	@Override
	public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		List<OpstiDomenskiObjekat> lista = new ArrayList();
		while (rs.next()) {
			int narudzbinaID = rs.getInt("NarudzbinaID");
			Date datumNarudzbine = rs.getTimestamp("DatumNarudzbine");
			int brojStola = rs.getInt("BrojStola");
			int ukupanIznos = rs.getInt("UkupanIznos");
			String status = rs.getString("Status");
			String username = rs.getString("Username");
			Konobar k = new Konobar();
			k.setUsername(username);
			Narudzbina n = new Narudzbina(narudzbinaID, datumNarudzbine,
					brojStola, ukupanIznos, status, k);
			lista.add(n);
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
		return String.valueOf(narudzbinaID);
	}

	@Override
	public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String vratiParametreZaUpdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datumNar = sdf.format(datumNarudzbine);
		return 	"BrojStola= "+brojStola
				+ ",UkupanIznos = '"
				+ukupanIznos+"',Status = '"
				+ status +"'";
	}

	@Override
	public String vratiNazivTabeleZaBrisanje() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String vratiNazivTabeleZaInsert() {
		// TODO Auto-generated method stub
		return "Narudzbina";
	}

	@Override
	public String vratiNazivTabeleZaUpdate() {
		// TODO Auto-generated method stub
		return "Narudzbina";
	}

	@Override
	public String uslov() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String uslov2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String uslov3() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return "Sto: " + brojStola +",             Vreme: " + sdf.format(datumNarudzbine) +",               racun: "
				+ ukupanIznos;
	}

}
