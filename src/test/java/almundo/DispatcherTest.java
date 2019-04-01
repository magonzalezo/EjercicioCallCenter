package almundo;

import almundo.fabrica.Empleado;
import almundo.fabrica.Llamada;
import almundo.util.ETipoEmpleado;
import junit.framework.TestCase;

/**
 * Esta Clase define los casos de prueba para la clase Dispatcher
 * @author Miguel A. Gonzalez O.
 * @version 31/03/2019 (1)
 * */
public class DispatcherTest extends TestCase{

	/**
	 * Test que realiza 10 llamadas de manera concurrente.
	 * Se da un tiempo de 12 segundos antes de la validacion para efectos de que
	 * atienda todas las llamadas
	 * */
	public void testDiezLlamadasConcurrentes() {
		
		//Se definen las variables que permitiran la evaluancion del resultado
		
		// Permitira saber que ya todos los empleados estan disponibles
		boolean empleadosDisponibles = false;
		
		// Permitira saber que ya no hay mas llamadas en la cola de llamadas
		boolean noHayLlamadasEnEspera = false; 
		
		// Se define un objeto general para acceder a los atributos estaticos
		Dispatcher dspGeneral = new Dispatcher();
		dspGeneral.agregarEmpleado(new Empleado("Jaime", ETipoEmpleado.OPERADOR));
		dspGeneral.agregarEmpleado(new Empleado("Natali", ETipoEmpleado.OPERADOR));
		dspGeneral.agregarEmpleado(new Empleado("Ramiro", ETipoEmpleado.OPERADOR));
		dspGeneral.agregarEmpleado(new Empleado("Camila", ETipoEmpleado.SUPERVISOR));
		dspGeneral.agregarEmpleado(new Empleado("Juan", ETipoEmpleado.SUPERVISOR));
		dspGeneral.agregarEmpleado(new Empleado("Jose", ETipoEmpleado.DIRECTOR));
    	
		// Se crean las 10 llamadas
    	for (int i = 1; i <= 10; i++) {
    		Dispatcher dp1 = new Dispatcher();
    		dp1.dispatchCall(new Llamada("Llamada_"+i));
    	}
    	
    	try {
			
    		// Se establece para que se lleve a cabo el proceso y poder evaluar
    		Thread.sleep(12000);
    		
    		// Se poblan los atributos de evaluacion
    		if (Dispatcher.getLstEmpleados().stream().filter(s -> !s.getDisponibilidad()).count()==0) {
				empleadosDisponibles = true;
			}
			
    		if (Dispatcher.getColaLLamadas().isEmpty()) {
    			noHayLlamadasEnEspera = true;
    		}
			
		} catch (Exception e) {
			System.out.println("En caso de que surja un error leyendo streams. "+e);
		}
    	
    	// Se valida que no hay ni llamadas pendientes y los empleados estan todos disponibles
        assertTrue(empleadosDisponibles && noHayLlamadasEnEspera);
    }
	
	/**
	 * Test que recibe 9 llamadas pero solo hay tres personas para atenderlas
	 * Se da un tiempo de 20 segundos para que atienda a todas las llamadas, ya que a menos personal,
	 * mas tiempo de ejecución
	 * */
	public void testSinEmpleadosDisponibles() {
		//Se definen las variables que permitiran la evaluancion del resultado
		
		// Permitira saber que ya todos los empleados estan disponibles
		boolean empleadosDisponibles = false;
		
		// Permitira saber que ya no hay mas llamadas en la cola de llamadas
		boolean noHayLlamadasEnEspera = false; 
		
		// Se define un objeto general para acceder a los atributos estaticos
		Dispatcher dspGeneral = new Dispatcher();
		dspGeneral.agregarEmpleado(new Empleado("Ramiro", ETipoEmpleado.OPERADOR));
		dspGeneral.agregarEmpleado(new Empleado("Camila", ETipoEmpleado.SUPERVISOR));
		dspGeneral.agregarEmpleado(new Empleado("Jose", ETipoEmpleado.DIRECTOR));
    	
		// Se crean las 9 llamadas
    	for (int i = 1; i <= 9; i++) {
    		Dispatcher dp1 = new Dispatcher();
    		dp1.dispatchCall(new Llamada("Llamada_"+i));
    	}
    	
    	try {
			
    		// Se establece para que se lleve a cabo el proceso y poder evaluar
    		Thread.sleep(20000);
    		
    		// Se poblan los atributos de evaluacion
    		if (Dispatcher.getLstEmpleados().stream().filter(s -> !s.getDisponibilidad()).count()==0) {
				empleadosDisponibles = true;
			}
			
    		if (Dispatcher.getColaLLamadas().isEmpty()) {
    			noHayLlamadasEnEspera = true;
    		}
			
		} catch (Exception e) {
			System.out.println("En caso de que surja un error leyendo streams. "+e);
		}
    	
    	// Se valida que no hay ni llamadas pendientes y los empleados estan todos disponibles
        assertTrue(empleadosDisponibles && noHayLlamadasEnEspera);
	}
	
