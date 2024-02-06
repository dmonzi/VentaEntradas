package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import Vistas.Servidor;

public class Dispatcher extends Thread {

	private static Socket cliente;
	private static OutputStream out;
	private static BufferedReader buffer;

	public static boolean comprar = false;

	public Dispatcher(Socket cliente) {
		super();
		Dispatcher.cliente = cliente;
	}

	@Override
	public void run() {

		try {
//		Inicializar las variables de comunicacion con el servidor
			out = cliente.getOutputStream();
			buffer = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

			mandar(Servidor.getTot_normales().getText() + "%" + Servidor.getTot_plus().getText() + "%"
					+ Servidor.getTot_vip().getText() + "\n");
			while (!comprar) {
				reservar(leer());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

//		ejecutar peticiones del cliente
		System.out.println("hilo conectado" + Thread.currentThread().getName());

	}
	
	private static void reservar(String[] op) {
		switch (op[0]) {
		case "50":
//			normales
			Servidor.getTot_plus()
					.setText((Integer.parseInt(Servidor.getTot_normales().getText()) - Integer.parseInt(op[1])) + "");
			break;
		case "51":
			Servidor.getTot_normales()
					.setText((Integer.parseInt(Servidor.getTot_normales().getText()) - Integer.parseInt(op[1])) + "");
			break;
		case "49":
			Servidor.getTot_vip()
					.setText((Integer.parseInt(Servidor.getTot_vip().getText()) - Integer.parseInt(op[1])) + "");
			break;
		default:
			break;
		}
		
		if (op[0].equals("comprar")) {
			comprar = true;
			Servidor.getTotal().setText((Integer.parseInt(Servidor.getTotal().getText())+Integer.parseInt(op[1]))+"");
//			;
		}

	}

	private static String[] leer() throws IOException {
		String salida = buffer.readLine();
		return salida.split("%");
	}

	private static void mandar(String mensaje) throws IOException {
		out.write(mensaje.getBytes());
		out.flush();
	}
}
