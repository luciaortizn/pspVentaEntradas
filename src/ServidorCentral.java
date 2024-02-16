import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextField;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ServidorCentral {

	private JFrame frame;
	static JLabel lblFT_int = new JLabel("0");
	static JLabel lblTotalEntradas = new JLabel("5");
	private JTextField txtGeneral;
	private JTextField txtVip;
	private JTextField txtAVip;

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
	 * Create the application.
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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(50, 50, 704, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 192));
		panel.setBounds(0, 0, 690, 104);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Primavera Sound Festival");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(48, 37, 270, 30);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 22));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(198, 70, 5, 5);
		panel.add(tabbedPane);
		
		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setForeground(Color.WHITE);
		lblServidor.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 22));
		lblServidor.setBounds(564, 37, 98, 30);
		panel.add(lblServidor);
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

	}

	public synchronized void mandarTotalEntradas(Socket cliente) throws IOException {

		OutputStream outputStream = cliente.getOutputStream();
		// ¿¿?también se puede hacer con el buffered reader : new bufferedReader.write
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        
		// Enviar el total al cliente
		bufferedWriter.write(lblTotalEntradas.getText().toString());
		bufferedWriter.write("\n");
		bufferedWriter.flush();
	}

	public synchronized static void cambiarFT(int cantidad) {
		int totalString = (Integer.parseInt(lblFT_int.getText().toString()) + cantidad);
		lblFT_int.setText(totalString + "");

	}

	private void startServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(123);
		
		boolean haycaracter = true;
		while (haycaracter) {
			
			Socket socket; 
			socket  = serverSocket.accept();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//aquí se diferencia entre si salida/entrada
			String tipo=bufferedReader.readLine();
			if(tipo.contains("mandar")) {
				System.out.println("se ha mandado");
				cambiarTotal(socket, bufferedReader, txtGeneral,txtVip,txtAVip);
			    
			}else if(tipo.contains("recibir")) {
				System.out.println("se ha recibido");
				mandarTotalEntradas(socket);
				
			}
			

		}
		

	}
	public static void cambiarTotal(Socket socket, BufferedReader bufferedReader,JTextField txt1, JTextField txt2, JTextField txt3) throws IOException {

		ReentrantLock lock = new ReentrantLock();
		int cantidad;
		String tipoEntradaString = bufferedReader.readLine();
		String entradaSinCorchetes = tipoEntradaString.replace("[", "").replace("]", "").trim();
		//System.out.println(tipoEntradaString);
		cantidad = Integer.parseInt(bufferedReader.readLine());
		String tipo = ""; 
		int nuevoTotal = Integer.valueOf(Integer.parseInt(lblTotalEntradas.getText().trim()) - cantidad);
		lock.lock();
		lblTotalEntradas.setText("" + nuevoTotal);
		lock.unlock();
		int precio = 0;

		switch (entradaSinCorchetes) {
		case "Entrada general":
			tipo ="Entrada general";
			precio = 10;
			lock.lock();
			txt1.setText(""+Integer.valueOf(Integer.parseInt(txt1.getText())+ precio*cantidad));
			lock.unlock();
			System.out.println(precio);
			break;
		case "Entrada VIP":
			tipo = "Entrada VIP";
			precio = 20;
			lock.lock();
			txt2.setText(""+Integer.valueOf(Integer.parseInt(txt2.getText())+ precio*cantidad));
			lock.unlock();
			System.out.println(precio);
			break;

		case "Abono VIP":
			tipo ="Abono VIP";
			lock.lock();
			txt3.setText(""+Integer.valueOf(Integer.parseInt(txt3.getText())+precio*cantidad));
			lock.unlock();
			precio = 50;
			break;

		}
		
	    lock.lock();
		lblFT_int.setText( "" +Integer.valueOf(Integer.parseInt(lblFT_int.getText())+cantidad*precio) );
		//genero
		generarPDF(precio, cantidad, tipo);
		lock.unlock();
	}
	public static void generarPDF(int precio, int cantidad, String tipo) {
		Map<String, Object> mapaMap = new HashMap<String, Object>();
		//clave valor
		
		mapaMap.put("Nombre", "Pepa");
		mapaMap.put("Apellido", "Ortiz");
		//clave debe coincidir con el nombre del parámetro
		mapaMap.put("PrecioEntrada", Integer.valueOf(precio).toString());
		mapaMap.put("Tipo",tipo.toString());
		mapaMap.put("Cantidad", Integer.valueOf(cantidad).toString());
		
		//generar pdf , compile report pilla xml del JasperReport a partir de la ruta
		try {
			// "C:\Users\Usuario\JaspersoftWorkspace\DI_VentaEntradas\Entrada_Landscape.jrxml"
			JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\Usuario\\JaspersoftWorkspace\\DI_VentaEntradas\\Entrada_Landscape.jrxml");
			System.out.println("se ve?");
			JasperPrint infoJasperPrint = JasperFillManager.fillReport(jasperReport, mapaMap,new JREmptyDataSource());

			//JR, objec, datasource;
			JasperViewer.viewReport(infoJasperPrint);
			
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
