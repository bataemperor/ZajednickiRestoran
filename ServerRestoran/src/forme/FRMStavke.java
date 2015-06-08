package forme;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

import forme.modeltabele.ModelTabeleNarudzbine;

public class FRMStavke extends JDialog {
	private JTable jtblStavke;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			FRMStavke dialog = new FRMStavke();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public FRMStavke(ModelTabeleNarudzbine mtn) {
		setBounds(100, 100, 840, 470);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 804, 294);
		getContentPane().add(scrollPane);
		
		jtblStavke = new JTable();
		scrollPane.setViewportView(jtblStavke);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setBounds(369, 372, 89, 23);
		getContentPane().add(btnOk);
		jtblStavke.setModel(mtn);
	}
}
