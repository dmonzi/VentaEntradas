package Vistas;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JTextField;

import Controller.Client;

import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Cliente {

	private JFrame frmClientes;
	private static JTextField vip;
	private static JTextField plus;
	private static JTextField normal;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public static JTextField getVip() {
		return vip;
	}

	public static void setVip(JTextField vip) {
		Cliente.vip = vip;
	}

	public static JTextField getPlus() {
		return plus;
	}

	public static void setPlus(JTextField plus) {
		Cliente.plus = plus;
	}

	public static JTextField getNormal() {
		return normal;
	}

	public static void setNormal(JTextField normal) {
		Cliente.normal = normal;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente window = new Cliente();
					window.frmClientes.setVisible(true);
					Thread hilo = new Thread(() -> {
						try {
							Client.conectar();
							Client.pedirEntradas();
						} catch (InterruptedException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
					hilo.start();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frmClientes = new JFrame();
		frmClientes.setTitle("Clientes");
		frmClientes.setResizable(false);
		frmClientes.setBounds(100, 100, 366, 208);
		frmClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClientes.getContentPane().setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Entrada VIP");
		rdbtnNewRadioButton.setMnemonic('1');
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(20, 53, 109, 23);
		frmClientes.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Entrada Plus");
		rdbtnNewRadioButton_1.setMnemonic('2');
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(20, 78, 109, 23);
		frmClientes.getContentPane().add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_1_1 = new JRadioButton("Entrada Normal");
		rdbtnNewRadioButton_1_1.setMnemonic('3');
		buttonGroup.add(rdbtnNewRadioButton_1_1);
		rdbtnNewRadioButton_1_1.setBounds(20, 104, 125, 23);
		frmClientes.getContentPane().add(rdbtnNewRadioButton_1_1);
		
		JLabel lblNewLabel = new JLabel("Portal de Venta de Entradas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(20, 11, 324, 27);
		frmClientes.getContentPane().add(lblNewLabel);
		
		vip = new JTextField();
		vip.setEditable(false);
		vip.setBounds(155, 53, 86, 20);
		frmClientes.getContentPane().add(vip);
		vip.setColumns(10);
		
		plus = new JTextField();
		plus.setEditable(false);
		plus.setColumns(10);
		plus.setBounds(155, 78, 86, 20);
		frmClientes.getContentPane().add(plus);
		
		normal = new JTextField();
		normal.setEditable(false);
		normal.setColumns(10);
		normal.setBounds(155, 104, 86, 20);
		frmClientes.getContentPane().add(normal);
		
		JButton reserva = new JButton("Reservar");
		reserva.setBounds(152, 134, 89, 23);
		frmClientes.getContentPane().add(reserva);
		
		JButton comprar = new JButton("Comprar");
		comprar.setBounds(251, 134, 89, 23);
		frmClientes.getContentPane().add(comprar);
		
		JComboBox cantidad = new JComboBox();
		cantidad.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		cantidad.setSelectedIndex(0);
		cantidad.setBounds(269, 53, 58, 22);
		frmClientes.getContentPane().add(cantidad);
		
		reserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numero = Client.getCantidad();
				int pedido = cantidad.getSelectedIndex()+1;
				
				try {
					if (numero + pedido <= 3) {
						Client.mandar("" + buttonGroup.getSelection().getMnemonic() + "%" + pedido + "%" + "\n");
						Client.setCantidad(pedido+numero);
						Client.sumar(buttonGroup.getSelection().getMnemonic(), pedido);
						if (Client.getCantidad() == 3) {
							reserva.setEnabled(false);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Solo se puede un total de 3 entradas");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		comprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					comprar.setEnabled(false);
					reserva.setEnabled(false);
					Client.mandar("comprar" + "%" + (Client.getPrecio()) + "\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
