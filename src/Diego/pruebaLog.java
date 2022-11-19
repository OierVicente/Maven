package Diego;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import eredua.Departamentua;

public class pruebaLog {
	final static Logger logger = Logger.getLogger(prueba.class);  
	public static void hola() {
		ArrayList<String> holaArray = new ArrayList<String>();
		
		try{   
		    System.out.println("Hola"); 
		    System.out.println(holaArray.get(1));
		    if(logger.isDebugEnabled()) logger.debug(("El metodo HOLA funciona"));  
		  } catch (Exception e){   
		    logger.error("Algo fallo en el metodo hola :"+e);  
		  }  
	}

}
