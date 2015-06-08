package forme.proizvod;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;

import poslovnalogika.Kontroler;
import poslovnalogika.so.proizvod.SacuvajProizvodSO;
import domen.Proizvod;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FRMIzmenaProizvoda extends JFrame {
	private Proizvod proizvod;
	private JPanel contentPane;
	private JComboBox jcbTipProizvoda;
	private JButton jbtSacuvaj;
	private JTextField jtfNazivProizvoda;
	private JTextField jtfKolicinaProizvoda;
	private JTextField jtfCenaProizvoda;
	private JTextArea jtaOpisProizvoda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMUnosProizvoda frame = new FRMUnosProizvoda();
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
	public FRMIzmenaProizvoda(Proizvod proizvod) {
		this.proizvod = proizvod;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jlNazivProizvoda = new JLabel("Naziv : ");
		jlNazivProizvoda.setBounds(20, 33, 46, 14);
		contentPane.add(jlNazivProizvoda);

		JLabel jlCenaProzvoda = new JLabel("Cena : ");
		jlCenaProzvoda.setBounds(20, 58, 46, 14);
		contentPane.add(jlCenaProzvoda);

		JLabel jlKolicina = new JLabel("Kolicina : ");
		jlKolicina.setBounds(20, 83, 67, 14);
		contentPane.add(jlKolicina);

		JLabel jlTip = new JLabel("Tip : ");
		jlTip.setBounds(20, 108, 46, 14);
		contentPane.add(jlTip);

		JLabel jlOpis = new JLabel("Opis : ");
		jlOpis.setBounds(20, 133, 46, 14);
		contentPane.add(jlOpis);

		jtfNazivProizvoda = new JTextField();
		jtfNazivProizvoda.setBounds(97, 30, 185, 20);
		contentPane.add(jtfNazivProizvoda);
		jtfNazivProizvoda.setColumns(10);

		jtfCenaProizvoda = new JTextField();
		jtfCenaProizvoda.setColumns(10);
		jtfCenaProizvoda.setBounds(97, 55, 185, 20);
		contentPane.add(jtfCenaProizvoda);

		jtfKolicinaProizvoda = new JTextField();
		jtfKolicinaProizvoda.setColumns(10);
		jtfKolicinaProizvoda.setBounds(97, 80, 185, 20);
		contentPane.add(jtfKolicinaProizvoda);

		jcbTipProizvoda = new JComboBox();
		jcbTipProizvoda.setBounds(97, 105, 185, 20);
		contentPane.add(jcbTipProizvoda);

		jbtSacuvaj = new JButton("Sacuvaj promene");
		jbtSacuvaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				izmeniProizvod();
			}

		});
		jbtSacuvaj.setBounds(20, 239, 142, 23);
		contentPane.add(jbtSacuvaj);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 132, 185, 93);
		contentPane.add(scrollPane);

		jtaOpisProizvoda = new JTextArea();
		scrollPane.setViewportView(jtaOpisProizvoda);
		jtaOpisProizvoda.setWrapStyleWord(true);
		jtaOpisProizvoda.setLineWrap(true);

		popuniFormu();
		
	}

	protected void izmeniProizvod() {
		// TODO Auto-generated method stub
		String nazivProizvoda = jtfNazivProizvoda.getText().trim();
		int cenaProizvoda = Integer.parseInt(jtfCenaProizvoda.getText().trim());
		int kolicinaNaStanju = Integer.parseInt(jtfKolicinaProizvoda.getText()
				.trim());
		String tipProizvoda = String.valueOf(jcbTipProizvoda.getSelectedItem());
		String opisProizvoda = jtaOpisProizvoda.getText().trim();
		Proizvod p = new Proizvod();
		p.setNazivProizvoda(nazivProizvoda);
		p.setCenaProizvoda(cenaProizvoda);
		p.setKolicinaNaStanju(kolicinaNaStanju);
		p.setTipProizvoda(tipProizvoda);
		p.setOpisProizvoda(opisProizvoda);
		p.setProizvodID(proizvod.getProizvodID());
		Kontroler.getInstance().izmeniProizvod(p);
		
		JOptionPane.showMessageDialog(this, "Uspesno ste izmeni proizvod : "+p.getNazivProizvoda(), "Izmena proizvoda", JOptionPane.INFORMATION_MESSAGE);
	}

	private void popuniFormu() {
		// punjenje kombo boksa
		String[] tipovi = { "Pice", "Dorucak", "Predjelo", "Supe",
				"Glavno jelo", "Desert" };
		jcbTipProizvoda.setModel(new DefaultComboBoxModel(tipovi));
		jcbTipProizvoda.setSelectedItem(proizvod.getTipProizvoda());
		jtfNazivProizvoda.setText(proizvod.getNazivProizvoda());
		jtfCenaProizvoda.setText(String.valueOf(proizvod.getCenaProizvoda()));
		jtfKolicinaProizvoda.setText(String.valueOf(proizvod.getKolicinaNaStanju()));
		jtaOpisProizvoda.setText(proizvod.getOpisProizvoda());
		
	}

}
