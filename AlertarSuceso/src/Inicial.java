import java.util.List;
import java.util.Timer;

import modelo.Alerta;
import constructores.FactoryAlertas;



public class Inicial {

	public static void main(String[] args) {
							
									
			FactoryAlertas fa = new FactoryAlertas();
			fa.Generar_Alertas();
			List<Alerta> alertas = fa.getAlertas();
			
			for (Alerta alerta:alertas){
				CrearTarea(alerta);								
			}
						
			try {
	            //espero 60 minutos para matar todas las tareas y salir
				Thread.sleep(60*60*1000);
				//matando tareas
				for (Alerta alerta:alertas){
					Finalizar(alerta);					
				}
	        } catch (InterruptedException e) {
	            e.printStackTrace();        
	        	
	        }
			
			System.out.println("cerrando");
			
			System.exit(0);
			
			
	}

	
	private static void Finalizar(Alerta task) {
		
		try {						
			
			task.cancel();
			
						
			//System.out.println("Cancelando Tarea " + task.getSuceso().getId());
		} catch (Exception e) {			
			e.printStackTrace();		
		}
		
	}

	private static void CrearTarea(Alerta task){
				
		Timer timer = new Timer(String.valueOf(task.getSuceso().getId()),true);
		
		switch (task.getSuceso().getVariable()){
		case "potencia":
			//cada 1 segundos
			timer.schedule(task,0,1*1000);
			break;
		case "reactiva":
			//cada minuto
			timer.schedule(task,0,60*1000);
			break;
		case "conexion":
			//cada 10 minutos
			timer.schedule(task,0,10*60*1000);
			break;
		default: 	
			//resto cada 5 minutos
			timer.schedule(task,0,5*60*1000);
			break;
		}	
		
		task.setTimer(timer);
		
		//System.out.println("Inicio Tarea " + task.getSuceso().getId());
        

        
	}
	
	


}
