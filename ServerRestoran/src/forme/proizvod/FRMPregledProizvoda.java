package forme.proizvod;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.JTable;

import poslovnalogika.Kontroler;
import poslovnalogika.so.proizvod.VratiSveProizvodeSO;
import domen.OpstiDomenskiObjekat;
import domen.Proizvod;
import forme.modeltabele.ModelTabeleProizvodi;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FRMPregledProizvoda extends JFrame {

	private JPanel contentPane;
	private JTable jtblProizvodi;
	private JTextField jtfPronadjiProizvod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMPregledProizvoda frame = new FRMPregledProizvoda();
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
	public FRMPregledProizvoda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton jbtObrisiProizvod = new JButton("Obrisi");
		jbtObrisiProizvod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				obrisiProizvod();
			}

		});
		jbtObrisiProizvod.setBounds(10, 356, 89, 23);
		// jbtObrisiProizvod.setBorderPainted(false);
		jbtObrisiProizvod.setForeground(Color.BLACK);
		jbtObrisiProizvod.setBackground(Color.WHITE);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		jbtObrisiProizvod.setBorder(compound);
		contentPane.add(jbtObrisiProizvod);

		JButton btnNewButton = new JButton("Izmeni");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				izmeniProizvod();
			}
		});
		btnNewButton.setBounds(461, 356, 89, 23);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 540, 272);
		contentPane.add(scrollPane);

		jtblProizvodi = new JTable();
		scrollPane.setViewportView(jtblProizvodi);
		
		JLabel lblPronadjiProizvod = new JLabel("Pronadji proizvod : ");
		lblPronadjiProizvod.setBounds(10, 26, 112, 14);
		contentPane.add(lblPronadjiProizvod);
		
		jtfPronadjiProizvod = new JTextField();
		jtfPronadjiProizvod.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				pretraziProizvode();
			}
		});
		jtfPronadjiProizvod.setBounds(120, 23, 176, 20);
		contentPane.add(jtfPronadjiProizvod);
		jtfPronadjiProizvod.setColumns(10);
		popuniTabelu();
	}

	protected void pretraziProizvode() {
		// pretrazivanje proizvoda po nazivu
		Proizvod p = new Proizvod(jtfPronadjiProizvod.getText().trim());
		List<Proizvod> listaProizvoda = (List<Proizvod>) (Object) (Kontroler
				.getInstance().pretraziProizvode(p));
		jtblProizvodi.setModel(new ModelTabeleProizvodi(listaProizvoda));
		
	}

	protected void izmeniProizvod() {
		// pozivanje forme Izmeni proizvod i prosledjivanje tog proizvoda
		int selektovaniRed = jtblProizvodi.getSelectedRow();
		if (selektovaniRed > -1) {
			ModelTabeleProizvodi mtp = (ModelTabeleProizvodi) jtblProizvodi
					.getModel();
			Proizvod proizvod = mtp.vratiSelektovaniProizvod(selektovaniRed);
			FRMIzmenaProizvoda frmIzmenaProizvoda = new FRMIzmenaProizvoda(proizvod);
			frmIzmenaProizvoda.setVisible(true);
			frmIzmenaProizvoda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frmIzmenaProizvoda.setTitle("Izmena proizvoda");
		} else {
			JOptionPane.showMessageDialog(jtblProizvodi,
					"Niste selektovali proizvod za izmenu", "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

	protected void obrisiProizvod() {
		// brisanje proizvoda
		int selektovaniRed = jtblProizvodi.getSelectedRow();
		if (selektovaniRed > -1) {
			ModelTabeleProizvodi mtp = (ModelTabeleProizvodi) jtblProizvodi
					.getModel();
			Kontroler.getInstance().obrisiProizvod(
					mtp.vratiSelektovaniProizvod(selektovaniRed));
			JOptionPane.showMessageDialog(jtblProizvodi,
					"Proizvod je obrisan!", "Brisanje proizvoda",
					JOptionPane.INFORMATION_MESSAGE);
			mtp.obrisiRed(selektovaniRed);
		} else {
			JOptionPane.showMessageDialog(jtblProizvodi,
					"Niste selektovali proizvod za brisanje", "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void popuniTabelu() {
		// TODO Auto-generated method stub

		List<Proizvod> listaProizvoda = (List<Proizvod>) (Object) (Kontroler
				.getInstance().vratiSveProizvode());

		ModelTabeleProizvodi mtp = new ModelTabeleProizvodi(listaProizvoda);
		jtblProizvodi.setModel(mtp);
		jtblProizvodi.getColumnModel().getColumn(0).setMinWidth(100);
		// jtblProizvodi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// jtblProizvodi
		// .setPreferredScrollableViewportSize(new Dimension(500, 70));
		jtblProizvodi.setFillsViewportHeight(true);
		// update(this);
	}
}
