package forme;

import java.awt.EventQueue;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JTable;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import komunikacija.ServerskaNit;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import forme.konobar.FRMPregledKonobara;
import forme.konobar.FRMUnosKonobara;
import forme.modeltabele.ModelListeNarudzbine;
import forme.modeltabele.ModelTabeleNarudzbine;
import forme.narudzbine.FRMUnosNoveNarudzbine;
import forme.niti.NitNarudzbine;
import forme.proizvod.FRMPregledProizvoda;
import forme.proizvod.FRMUnosProizvoda;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import poslovnalogika.Kontroler;
import domen.Narudzbina;
import domen.Proizvod;
import domen.StavkaNarudzbine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FRMGlavna {

	private JFrame frame;
	private JMenu jmProizvod;
	private JMenuItem jmiUnosNovogProizvoda;
	private JList jlistNarudzbine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMGlavna window = new FRMGlavna();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FRMGlavna() {
		initialize();
		pokreniServer();
		osvezavajFormu();
		
	}

	private void osvezavajFormu() {
		// osvezavanje liste

		NitNarudzbine nitNarudzbine = new NitNarudzbine(jlistNarudzbine);
		nitNarudzbine.start();

		jlistNarudzbine.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (!e.getValueIsAdjusting()) {
					if (jlistNarudzbine.getSelectedIndex() != -1) {
						Narudzbina n = (Narudzbina) jlistNarudzbine
								.getSelectedValue();
						ModelTabeleNarudzbine mtn = new ModelTabeleNarudzbine(n);
						// jtblNarudzbina.setModel(mtn);
						FRMStavke dialog = new FRMStavke(mtn);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						dialog.setTitle("Broj Stola : " + mtn.getNarudzbina().getBrojStola());
						jlistNarudzbine.clearSelection();
					}

				}

			}
		});

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 889, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Lista Narudzbina");
		//postavljanje ikonice na Jframe
		ImageIcon ii = new ImageIcon(getClass().getResource("/fud.png"));
		frame.setIconImage(ii.getImage());
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1216, 21);
		frame.getContentPane().add(menuBar);
		
		jmProizvod = new JMenu("Proizvodi");
		menuBar.add(jmProizvod);

		jmiUnosNovogProizvoda = new JMenuItem("Unos novog proizvoda");
		jmiUnosNovogProizvoda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FRMUnosProizvoda frmProizvod = new FRMUnosProizvoda();
				frmProizvod.setVisible(true);
				//postavljanje ikonice na FrmUnosProizvoda
				ImageIcon ii = new ImageIcon(getClass().getResource("/fud.png"));
				frmProizvod.setIconImage(ii.getImage());
				frmProizvod.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frmProizvod.setTitle("Unos proizvoda");
			}
		});
		jmProizvod.add(jmiUnosNovogProizvoda);

		JMenuItem mntmPregledProizvoda = new JMenuItem("Pregled proizvoda");
		mntmPregledProizvoda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FRMPregledProizvoda frmPregledProizvoda = new FRMPregledProizvoda();
				frmPregledProizvoda.setVisible(true);
				//postavljanje ikonice na FrmPregledProizvoda
				ImageIcon ii = new ImageIcon(getClass().getResource("/fud.png"));
				frmPregledProizvoda.setIconImage(ii.getImage());
				frmPregledProizvoda
						.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frmPregledProizvoda.setTitle("Pregled proizvoda");
			}
		});
		jmProizvod.add(mntmPregledProizvoda);

		JMenu mnKonobari = new JMenu("Konobari");
		menuBar.add(mnKonobari);

		JMenuItem jmiUnosNovogKonobara = new JMenuItem("Unos novog konobara");
		jmiUnosNovogKonobara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FRMUnosKonobara frmUnosKonobara = new FRMUnosKonobara();
				frmUnosKonobara.setVisible(true);
				//postavljanje ikonice na FRMUnosKonobara
				ImageIcon ii = new ImageIcon(getClass().getResource("/fud.png"));
				frmUnosKonobara.setIconImage(ii.getImage());
				frmUnosKonobara
						.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frmUnosKonobara.setTitle("Unos novog konobara");
			}
		});
		mnKonobari.add(jmiUnosNovogKonobara);

		JMenuItem jmiPregledKonobara = new JMenuItem("Pregled konobara");
		jmiPregledKonobara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FRMPregledKonobara frmPregledKonobara = new FRMPregledKonobara();
				frmPregledKonobara.setVisible(true);
				//postavljanje ikonice na FrmPregledKonobara
				ImageIcon ii = new ImageIcon(getClass().getResource("/fud.png"));
				frmPregledKonobara.setIconImage(ii.getImage());
				frmPregledKonobara
						.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frmPregledKonobara.setTitle("Pregled konobara");
			}
		});
		mnKonobari.add(jmiPregledKonobara);
		
		JMenu jmNarudzbine = new JMenu("Narudzbine");
		menuBar.add(jmNarudzbine);

		JMenuItem mntmUnosNoveNarudzbine = new JMenuItem("Unos nove narudzbine");
		mntmUnosNoveNarudzbine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FRMUnosNoveNarudzbine frmUnosNoveNarudzbine = new FRMUnosNoveNarudzbine();
				frmUnosNoveNarudzbine.setVisible(true);
				//postavljanje ikonice na FrmUnosNoveNarudzbine
				ImageIcon ii = new ImageIcon(getClass().getResource("/fud.png"));
				frmUnosNoveNarudzbine.setIconImage(ii.getImage());
				frmUnosNoveNarudzbine
						.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frmUnosNoveNarudzbine.setTitle("Unos nove narudzbine");
			}
		});
		jmNarudzbine.add(mntmUnosNoveNarudzbine);

		JScrollPane spListaNarudzbina = new JScrollPane();
		spListaNarudzbina.setBounds(10, 32, 853, 507);
		frame.getContentPane().add(spListaNarudzbina);

		jlistNarudzbine = new JList();
		jlistNarudzbine.setFont(new Font("Arial",Font.BOLD,24));
		spListaNarudzbina.setViewportView(jlistNarudzbine);

	}

	private void pokreniServer() {
		ServerskaNit serverskaNit = new ServerskaNit();
		serverskaNit.start();
	}
	
}
