package Vistas;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Cliente {

	private JFrame frmClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente window = new Cliente();
					window.frmClientes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClientes = new JFrame();
		frmClientes.setTitle("Clientes");
		frmClientes.setResizable(false);
		frmClientes.setBounds(100, 100, 735, 430);
		frmClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClientes.getContentPane().setLayout(null);
	}

}
