package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Proizvod implements Serializable, OpstiDomenskiObjekat {

	private int proizvodID;
	private String nazivProizvoda;
	private int cenaProizvoda;
	private int kolicinaNaStanju;
	private String tipProizvoda;
	private String opisProizvoda;

	public Proizvod(int proizvodID, String nazivProizvoda,
			int cenaProizvoda, int kolicinaNaStanju, String tipProizvoda,
			String opisProizvoda) {
		this.proizvodID = proizvodID;
		this.nazivProizvoda = nazivProizvoda;
		this.cenaProizvoda = cenaProizvoda;
		this.kolicinaNaStanju = kolicinaNaStanju;
		this.tipProizvoda = tipProizvoda;
		this.opisProizvoda = opisProizvoda;
	}
	public Proizvod (){
		
	}
	public Proizvod (String uslov){
		this.nazivProizvoda = uslov;
	}

	public int getProizvodID() {
		return proizvodID;
	}

	public void setProizvodID(int proizvodID) {
		this.proizvodID = proizvodID;
	}

	public String getNazivProizvoda() {
		return nazivProizvoda;
	}

	public void setNazivProizvoda(String nazivProizvoda) {
		this.nazivProizvoda = nazivProizvoda;
	}

	public int getCenaProizvoda() {
		return cenaProizvoda;
	}

	public void setCenaProizvoda(int cenaProizvoda) {
		this.cenaProizvoda = cenaProizvoda;
	}

	public int getKolicinaNaStanju() {
		return kolicinaNaStanju;
	}

	public void setKolicinaNaStanju(int kolicinaNaStanju) {
		this.kolicinaNaStanju = kolicinaNaStanju;
	}

	public String getTipProizvoda() {
		return tipProizvoda;
	}

	public void setTipProizvoda(String tipProizvoda) {
		this.tipProizvoda = tipProizvoda;
	}

	public String getOpisProizvoda() {
		return opisProizvoda;
	}

	public void setOpisProizvoda(String opisProizvoda) {
		this.opisProizvoda = opisProizvoda;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nazivProizvoda;
	}

	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "Proizvod";
	}

	@Override
	public String vratiParametreZaInsert() {
		// TODO Auto-generated method stub
		return  "" + proizvodID + ",'" + nazivProizvoda + "'," + cenaProizvoda + ","+kolicinaNaStanju+",'"+tipProizvoda+"','"+opisProizvoda+"'";
	}

	@Override
	public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
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
			lista.add(proizvod);
		}
		return lista;
	}

	@Override
	public String vratiNazivKolonePrimarnogKljuca() {
		// TODO Auto-generated method stub
		return "ProizvodID";
	}

	@Override
	public String vratiSifru() {
		// TODO Auto-generated method stub
		return String.valueOf(proizvodID);
	}

	@Override
	public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String vratiParametreZaUpdate() {
		// TODO Auto-generated method stub
		return  "NazivProizvoda = '" + nazivProizvoda + "', CenaProizvoda = " + cenaProizvoda + ", KolicinaNaStanju = "+kolicinaNaStanju+", TipProizvoda = '"+tipProizvoda+"', OpisProizvoda = '"+opisProizvoda+"'";
	}

	@Override
	public String vratiNazivTabeleZaBrisanje() {
		// TODO Auto-generated method stub
		return "Proizvod";
	}

	@Override
	public String vratiNazivTabeleZaInsert() {
		// TODO Auto-generated method stub
		return "Proizvod(ProizvodID,NazivProizvoda,CenaProizvoda,KolicinaNaStanju,TipProizvoda,OpisProizvoda)";
	}

	@Override
	public String vratiNazivTabeleZaUpdate() {
		// TODO Auto-generated method stub
		return "Proizvod";
	}

	@Override
	public String uslov() {
		// TODO Auto-generated method stub
		return " WHERE nazivProizvoda LIKE '"+nazivProizvoda+"%'";
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

}
