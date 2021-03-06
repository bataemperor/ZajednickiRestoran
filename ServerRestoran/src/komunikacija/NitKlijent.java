package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JDialog;

import domen.Konobar;
import domen.Narudzbina;
import domen.OpstiDomenskiObjekat;
import domen.StavkaNarudzbine;
import forme.FRMStavke;
import forme.modeltabele.ModelTabeleNarudzbine;
import forme.niti.NitNarudzbine;
import poslovnalogika.Kontroler;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;
import util.Konstante;

public class NitKlijent extends Thread {
	@Override
	public void run() {
		try {
			izvrsenjeOperacija();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Socket socket;

	public NitKlijent(Socket socket) {
		this.socket = socket;
	}

	private void izvrsenjeOperacija() throws IOException,
			ClassNotFoundException {
		ObjectInputStream inSocket = new ObjectInputStream(
				socket.getInputStream());
		TransferObjekatZahtev toZahtev = (TransferObjekatZahtev) inSocket
				.readObject();
		TransferObjekatOdgovor toOdgovor = new TransferObjekatOdgovor();
		FRMStavke dialog;
		ModelTabeleNarudzbine mtn;
		switch (toZahtev.getOperacija()) {
		case Konstante.VRATI_SVE_PROIZVODE:
			System.out.println("O:" + Konstante.VRATI_SVE_PROIZVODE);
			List<OpstiDomenskiObjekat> listaProizvoda = Kontroler.getInstance()
					.vratiSveProizvode();
			toOdgovor.setRezultat(listaProizvoda);
			if (listaProizvoda.isEmpty()) {
				toOdgovor.setOdgovor("Error lista organizacija");
			} else {
				toOdgovor.setOdgovor("Ok lista organizacija");
			}
			break;
		case Konstante.VRATI_SVE_NARUDZBINE:
			System.out.println("O:" + Konstante.VRATI_SVE_NARUDZBINE);
			@SuppressWarnings("unchecked")
			List<Narudzbina> listaNarudzbina = (List<Narudzbina>) (Object) Kontroler
					.getInstance().vratiSveNarudzbine();
			for (Narudzbina narudzbina : listaNarudzbina) {
				@SuppressWarnings("unchecked")
				List<StavkaNarudzbine> listaStavki = (List<StavkaNarudzbine>) (Object) Kontroler
						.getInstance().vratiSveStavkeNarudzbine(
								narudzbina.getNarudzbinaID());
				narudzbina.setListaStavki(listaStavki);
				toOdgovor.setRezultat(listaNarudzbina);
			}
			if (listaNarudzbina.isEmpty()) {
				toOdgovor.setOdgovor("Error lista organizacija");
			} else {
				toOdgovor.setOdgovor("Ok lista organizacija");
			}
			break;
		case Konstante.PRONADJI_KONOBARE:
			System.out.println("O:" + Konstante.PRONADJI_KONOBARE);
			Konobar k = (Konobar) toZahtev.getParametar();
			boolean postoji = Kontroler.getInstance().pronadjiKonobare(k);
			toOdgovor.setRezultat(postoji);
			if (postoji == false) {
				toOdgovor.setOdgovor("Ne postoji konobar u bazi");
			} else {
				toOdgovor.setOdgovor("Uspesno ste se ulogovali");

			}
			break;
		case Konstante.SACUVAJ_NARUDZBINU:
			System.out.println("O:" + Konstante.SACUVAJ_NARUDZBINU);
			Narudzbina n = (Narudzbina) toZahtev.getParametar();
			n.setNarudzbinaID(Kontroler.getInstance().vratiIDNarudzbine());
			Kontroler.getInstance().sacuvajNarudzbinu(n);
			toOdgovor.setOdgovor("Uspesno poslata narudzbina");

			// otvaranje forme kada stigne nova narudzbina
			mtn = new ModelTabeleNarudzbine(n);
			dialog = new FRMStavke(mtn);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setTitle("Nova Narudzbina ! Broj Stola : " + n.getBrojStola());
			dialog.setVisible(true);
			NitNarudzbine.playSound();

			break;
		case Konstante.IZMENI_NARUDZBINU:
			System.out.println("O:" + Konstante.IZMENI_NARUDZBINU);
			Narudzbina narudzbina = (Narudzbina) toZahtev.getParametar();
			Kontroler.getInstance().izmeniNarudzbinu(narudzbina);
			toOdgovor.setOdgovor("Uspesno izmenjena narudbina : "
					+ narudzbina.getNarudzbinaID());

			// otvaranje forme kada se izmeni narudzbina
			mtn = new ModelTabeleNarudzbine(narudzbina);
			dialog = new FRMStavke(mtn);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setTitle("Izmena narudzbine ! Broj Stola : " + narudzbina.getBrojStola());
			dialog.setVisible(true);
			NitNarudzbine.playSound();
			break;
		case Konstante.NAPLATI_NARUDZBINU:
			System.out.println("O:" + Konstante.NAPLATI_NARUDZBINU);
			Narudzbina narudzbinaZaNaplatu = (Narudzbina) toZahtev
					.getParametar();
			Kontroler.getInstance().naplatiNarudzbinu(narudzbinaZaNaplatu);
			toOdgovor.setOdgovor("Uspesno naplacena narudzbina! ID : "
					+ narudzbinaZaNaplatu.getNarudzbinaID());
			break;
		case Konstante.OBRISI_NARUDZBINU:
			System.out.println("O:" + Konstante.OBRISI_NARUDZBINU);
			Narudzbina narudzbinaZaBrisanje = (Narudzbina) toZahtev
					.getParametar();
			Kontroler.getInstance().obrisiNarudzbinu(narudzbinaZaBrisanje);
			toOdgovor.setOdgovor("Uspesno obrisana narudzbina! ID : "
					+ narudzbinaZaBrisanje.getNarudzbinaID());
			break;
		}
		posaljiOdgovor(toOdgovor);
		socket.close();
	}

	private void posaljiOdgovor(TransferObjekatOdgovor toOdgovor)
			throws IOException {
		ObjectOutputStream outSocket = new ObjectOutputStream(
				socket.getOutputStream());
		outSocket.writeObject(toOdgovor);
		// outSocket.flush();

	}
}
