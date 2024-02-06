import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class Cliente {

	private JFrame frame;
	private JTextField txtTotal;
	private int cont = 0;
	private Timer timer = new Timer();
	public static ArrayList<String> listaTicketStrings = new ArrayList<>();
	public static Socket socket;
	public static boolean reservaClicked =false;
	public static Integer cant;
	public int totalEntradas=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente window = new Cliente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application. Cosas que hacer: -cambiar el combobox a 3 txtfields -
	 * hacer los correspondientes sockets para : *total y comprar * -
	 * 
	 * 
	 * 
	 * @throws IOException
	 */
	public Cliente() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(50, 50, 809, 473);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(128, 128, 192));
		panel.setBounds(0, 0, 824, 104);
		frame.getContentPane().add(panel);

		JLabel lblNoQuedanEntradas = new JLabel("Lo sentimos, no quedan entradas. ");

		JLabel lblNewLabel = new JLabel("Primavera Sound Festival");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 22));
		lblNewLabel.setBounds(33, 29, 270, 30);
		panel.add(lblNewLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(198, 70, 5, 5);
		panel.add(tabbedPane);

		JLabel lblVentaDeEntradas = new JLabel("Reserva de entradas");
		lblVentaDeEntradas.setBackground(new Color(255, 255, 255));
		lblVentaDeEntradas.setForeground(new Color(0, 0, 0));
		lblVentaDeEntradas.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 19));
		lblVentaDeEntradas.setBounds(35, 115, 253, 64);
		frame.getContentPane().add(lblVentaDeEntradas);

		txtTotal = new JTextField();
		txtTotal.setBounds(451, 136, 50, 30);
		frame.getContentPane().add(txtTotal);
		txtTotal.setColumns(10);
		JComboBox<String> comboBox_cant = new JComboBox<String>();
		JLabel lblErrorNumEntradas = new JLabel("No se puede reservar más de 3 entradas, ni varias de distinto tipo.");
		JButton btnReserva = new JButton("Reservar");
		JButton btnNewButton = new JButton("Solicitar total");
		JButton btnConfirmar = new JButton("Confirmar compra");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					
					socket = new Socket("localhost", 123);
					
					OutputStream outputStream = socket.getOutputStream();
					//para poder recoger el string luego 
					outputStream.write(("recibir").getBytes());
					outputStream.write(("\n").getBytes());
					
					InputStream inputStream = socket.getInputStream();
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

					BufferedReader bufferedReader = new BufferedReader(inputStreamReader); // Leer y mostrar la
					String respuestaDesdeServidor;
					respuestaDesdeServidor = bufferedReader.readLine();
					System.out.println(respuestaDesdeServidor);
					//System.out.println("el total es: " + respuestaDesdeServidor);

					txtTotal.setText(respuestaDesdeServidor);
					if (Integer.parseInt(respuestaDesdeServidor.toString()) < Integer.parseInt(comboBox_cant.getModel().getSelectedItem().toString()) ) {
						lblNoQuedanEntradas.setVisible(true);
						btnConfirmar.setEnabled(false);
						btnReserva.setEnabled(false);
					}else {
						btnReserva.setEnabled(true);
						btnConfirmar.setEnabled(true);
						lblNoQuedanEntradas.setVisible(false);
					}
					socket.close();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		});
		btnNewButton.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 12));
		btnNewButton.setBounds(308, 131, 118, 38);
		frame.getContentPane().add(btnNewButton);

		
		btnReserva.setForeground(new Color(255, 255, 255));
		btnReserva.setBackground(new Color(0, 0, 0));

		btnReserva.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));
		btnReserva.setBounds(343, 236, 103, 46);
		frame.getContentPane().add(btnReserva);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 14));
		comboBox.setBounds(35, 237, 169, 46);
		comboBox.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Entrada general", "Entrada VIP", "Abono VIP" }));

		frame.getContentPane().add(comboBox);

		
		lblErrorNumEntradas.setForeground(new Color(0, 0, 0));
		lblErrorNumEntradas.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 11));
		lblErrorNumEntradas.setBounds(35, 195, 355, 30);
		lblErrorNumEntradas.setVisible(false);
		;
		frame.getContentPane().add(lblErrorNumEntradas);

		
		btnConfirmar.setForeground(new Color(255, 255, 255));
		btnConfirmar.setBackground(new Color(0, 0, 0));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					
					//está en ese socket y no sale. problema al solicitar total
					Socket socketConfirmar = new Socket("localhost", 123);
					

					OutputStream outputStream = socketConfirmar.getOutputStream();

					outputStream.write(("mandar").getBytes());
					outputStream.write("\n".getBytes());
					outputStream.write((listaTicketStrings.toString()).getBytes());
					outputStream.write("\n".getBytes());
					outputStream.write((cant.toString()).getBytes());
					outputStream.write("\n".getBytes());
					outputStream.flush();
				
				    socketConfirmar.close();
					

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnConfirmar.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 14));
		btnConfirmar.setBounds(587, 347, 158, 53);
		frame.getContentPane().add(btnConfirmar);

		lblNoQuedanEntradas.setForeground(Color.BLACK);
		lblNoQuedanEntradas.setVisible(false);
		lblNoQuedanEntradas.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblNoQuedanEntradas.setBounds(534, 135, 211, 30);
		lblErrorNumEntradas.setVisible(false);
		frame.getContentPane().add(lblNoQuedanEntradas);

		JLabel lblTimeout = new JLabel("Se terminó el tiempo.");
		lblTimeout.setForeground(Color.BLACK);
		lblTimeout.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblTimeout.setBounds(587, 294, 150, 30);
		lblTimeout.setVisible(false);
		frame.getContentPane().add(lblTimeout);

		
		comboBox_cant.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 15));
		comboBox_cant.setBounds(214, 236, 69, 46);
		comboBox_cant.setModel(new DefaultComboBoxModel<String>(new String[] { "1", "2", "3" }));
		frame.getContentPane().add(comboBox_cant);

		btnReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// reserva
				try {
					activarContador(timer, lblTimeout, frame, btnReserva, btnConfirmar);
					cont++;
					
					if (cont <= 1) {
						cant = Integer.parseInt(comboBox_cant.getModel().getSelectedItem().toString());
						String selectedString = comboBox.getModel().getSelectedItem().toString();
						listaTicketStrings.add(selectedString);	
						

					} else {
						
						lblErrorNumEntradas.setVisible(true);
						btnReserva.setEnabled(false);
						btnConfirmar.setEnabled(false);
					}
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}

		});
	}

	private static void activarContador(Timer timer, JLabel lblTimeout, JFrame frame, JButton reserva, JButton confirmar) {

			timer.schedule(new TimerTask() {

			@Override
			public void run() {
				lblTimeout.setVisible(true);
				confirmar.setEnabled(false);
				reserva.setEnabled(false);
				
			}
		},120 * 1000);
			
		
	}
	
}
