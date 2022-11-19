package Oier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import eredua.Departamentua;
import eredua.Enplegatua;

public class prueba {



	public static void reiniciarArray (String [] array) {
		for (int i = 0; i < array.length-1; i++) {
			array[i]=null;
		}
	}

	/**********************
	 * 
	 * CSV
	 * 
	 * ********************
	 */

	public static ArrayList<Departamentua> CSVDepartamentuakIrakurri (){

		//Bariableak
		ArrayList<Departamentua> arrayDepartamentuakCSV = new ArrayList<Departamentua>();
		String Kodea_string=null;
		int Kodea_int=0;
		String Izena=null;
		String Kokapena=null;

		String csvFile = ".\\src\\Departamentuak.csv";
		BufferedReader br = null;
		String line = "";
		//Se define separador ","
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			//Lehengo linea ez irakurtzeko
			br.readLine();
			while ((line = br.readLine()) != null) {                
				String[] datos = line.split(cvsSplitBy);
				Kodea_string = datos[0];
				Izena = datos[1];
				Kokapena = datos[2];

				//Imprime datos.
				System.out.println(datos[0] + " " + datos[1] + " " + datos[2]);

				Kodea_int = Integer.parseInt(Kodea_string);    

				Departamentua departamentua = new Departamentua(Kodea_int, Izena, Kokapena);
				arrayDepartamentuakCSV.add(departamentua);

				reiniciarArray(datos);

			}



		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


		return arrayDepartamentuakCSV;

	}

	public static ArrayList<Enplegatua> CSVEnplegatuakIrakurri (){

		//Bariableak
		ArrayList<Enplegatua> arrayEnplegatuakCSV = new ArrayList<Enplegatua>();
		String zenbaki_string=null;
		int zenbaki_int=0;
		String izena=null;
		String abizenak=null;
		String soldata_string=null;
		double soldata_double=0.00;
		String alta=null;
		String depKod_string=null;
		int depKod_int=0;
		String ardura=null;

		String csvFile = ".\\src\\Enplegatuak.csv";
		BufferedReader br = null;
		String line = "";
		//Se define separador ","
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			//Lehengo linea ez irakurtzeko
			br.readLine();
			while ((line = br.readLine()) != null) {                
				String[] datos = line.split(cvsSplitBy);
				zenbaki_string = datos[0];
				izena = datos[1];
				abizenak = datos[2];
				soldata_string = datos[3];
				ardura = datos[4];
				alta = datos[5];
				depKod_string = datos[6];

				//Imprime datos.
				System.out.println(datos[0] + " " + datos[1] + " " + datos[2]);

				zenbaki_int = Integer.parseInt(zenbaki_string);
				soldata_double = Double.parseDouble(soldata_string);
				depKod_int = Integer.parseInt(depKod_string);
				

				Enplegatua enplegatua = new Enplegatua(zenbaki_int, izena, abizenak, soldata_double, alta, depKod_int, ardura);
				arrayEnplegatuakCSV.add(enplegatua);

				reiniciarArray(datos);

			}



		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


		return arrayEnplegatuakCSV;

	}


	/**********************
	 * 
	 * XML
	 * 
	 * ********************
	 */





