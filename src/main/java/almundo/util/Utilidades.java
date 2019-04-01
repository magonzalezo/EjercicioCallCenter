package almundo.util;

public class Utilidades {

	private static final int DURACION_MINIMA = 5;
	private static final int DURACION_MAXIMA = 10;
	
	/**
	 * Metodo encargado de retornar tiempo en milisegundos entre 
	 * 5 y 10 segundos de manera aleatoria
	 * */
	public static int obtenerDuracionLlamada() {
		return (int) (Math.random()*(DURACION_MAXIMA-DURACION_MINIMA+1)+DURACION_MINIMA);
	}
}
