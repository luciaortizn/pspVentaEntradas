import java.util.ArrayList;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * Clase que implementa la interfaz JRDataSource para proporcionar datos a un
 * informe JasperReports. Se utiliza como fuente de datos para generar informes
 * basados en la plantilla de JasperReports.
 * 
 * @author Lucía Ortiz
 *
 */
public class DataSource implements JRDataSource {

	private ArrayList<Usuario> listaUsuarios;

	private int index;

	/**
	 * Constructor de la clase DataSource.
	 * 
	 * @param listaArraylist La lista de usuarios que se utilizará como fuente de
	 *                       datos.
	 */
	public DataSource(ArrayList<Usuario> listaArraylist) {
		index = -1;
		this.listaUsuarios = listaArraylist;
	}

	/**
	 * Obtiene el valor del campo especificado por el objeto JRField.
	 * 
	 * @param field Objeto JRField que representa el campo para el que se obtiene el
	 *              valor.
	 * @return El valor del campo especificado.
	 * @throws JRException Si ocurre un error al obtener el valor del campo.
	 */
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {

		Object objeto = new Object();
		String nameString = arg0.getName();

		switch (nameString) {

		case "nombre":
			objeto = listaUsuarios.get(index).getNombre();
			break;

		case "apellido":
			objeto = listaUsuarios.get(index).getApellido();

			break;
		case "cantidadEntrada":
			objeto = listaUsuarios.get(index).getCantidadEntrada();
			break;

		case "tipoEntrada":
			objeto = listaUsuarios.get(index).getTipoEntrada();
			break;
		case "precio":
			objeto = listaUsuarios.get(index).getPrecio();
			break;

		}

		return objeto;

	}

	/**
	 * Mueve al siguiente elemento en la fuente de datos.
	 * 
	 * @return true si hay más elementos en la fuente de datos, false si se alcanzó
	 *         el final.
	 * @throws JRException Si ocurre un error durante el proceso de avance en la
	 *                     fuente de datos.
	 */
	@Override
	public boolean next() throws JRException {

		index++;

		return index < listaUsuarios.size();
	}

}
