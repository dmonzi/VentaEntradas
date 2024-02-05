package Controller;

import java.net.Socket;

public class Dispatcher extends Thread{
	
	private Socket cliente;
	
	public Dispatcher(Socket cliente) {
		super();
		this.cliente = cliente;
	}

	@Override
	public void run(){
//		ejecutar peticiones del cliente
		System.out.println("hilo conectado" + Thread.currentThread().getName());
	}
}
