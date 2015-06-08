package forme.konobar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import poslovnalogika.Kontroler;
import domen.Konobar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FRMIzmenaKonobara extends JFrame {


	private JPanel contentPane;
	private JTextField jtfUsername;
	private JPasswordField jpfPassword;
	private Konobar konobar;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public FRMIzmenaKonobara(Konobar k) {
		this.konobar = k;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setBounds(10, 35, 88, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setBounds(10, 60, 88, 14);
		contentPane.add(lblPassword);
		
		jtfUsername = new JTextField();
		jtfUsername.setBounds(102, 32, 130, 20);
		contentPane.add(jtfUsername);
		jtfUsername.setColumns(10);
		
		jpfPassword = new JPasswordField();
		jpfPassword.setBounds(102, 57, 130, 20);
		contentPane.add(jpfPassword);
		
		JButton jbtSacuvajKonobara = new JButton("Sacuvaj");
		jbtSacuvajKonobara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sacuvajKonobara();
			}
		});
		jbtSacuvajKonobara.setBounds(10, 103, 88, 23);
		contentPane.add(jbtSacuvajKonobara);
		postaviKonobaraNaFormu();
	}

	private void postaviKonobaraNaFormu() {
		jtfUsername.setText(konobar.getUsername());
		jpfPassword.setText(konobar.getPassword());
		konobar = new Konobar();
		konobar.setUserKojiSeMenja(jtfUsername.getText());
	}

	protected void sacuvajKonobara() {
		String username = jtfUsername.getText();
		String password = String.valueOf(jpfPassword.getPassword());
		konobar.setUsername(username);
		konobar.setPassword(password);
		Kontroler.getInstance().izmeniKonobara(konobar);
		JOptionPane.showMessageDialog(this, "Uspesno ste izmenili konobara : "+konobar.getUsername(), "Izmena konobara", JOptionPane.INFORMATION_MESSAGE);
	}
}
