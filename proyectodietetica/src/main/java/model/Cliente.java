package model;

public class Cliente {

	private int idCliente;
	private int dni;
	private String nombreCliente;
	private String email;
	private int telefono;
	
	
	public Cliente(int idCliente, int dni, String nombreCliente, String email, int telefono) {
		this.idCliente = idCliente;
		this.dni = dni;
		this.nombreCliente = nombreCliente;
		this.email = email;
		this.telefono = telefono;
	}
	
	Cliente (){
		
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", dni=" + dni + ", nombreCliente=" + nombreCliente + ", email="
				+ email + ", telefono=" + telefono + "]";
	}
	
	
	
	
	
}
