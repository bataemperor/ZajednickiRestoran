package komunikacija;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Komunikacija {
	private Socket socket;
	private boolean kraj = false;
	private ServerSocket serverSocket;

	public void pokreniServer() throws IOException {
		serverSocket = new ServerSocket(9998);
		System.out.println("Server je pokrenut i spreman za rad");
		while (!kraj) {
			socket = serverSocket.accept();
			System.out.println("Klijent se povezao");
			NitKlijent nitKlijent = new NitKlijent(socket);
			nitKlijent.start();
		}
	}
}
