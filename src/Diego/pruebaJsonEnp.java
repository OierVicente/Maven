 package Diego;

import java.util.ArrayList;

import eredua.DepKonexio;
import eredua.Departamentua;
import eredua.Enplegatua;
import eredua.konexioa;

public class pruebaJsonEnp {

	public static void main(String[] args) {
		String json = "C:\\Users\\admin1\\git\\EthaziAitor2\\EthaziAitor\\src\\Enplegatuak.json";
		ArrayList<Enplegatua> enp = new ArrayList<Enplegatua>();
		
		
		enp = ReadEnpleJSON.read(json, enp);
		
	
		

		for(int x=0;x<enp.size();x++) {
			System.out.println(enp.get(x));
			}	
		
		
	}

}
