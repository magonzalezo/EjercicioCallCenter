package almundo.util;

public enum ETipoEmpleado {
	OPERADOR(1),
	SUPERVISOR(2),
	DIRECTOR(3);
	
	private final int prioridad;
	
	private ETipoEmpleado(int prioridad) {
		this.prioridad = prioridad;
	}
}
