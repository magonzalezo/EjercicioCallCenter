package almundo.fabrica;

import almundo.util.Utilidades;

public class Llamada {
	
	private String nombre;
	private int duracion;

	private static final int DURACION_MAXIMA = 10;
	
	public Llamada () {
		duracion = Utilidades.obtenerDuracionLlamada();
	}
	
	public Llamada (String nombre) {
		this.nombre = nombre;
		duracion = Utilidades.obtenerDuracionLlamada();
	}
	
	public Llamada (String nombre, int duracion) {
		this.nombre = nombre;		
		validarDuracion(duracion);
	}
	
	private void validarDuracion(int duracion) {
		if (duracion > DURACION_MAXIMA || duracion <= 0) {
			this.duracion = DURACION_MAXIMA*1000;
		} else {
			this.duracion = duracion*1000;
		}
	}
	
	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		validarDuracion(duracion);
	}

	@Override
	public String toString() {
		return "Llamada [nombre=" + nombre + ", duracion=" + duracion + "]";
	}
	
}
