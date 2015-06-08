package forme.niti;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JList;

import poslovnalogika.Kontroler;
import domen.Narudzbina;
import domen.StavkaNarudzbine;
import forme.modeltabele.ModelListeNarudzbine;

public class NitNarudzbine extends Thread {

	private JList<Narudzbina> jlistaNarudzbina;
	private List<Narudzbina> listaNarudzbinaTrenutna;

	// private JTable jtblNarudzbine;
	public NitNarudzbine(JList<Narudzbina> jlistaNarudzbina) {
		this.jlistaNarudzbina = jlistaNarudzbina;
		// this.jtblNarudzbine = jtblNarudzbine;
		listaNarudzbinaTrenutna = new ArrayList<Narudzbina>();
	}

	@Override
	public void run() {
		while (true) {

			List<Narudzbina> listaNarudzbina = (List<Narudzbina>) (Object) Kontroler
					.getInstance().vratiSveNarudzbine();
			for (Narudzbina narudzbina : listaNarudzbina) {
				List<StavkaNarudzbine> listaStavki = (List<StavkaNarudzbine>) (Object) Kontroler
						.getInstance().vratiSveStavkeNarudzbine(
								narudzbina.getNarudzbinaID());
				narudzbina.setListaStavki(listaStavki);
			}
			if (!(listaNarudzbina.size() == listaNarudzbinaTrenutna.size())) {
				listaNarudzbinaTrenutna = listaNarudzbina;
				ModelListeNarudzbine mln = new ModelListeNarudzbine(
						listaNarudzbinaTrenutna);
				jlistaNarudzbina.setModel(mln);
				playSound();

			}

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static synchronized void playSound() {
		// zvucno obavestenje za novu narudzbinu
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("novaPorudzbina.wav"));
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}


}
