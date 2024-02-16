import java.util.ArrayList;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSource implements JRDataSource {

	private ArrayList<Usuario> listaUsuarios;
	// con este controlo el booleano

	private int index;

	public DataSource(ArrayList<Usuario> listaArraylist) {
		index = -1;
		this.listaUsuarios = listaArraylist;
	}

	@Override
	public Object getFieldValue(JRField arg0) throws JRException {

		Object objeto = new Object();
		String nameString = arg0.getName();

		switch (nameString) {

		case "Nombre":
			objeto = listaUsuarios.get(index).getNombre();
			break;

		case "Apellido":
			objeto = listaUsuarios.get(index).getApellido();

			break;
		case "Cantidad":
			objeto = listaUsuarios.get(index).getCantidadEntrada();
			break;

		case "Tipo":
			objeto = listaUsuarios.get(index).getTipoEntrada();
			break;

		}

		return objeto;

	}

	@Override
	public boolean next() throws JRException {

		index++;

		return index < listaUsuarios.size();
	}

}
