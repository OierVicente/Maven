package Diego;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eredua.Departamentua;
import eredua.Enplegatua;


public class ReadEnpleJSON
{
	
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Enplegatua> read(String json, ArrayList<Enplegatua> depArrayList){
		
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader(json))
		{
			//Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
           
            
            //Iterate over employee array
            
            
            
            employeeList.forEach( emp -> {
				try {
					parsedepartObject( (JSONObject) emp, depArrayList );
				} catch (IOException e) {
				
					e.printStackTrace();
				}
			} );
            
          

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
			
		
		return depArrayList;
	}

	
	private static void parsedepartObject(JSONObject employee, ArrayList<Enplegatua> enpArrayList) throws IOException{
		
		Enplegatua enp = new Enplegatua(0, null, null, 0.00, null, 0, null); // creamos objeto 
		//Get employee object within list
		JSONObject departamentua = (JSONObject) employee.get("enplegatu");
	
		
		int Zenbaki = Integer.parseInt((String) departamentua.get("zenbaki"));
		String Izena = (String) departamentua.get("izena");	
		 String Abizenak = (String) departamentua.get("abizenak");
		 double Soldata = Double.parseDouble((String) departamentua.get("soldata"));
		 String Alta = (String) departamentua.get("alta");
		 int DepKod = Integer.parseInt((String) departamentua.get("depKod"));
		 String ArduraMota = (String) departamentua.get("arduraMota");
		
		
		
		
		 enp.setZenbaki(Zenbaki);
		 enp.setIzena(Izena);
		 enp.setAbizenak(Abizenak);
		 enp.setSoldata(Soldata);
		 enp.setAlta(Alta);
		 enp.setDepKod(DepKod);
		 enp.setArduraMota(ArduraMota);
		
		 enpArrayList.add(enp);
	
	
	}
	
}
