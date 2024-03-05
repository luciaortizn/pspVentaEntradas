import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.netbeans.lib.awtextra.AbsoluteLayout;

import com.itextpdf.text.log.SysoCounter;

import groovy.lang.Newify;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Maneja el servidor que gestiona los cambios en las compras de las entradas
 * 
 * @author Lucia Ortiz
 *
 */
public class ServidorCentral {

	private JFrame frame;
	static JLabel lblFT_int = new JLabel("0");
	static JLabel lblTotalEntradas = new JLabel("30");
	private JTextField txtGeneral;
	private JTextField txtVip;
	private JTextField txtAVip;

	// public static Usuarios usuarios = new Usuarios();
	public static ArrayList<Usuario> userArrayList = new ArrayList<Usuario>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					ServidorCentral window = new ServidorCentral();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la aplicación.
	 */
	public ServidorCentral() {
		initialize();

		new Thread(() -> {

			try {

				startServer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	/**
	 * Inicializa la vista.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(50, 50, 704, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 690, 104);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(198, 70, 5, 5);
		panel.add(tabbedPane);

		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setForeground(Color.WHITE);
		lblServidor.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 22));
		lblServidor.setBounds(562, 45, 98, 30);
		panel.add(lblServidor);

		JLabel lblNewLabel = new JLabel("PRIMAVERA SOUND");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Nirmala UI", Font.BOLD, 28));
		lblNewLabel.setBounds(58, 39, 369, 37);
		panel.add(lblNewLabel);
		lblTotalEntradas.setBackground(new Color(255, 255, 255));

		lblTotalEntradas.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));
		lblTotalEntradas.setBounds(205, 126, 53, 40);
		frame.getContentPane().add(lblTotalEntradas);

		lblFT_int.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));
		lblFT_int.setBounds(566, 126, 41, 40);
		frame.getContentPane().add(lblFT_int);

		JLabel lblFT = new JLabel("Facturación total:");
		lblFT.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));
		lblFT.setBounds(436, 126, 136, 40);
		frame.getContentPane().add(lblFT);

		JLabel lblEntradasDisponibles = new JLabel("Entradas disponibles:");
		lblEntradasDisponibles.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));
		lblEntradasDisponibles.setBounds(54, 126, 163, 40);
		frame.getContentPane().add(lblEntradasDisponibles);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 192));
		panel_1.setBounds(90, 250, 163, 74);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("Entrada general: 10€");
		lblNewLabel_1_1.setBackground(new Color(0, 128, 192));
		lblNewLabel_1_1.setBounds(10, 11, 150, 40);
		panel_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(128, 255, 255));
		panel_1_1.setBounds(263, 250, 163, 74);
		frame.getContentPane().add(panel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Entrada VIP: 20€");
		lblNewLabel_1_1_1.setBounds(20, 11, 121, 40);
		panel_1_1.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));

		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBackground(new Color(128, 255, 128));
		panel_1_2.setBounds(436, 250, 163, 74);
		frame.getContentPane().add(panel_1_2);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Abono VIP: 50€");
		lblNewLabel_1_1_1_1.setBounds(21, 11, 121, 40);
		panel_1_2.add(lblNewLabel_1_1_1_1);
		lblNewLabel_1_1_1_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));

		JLabel lblNewLabel_1 = new JLabel("__________________________________________________");
		lblNewLabel_1.setBounds(195, 196, 363, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel euros = new JLabel("€");
		euros.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 14));
		euros.setBounds(597, 140, 26, 14);
		frame.getContentPane().add(euros);

		txtGeneral = new JTextField();
		txtGeneral.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtGeneral.setText("0");
		txtGeneral.setBounds(149, 336, 53, 20);
		frame.getContentPane().add(txtGeneral);
		txtGeneral.setColumns(10);

		txtVip = new JTextField();
		txtVip.setText("0");
		txtVip.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtVip.setColumns(10);
		txtVip.setBounds(315, 336, 53, 20);
		frame.getContentPane().add(txtVip);

		txtAVip = new JTextField();
		txtAVip.setText("0");
		txtAVip.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtAVip.setColumns(10);
		txtAVip.setBounds(493, 335, 53, 20);
		frame.getContentPane().add(txtAVip);

		JLabel lblEmptyList = new JLabel("No existen usuarios registrados en el evento.");
		lblEmptyList.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		lblEmptyList.setBounds(247, 389, 253, 14);
		lblEmptyList.setVisible(false);
		frame.getContentPane().add(lblEmptyList);

		JButton btnReporteLista = new JButton("Generar Informe");
		btnReporteLista.setForeground(new Color(255, 255, 255));
		btnReporteLista.setBackground(new Color(0, 0, 0));

		btnReporteLista.addActionListener(new ActionListener() {
			/**
			 * Método de evento que se llama cuando se hace click. En este caso, se intenta
			 * generar un informe de usuarios si la lista de usuarios no está vacía.
			 * 
			 * @param e Objeto ActionEvent que representa el evento de acción.
			 */
			public void actionPerformed(ActionEvent e) {

				try {
					if (!userArrayList.isEmpty()) {
						lblEmptyList.setVisible(false);
						generarInformeUsuarios(userArrayList);
					} else {
						lblEmptyList.setVisible(true);
					}

				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnReporteLista.setBounds(510, 376, 143, 40);
		frame.getContentPane().add(btnReporteLista);

	}

	/**
	 * Manda el stream que corresponde al total de entradas a la clase Cliente con
	 * el BufferedWriter.
	 * 
	 * 
	 * @param cliente El Socket usuario actual conectado al servidor
	 * @throws IOException El error que se produce cuando hay problemas de lectura
	 *                     en el socket
	 */
	public synchronized void mandarTotalEntradas(Socket cliente) throws IOException {

		OutputStream outputStream = cliente.getOutputStream();
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

		// Enviar el total al cliente
		bufferedWriter.write(lblTotalEntradas.getText().toString());
		bufferedWriter.write("\n");
		bufferedWriter.flush();
	}

	/**
	 * Cambia el label de factuación total en la ventana JSwing del servidor
	 * 
	 * @param cantidad El valor de facturación total.
	 */
	public synchronized static void cambiarFT(int cantidad) {
		int totalString = (Integer.parseInt(lblFT_int.getText().toString()) + cantidad);
		lblFT_int.setText(totalString + "");

	}

	/**
	 * Crea una conexión y permite a los clientes conectarse mediante ServerSocket y
	 * Socket, además de leer la primera línea para saber si se recibe o envía
	 * información.
	 * 
	 * @throws IOException El error que se produce cuando hay problemas de
	 *                     lectura/escritura en el socket.
	 */
	private void startServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(123);

		boolean haycaracter = true;
		while (haycaracter) {

			Socket socket;
			socket = serverSocket.accept();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// aquí se diferencia entre si salida/entrada
			String tipo = bufferedReader.readLine();
			if (tipo.contains("mandar")) {
				System.out.println("se ha mandado");
				cambiarTotal(socket, bufferedReader, txtGeneral, txtVip, txtAVip);

			} else if (tipo.contains("recibir")) {
				System.out.println("se ha recibido");
				mandarTotalEntradas(socket);

			}

		}

	}

	/**
	 * Cambia el total de cada campo de entrada según lo que se lee del
	 * BufferedReader.
	 * 
	 * @param socket         El Socket usuario actual conectado al servidor.
	 * @param bufferedReader El BufferedReader desde el cual se leerán las líneas.
	 * @param txt1           El campo de texto para el 1º tipo de entrada.
	 * @param txt2           El campo de texto para el 2º tipo de entrada.
	 * @param txt3           El campo de texto para el 3º tipo de entrada.
	 * @throws IOException El error que se produce cuando hay problemas de
	 *                     lectura/escritura en el socket.
	 */
	public static void cambiarTotal(Socket socket, BufferedReader bufferedReader, JTextField txt1, JTextField txt2,
			JTextField txt3) throws IOException {

		ReentrantLock lock = new ReentrantLock();
		int cantidad;
		String tipoEntradaString = bufferedReader.readLine();
		String entradaSinCorchetes = tipoEntradaString.replace("[", "").replace("]", "").trim();
		cantidad = Integer.parseInt(bufferedReader.readLine());
		String tipo = "";
		int nuevoTotal = Integer.valueOf(Integer.parseInt(lblTotalEntradas.getText().trim()) - cantidad);
		lock.lock();
		lblTotalEntradas.setText("" + nuevoTotal);
		lock.unlock();
		int precio = 0;

		switch (entradaSinCorchetes) {
		case "Entrada general":
			tipo = "Entrada general";
			precio = 10;
			lock.lock();
			txt1.setText("" + Integer.valueOf(Integer.parseInt(txt1.getText()) + precio * cantidad));
			lock.unlock();
			System.out.println(precio);
			break;
		case "Entrada VIP":
			tipo = "Entrada VIP";
			precio = 20;
			lock.lock();
			txt2.setText("" + Integer.valueOf(Integer.parseInt(txt2.getText()) + precio * cantidad));
			lock.unlock();
			System.out.println(precio);
			break;

		case "Abono VIP":
			tipo = "Abono VIP";
			lock.lock();
			txt3.setText("" + Integer.valueOf(Integer.parseInt(txt3.getText()) + precio * cantidad));
			lock.unlock();
			precio = 50;
			break;

		}

		lock.lock();
		lblFT_int.setText("" + Integer.valueOf(Integer.parseInt(lblFT_int.getText()) + cantidad * precio));
		mostrarVentanaEmergente(cantidad, entradaSinCorchetes, precio);
		System.out.println("Mostramos la ventana emergente");
		lock.unlock();
	}

	/**
	 * Genera el informe JasperSoft para una lista de usuarios que han comprado una
	 * entrada.
	 * 
	 * @param lista Una Arraylist de Usuarios la cual es el parámetro de dataSource.
	 * @throws JRException Si ocurre un error específico de JasperReports al generar
	 *                     el reporte.
	 */
	public static void generarInformeUsuarios(ArrayList<Usuario> lista) throws JRException {

		DataSource dataSource = new DataSource(lista);
		System.out.println("Lista:" + lista.toString() + " o " + lista.get(0).toString());

		// ruta relativa ../PSP_Sockets_2T_Lucia_Ortiz/reports/ListUsers.jrxml
		// ruta completa desde jasper:
		// C:\\Users\\Usuario\\JaspersoftWorkspace\\DI_VentaEntradas\\ListUsers.jrxml
		JasperReport jasperReport = JasperCompileManager
				.compileReport("../PSP_Sockets_2T_Lucia_Ortiz/reports/ListUsers.jrxml");

		JasperPrint infoJasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
		JasperViewer.viewReport(infoJasperPrint);
	}

	/**
	 * 
	 * @param cantidadEntrada
	 * @param tipoEntrada
	 * @param precio
	 */
	private static void mostrarVentanaEmergente(int cantidadEntrada, String tipoEntrada, int precio) {

		ReentrantLock lock = new ReentrantLock();

		JFrame ventanaEmergente = new JFrame("Datos personales");

		ventanaEmergente.setBounds(100, 100, 450, 300);
		ventanaEmergente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaEmergente.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 436, 263);
		panel.setBackground(new Color(255, 255, 255));
		ventanaEmergente.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Datos personales");
		lblNewLabel.setBounds(133, 11, 173, 25);
		lblNewLabel.setFont(new Font("Nirmala UI", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		panel.add(lblNewLabel);

		JTextField txtFieldNombre = new JTextField();
		txtFieldNombre.setBackground(new Color(216, 254, 251));
		txtFieldNombre.setBounds(68, 100, 118, 25);
		panel.add(txtFieldNombre);
		txtFieldNombre.setColumns(10);

		JTextField txtFieldApellido = new JTextField();
		txtFieldApellido.setBackground(new Color(216, 254, 251));
		txtFieldApellido.setColumns(10);
		txtFieldApellido.setBounds(68, 183, 118, 25);
		panel.add(txtFieldApellido);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Nirmala UI", Font.PLAIN, 17));
		lblNombre.setBounds(70, 69, 98, 20);
		panel.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Nirmala UI", Font.PLAIN, 17));
		lblApellido.setBounds(67, 152, 140, 25);
		panel.add(lblApellido);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(0, 0, 0));
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.setFont(new Font("Nirmala UI", Font.BOLD, 13));
		btnGuardar.setBounds(287, 184, 98, 34);
		panel.add(btnGuardar);

		JLabel lblDescripcion = new JLabel("Por favor, rellene los siguientes campos");
		lblDescripcion.setFont(new Font("Nirmala UI", Font.PLAIN, 13));
		lblDescripcion.setBounds(94, 38, 254, 20);
		panel.add(lblDescripcion);
		ventanaEmergente.setVisible(true);

		btnGuardar.addActionListener(e -> {

			// Confirmar y meter en la lista

			String nombre = txtFieldNombre.getText().toString();
			String apellido = txtFieldApellido.getText().toString();

			lock.lock();

			Usuario nuevoUsuario = new Usuario(nombre, apellido, cantidadEntrada, tipoEntrada,
					precio * cantidadEntrada);
			ServidorCentral.userArrayList.add(nuevoUsuario);
			generarPDF(nuevoUsuario); // si se imprime
			System.out.println(ServidorCentral.userArrayList.toString());

			lock.unlock();
			ventanaEmergente.dispose();
		});

	}

	/**
	 * 
	 * @param user
	 */
	public static void generarPDF(Usuario user) {
		Map<String, Object> mapaMap = new HashMap<String, Object>();

		// clave valor
		mapaMap.put("Nombre", user.getNombre());
		mapaMap.put("Apellido", user.getApellido());
		// clave debe coincidir con el nombre del parámetro
		mapaMap.put("PrecioEntrada", Integer.valueOf(user.getPrecio()).toString());
		mapaMap.put("Tipo", user.getTipoEntrada().toString());
		mapaMap.put("Cantidad", Integer.valueOf(user.getCantidadEntrada()).toString());

		try {
			// "C:\Users\Usuario\eclipse-workspace\PSP_Sockets_2T_Lucia_Ortiz\src\ServidorCentral.java"

			// "C:\Users\Usuario\eclipse-workspace\PSP_Sockets_2T_Lucia_Ortiz\\reports\\Entrada_Landscape.jrxml"
			// absoluta del jasper:
			// C:\\Users\\Usuario\\JaspersoftWorkspace\\DI_VentaEntradas\\Entrada_Landscape.jrxml
			// relativa: ../PSP_Sockets_2T_Lucia_Ortiz/reports/Entrada_Landscape.jrxml
			JasperReport jasperReport = JasperCompileManager
					.compileReport("../PSP_Sockets_2T_Lucia_Ortiz/reports/Entrada_Landscape.jrxml");
			JasperPrint infoJasperPrint = JasperFillManager.fillReport(jasperReport, mapaMap, new JREmptyDataSource());

			JasperViewer.viewReport(infoJasperPrint);
		} catch (JRException e1) {

			e1.printStackTrace();
		}
	}

}
