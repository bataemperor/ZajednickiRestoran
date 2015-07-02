package poslovnalogika;

import java.util.List;

import poslovnalogika.so.konobar.IzmeniKonobaraSO;
import poslovnalogika.so.konobar.ObrisiKonobaraSO;
import poslovnalogika.so.konobar.PronadjiKonobareSO;
import poslovnalogika.so.konobar.SacuvajKonobaraSO;
import poslovnalogika.so.konobar.VratiSveKonobareSO;
import poslovnalogika.so.narudzbina.IzmeniNarudzbinuSO;
import poslovnalogika.so.narudzbina.NaplatiNarudzbinuSO;
import poslovnalogika.so.narudzbina.ObrisiNarudzbinuSO;
import poslovnalogika.so.narudzbina.SacuvajNarudzbinuSO;
import poslovnalogika.so.narudzbina.VratiIDNarudzbineSO;
import poslovnalogika.so.narudzbina.VratiSveNarudzbineSO;
import poslovnalogika.so.narudzbina.VratiSveStavkeNarudzbine;
import poslovnalogika.so.proizvod.IzmeniProizvodSO;
import poslovnalogika.so.proizvod.ObrisiProizvodSO;
import poslovnalogika.so.proizvod.PronadjiProizvodeSO;
import poslovnalogika.so.proizvod.SacuvajProizvodSO;
import poslovnalogika.so.proizvod.VratiIDProizvodaSO;
import poslovnalogika.so.proizvod.VratiSveProizvodeSO;
import domen.Konobar;
import domen.Narudzbina;
import domen.OpstiDomenskiObjekat;
import domen.Proizvod;
import domen.StavkaNarudzbine;
import baza.DatabaseBroker;

public class Kontroler {
	private DatabaseBroker db;
	private static Kontroler instance;

	private Kontroler() {

		db = DatabaseBroker.getInstance();
	}

	public static Kontroler getInstance() {
		if (instance == null) {
			instance = new Kontroler();
		}
		return instance;
	}
	public List<OpstiDomenskiObjekat> vratiSveProizvode(){
		VratiSveProizvodeSO vsp = new VratiSveProizvodeSO();
		vsp.izvrsiOperaciju(new Proizvod());
		return vsp.getLista();
	}
	public void sacuvajProizvod(Proizvod proizvod){
		SacuvajProizvodSO sp = new SacuvajProizvodSO();
		sp.izvrsiOperaciju(proizvod);
	}
	public int vratiIDProizvoda(){
		VratiIDProizvodaSO vip = new VratiIDProizvodaSO();
		vip.izvrsiOperaciju(new Proizvod());
		return vip.getId();
	}
	public void obrisiProizvod(Proizvod proizvod){
		ObrisiProizvodSO obrisiProizvodSO = new ObrisiProizvodSO();
		obrisiProizvodSO.izvrsiOperaciju(proizvod);
	}
	public void izmeniProizvod(Proizvod proizvod){
		IzmeniProizvodSO izmeniProizvodSO = new IzmeniProizvodSO();
		izmeniProizvodSO.izvrsiOperaciju(proizvod);
	}
	public List<OpstiDomenskiObjekat> pretraziProizvode(Object obj){
		PronadjiProizvodeSO proizvodeSO = new PronadjiProizvodeSO();
		proizvodeSO.izvrsiOperaciju(obj);
		return proizvodeSO.getLista();
	}
	public void sacuvajKonobara(Konobar k){
		SacuvajKonobaraSO sacuvajKonobaraSO = new SacuvajKonobaraSO();
		sacuvajKonobaraSO.izvrsiOperaciju(k);
	}
	public List<OpstiDomenskiObjekat> vratiSveKonobare(){
		VratiSveKonobareSO vratiSveKonobareSO = new VratiSveKonobareSO();
		vratiSveKonobareSO.izvrsiOperaciju(new Konobar());
		return vratiSveKonobareSO.getLista();
	}
	public void obrisiKonobara(Konobar k){
		ObrisiKonobaraSO obrisiKonobaraSO = new ObrisiKonobaraSO();
		obrisiKonobaraSO.izvrsiOperaciju(k);
	}
	public void izmeniKonobara(Konobar k){
		IzmeniKonobaraSO izmeniKonobaraSO = new IzmeniKonobaraSO();
		izmeniKonobaraSO.izvrsiOperaciju(k);
	}
	public boolean pronadjiKonobare(Object obj){
		PronadjiKonobareSO konobareSO = new PronadjiKonobareSO();
		konobareSO.izvrsiOperaciju(obj);
		return konobareSO.getProvera();
	}
	public int vratiIDNarudzbine(){
		VratiIDNarudzbineSO vin = new VratiIDNarudzbineSO();
		vin.izvrsiOperaciju(new Narudzbina());
		return vin.getId();
	}
	public void sacuvajNarudzbinu(Narudzbina n){
		SacuvajNarudzbinuSO sacuvajNarudzbinuSO = new SacuvajNarudzbinuSO();
		sacuvajNarudzbinuSO.izvrsiOperaciju(n);
	}
	public List<OpstiDomenskiObjekat> vratiSveNarudzbine(){
		VratiSveNarudzbineSO narudzbineSO = new VratiSveNarudzbineSO();
		narudzbineSO.izvrsiOperaciju(new Narudzbina());
		return narudzbineSO.getLista();
	}
	public List<OpstiDomenskiObjekat> vratiSveStavkeNarudzbine(int narudzbinaID){
		VratiSveStavkeNarudzbine stavkeNarudzbineSO = new VratiSveStavkeNarudzbine();
		stavkeNarudzbineSO.izvrsiOperaciju(new StavkaNarudzbine(narudzbinaID));
		return stavkeNarudzbineSO.getLista();
	}
	public void izmeniNarudzbinu(Narudzbina n){
		IzmeniNarudzbinuSO izmeniNarudzbinuSO =  new IzmeniNarudzbinuSO();
		izmeniNarudzbinuSO.izvrsiOperaciju(n);
	}
	public void naplatiNarudzbinu(Narudzbina n){
		NaplatiNarudzbinuSO naplatiNarudzbinuSO = new NaplatiNarudzbinuSO();
		naplatiNarudzbinuSO.izvrsiOperaciju(n);
	}
	public void obrisiNarudzbinu(Narudzbina n){
		ObrisiNarudzbinuSO obrisiNarudzbinuSO = new ObrisiNarudzbinuSO();
		obrisiNarudzbinuSO.izvrsiOperaciju(n);
	}
	
}
