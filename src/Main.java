import java.io.IOException;
import java.net.Socket;

import Vistas.Servidor;

public class Main {
	public static void main(String[] args) throws InterruptedException {
//		Uso esta clase como lanzador de las otras dos
//		Servidor.main(args);
		
		while (true) {
			try {
				Socket server = new Socket("localhost", 123);
			} catch (IOException e) {
				System.err.println("Esperando al servidor");
				Thread.sleep(2000);
			}
		}
	}
}
