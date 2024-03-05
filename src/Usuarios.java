import java.util.ArrayList;

/**
 * Clase que representa una lista de objetos Usuario.
 * 
 * @author Lucia Ortiz
 *
 */
public class Usuarios {

	private ArrayList<Usuario> listaUsuarios;

	/**
	 * Constructor de la clase Usuarios.
	 * 
	 * @param listaUsuarios La lista de usuarios que se utilizará en la colección.
	 */
	public Usuarios(ArrayList<Usuario> listaUsuarios) {
		super();
		this.listaUsuarios = listaUsuarios;
	}

	/**
	 * Obtiene la lista de usuarios de la colección.
	 * 
	 * @return La lista de usuarios.
	 */
	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * Establece la lista de usuarios de la colección.
	 * 
	 * @param listaUsuarios La nueva lista de usuarios.
	 */
	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	/**
	 * Agrega un nuevo usuario a la colección.
	 * 
	 * @param user El usuario que se agregará a la colección.
	 */
	public void addUser(Usuario user) {

		listaUsuarios.add(user);

	}

	/**
	 * Constructor por defecto de la clase Usuarios.
	 */
	public Usuarios() {
		super();
	}

	/**
	 * Genera una representación en cadena del objeto Usuarios.
	 * 
	 * @return Una cadena que representa la colección de usuarios.
	 */
	@Override
	public String toString() {
		return "Usuarios [listaUsuarios=" + listaUsuarios + "]";
	}

}
