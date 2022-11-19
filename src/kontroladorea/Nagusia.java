package kontroladorea;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import Diego.prueba;
import eredua.konexioa;
import leihoak.DepartamentuakMenu;
import leihoak.EnplegatuakMenu;
import leihoak.FitxategiaAukeratuDepartamentuak;
import leihoak.FitxategiaAukeratuEnplegatuak;
import leihoak.OngiEtorria;

public class Nagusia {
	public final static Logger logger = Logger.getLogger(Nagusia.class);  
	public static void main(String[] args) {

			PropertyConfigurator.configure(".\\src\\log4j.properties");   
			logger.info("Aplikazioa abiarazi da...");  
			

			konexioa konexioa = new konexioa("ethazi");  
			logger.info(("Datu basearekin konexioa eginda..."));

		  
	
			 
			    
		
		
		
		//Ventanas
		Kontroladorea kontroladorea = new Kontroladorea();
		
		OngiEtorria ongietorria = new OngiEtorria();
		
		FitxategiaAukeratuDepartamentuak fitxategiaukeratuDepartamentuak = new FitxategiaAukeratuDepartamentuak();
		FitxategiaAukeratuEnplegatuak fitxategiaukeratuEnplegatuak = new FitxategiaAukeratuEnplegatuak();
		
		DepartamentuakMenu departamentuakmenu = new DepartamentuakMenu();
		EnplegatuakMenu enplegatuakmenu = new EnplegatuakMenu();
		
//***********************************************************************************
		kontroladorea.ateraOngietorria(ongietorria);
		ongietorria.Kontroladorea(kontroladorea);
//***********************************************************************************
		kontroladorea.ateraFitxategiaAukeratuDepartamentuak(fitxategiaukeratuDepartamentuak);
		fitxategiaukeratuDepartamentuak.Kontroladorea(kontroladorea);
		
//***********************************************************************************
		kontroladorea.ateraFitxategiaAukeratuEnplegatuak(fitxategiaukeratuEnplegatuak);
		fitxategiaukeratuEnplegatuak.Kontroladorea(kontroladorea);
		
//***********************************************************************************
		kontroladorea.ateraDepartamentumenu(departamentuakmenu);
		departamentuakmenu.Kontroladorea(kontroladorea);
		
//***********************************************************************************
		kontroladorea.ateraEnplegatuakmenu(enplegatuakmenu);
		enplegatuakmenu.Kontroladorea(kontroladorea);
		
//***********************************************************************************


		ongietorria.setVisible(true);
		
//		ArrayList<Enplegatua> arrayEnplegatu = new ArrayList<Enplegatua>();
//		arrayEnplegatu = Enplegatua.CSVEnplegatuakIrakurri();
//		Enplegatua.EnplegatuakIgo(arrayEnplegatu);
		
		
	}

}
