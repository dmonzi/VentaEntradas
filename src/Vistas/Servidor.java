package Vistas;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextField;

import Controller.Server;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Servidor {

	private JFrame frmServidor;
	private JTextField total;
	private JTextField tot_vip;
	private JTextField restante;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField tot_plus;
	private JTextField tot_normales;
	
	private static int entradasTotales = 0;

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
		frmServidor.setBounds(100, 100, 450, 300);
		frmServidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServidor.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Servidor de entradas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 305, 31);
		frmServidor.getContentPane().add(lblNewLabel);
		
		total = new JTextField();
		total.setEditable(false);
		total.setBounds(325, 230, 86, 20);
		frmServidor.getContentPane().add(total);
		total.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Facturación:");
		lblNewLabel_1.setBounds(254, 233, 72, 14);
		frmServidor.getContentPane().add(lblNewLabel_1);
		
		tot_vip = new JTextField();
		tot_vip.setText("0");
		tot_vip.setBounds(20, 194, 86, 20);
		frmServidor.getContentPane().add(tot_vip);
		tot_vip.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Entradas disponibles: ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(20, 47, 117, 14);
		frmServidor.getContentPane().add(lblNewLabel_2_1);
		
		restante = new JTextField();
		restante.setEditable(false);
		restante.setColumns(10);
		restante.setBounds(140, 45, 86, 20);
		frmServidor.getContentPane().add(restante);
		
		JButton inicio = new JButton("Iniciar Venta");
		inicio.setBackground(new Color(255, 255, 255));
		inicio.setBounds(301, 44, 110, 23);
		frmServidor.getContentPane().add(inicio);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(20, 78, 86, 86);
		frmServidor.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(173, 78, 86, 86);
		frmServidor.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(325, 78, 86, 86);
		frmServidor.getContentPane().add(textField_5);
		
		JLabel lblNewLabel_3 = new JLabel("VIP:");
		lblNewLabel_3.setBounds(20, 169, 46, 14);
		frmServidor.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Venta 2");
		lblNewLabel_3_1.setBounds(173, 169, 46, 14);
		frmServidor.getContentPane().add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Venta 3");
		lblNewLabel_3_2.setBounds(326, 169, 46, 14);
		frmServidor.getContentPane().add(lblNewLabel_3_2);
		
		tot_plus = new JTextField();
		tot_plus.setText("0");
		tot_plus.setColumns(10);
		tot_plus.setBounds(173, 194, 86, 20);
		frmServidor.getContentPane().add(tot_plus);
		
		tot_normales = new JTextField();
		tot_normales.setText("0");
		tot_normales.setColumns(10);
		tot_normales.setBounds(325, 194, 86, 20);
		frmServidor.getContentPane().add(tot_normales);
		
		inicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tot_vip.setEnabled(false);
				tot_normales.setEnabled(false);
				tot_plus.setEnabled(false);
				
				entradasTotales = Integer.parseInt(tot_vip.getText()) + Integer.parseInt(tot_plus.getText()) + Integer.parseInt(tot_normales.getText());
				restante.setText(entradasTotales + "");
				//				Iniciar el servidor
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
