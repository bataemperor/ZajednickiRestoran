package forme.narudzbine;

import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import poslovnalogika.Kontroler;
import domen.Narudzbina;
import domen.Proizvod;
import forme.modeltabele.ModelTabeleNarudzbine;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FRMUnosNoveNarudzbine extends JFrame {

	private JPanel contentPane;
	private JTable jtblStavkeNarudzbine;
	private JLabel jlDatum;
	private JComboBox jcbBrojStola;
	private ModelTabeleNarudzbine mtn;
	private JLabel jlUkupanIznos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMUnosNoveNarudzbine frame = new FRMUnosNoveNarudzbine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FRMUnosNoveNarudzbine() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDatum = new JLabel("Datum : ");
		lblDatum.setBounds(21, 23, 57, 14);
		contentPane.add(lblDatum);

		jlDatum = new JLabel("New label");
		jlDatum.setBounds(119, 23, 128, 14);
		contentPane.add(jlDatum);

		JLabel lblBrojStola = new JLabel("Broj stola : ");
		lblBrojStola.setBounds(21, 54, 88, 14);
		contentPane.add(lblBrojStola);

		jcbBrojStola = new JComboBox();
		jcbBrojStola.setBounds(119, 51, 128, 20);
		contentPane.add(jcbBrojStola);

		JLabel lblUkupanIznos = new JLabel("Ukupan iznos :");
		lblUkupanIznos.setBounds(21, 85, 97, 14);
		contentPane.add(lblUkupanIznos);

		jlUkupanIznos = new JLabel("Ukupan iznos");
		jlUkupanIznos.setBounds(119, 85, 128, 14);
		contentPane.add(jlUkupanIznos);

		JLabel lblStatus = new JLabel("Status : ");
		lblStatus.setBounds(21, 116, 46, 14);
		contentPane.add(lblStatus);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(119, 116, 128, 14);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 171, 552, 183);
		contentPane.add(scrollPane);

		jtblStavkeNarudzbine = new JTable();
		scrollPane.setViewportView(jtblStavkeNarudzbine);

		JButton jbtDodajStavku = new JButton("Dodaj stavku");
		jbtDodajStavku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dodajStavku();
			}
		});
		jbtDodajStavku.setBounds(21, 376, 113, 23);
		contentPane.add(jbtDodajStavku);

		JButton btnObrisiStavku = new JButton("Obrisi stavku");
		btnObrisiStavku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obrisiStavku();
			}
		});
		btnObrisiStavku.setBounds(187, 376, 113, 23);
		contentPane.add(btnObrisiStavku);

		JButton btnSacuvajNarudzbinu = new JButton("Sacuvaj narudzbinu");
		btnSacuvajNarudzbinu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sacuvajNarudzbinu();
			}
		});
		btnSacuvajNarudzbinu.setBounds(419, 376, 154, 23);
		contentPane.add(btnSacuvajNarudzbinu);
		popuniFormu();
	}

	protected void sacuvajNarudzbinu() {
		// TODO Auto-generated method stub
		Narudzbina narudzbina = mtn.getNarudzbina();
		narudzbina.setBrojStola((int) jcbBrojStola.getSelectedItem());
		narudzbina.setDatumNarudzbine(new Date());
		narudzbina.setUkupanIznos(Integer.parseInt(jlUkupanIznos.getText()));

		Kontroler.getInstance().sacuvajNarudzbinu(narudzbina);
		JOptionPane.showMessageDialog(jtblStavkeNarudzbine,
				"Uspešno ste sacuvali narudzbinu", "Nova narudzbina",
				JOptionPane.INFORMATION_MESSAGE);
	}

	protected void obrisiStavku() {
		int izabraniRed = jtblStavkeNarudzbine.getSelectedRow();
		if (izabraniRed > -1) {

			ModelTabeleNarudzbine mtn = (ModelTabeleNarudzbine) jtblStavkeNarudzbine
					.getModel();
			mtn.obrisiStavku(izabraniRed);
		} else {
			JOptionPane.showMessageDialog(jtblStavkeNarudzbine,
					"Niste odabrali stavku", "Greska",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	protected void dodajStavku() {
		ModelTabeleNarudzbine mtn = (ModelTabeleNarudzbine) jtblStavkeNarudzbine
				.getModel();
		mtn.dodajStavku();
	}

	private void popuniFormu() {
		int[] brojeviStolova = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (int i = 0; i < brojeviStolova.length; i++) {
			jcbBrojStola.addItem(brojeviStolova[i]);
		}
		// postavljanje danasnjeg datuma
		jlDatum.setText(new SimpleDateFormat().format(new Date()));
		// postavljanje modela tabele
		Narudzbina narudzbina = new Narudzbina();
		narudzbina.setNarudzbinaID(Kontroler.getInstance().vratiIDNarudzbine());
		mtn = new ModelTabeleNarudzbine(narudzbina);
		jtblStavkeNarudzbine.setModel(mtn);
		// kombo boks Proizvodi

		List<Proizvod> listaProizvoda = (List<Proizvod>) (Object) (Kontroler
				.getInstance().vratiSveProizvode());

		TableColumn tcProizvod = jtblStavkeNarudzbine.getColumnModel()
				.getColumn(2);
		JComboBox jcbProizvod = new JComboBox();
		for (Proizvod p : listaProizvoda) {
			jcbProizvod.addItem(p);
		}
		tcProizvod.setCellEditor(new DefaultCellEditor(jcbProizvod));

		// postavljanje listener-a koji ce izracunavati ukupan iznos
		mtn.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				mtn.getNarudzbina().izracunajUkupanIznos();
				jlUkupanIznos.setText(String.valueOf(mtn.getNarudzbina()
						.getUkupanIznos()));
			}
		});
	}
}