	public static ArrayList<Departamentua> XMLDepartamentuakIrakurri() {

		//Bariableak
		ArrayList<Departamentua> arrayDepartamentuaXML = new ArrayList<Departamentua>();
		String Kodea_string=null;
		int Kodea_int=0;
		String Izena=null;
		String Kokapena=null;
		//String xmlFile = ".\\src\\Oharrak.xml";
		int count = 0;

		try {

			File archivo = new File(".\\src\\Departamentuak.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			document.getDocumentElement().normalize();
			System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
			NodeList listaDepartamentua = document.getElementsByTagName("departamentua");
			for (int temp = 0; temp < listaDepartamentua.getLength(); temp++) {
				Node nodo = listaDepartamentua.item(temp);
				System.out.println("Elemento:" + nodo.getNodeName());
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nodo;



					Kodea_string = document.getElementsByTagName("kodea").item(temp).getTextContent();
					Izena = document.getElementsByTagName("izena").item(temp).getTextContent();
					Kokapena = document.getElementsByTagName("kokapena").item(temp).getTextContent();


					Kodea_int = Integer.parseInt(Kodea_string);

					Departamentua departamentua = new Departamentua(Kodea_int, Izena, Kokapena);
					System.out.println(departamentua.toString());
					arrayDepartamentuaXML.add(departamentua);
					//count++;






				}
				//if (count==6) break;
				//if (nodo.getNodeName().equals("oharra")) break;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrayDepartamentuaXML;


	}



	public static ArrayList<Enplegatua> XMLEnplegatuakIrakurri() {

		//Bariableak
		ArrayList<Enplegatua> arrayEnplegatuaXML = new ArrayList<Enplegatua>();
		String zenbaki_string=null;
		int zenbaki_int=0;
		String izena=null;
		String abizenak=null;
		String soldata_string=null;
		double soldata_double=0.00;
		String alta=null;
		String depKod_string=null;
		String ardura=null;
		int depKod_int=0;

		
		//String xmlFile = ".\\src\\Oharrak.xml";
		int count = 0;

		try {

			File archivo = new File(".\\src\\Enplegatuak.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			document.getDocumentElement().normalize();
			System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
			NodeList listaEnplegatua = document.getElementsByTagName("enplegatua");
			for (int temp = 0; temp < listaEnplegatua.getLength(); temp++) {
				Node nodo = listaEnplegatua.item(temp);
				System.out.println("Elemento:" + nodo.getNodeName());
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nodo;



					zenbaki_string = document.getElementsByTagName("kodea").item(temp).getTextContent();
					izena = document.getElementsByTagName("izena").item(temp).getTextContent();
					abizenak = document.getElementsByTagName("abizena").item(temp).getTextContent();
					soldata_string = document.getElementsByTagName("soldata").item(temp).getTextContent();
					ardura = document.getElementsByTagName("ardura").item(temp).getTextContent();
					alta = document.getElementsByTagName("alta").item(temp).getTextContent();
					depKod_string = document.getElementsByTagName("depKod").item(temp).getTextContent();


					zenbaki_int = Integer.parseInt(zenbaki_string);
					soldata_double = Double.parseDouble(soldata_string);
					depKod_int = Integer.parseInt(depKod_string);
					
					Enplegatua enplegatua = new Enplegatua(zenbaki_int, izena, abizenak, soldata_double, alta, depKod_int, ardura);

					System.out.println(enplegatua.toString());
					arrayEnplegatuaXML.add(enplegatua);
					//count++;






				}
				//if (count==6) break;
				//if (nodo.getNodeName().equals("oharra")) break;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrayEnplegatuaXML;


	}

	/**********************
	 * 
	 * JSON
	 * 
	 * ********************
	 */

	// .json an dauden lerroak arraylist batean sartu
	public static ArrayList<Departamentua> JSONDepartamentuakIrakurri() {
		ArrayList<Departamentua> arrayDepartamentuaJSON = new ArrayList<Departamentua>();

		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(".\\src\\Departamentuak.json")) {
			Object obj = jsonParser.parse(reader);
			JSONArray employeeList = (JSONArray) obj;
			employeeList.forEach(emp -> parseDepartamentuakObject((JSONObject) emp,arrayDepartamentuaJSON));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return arrayDepartamentuaJSON;
		
		
	}

	private static void parseDepartamentuakObject(JSONObject employee,ArrayList<Departamentua> arraylist) {

		JSONObject departamentuaObject = (JSONObject) employee.get("departamentua");

		String kodea_string = (String) departamentuaObject.get("kodea");
		String izena = (String) departamentuaObject.get("izena");
		String kokapena = (String) departamentuaObject.get("kokapena");

		int kodea_int = Integer.parseInt(kodea_string);

		//Oharra oharra = new Oharra(data, ordua, nori, nork, titulua, edukia);
		Departamentua departamentua = new Departamentua(kodea_int, izena, kokapena);
		arraylist.add(departamentua);
		
		
	}
	///////////////////////////////////////////////////////////////////////////////////

	//		public static ArrayList<Departamentua> JSONDepartamentuaIrakurri () {
	//			ArrayList<Departamentua> arrayDepartamentuaJSON = new ArrayList<Departamentua>();
	//
	//			// .json an dauden lerroak arraylist batean sartu
	//			//public static ArrayList<Oharra> irakurriOharrak() {
	//				JSONParser jsonParser = new JSONParser();
	//
	//				try (FileReader reader = new FileReader(".\\src\\Departamentuak.json")) {
	//					Object obj = jsonParser.parse(reader);
	//					JSONArray employeeList = (JSONArray) obj;
	//					employeeList.forEach(emp -> parseOharrakObject((JSONObject) emp));
	//					
	//					
	//					
	//				} catch (FileNotFoundException e) {
	//					e.printStackTrace();
	//				} catch (IOException e) {
	//					e.printStackTrace();
	//				} catch (ParseException e) {
	//					e.printStackTrace();
	//				}
	//				
	//				return arrayDepartamentuaJSON;
	//			}
	//		
	//		
	//
	//		private static ArrayList<Departamentua> parseOharrakObject(JSONObject employee) {
	//			
	//			ArrayList<Departamentua> returnn = new ArrayList<Departamentua>();
	//			
	//			JSONObject departamentuaObject = (JSONObject) employee.get("departamentua");
	//
	//			String kodea_string = (String) departamentuaObject.get("kodea");
	//			String izena = (String) departamentuaObject.get("izena");
	//			String kokapena = (String) departamentuaObject.get("kokapena");
	//			
	//			int kodea_int = Integer.parseInt(kodea_string);
	//
	//			//Oharra oharra = new Oharra(data, ordua, nori, nork, titulua, edukia);
	//			Departamentua departamentua = new Departamentua(kodea_int, izena, kokapena);
	//			returnn.add(departamentua);
	//			
	//			return returnn;
	//			
	//		}

	// .json aren amaieran idazten du.
	//		public static int idatziOharrak(Oharra oharra) {
	//			int idatzita = 0;
	//			lista_oharrak.add(oharra);
	//
	//			JSONObject oharraDetails = new JSONObject();
	//			JSONArray employeeList = new JSONArray();
	//			JSONObject oharraObject = new JSONObject();
	//
	//			for (int i = 0; i < lista_oharrak.size(); i++) {
	//				oharraDetails = new JSONObject();
	//				oharraObject = new JSONObject();
	//
	//				oharraDetails.put("data", lista_oharrak.get(i).getData());
	//				oharraDetails.put("ordua", lista_oharrak.get(i).getOrdua());
	//				oharraDetails.put("nori", lista_oharrak.get(i).getNori());
	//				oharraDetails.put("nork", lista_oharrak.get(i).getNork());
	//				oharraDetails.put("titulua", lista_oharrak.get(i).getTitulua());
	//				oharraDetails.put("edukia", lista_oharrak.get(i).getEdukia());
	//
	//				oharraObject.put("oharra", oharraDetails);
	//				employeeList.add(oharraObject);
	//
	//			}
	//			try (FileWriter file = new FileWriter("src/Oharrak.json")) {
	//				file.write(employeeList.toJSONString());
	//				file.flush();
	//				idatzita = 1;
	//			} catch (IOException e) {
	//				e.printStackTrace();
	//			}
	//			return idatzita;
	//		}

	
	
	public static ArrayList<Enplegatua> JSONEnplegatuakIrakurri() {
		ArrayList<Enplegatua> arrayEnplegatuakJSON = new ArrayList<Enplegatua>();

		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(".\\src\\Enplegatuak.json")) {
			Object obj = jsonParser.parse(reader);
			JSONArray employeeList = (JSONArray) obj;
			employeeList.forEach(emp -> parseEnplegatuakObject((JSONObject) emp,arrayEnplegatuakJSON));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return arrayEnplegatuakJSON;
		
		
	}

	private static void parseEnplegatuakObject(JSONObject employee,ArrayList<Enplegatua> arraylist) {

		JSONObject departamentuaObject = (JSONObject) employee.get("enplegatua");

		String zenbaki_string = (String) departamentuaObject.get("zenbaki");
		String izena = (String) departamentuaObject.get("izena");
		String abizenak = (String) departamentuaObject.get("abizenak");
		String soldata_string = (String) departamentuaObject.get("soldata");
		String alta = (String) departamentuaObject.get("alta");
		String depKod_string = (String) departamentuaObject.get("depKod");
		String ardura = (String) departamentuaObject.get("ardura");
		
		int zenbaki_int = Integer.parseInt(zenbaki_string);
		double soldata_double = Double.parseDouble(soldata_string);
		int depKod_int = Integer.parseInt(depKod_string);

		//Oharra oharra = new Oharra(data, ordua, nori, nork, titulua, edukia);
		Enplegatua enplegatu = new Enplegatua(zenbaki_int, izena, abizenak, soldata_double, alta, depKod_int, ardura);
		arraylist.add(enplegatu);
		
		
	}
	
	
	




}
