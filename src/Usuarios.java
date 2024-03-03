import java.util.ArrayList;

public class Usuarios {
	
	private ArrayList<Usuario> listaUsuarios;

	public Usuarios(ArrayList<Usuario> listaUsuarios) {
		super();
		this.listaUsuarios = listaUsuarios;
	}

	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	@Override
	public String toString() {
		return "Usuarios [listaUsuarios=" + listaUsuarios + "]";
	}
	
	public void addUser(Usuario user) {
		
		listaUsuarios.add(user);
	
	}

	public Usuarios() {
		super();
	}
	

}
