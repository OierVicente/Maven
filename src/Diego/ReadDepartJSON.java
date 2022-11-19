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


public class ReadDepartJSON
{
	
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Departamentua> read(String json, ArrayList<Departamentua> depArrayList){
		
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

	
	private static void parsedepartObject(JSONObject employee, ArrayList<Departamentua> depArrayList) throws IOException{
		
		Departamentua dep = new Departamentua(0, null, null); // creamos objeto 
		//Get employee object within list
		JSONObject departamentua = (JSONObject) employee.get("departamentua");
	
		
		
		int Kodea = Integer.parseInt((String) departamentua.get("Kodea"));
		String Izena = (String) departamentua.get("Izena");	
		 String Kokapena = (String) departamentua.get("Kokapena");
		
		
		
		dep.setKodea(Kodea);
		dep.setIzena(Izena);
		dep.setKokapena(Kokapena);
		
		depArrayList.add(dep);
	
	
	}
	
}
