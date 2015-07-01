package baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import domen.Konobar;
import domen.OpstiDomenskiObjekat;
import domen.StavkaNarudzbine;

public class DatabaseBroker {
	private Connection connection;
	private static DatabaseBroker instance = new DatabaseBroker();

	public static DatabaseBroker getInstance() {
		return instance;
	}

	// public void otvoriKonekciju() throws RuntimeException {
	// try {
	// connection = DriverManager
	// .getConnection("jdbc:ucanaccess://C:/Users/Bata/Desktop/dipl/restoran.accdb");
	// connection.setAutoCommit(false);// zahteva elksplicitnu potvrdu
	// // transakcije
	// } catch (Exception ex) {
	// throw new RuntimeException("Neuspesno otvaranje konekcije!");
	// }
	// }

	public void otvoriKonekciju() throws java.lang.Exception {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			throw new Exception("Ne postoji driver baze podataka!");
		}
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restoran", "root", "");
			connection.setAutoCommit(false);
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new Exception("Greska prilikom uspostavljanja konekcije!");
		}
		// System.out.println("Uspesna konekcija!");
	}

	public void commitTransakcije() throws RuntimeException {
		try {
			connection.commit();
		} catch (SQLException ex) {
			throw new RuntimeException("Neuspesan commit!");
		}
	}

	public void rollbackTransakcije() throws RuntimeException {
		try {
			connection.rollback();
		} catch (SQLException ex) {
			throw new RuntimeException("Neuspesan rollback!");
		}
	}

	public void zatvoriKonekciju() throws RuntimeException {
		try {
			connection.close();
		} catch (SQLException ex) {
			throw new RuntimeException("Neuspesno zatvaranje konekcije!");
		}
	}

	public List<OpstiDomenskiObjekat> vratiListu(OpstiDomenskiObjekat odo) {
		try {
			String sql = "SELECT * FROM " + odo.vratiNazivTabele()
					+ odo.uslov3();

			// System.out.println(sql);
			Statement sqlNaredba = connection.createStatement();
			ResultSet rs = sqlNaredba.executeQuery(sql);
			return odo.vratiListu(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Neuspesno vracanje liste");
		}
	}

	public List<OpstiDomenskiObjekat> pronadjiListu(OpstiDomenskiObjekat odo) {
		try {
			String sql = "SELECT * FROM " + odo.vratiNazivTabele()
					+ odo.uslov() + odo.uslov3();
			// System.out.println(sql);
			Statement sqlNaredba = connection.createStatement();
			ResultSet rs = sqlNaredba.executeQuery(sql);
			return odo.vratiListu(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Neuspesno vracanje liste");
		}
	}

	public void sacuvaj(OpstiDomenskiObjekat odo) {
		try {
			String sql = "INSERT INTO " + odo.vratiNazivTabeleZaInsert()
					+ " VALUES (" + odo.vratiParametreZaInsert() + ")";
			System.out.println(sql);
			Statement sqlNaredba = connection.createStatement();
			// sqlNaredba.executeQuery(sql); pravilo gresku
			sqlNaredba.executeUpdate(sql);
			sqlNaredba.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("" + e.getMessage());
			e.printStackTrace();
		}
	}

	public int kreirajSifru(OpstiDomenskiObjekat odo) {
		try {
			String sql = "SELECT MAX(" + odo.vratiNazivKolonePrimarnogKljuca()
					+ ") AS Sifra FROM " + odo.vratiNazivTabele();
			System.out.println(sql);
			Statement sqlNaredba = connection.createStatement();
			ResultSet rs = sqlNaredba.executeQuery(sql);
			int sifra = 0;
			if (rs.next()) {
				try {
					sifra = Integer.parseInt(rs.getString("Sifra"));
				} catch (Exception e) {
					System.out.println("Prvi unos u bazu");
				}

			}
			sifra++;
			rs.close();
			sqlNaredba.close();
			return sifra;
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Neuspesno generisanje sifre racuna!");
		}

	}

	public void obrisi(OpstiDomenskiObjekat odo) {
		try {
			String sql = "";
			// if (odo instanceof Clan) {
			// sql = "DELETE FROM " + odo.vratiNazivTabeleZaBrisanje() +
			// " WHERE " + odo.vratiNazivKolonePrimarnogKljuca() + " = " + "'" +
			// odo.vratiSifru() + "'";
			// } else {
			// sql = "DELETE FROM " + odo.vratiNazivTabeleZaBrisanje() +
			// " WHERE " + odo.vratiNazivKolonePrimarnogKljuca() + " = " +
			// odo.vratiSifru();
			// }
			if(odo instanceof StavkaNarudzbine){
				sql = "DELETE FROM " + odo.vratiNazivTabeleZaBrisanje() + " WHERE "
						+ odo.vratiNazivKolonePrimarnogKljuca() + " = "
						+ odo.vratiSifru() + " AND RbStavke = "+odo.uslov2();
			}
			else{
				sql = "DELETE FROM " + odo.vratiNazivTabeleZaBrisanje() + " WHERE "
						+ odo.vratiNazivKolonePrimarnogKljuca() + " = "
						+ odo.vratiSifru();
			}
			System.out.println(sql);
			PreparedStatement sqlNaredba = connection.prepareStatement(sql);
			sqlNaredba.executeUpdate();
			sqlNaredba.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Neuspesno cuvanje izmena");
		}
	}

	public void izmeni(OpstiDomenskiObjekat odo) {
		try {
			String sql = "";
			if (odo instanceof Konobar) {
				sql = "UPDATE " + odo.vratiNazivTabeleZaUpdate() + " SET "
						+ odo.vratiParametreZaUpdate() + " WHERE "
						+ odo.vratiNazivKolonePrimarnogKljuca() + " = '"
						+ odo.uslov2() + "'";
			}

			else if (odo instanceof StavkaNarudzbine) {
				sql = "UPDATE " + odo.vratiNazivTabeleZaUpdate() + " SET "
						+ odo.vratiParametreZaUpdate() + " WHERE "
						+ odo.vratiNazivKolonePrimarnogKljuca() + " = "
						+ odo.vratiSifru() + " AND RbStavke = "+odo.uslov2();
			}
			else {
				sql = "UPDATE " + odo.vratiNazivTabeleZaUpdate() + " SET "
						+ odo.vratiParametreZaUpdate() + " WHERE "
						+ odo.vratiNazivKolonePrimarnogKljuca() + " = "
						+ odo.vratiSifru();
			}
			// sql = "UPDATE " + odo.vratiNazivTabeleZaUpdate() + " SET "
			// + odo.vratiParametreZaUpdate() + " WHERE "
			// + odo.vratiNazivKolonePrimarnogKljuca() + " = "
			// + odo.vratiSifru();
			System.out.println(sql);
			PreparedStatement sqlNaredba = connection.prepareStatement(sql);
			sqlNaredba.executeUpdate();
			sqlNaredba.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Neuspesno cuvanje izmena");
		}
	}

}
