package Vistas;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;

import Controller.Server;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Servidor {

	private JFrame frmServidor;
	private static JTextField total;
	private static JTextField tot_vip;
	private static JTextField tot_plus;
	private static JTextField tot_normales;
	
	public static JTextField getTotal() {
		return total;
	}

	public static void setTotal(JTextField total) {
		Servidor.total = total;
	}

	public static JTextField getTot_vip() {
		return tot_vip;
	}

	public static void setTot_vip(JTextField tot_vip) {
		Servidor.tot_vip = tot_vip;
	}

	public static JTextField getTot_plus() {
		return tot_plus;
	}

	public static void setTot_plus(JTextField tot_plus) {
		Servidor.tot_plus = tot_plus;
	}

	public static JTextField getTot_normales() {
		return tot_normales;
	}

	public static void setTot_normales(JTextField tot_normales) {
		Servidor.tot_normales = tot_normales;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servidor window = new Servidor();
					window.frmServidor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Servidor() {
		initialize();
	}

	private void initialize() {
		frmServidor = new JFrame();
		frmServidor.setResizable(false);
		frmServidor.setTitle("Servidor");
		frmServidor.setBounds(100, 100, 450, 195);
		frmServidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServidor.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Servidor de entradas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 305, 31);
		frmServidor.getContentPane().add(lblNewLabel);
		
		total = new JTextField();
		total.setEditable(false);
		total.setBounds(104, 120, 86, 20);
		total.setText("0");
		frmServidor.getContentPane().add(total);
		total.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Facturación:");
		lblNewLabel_1.setBounds(20, 120, 72, 14);
		frmServidor.getContentPane().add(lblNewLabel_1);
		
		tot_vip = new JTextField();
		tot_vip.setText("0");
		tot_vip.setBounds(20, 85, 86, 20);
		frmServidor.getContentPane().add(tot_vip);
		tot_vip.setColumns(10);
		
		JButton inicio = new JButton("Iniciar Venta");
		inicio.setBackground(new Color(255, 255, 255));
		inicio.setBounds(301, 120, 110, 23);
		frmServidor.getContentPane().add(inicio);
		
		JLabel lblNewLabel_3 = new JLabel("VIP:");
		lblNewLabel_3.setBounds(20, 60, 46, 14);
		frmServidor.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Plus:");
		lblNewLabel_3_1.setBounds(173, 60, 46, 14);
		frmServidor.getContentPane().add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Normal:");
		lblNewLabel_3_2.setBounds(326, 60, 46, 14);
		frmServidor.getContentPane().add(lblNewLabel_3_2);
		
		tot_plus = new JTextField();
		tot_plus.setText("0");
		tot_plus.setColumns(10);
		tot_plus.setBounds(173, 85, 86, 20);
		frmServidor.getContentPane().add(tot_plus);
		
		tot_normales = new JTextField();
		tot_normales.setText("0");
		tot_normales.setColumns(10);
		tot_normales.setBounds(325, 85, 86, 20);
		frmServidor.getContentPane().add(tot_normales);
		
		inicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tot_vip.setEnabled(false);
				tot_normales.setEnabled(false);
				tot_plus.setEnabled(false);
				
				//Iniciar el servidor
				Thread hilo = new Thread(()->{
					try {
						Server.initServer();
//						objectoutputstream
					}catch (Exception e1) {
						e1.printStackTrace();
					}
				});
				hilo.start();
			}
		});
	}
}
