package eredua;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Enplegatua {


	private int zenbaki;
	private String izena;
	private String abizenak;
	private double soldata;
	private String alta;
	private int depKod;
	private String arduraMota;


	public Enplegatua(int zenbaki, String izena, String abizenak, double soldata, String alta, int depKod,String arduraMota) {

		this.zenbaki = zenbaki;
		this.izena = izena;
		this.abizenak = abizenak;
		this.soldata = soldata;
		this.alta = alta;
		this.depKod = depKod;
		this.arduraMota = arduraMota;
	}

	public int getZenbaki() {
		return zenbaki;
	}

	public void setZenbaki(int zenbaki) {
		this.zenbaki = zenbaki;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizenak() {
		return abizenak;
	}

	public void setAbizenak(String abizenak) {
		this.abizenak = abizenak;
	}

	public double getSoldata() {
		return soldata;
	}

	public void setSoldata(double soldata) {
		this.soldata = soldata;
	}

	public String getAlta() {
		return alta;
	}

	public void setAlta(String alta) {
		this.alta = alta;
	}

	public int getDepKod() {
		return depKod;
	}

	public void setDepKod(int depKod) {
		this.depKod = depKod;
	}

	public String getArduraMota() {
		return arduraMota;
	}

	public void setArduraMota(String arduraMota) {
		this.arduraMota = arduraMota;
	}

	@Override
	public String toString() {
		return "Enplegatua [zenbaki=" + zenbaki + ", izena=" + izena + ", abizenak=" + abizenak + ", soldata=" + soldata
				+ ", alta=" + alta + ", depKod=" + depKod + ", arduraMota=" + arduraMota + "]";
	}




	public static void reiniciarArray (String [] array) {
		for (int i = 0; i < array.length-1; i++) {
			array[i]=null;
		}
	}



	//Enplegatua CSVtik irakurtzeko

	public static ArrayList<Enplegatua> CSVEnplegatuakIrakurri (File fitxategiaEnplegatuak) throws IOException{

		//Bariableak
		ArrayList<Enplegatua> arrayEnplegatuakCSV = new ArrayList<Enplegatua>();
		String zenbaki_string=null;
		int zenbaki_int=0;
		String izena=null;
		String abizenak=null;
		String soldata_string=null;
		double soldata_double=0.00;
		String ardura=null;
		String alta=null;
		String depKod_string=null;
		int depKod_int=0;

		String csvFile = fitxategiaEnplegatuak.getAbsolutePath();
		BufferedReader br = null;
		String line = "";
		//Se define separador ","
		String cvsSplitBy = ",";
		
		//try {
		
			br = new BufferedReader(new FileReader(csvFile));
			//Lehengo linea ez irakurtzeko
			br.readLine();
			while ((line = br.readLine()) != null) {                
				String[] datos = line.split(cvsSplitBy);
				zenbaki_string = datos[0];
				izena = datos[1];
				abizenak = datos[2];
				soldata_string = datos[3];
				alta = datos[4];
				depKod_string = datos[5];
				ardura = datos[6];


				//Imprime datos.
				System.out.println(datos[0] + " " + datos[1] + " " + datos[2]+ " " + datos[3]+ " " + datos[4]+ " " + datos[5]+ " " + datos[6]);

				zenbaki_int = Integer.parseInt(zenbaki_string);
				soldata_double = Double.parseDouble(soldata_string);
				depKod_int = Integer.parseInt(depKod_string);

				Enplegatua enplegatua = new Enplegatua(zenbaki_int, izena, abizenak, soldata_double, alta, depKod_int, ardura);
				arrayEnplegatuakCSV.add(enplegatua);

				reiniciarArray(datos);

			}



//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (br != null) {
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}


		return arrayEnplegatuakCSV;

	}


	//Enplegatua XMLtik irakurtzeko


	public static ArrayList<Enplegatua> XMLEnplegatuakIrakurri(File fitxategiaEnplegatuak) throws ParserConfigurationException, SAXException, IOException {

		//Bariableak
		ArrayList<Enplegatua> arrayEnplegatuaXML = new ArrayList<Enplegatua>();
		String zenbaki_string=null;
		int zenbaki_int=0;
		String izena=null;
		String abizenak=null;
		String soldata_string=null;
		double soldata_double=0.00;
		String ardura=null;
		String alta=null;
		String depKod_string=null;
		int depKod_int=0;

		//String xmlFile = ".\\src\\Oharrak.xml";
		int count = 0;

		//try {

			File archivo = new File(fitxategiaEnplegatuak.getAbsolutePath());
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



					zenbaki_string = document.getElementsByTagName("zenbaki").item(temp).getTextContent();
					izena = document.getElementsByTagName("izena").item(temp).getTextContent();
					abizenak = document.getElementsByTagName("abizena").item(temp).getTextContent();
					soldata_string = document.getElementsByTagName("soldata").item(temp).getTextContent();
					alta = document.getElementsByTagName("alta").item(temp).getTextContent();
					depKod_string = document.getElementsByTagName("departamentu_kodea").item(temp).getTextContent();
					ardura = document.getElementsByTagName("ardurak_arduraMota").item(temp).getTextContent();


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
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return arrayEnplegatuaXML;


	}

	
public static ArrayList<Enplegatua> JSONEnplegatuakIrakurri(File fitxategia) throws IOException, ParseException{
		ArrayList<Enplegatua> depArrayList = new ArrayList<Enplegatua>();
		
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		
		//try {
		
			FileReader reader = new FileReader(fitxategia);
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
            
          

//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
			
		
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



	//Enplegatua JSONtik irakurtzeko

//	public static ArrayList<Enplegatua> JSONEnplegatuakIrakurri() {
//		ArrayList<Enplegatua> arrayEnplegatuakJSON = new ArrayList<Enplegatua>();
//
//		JSONParser jsonParser = new JSONParser();
//
//		try (FileReader reader = new FileReader(".\\src\\Enplegatuak.json")) {
//			Object obj = jsonParser.parse(reader);
//			JSONArray employeeList = (JSONArray) obj;
//			employeeList.forEach(emp -> parseEnplegatuakObject((JSONObject) emp,arrayEnplegatuakJSON));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		return arrayEnplegatuakJSON;
//
//
//	}
//
//	private static void parseEnplegatuakObject(JSONObject employee,ArrayList<Enplegatua> arraylist) {
//
//		JSONObject departamentuaObject = (JSONObject) employee.get("enplegatua");
//
//		String zenbaki_string = (String) departamentuaObject.get("zenbaki");
//		String izena = (String) departamentuaObject.get("izena");
//		String abizenak = (String) departamentuaObject.get("abizenak");
//		String soldata_string = (String) departamentuaObject.get("soldata");
//		String alta = (String) departamentuaObject.get("alta");
//		String depKod_string = (String) departamentuaObject.get("depKod");
//		String ardura = (String) departamentuaObject.get("ardura");
//
//		int zenbaki_int = Integer.parseInt(zenbaki_string);
//		double soldata_double = Double.parseDouble(soldata_string);
//		int depKod_int = Integer.parseInt(depKod_string);
//
//		//Oharra oharra = new Oharra(data, ordua, nori, nork, titulua, edukia);
//		Enplegatua enplegatu = new Enplegatua(zenbaki_int, izena, abizenak, soldata_double, alta, depKod_int, ardura);
//		arraylist.add(enplegatu);
//
//
//	}
//


	public static void EnplegatuakIgo (ArrayList<Enplegatua> arraylistEnplegatua) throws SQLException {

		Connection conexion = (Connection) konexioa.getConnection();
		//try {
			Statement s = conexion.createStatement();

			for (int i = 0; i < arraylistEnplegatua.size(); i++) {

				s.executeUpdate("INSERT INTO `enplegatu` (`zenbaki`, `izena`, `abizena`, `soldata`, `alta`, `departamentu_kodea`, `ardurak_arduraMota`) VALUES"
						+ " ("+ arraylistEnplegatua.get(i).getZenbaki() +", '"+ arraylistEnplegatua.get(i).getIzena() +"', '"+ arraylistEnplegatua.get(i).getAbizenak() +"', "+ arraylistEnplegatua.get(i).getSoldata() +",'"+ arraylistEnplegatua.get(i).getAlta() +"','"+ arraylistEnplegatua.get(i).getDepKod() +"', '"+ arraylistEnplegatua.get(i).getArduraMota() +"')");

			}
			s.close();

			System.out.println("Konexioa Eginda Enplegatuak Igo");
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}


	}
	
	public static void EnplegatuBatIgo (Enplegatua enplegatua) {

		Connection conexion = (Connection) konexioa.getConnection();
		try {
			Statement s = conexion.createStatement();


				s.executeUpdate("INSERT INTO `enplegatu` (`zenbaki`, `izena`, `abizena`, `soldata`, `alta`, `departamentu_kodea`, `ardurak_arduraMota`) VALUES"
						+ " ("+ enplegatua.getZenbaki() +", '"+ enplegatua.getIzena() +"', '"+ enplegatua.getAbizenak() +"', "+ enplegatua.getSoldata() +",'"+ enplegatua.getAlta() +"','"+ enplegatua.getDepKod() +"', '"+ enplegatua.getArduraMota() +"')");

			
			s.close();

			System.out.println("Konexioa Eginda Enplegatuak Igo");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}


	}
	
	public static void EnplegatuBatBakarrikEzabatu(int EzabatuNahidenZenbakia) { //Departamentu objetua DELETE bd

		Connection conexion = (Connection) konexioa.getConnection();
		try {
			Statement s = conexion.createStatement();
				s.executeUpdate("DELETE FROM enplegatu WHERE zenbaki ="+EzabatuNahidenZenbakia);	
			
			s.close();

			System.out.println("Konexioa Eginda DELETE Enplegatu");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static void EnplegatuBatBakarrikAldatu(Enplegatua enplegatu) {
		Connection conexion = (Connection) konexioa.getConnection();
		try {
			Statement s = conexion.createStatement();
			
				s.executeUpdate("UPDATE `enplegatu` SET `izena` = '"+enplegatu.getIzena() +"' WHERE `zenbaki` = "+enplegatu.getZenbaki());	
				s.executeUpdate("UPDATE `enplegatu` SET `abizena` = '"+enplegatu.getAbizenak() +"' WHERE `zenbaki` = "+enplegatu.getZenbaki());	
				s.executeUpdate("UPDATE `enplegatu` SET `soldata` = '"+enplegatu.getSoldata() +"' WHERE `zenbaki` = "+enplegatu.getZenbaki());	
				s.executeUpdate("UPDATE `enplegatu` SET `departamentu_kodea` = '"+enplegatu.getDepKod() +"' WHERE `zenbaki` = "+enplegatu.getZenbaki());	
				s.executeUpdate("UPDATE `enplegatu` SET `ardurak_arduraMota` = '"+enplegatu.getArduraMota() +"' WHERE `zenbaki` = "+enplegatu.getZenbaki());

				
			s.close();

			System.out.println("Konexioa Eginda UPDATE Departamentua kodea "+enplegatu.getZenbaki());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


	public static ArrayList <Enplegatua> EnplegatuakSelect() {

		ArrayList <Enplegatua> enplegatuak = new ArrayList<Enplegatua>();
		int zenbaki=0;
		String izena=null;
		String abizenak=null;
		double soldata=0.00;
		String alta=null;
		int depKod=0;
		String arduraMota=null;


		Connection Conexion = (Connection) konexioa.getConnection();
		Statement s =null;

		try {
			s =(Statement) Conexion.createStatement();

			ResultSet rs = ((java.sql.Statement) s).executeQuery("SELECT zenbaki,izena,abizena,soldata,alta,departamentu_kodea,ardurak_arduraMota FROM enplegatu");
			while (rs.next()) {
				zenbaki = rs.getInt("zenbaki");
				izena = rs.getString("izena");
				abizenak = rs.getString("abizena");
				soldata = rs.getDouble("soldata");
				alta = rs.getString("alta");
				depKod = rs.getInt("departamentu_kodea");
				arduraMota = rs.getString("ardurak_arduraMota");
				Enplegatua enplegatua = new Enplegatua(zenbaki, izena, abizenak, soldata, alta, depKod, arduraMota);
				enplegatuak.add(enplegatua);


			}
			System.out.println("Konexioa eginda Enplegatuak Select");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return enplegatuak;

	}
	
	public static ArrayList <String> ardurakSelect() {

		ArrayList <String> ardurak = new ArrayList<String>();
		String ardurakString=null;


		Connection Conexion = (Connection) konexioa.getConnection();
		Statement s =null;

		try {
			s =(Statement) Conexion.createStatement();

			ResultSet rs = ((java.sql.Statement) s).executeQuery("SELECT arduraMota FROM ardurak");
			while (rs.next()) {
				ardurakString = rs.getString("arduraMota");
				ardurak.add(ardurakString);
				
				
			}
			System.out.println("Konexioa eginda ardurak Select");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return ardurak;

	}
	
	public static int KodeAltuenaAtera() {
		int Kodea=0; 

		Connection Conexion = (Connection) konexioa.getConnection();
		Statement s =null;

		try {
			//Conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+"bidaion","root","");
			s =(Statement) Conexion.createStatement();

			ResultSet rs = ((java.sql.Statement) s).executeQuery("SELECT max(zenbaki) FROM enplegatu");
			while (rs.next()) {
				Kodea = rs.getInt("max(zenbaki)");

			}
			System.out.println();
			System.out.println("Konexioa eginda Kode Altuena Enplegatu");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return Kodea;
	}







}
