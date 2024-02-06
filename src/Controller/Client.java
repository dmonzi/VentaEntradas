package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import Vistas.Cliente;

public class Client {

	private static int precioVip = 100;
	private static int precioPlus = 40;
	private static int precioNormal = 20;

	private static OutputStream out;
	private static BufferedReader buffer;
	private static Socket server;

	private static int cantidad = 0;
	private static int precio = 0;

	public static int getCantidad() {
		return cantidad;
	}

	public static void setCantidad(int cantidad) {
		Client.cantidad = cantidad;
	}

	public static int getPrecio() {
		return precio;
	}

	public static void setPrecio(int precio) {
		Client.precio = precio;
	}

	public static void sumar(int op, int numero) {
		switch (op) {
		case 49:
			precio = precio + (precioVip*numero);
			break;
		case 50:
			precio = precio + (precioPlus*numero);
			break;
		case 51:
			precio = precio + (precioNormal*numero);
			break;
		default:
			break;
		}
	}

	public static void conectar() throws InterruptedException {

		boolean conectado = false;

		while (!conectado) {
			try {
				server = new Socket("localhost", 123);
				conectado = true;

//				Inicializar las variables de comunicacion con el servidor
				out = server.getOutputStream();
				buffer = new BufferedReader(new InputStreamReader(server.getInputStream()));

			} catch (IOException e) {
				System.err.println("Esperando al servidor");
				Thread.sleep(2000);
			}
		}
	}

	public static void pedirEntradas() throws IOException {
		String[] listado = buffer.readLine().split("%");
		Cliente.getNormal().setText(listado[0]);
		Cliente.getPlus().setText(listado[1]);
		Cliente.getVip().setText(listado[2]);

	}

	public static void mandar(String mensaje) throws IOException {
		out.write(mensaje.getBytes());
		out.flush();
	}
}
