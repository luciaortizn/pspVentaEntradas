import java.util.ArrayList;

import groovy.lang.Newify;

public class Usuario {
	
	private String nombre;
	
	private String apellido;
	
	private int cantidadEntrada;
	
	private String tipoEntrada;
	
	private int precio;
	



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getCantidadEntrada() {
		return cantidadEntrada;
	}

	public void setCantidadEntrada(int cantidadEntrada) {
		this.cantidadEntrada = cantidadEntrada;
	}

	public String getTipoEntrada() {
		return tipoEntrada;
	}

	public void setTipoEntrada(String tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
	}

	public Usuario(String nombre, String apellido, int cantidadEntrada, String tipoEntrada, int precio) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cantidadEntrada = cantidadEntrada;
		this.tipoEntrada = tipoEntrada;
		this.precio = precio;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", cantidadEntrada=" + cantidadEntrada
				+ ", tipoEntrada=" + tipoEntrada + ", precio=" + precio + "]";
	}

	
	

}
