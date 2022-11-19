package Diego;
 
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import eredua.Departamentua;
 
public class WriteJSONnoSeSiHaceFalta
{
    @SuppressWarnings("unchecked")
    public static void pepe(String json, ArrayList<Departamentua> depArrayList)
    {
   
    	
    	
    	 JSONArray employeeList = new JSONArray();
        //First Employee
    	 
    	 for(int x=0;x<depArrayList.size();x++) {
        JSONObject deptBlokea = new JSONObject();
        
        String kodeaString = Integer.toString(depArrayList.get(x).getKodea());
    	deptBlokea.put("Kodea", kodeaString);
    	deptBlokea.put("Izena", depArrayList.get(x).getIzena());
    	deptBlokea.put("Kokapena", depArrayList.get(x).getKokapena());
         
        JSONObject employeeObject = new JSONObject(); 
        employeeObject.put("departamentua", deptBlokea);
      

       
        employeeList.add(employeeObject);
//        employeeList.add(employeeObject2);
        
      
        
         
        //Write JSON file
        try (FileWriter file = new FileWriter("C:\\Diego Gomez\\Aitor\\Ethazi\\prueba.json")) {
 
            file.write(employeeList.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    	 }
    }
}