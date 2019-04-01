package almundo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;
import almundo.fabrica.Empleado;
import almundo.fabrica.Llamada;
import almundo.util.ETipoEmpleado;

/**
 * Esta Clase define el comportamiento y asignacion de la funcionalidad del callcenter
 * @author Miguel A. Gonzalez O.
 * @version 31/03/2019 (1)
 * */
public class Dispatcher extends Thread {

	private static final int LIMITE_CONCURRENCIA = 10;
	private static List<Empleado> lstEmpleados = new ArrayList<>();
	private static Queue<Llamada> colaLLamadas = new LinkedList<>();
	private static int controlConcurrencias;
	
	private Llamada llamada;
	private Empleado empleadoEnAtencion;
	
	private boolean llamadaSinAsignar = true;
	
	/**
	 * Metodo encargado de encontrar un empleado segun prioridad dada y le asigna la llamada y disponibilidad del empleado
	 * @param ETipoEmpleado
	 * @return boolean
	 * */
	private boolean empleadoDiponible(ETipoEmpleado tipoEmpleado) {
		Stream <Empleado> strOpDiponibles = lstEmpleados.stream().filter(s ->    s.getRol() == tipoEmpleado 
				                                                              && s.getDisponibilidad());
		try {
			empleadoEnAtencion = strOpDiponibles.findFirst().get();
			empleadoEnAtencion.setLlamada(llamada);
			empleadoEnAtencion.setDisponibilidad(false);
			return true;
		} catch (Exception e) {
			empleadoEnAtencion = null;
			return false;
		}
		
	}
	
	/**
	 * Metodo encargado de gestionar la asignacion de llamadas y en caso dado poblar la cola de llamadas
	 * */
	public void dispatchCall (Llamada llamada) {
		this.llamada = llamada;
		if (controlConcurrencias < LIMITE_CONCURRENCIA) {
			
			for (ETipoEmpleado prioridad : ETipoEmpleado.values()) {
				if (empleadoDiponible(prioridad)) {
					controlConcurrencias++;
					start();
					llamadaSinAsignar = false;
					break;
				}
			}					
		} else {
			llamadaSinAsignar = true;
		}
		
		if (llamadaSinAsignar) {
			colaLLamadas.add(llamada);
		}
	}

	/**
	 * Metodo encargado de validar el estado de la cola de llamadas
	 * */
	private void validarLlamadasEnCola () {
		if (!colaLLamadas.isEmpty()) {
			Dispatcher dspPendiente = new Dispatcher();
			try {
				Llamada llamaEnCola = colaLLamadas.stream().findFirst().get();
				dspPendiente.dispatchCall(llamaEnCola);
				colaLLamadas.remove(llamaEnCola);
			} catch (Exception e) {
				System.out.println("Error obteniendo llamada en cola. "+e);
			}			
		} else {
			System.out.println("No hay mas llamadas en cola por el momento.");
		}
	}
	
	/**
	 * Metodo encargado de ejecutar el metodo run heredado de Thread que implementa a su vez la interfaz Runnable
	 * */
	@Override
	public void run() {
		try {
			sleep(llamada.getDuracion());
		} catch (InterruptedException e) {
			System.out.println("Error atendiendo llamada. "+e);
		}
		empleadoEnAtencion.setDisponibilidad(true);
		controlConcurrencias--;
		validarLlamadasEnCola();		
	}

	/**
	 * Metodo principal para ejecuciones de prueba independientes
	 * */
	public static void main( String[] args )
    {
    	
    	Dispatcher dspEmpleado = new Dispatcher();
    	dspEmpleado.agregarEmpleado(new Empleado("Jaime", ETipoEmpleado.OPERADOR));
    	dspEmpleado.agregarEmpleado(new Empleado("Natali", ETipoEmpleado.OPERADOR));
    	dspEmpleado.agregarEmpleado(new Empleado("Ramiro", ETipoEmpleado.OPERADOR));
    	dspEmpleado.agregarEmpleado(new Empleado("Camila", ETipoEmpleado.SUPERVISOR));
    	dspEmpleado.agregarEmpleado(new Empleado("Juan", ETipoEmpleado.SUPERVISOR));
    	dspEmpleado.agregarEmpleado(new Empleado("Jose", ETipoEmpleado.DIRECTOR));
    	
    	for (int i = 1; i <= 10; i++) {
    		Dispatcher dp1 = new Dispatcher();
    		dp1.dispatchCall(new Llamada(""+i));
    	}
    	    	
    }
	
	public void agregarEmpleado(Empleado empleado) {
		lstEmpleados.add(empleado);
	}

	public static List<Empleado> getLstEmpleados() {
		return lstEmpleados;
	}

	public static Queue<Llamada> getColaLLamadas() {
		return colaLLamadas;
	}
	
	public static int getcontrolConcurrencias() {
		return controlConcurrencias;
	}

	public static void setcontrolConcurrencias(int controlConcurrencias) {
		Dispatcher.controlConcurrencias = controlConcurrencias;
	}
		
}
