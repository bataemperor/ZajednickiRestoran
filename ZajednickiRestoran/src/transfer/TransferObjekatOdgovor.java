package transfer;

import java.io.Serializable;

public class TransferObjekatOdgovor implements Serializable {
	private String odgovor;
	private Object rezultat;

	public String getOdgovor() {
		return odgovor;
	}

	public void setOdgovor(String odgovor) {
		this.odgovor = odgovor;
	}

	public Object getRezultat() {
		return rezultat;
	}

	public void setRezultat(Object rezultat) {
		this.rezultat = rezultat;
	}

}
