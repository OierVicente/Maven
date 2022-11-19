package Diego;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import eredua.Departamentua;
import eredua.Enplegatua;
import eredua.konexioa;

public class UpdatesEnple {
	
	public static void gordeDepart(ArrayList<Enplegatua> depArrayList){ //Departamentu arrayList sartu bd
		Enplegatua p = new Enplegatua(0, null, null, 0.00, null, 0, null);
		Connection conexion = (Connection) konexioa.getConnection();
		try {
			Statement s = conexion.createStatement();
			for(int x=0;x<depArrayList.size();x++) {
				  p = depArrayList.get(x);
				  s.executeUpdate("UPDATE `enplegatu` SET `izena` = '" + p.getIzena() + "', `abizena` = '" + p.getAbizenak() + "' "
				  		+ ", `soldata` = '" + p.getSoldata() + "',`alta` = '" + p.getAlta() + "',`departamentu_kodea` = '" + p.getDepKod() + "' "
				  				+ ",`ardurak_arduraMota` = '" + p.getArduraMota() + "' WHERE `enplegatu`.`zenbaki` = '" + p.getZenbaki() + "'");
				}s.close();
	
			System.out.println("Konexioa Eginda");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        }}

}
