package Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void initServer() throws IOException {
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(123);
		while (true) {
			Socket cliente = server.accept();
			
			Dispatcher dispatcher = new Dispatcher(cliente);
			dispatcher.start();
		}
	}
}
