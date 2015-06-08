package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Konobar implements Serializable ,OpstiDomenskiObjekat{
	private String username;
	private String password;
	private String userKojiSeMenja;
	
	public String getUserKojiSeMenja() {
		return userKojiSeMenja;
	}
	public void setUserKojiSeMenja(String userKojiSeMenja) {
		this.userKojiSeMenja = userKojiSeMenja;
	}
	public Konobar(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public Konobar() {
		// TODO Auto-generated constructor stub
	}
	public Konobar(String uslov) {
		// TODO Auto-generated constructor stub
		this.username = uslov;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return "Konobar";
	}
	@Override
	public String vratiParametreZaInsert() {
		// TODO Auto-generated method stub
		return "'"+username+"' , '"+password+"'";
	}
	@Override
	public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		List<OpstiDomenskiObjekat> lista = new ArrayList<OpstiDomenskiObjekat>();
		while (rs.next()) {
			String userName = rs.getString("Username");
			String password = rs.getString("Password");
			Konobar k = new Konobar();
			k.setPassword(password);
			k.setUsername(userName);
			lista.add(k);
		}
		return lista;
	}
	@Override
	public String vratiNazivKolonePrimarnogKljuca() {
		// TODO Auto-generated method stub
		return "Username";
	}
	@Override
	public String vratiSifru() {
		// TODO Auto-generated method stub
		return "'"+username+"'";
	}
	@Override
	public OpstiDomenskiObjekat vratiObjekat(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String vratiParametreZaUpdate() {
		// TODO Auto-generated method stub
		return  "Username ='" + username+ "', Password = '" +password+"'";
	}
	@Override
	public String vratiNazivTabeleZaBrisanje() {
		// TODO Auto-generated method stub
		return "Konobar";
	}
	@Override
	public String vratiNazivTabeleZaInsert() {
		// TODO Auto-generated method stub
		return "Konobar";
	}
	@Override
	public String vratiNazivTabeleZaUpdate() {
		// TODO Auto-generated method stub
		return "Konobar";
	}
	@Override
	public String uslov() {
		// TODO Auto-generated method stub
		return " WHERE Username = '"+username+"' AND Password = '"+password+"'";
	}
	@Override
	public String uslov2() {
		// TODO Auto-generated method stub
		return userKojiSeMenja;
	}
	@Override
	public String uslov3() {
		// TODO Auto-generated method stub
		return "";
	}

}
