package domen;

import java.sql.ResultSet;
import java.util.List;

public interface OpstiDomenskiObjekat {
	
	public String vratiNazivTabele();

	public String vratiParametreZaInsert();

	public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;

	public String vratiNazivKolonePrimarnogKljuca();

	public String vratiSifru();

	public OpstiDomenskiObjekat vratiObjekat(ResultSet rs);

	public String vratiParametreZaUpdate();

	public String vratiNazivTabeleZaBrisanje();

	public String vratiNazivTabeleZaInsert();

	public String vratiNazivTabeleZaUpdate();

	public String uslov();

	public String uslov2();

	public String uslov3();

}
