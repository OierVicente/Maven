package Diego;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import eredua.Departamentua;
import eredua.Enplegatua;
import eredua.konexioa;

public class UpdatesDepart {
	
	public static void gordeDepart(ArrayList<Departamentua> depArrayList){ //Departamentu arrayList sartu bd
		Departamentua p = new Departamentua(0, null, null);
		Connection conexion = (Connection) konexioa.getConnection();
		try {
			Statement s = conexion.createStatement();
			for(int x=0;x<depArrayList.size();x++) {
				  System.out.println(p);
				  p = depArrayList.get(x);
				  System.out.println(p);

				  s.executeUpdate("UPDATE `departamentu` SET `izena` = '" + p.getIzena() + "', `kokapena` = '" 
				  + p.getKokapena() + "' WHERE `departamentu`.`kodea` = '" + p.getKodea() + "'");	
				}s.close();
	
			System.out.println("Konexioa Eginda");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        }}

}