	/**
	 * Test que recibe 35 llamadas para atenderlas de manera concurrente
	 * Este test tendra 10 empleados para atenderlas, donde se debe las llamadas pendientes van
	 * a una cola de llamadas para luego ser atendida por el empleado que se libere mas pronto
	 * Se dara un tiempo de 30 segundos para que se puedan atender todas las llamadas
	 * */
	public void testSobrecargaConcurrencia() {
		
		//Se definen las variables que permitiran la evaluancion del resultado
		
		// Permitira saber que ya todos los empleados estan disponibles
		boolean empleadosDisponibles = false;
		
		// Permitira saber que ya no hay mas llamadas en la cola de llamadas
		boolean noHayLlamadasEnEspera = false; 
		
		// Se define un objeto general para acceder a los atributos estaticos
		Dispatcher dspGeneral = new Dispatcher();
		dspGeneral.agregarEmpleado(new Empleado("Jaime", ETipoEmpleado.OPERADOR));
		dspGeneral.agregarEmpleado(new Empleado("Natali", ETipoEmpleado.OPERADOR));
		dspGeneral.agregarEmpleado(new Empleado("Ramiro", ETipoEmpleado.OPERADOR));
		dspGeneral.agregarEmpleado(new Empleado("Mauricio", ETipoEmpleado.OPERADOR));
		dspGeneral.agregarEmpleado(new Empleado("Daniela", ETipoEmpleado.OPERADOR));
		dspGeneral.agregarEmpleado(new Empleado("Camila", ETipoEmpleado.SUPERVISOR));
		dspGeneral.agregarEmpleado(new Empleado("Juan", ETipoEmpleado.SUPERVISOR));
		dspGeneral.agregarEmpleado(new Empleado("Gabriela", ETipoEmpleado.SUPERVISOR));
		dspGeneral.agregarEmpleado(new Empleado("German", ETipoEmpleado.SUPERVISOR));
		dspGeneral.agregarEmpleado(new Empleado("Jose", ETipoEmpleado.DIRECTOR));
    	
		// Se crean las 35 llamadas
    	for (int i = 1; i <= 35; i++) {
    		Dispatcher dp1 = new Dispatcher();
    		dp1.dispatchCall(new Llamada("Llamada_"+i));
    	}
    	
    	try {
			
    		// Se establece para que se lleve a cabo el proceso y poder evaluar
    		Thread.sleep(30000);
    		
    		// Se poblan los atributos de evaluacion
    		if (Dispatcher.getLstEmpleados().stream().filter(s -> !s.getDisponibilidad()).count()==0) {
				empleadosDisponibles = true;
			}
			
    		if (Dispatcher.getColaLLamadas().isEmpty()) {
    			noHayLlamadasEnEspera = true;
    		}
			
		} catch (Exception e) {
			System.out.println("En caso de que surja un error leyendo streams. "+e);
		}
    	
    	// Se valida que no hay ni llamadas pendientes y los empleados estan todos disponibles
        assertTrue(empleadosDisponibles && noHayLlamadasEnEspera);
		
	}
	
}
