package forme.niti;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
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
			jlistaNarudzbina.setModel(new ModelListeNarudzbine(listaNarudzbina));
			if (!(listaNarudzbina.size() == listaNarudzbinaTrenutna.size())) {
				listaNarudzbinaTrenutna = listaNarudzbina;
				ModelListeNarudzbine mln = new ModelListeNarudzbine(
						listaNarudzbinaTrenutna);
				jlistaNarudzbina.setModel(mln);
//				playSound();

			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static synchronized void playSound() {
		// zvucno obavestenje za novu narudzbinu
	    try {
	    	InputStream audioSrc = NitNarudzbine.class.getResourceAsStream("novaPorudzbina.wav");
	    	InputStream bufferedIn = new BufferedInputStream(audioSrc);
	    	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}


}
