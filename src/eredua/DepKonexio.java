package eredua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;



public class DepKonexio {


	
	public static ArrayList<String> kontsulaArdurak(){ // ArduraMotak kontsula
		ArrayList<String> arduraArrayList = new ArrayList<String>();
		Connection conexion = konexioa.getConnection();
		try {
			Statement s = conexion.createStatement();
	        String query = "select * from ardurak";
	        ResultSet rs = s.executeQuery(query);
	        
	        while (rs.next()) {
	        	String arduraMota=rs.getString(1);
	        	arduraArrayList.add(arduraMota);       	
	        	 }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
		return arduraArrayList;
		}
	
	
	

	
	public static void gordeEnple(ArrayList<Enplegatua> EnpArrayList){ //Enplegatu arrayList satu bd
		Enplegatua en = new Enplegatua(0, null, null, 0.00, null, 0, null);
		Connection conexion = (Connection) konexioa.getConnection();
		ArrayList<String> arduraArrayList = kontsulaArdurak();
		try {
			Statement s = conexion.createStatement();
			for(int x=0;x<EnpArrayList.size();x++) {
				  en = EnpArrayList.get(x);
				  s.executeUpdate("INSERT INTO `enplegatu` (`zenbaki`, `izena`, `abizena`"
				  		+ ",`soldata`, `alta`, `departamentu_kodea`,`ardurak_arduraMota`) VALUES"
							+ " ('" + en.getZenbaki() + "', '" + en.getIzena() + "', '" + en.getAbizenak() + "','" + en.getSoldata() + "'"
									+ ",'" + en.getAlta() + "','" + en.getDepKod() + "','" + en.getArduraMota() + "')");
				  }	s.close();
			System.out.println("Konexioa Eginda");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
		}
	}
	


}
