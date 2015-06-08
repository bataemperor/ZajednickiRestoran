package transfer;

import java.io.Serializable;

public class TransferObjekatZahtev implements Serializable {
	
	private Object parametar;
	private int operacija;

	public Object getParametar() {
		return parametar;
	}

	public void setParametar(Object parametar) {
		this.parametar = parametar;
	}

	public int getOperacija() {
		return operacija;
	}

	public void setOperacija(int operacija) {
		this.operacija = operacija;
	}

}
