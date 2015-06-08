package forme.konobar;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;

import poslovnalogika.Kontroler;
import domen.Konobar;
import domen.Proizvod;
import forme.modeltabele.ModelListeKonobari;
import forme.modeltabele.ModelTabeleProizvodi;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class FRMPregledKonobara extends JFrame {

	private JPanel contentPane;
	private JList jlistKonobari;
	private JTextField jtfUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRMPregledKonobara frame = new FRMPregledKonobara();
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
	public FRMPregledKonobara() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 371, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton jbtObrisiKonobara = new JButton("Obri\u0161i");
		jbtObrisiKonobara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				obrisiKonobara();
			}
		});
		jbtObrisiKonobara.setBounds(188, 45, 89, 23);
		contentPane.add(jbtObrisiKonobara);

		JButton jbtIzmeni = new JButton("Izmeni");
		jbtIzmeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				izmeniKonobara();
			}
		});
		jbtIzmeni.setBounds(188, 79, 89, 23);
		contentPane.add(jbtIzmeni);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 168, 157);
		contentPane.add(scrollPane);

		jlistKonobari = new JList();
		jlistKonobari.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				popuniListu();
			}
		});
		scrollPane.setViewportView(jlistKonobari);

		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setBounds(10, 15, 77, 14);
		contentPane.add(lblUsername);

		jtfUsername = new JTextField();
		jtfUsername.setBounds(70, 12, 108, 20);
		contentPane.add(jtfUsername);
		jtfUsername.setColumns(10);
		popuniListu();

	}

	protected void izmeniKonobara() {
		int selektovaniRed = jlistKonobari.getSelectedIndex();
		if (selektovaniRed != -1) {
			Konobar k = (Konobar) jlistKonobari.getSelectedValue();
			FRMIzmenaKonobara frmIzmenaKonobara = new FRMIzmenaKonobara(k);
			frmIzmenaKonobara.setVisible(true);
			frmIzmenaKonobara.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frmIzmenaKonobara.setTitle("Izmena konobara : " + k.getUsername());
		} else {
			JOptionPane.showMessageDialog(jlistKonobari,
					"Morate izabrati konobara za izmenu", "Izmena",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	protected void obrisiKonobara() {
		int selektovaniRed = jlistKonobari.getSelectedIndex();
		if (selektovaniRed != -1) {
			Konobar k = (Konobar) jlistKonobari.getSelectedValue();
			try {
				Kontroler.getInstance().obrisiKonobara(k);

				JOptionPane.showMessageDialog(jlistKonobari,
						"Uspesno ste obrisali konobara", "Brisanje konobara",
						JOptionPane.INFORMATION_MESSAGE);
				popuniListu();
			} catch (Exception e) {
				JOptionPane
						.showMessageDialog(
								jlistKonobari,
								"Ne mozete izbrisati ovog konobara jer postoje njegove narudzbine !",
								"Brisanje konobara", JOptionPane.ERROR_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(jlistKonobari,
					"Morate izabrati konobara za brisanje",
					"Brisanje konobara", JOptionPane.WARNING_MESSAGE);
		}

	}

	private void popuniListu() {
		List<Konobar> listaKonobara = (List<Konobar>) (Object) Kontroler
				.getInstance().vratiSveKonobare();
		jlistKonobari.setModel(new ModelListeKonobari(listaKonobara));
	}
}
