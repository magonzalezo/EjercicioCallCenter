package almundo.fabrica;

import almundo.util.ETipoEmpleado;

public class Empleado {

	private String nombre;
	private Llamada llamada;
	private ETipoEmpleado rol;
	private boolean disponibilidad;
	
	public Empleado (String nombre, ETipoEmpleado rol) {
		this.nombre = nombre;
		this.rol = rol;
		this.disponibilidad= true;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Llamada getLlamada() {
		return llamada;
	}
	
	public void setLlamada(Llamada llamada) {
		this.llamada = llamada;
	}
	
	public ETipoEmpleado getRol() {
		return rol;
	}
	
	public void setRol(ETipoEmpleado rol) {
		this.rol = rol;
	}

	public boolean getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + ", llamada=" + llamada + ", rol=" + rol + ", disponibilidad="
				+ disponibilidad + "]";
	}
	
}
