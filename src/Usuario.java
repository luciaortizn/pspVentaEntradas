/**
 * Clase que representa a un usuario con información relacionada a la creación de un
 * informe JasperSoft
 * 
 * @author Lucia Ortiz
 *
 */
public class Usuario {

	private String nombre;

	private String apellido;

	private int cantidadEntrada;

	private String tipoEntrada;

	private int precio;

	/**
	 * Obtiene el nombre del usuario.
	 * 
	 * @return El nombre del usuario.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del usuario.
	 * 
	 * @param nombre El nuevo nombre del usuario.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el apellido del usuario.
	 * 
	 * @return El apellido del usuario.
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Establece el apellido del usuario.
	 * 
	 * @param apellido El nuevo apellido del usuario.
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Obtiene la cantidad de entradas asignadas al usuario.
	 * 
	 * @return La cantidad de entradas del usuario.
	 */
	public int getCantidadEntrada() {
		return cantidadEntrada;
	}

	/**
	 * Establece la cantidad de entradas asignadas al usuario.
	 * 
	 * @param cantidadEntrada La nueva cantidad de entradas del usuario.
	 */
	public void setCantidadEntrada(int cantidadEntrada) {
		this.cantidadEntrada = cantidadEntrada;
	}

	/**
	 * Obtiene el tipo de entrada asignado al usuario.
	 * 
	 * @return El tipo de entrada del usuario.
	 */
	public String getTipoEntrada() {
		return tipoEntrada;
	}

	/**
	 * Establece el tipo de entrada asignado al usuario.
	 * 
	 * @param tipoEntrada El nuevo tipo de entrada del usuario.
	 */
	public void setTipoEntrada(String tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
	}

	/**
	 * Constructor de la clase Usuario.
	 * 
	 * @param nombre          El nombre del usuario.
	 * @param apellido        El apellido del usuario.
	 * @param cantidadEntrada La cantidad de entradas asignadas al usuario.
	 * @param tipoEntrada     El tipo de entrada asignado al usuario.
	 * @param precio          El precio asociado al usuario.
	 */
	public Usuario(String nombre, String apellido, int cantidadEntrada, String tipoEntrada, int precio) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cantidadEntrada = cantidadEntrada;
		this.tipoEntrada = tipoEntrada;
		this.precio = precio;
	}

	/**
	 * Obtiene el precio asociado al usuario.
	 * 
	 * @return El precio asociado al usuario.
	 */
	public int getPrecio() {
		return precio;
	}

	/**
	 * Establece el precio asociado al usuario.
	 * 
	 * @param precio El nuevo precio asociado al usuario.
	 */
	public void setPrecio(int precio) {
		this.precio = precio;
	}

	/**
	 * Genera una representación en cadena del objeto Usuario.
	 * 
	 * @return Una cadena que representa al usuario.
	 */
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", cantidadEntrada=" + cantidadEntrada
				+ ", tipoEntrada=" + tipoEntrada + ", precio=" + precio + "]";
	}

}
