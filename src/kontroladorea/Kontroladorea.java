package kontroladorea;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import eredua.Departamentua;
import eredua.Enplegatua;
import leihoak.DepartamentuakMenu;
import leihoak.EnplegatuakMenu;
import leihoak.FitxategiaAukeratuDepartamentuak;
import leihoak.FitxategiaAukeratuEnplegatuak;
import leihoak.OngiEtorria;

public class Kontroladorea {
	
	//Leihoa deklaratu
	private OngiEtorria ongietorria ;
	
	private FitxategiaAukeratuDepartamentuak fitxategiaukeratuDepartamentuak;
	private FitxategiaAukeratuEnplegatuak fitxategiaukeratuEnplegatuak;
	
	private DepartamentuakMenu departamentuakmenu;
	private EnplegatuakMenu enplegatuakmenu;
	
	
	private File Fitxategia;
	private ArrayList<Departamentua> departamentuak = new ArrayList<Departamentua>();
	private ArrayList<Enplegatua> enplegatuak = new ArrayList<Enplegatua>();
	private ArrayList<String> ardurak = new ArrayList<String>();
	// Fitxategiko
	public static ArrayList<Departamentua> departamentuakFitxero = new ArrayList<Departamentua>();
	public static ArrayList<Enplegatua> enplegatuakFitxero = new ArrayList<Enplegatua>();
	
	
	protected Object[][] datos;
	//Para que las ventanas aparezcan
	
		public void ateraOngietorria(OngiEtorria ongietorria) {
			this.ongietorria=ongietorria;
		}
		
		public void ateraFitxategiaAukeratuDepartamentuak(FitxategiaAukeratuDepartamentuak fitxategiaukeratuDepartamentuak) {
			this.fitxategiaukeratuDepartamentuak=fitxategiaukeratuDepartamentuak;
		}
		public void ateraFitxategiaAukeratuEnplegatuak(FitxategiaAukeratuEnplegatuak fitxategiaukeratuEnplegatuak) {
			this.fitxategiaukeratuEnplegatuak=fitxategiaukeratuEnplegatuak;
		}
		
		public void ateraDepartamentumenu(DepartamentuakMenu departamentuakmenu) {
			this.departamentuakmenu = departamentuakmenu;
		}
		public void ateraEnplegatuakmenu(EnplegatuakMenu enplegatuakmenu) {
			this.enplegatuakmenu = enplegatuakmenu;
		}
//------------------------------------------------------------------------------------------------
		//Leihotik pasatzeko
		public void fitxategiakaukeratuDepartLeihoa() {
			ongietorria.setVisible(false);
			fitxategiaukeratuDepartamentuak.setVisible(true);
			
		}
		public void fitxategiakaukeratuEnplegLeihoa() {
			ongietorria.setVisible(false);
			fitxategiaukeratuEnplegatuak.setVisible(true);
			
		}
		
		public void depatamentuMenuraEzEginBotoia() {
			this.departamentuak = Departamentua.DepartamentuakSelect();
			this.departamentuakmenu.sortuTaulaDepart(departamentuak);			
			this.departamentuakmenu.botoiakOndoJarriDepart();
			
			this.fitxategiaukeratuDepartamentuak.setVisible(false);
			this.departamentuakmenu.setVisible(true);
			
			//departamentuakmenu.arrayDepartamentuaArtu(this.departamentuak);
			
			
			//departamentuakmenu.objectBidimensionaToDepartamentuak(this.departamentuak);
			//departamentuakmenu.taulaBete(this.departamentuak);
		}
		
		public void depatamentuMenuraHurrengoBotoia() {
			this.departamentuak = Departamentua.DepartamentuakSelect();
			this.departamentuakmenu.sortuTaulaDepart(departamentuak);
			departamentuakmenu.FitxategikoaJTableraGehituDepartamentuak();
			this.departamentuakmenu.botoiakOndoJarriDepart();
			
			this.fitxategiaukeratuDepartamentuak.setVisible(false);
			this.departamentuakmenu.setVisible(true);
			
			//departamentuakmenu.arrayDepartamentuaArtu(this.departamentuak);
			
			
			//departamentuakmenu.objectBidimensionaToDepartamentuak(this.departamentuak);
			//departamentuakmenu.taulaBete(this.departamentuak);
		}
		
		public void enplegatuMenuraEzEginBotoia() {
			
			this.enplegatuak = Enplegatua.EnplegatuakSelect();
			this.enplegatuakmenu.sortuTaulaEnple(enplegatuak);
			this.enplegatuakmenu.SetMaxDepart();
			this.enplegatuakmenu.MaxEnple();
			this.enplegatuakmenu.ComboboxForArdura();
			this.enplegatuakmenu.ComboboxForDepartamentuZenbakia();
			this.enplegatuakmenu.botoiakOndoJarriEnple();

			this.fitxategiaukeratuEnplegatuak.setVisible(false);
			this.enplegatuakmenu.setVisible(true);
		}
		
		public void enplegatuMenuraHurrengoBotoia() {
			
			this.enplegatuak = Enplegatua.EnplegatuakSelect();
			this.enplegatuakmenu.sortuTaulaEnple(enplegatuak);
			enplegatuakmenu.FitxategikoaJTableraGehituEnplegatuak();
			this.enplegatuakmenu.SetMaxDepart();
			this.enplegatuakmenu.MaxEnple();
			this.enplegatuakmenu.ComboboxForArdura();
			this.enplegatuakmenu.ComboboxForDepartamentuZenbakia();
			this.enplegatuakmenu.botoiakOndoJarriEnple();

			this.fitxategiaukeratuEnplegatuak.setVisible(false);
			this.enplegatuakmenu.setVisible(true);
		}
		
		
		
		public void AteraOngietorria() {

			this.ongietorria.setVisible(true);
			
		}
		
		public void AtzeraFitxategiakDepart() {
			this.fitxategiaukeratuDepartamentuak.setVisible(true);
		}
		public void AtzeraFitxategiakEnpleg() {
			this.fitxategiaukeratuEnplegatuak.setVisible(true);
		}
		
//------------------------------------------------------------------------------------------------

		public void fitxategiaGorde(File fitxategia) {
			this.Fitxategia = fitxategia;
			
		}
		
//		public void JtableSortu() {
//			
//			departamentuakmenu.taulaBete(departamentuak);
//		}
		
		//Esto en una ventana de una ventana
//		public ArrayList<Departamentua> returnDepartamentu(){
//			this.departamentuak = Departamentua.DepartamentuakSelect();
//			return this.departamentuak;
//		}
		
//		public Object[][] datuakSartu(){
//			this.departamentuak = Departamentua.DepartamentuakSelect();
//			return departamentuakmenu.objectBidimensionaToDepartamentuak(this.departamentuak);
//		}
		
		
		
		
		public void fitxategiaIgoDepartamentua(File fitxategiaLehioa) {
			
			//File fitxategia = this.Fitxategia;
			File fitxategia = fitxategiaLehioa;
			String nombreArchivo = fitxategia.getName();
			System.out.println(nombreArchivo);
			
			//String [] array = nombreArchivo.split(".");
			StringTokenizer st = new StringTokenizer(nombreArchivo, ".");
			String [] array = new String[2];
			array[0] = st.nextToken();
			array[1] = st.nextToken();
			System.out.println(array[0]);
			System.out.println(array[1]);

			String extension = array[1];
			
			if (extension.equals("csv")) {
				System.out.println(fitxategia);
				//ArrayList<Departamentua> arrayDepart = new ArrayList<Departamentua>();
//				ArrayList<Integer> numerosDepartFitxero = new ArrayList<Integer>();
//				boolean errepikatuta=false;
				try {
					this.departamentuak = Departamentua.CSVDepartamentuakIrakurri(this.Fitxategia);
					this.departamentuakFitxero = Departamentua.CSVDepartamentuakIrakurri(this.Fitxategia);
					
					
					try {
						Departamentua.DepartamentuakIgo(this.departamentuak);
						depatamentuMenuraHurrengoBotoia();
						JOptionPane.showMessageDialog(null, "Fitxategia Gorde da eta datu basera igo da ");
						Nagusia.logger.info(("Fitxategia Gorde da eta datu basera igo da"));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Ezin izan da igo zeren fitxategikoa errepikatuta dago", "ERROR!", JOptionPane.WARNING_MESSAGE);
						Nagusia.logger.info(("Ezin izan da igo zeren fitxategikoa errepikatuta dago"));  
					}
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Sartutako daturen bat txarto dago", "ERROR!", JOptionPane.ERROR_MESSAGE);
					Nagusia.logger.info(("Fitxategia Gordetzean sartutako daturen bat txarto dago"));
				}
				
				
				
				for (int i = 0; i < this.departamentuak.size(); i++) {
					System.out.println(this.departamentuak.get(i).toString());
				}
				
				
				
					
				//this.departamentuak = Departamentua.DepartamentuakSelect();
				//departamentuakmenu.taulaBete(this.departamentuak);

			}
			else if (extension.equals("xml")) {
				System.out.println(fitxategia);
				
//				ArrayList<Integer> numerosDepartFitxero = new ArrayList<Integer>();
//				boolean errepikatuta=false;
				try {
					this.departamentuak = Departamentua.XMLDepartamentuakIrakurri(this.Fitxategia);
					this.departamentuakFitxero = Departamentua.XMLDepartamentuakIrakurri(this.Fitxategia);
					
					try {
						Departamentua.DepartamentuakIgo(this.departamentuak);
						depatamentuMenuraHurrengoBotoia();
						JOptionPane.showMessageDialog(null, "Fitxategia Gorde da eta datu basera igo da ");
						Nagusia.logger.info(("Fitxategia Gorde da eta datu basera igo da"));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Ezin izan da igo zeren fitxategikoa errepikatuta dago", "ERROR!", JOptionPane.WARNING_MESSAGE);
						Nagusia.logger.info(("Ezin izan da igo zeren fitxategikoa errepikatuta dago"));
					}
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Sartutako daturen bat txarto dago", "ERROR!", JOptionPane.ERROR_MESSAGE);
					Nagusia.logger.info(("Fitxategia Gordetzean sartutako daturen bat txarto dago"));
				}
				
				
				
				for (int i = 0; i < this.departamentuak.size(); i++) {
					System.out.println(this.departamentuak.get(i).toString());
				}
				int kodea=0;
				
				
				
					
				
				//this.departamentuak = Departamentua.DepartamentuakSelect();
				//departamentuakmenu.taulaBete(this.departamentuak);


			}
			else if (extension.equals("json")) {
				System.out.println(fitxategia);
				
//				ArrayList<Integer> numerosDepartFitxero = new ArrayList<Integer>();
//				boolean errepikatuta=false;
				
				//this.departamentuak = Departamentua.JSONDepartamentuakIrakurri(departamentuak,fitxategia);
				try {
					this.departamentuak = Departamentua.JSONDepartamentuakIrakurri(this.Fitxategia);
					this.departamentuakFitxero = Departamentua.JSONDepartamentuakIrakurri(this.Fitxategia);
					
					try {
						Departamentua.DepartamentuakIgo(this.departamentuak);
						depatamentuMenuraHurrengoBotoia();
						JOptionPane.showMessageDialog(null, "Fitxategia Gorde da eta datu basera igo da ");
						Nagusia.logger.info(("Fitxategia Gorde da eta datu basera igo da"));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Ezin izan da igo zeren fitxategikoa errepikatuta dago", "ERROR!", JOptionPane.WARNING_MESSAGE);
						Nagusia.logger.info(("Ezin izan da igo zeren fitxategikoa errepikatuta dago")); 
					}
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Sartutako daturen bat txarto dago", "ERROR!", JOptionPane.ERROR_MESSAGE);
					Nagusia.logger.info(("Fitxategia Gordetzean sartutako daturen bat txarto dago"));
				}
				
				
				
				for (int i = 0; i < this.departamentuak.size(); i++) {
					System.out.println(this.departamentuak.get(i).toString());
				}
				
				
				
				
				
					
					
				//this.departamentuak = Departamentua.JSONDepartamentuakIrakurri();
				//Departamentua.DepartamentuakIgo(this.departamentuak);
				//this.departamentuak = Departamentua.DepartamentuakSelect();
				//departamentuakmenu.taulaBete(departamentuak);


			}else {
				JOptionPane.showMessageDialog(null, "Sartutako luzapena txarto dago", "ERROR!", JOptionPane.WARNING_MESSAGE);
				Nagusia.logger.info(("Sartutako luzapena txarto dago"));
			}
			
		}
		
		
		public void fitxategiaIgoEnplegatuak(File fitxategiLeihoa) {
			
			this.Fitxategia = fitxategiLeihoa;
			File fitxategia = this.Fitxategia;
			String nombreArchivo = fitxategia.getName();
			
			StringTokenizer st = new StringTokenizer(nombreArchivo, ".");
			String [] array = new String[2];
			array[0] = st.nextToken();
			array[1] = st.nextToken();
			System.out.println(array[0]);
			System.out.println(array[1]);

			String extension = array[1];
			
			if (extension.equals("csv")) {
				System.out.println(fitxategia);
				
//				ArrayList<Integer> numerosEnplegaFitxero = new ArrayList<Integer>();
//				boolean errepikatuta=false;
				try {
					this.enplegatuak = Enplegatua.CSVEnplegatuakIrakurri(fitxategia);
					this.enplegatuakFitxero = Enplegatua.CSVEnplegatuakIrakurri(fitxategia);
					
					try {
						Enplegatua.EnplegatuakIgo(this.enplegatuak);
						enplegatuMenuraHurrengoBotoia();
						JOptionPane.showMessageDialog(null, "Fitxategia Gorde da eta datu basera igo da ");
						Nagusia.logger.info(("Fitxategia Gorde da eta datu basera igo da"));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Ezin izan da igo zeren fitxategikoa errepikatuta dago", "ERROR!", JOptionPane.WARNING_MESSAGE);
						Nagusia.logger.info(("Ezin izan da igo zeren fitxategikoa errepikatuta dago"));  
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Sartutako daturen bat txarto dago", "ERROR!", JOptionPane.ERROR_MESSAGE);
					Nagusia.logger.info(("Fitxategia Gordetzean sartutako daturen bat txarto dago"));
				}
				
				
				for (int i = 0; i < this.enplegatuak.size(); i++) {
					System.out.println(this.enplegatuak.get(i).toString());
				}
				
				
				for (int i = 0; i < this.enplegatuak.size(); i++) {
					System.out.println(this.enplegatuak.get(i).toString());
				}
				
				
				
				
					
				
			}
			else if (extension.equals("xml")) {
				System.out.println(fitxategia);
				
//				ArrayList<Integer> numerosEnplegaFitxero = new ArrayList<Integer>();
//				boolean errepikatuta=false;
				try {
					this.enplegatuak = Enplegatua.XMLEnplegatuakIrakurri(fitxategia);
					this.enplegatuakFitxero = Enplegatua.XMLEnplegatuakIrakurri(fitxategia);
					
					
					try {
						Enplegatua.EnplegatuakIgo(this.enplegatuak);
						enplegatuMenuraHurrengoBotoia();
						JOptionPane.showMessageDialog(null, "Fitxategia Gorde da eta datu basera igo da ");
						Nagusia.logger.info(("Fitxategia Gorde da eta datu basera igo da"));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Ezin izan da igo zeren fitxategikoa errepikatuta dago", "ERROR!", JOptionPane.WARNING_MESSAGE);
						Nagusia.logger.info(("Ezin izan da igo zeren fitxategikoa errepikatuta dago"));
					}
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Sartutako daturen bat txarto dago", "ERROR!", JOptionPane.WARNING_MESSAGE);
					Nagusia.logger.info(("Fitxategia Gordetzean sartutako daturen bat txarto dago"));
				}
				
				
				for (int i = 0; i < this.enplegatuak.size()-1; i++) {
					System.out.println(this.enplegatuak.get(i).toString());
				}
				
				
				
				for (int i = 0; i < this.enplegatuak.size(); i++) {
					System.out.println(this.enplegatuak.get(i).toString());
				}
				
					
			

			}
			else if (extension.equals("json")) {
				System.out.println(fitxategia);
				
//				ArrayList<Integer> numerosEnplegaFitxero = new ArrayList<Integer>();
//				boolean errepikatuta=false;
				
				//this.enplegatuak = Enplegatua.JSONEnplegatuakIrakurri(fitxategia,enplegatuak);
				try {
					this.enplegatuak = Enplegatua.JSONEnplegatuakIrakurri(fitxategia);
					this.enplegatuakFitxero = Enplegatua.JSONEnplegatuakIrakurri(fitxategia);
					
					
					try {
						Enplegatua.EnplegatuakIgo(this.enplegatuak);
						enplegatuMenuraHurrengoBotoia();
						JOptionPane.showMessageDialog(null, "Fitxategia Gorde da eta datu basera igo da ");
						Nagusia.logger.info(("Fitxategia Gorde da eta datu basera igo da"));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Ezin izan da igo zeren fitxategikoa errepikatuta dago", "ERROR!", JOptionPane.WARNING_MESSAGE);
						Nagusia.logger.info(("Ezin izan da igo zeren fitxategikoa errepikatuta dago")); 
					}
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Sartutako daturen bat txarto dago", "ERROR!", JOptionPane.WARNING_MESSAGE);
					Nagusia.logger.info(("Fitxategia Gordetzean sartutako daturen bat txarto dago"));
				}
				
				
				for (int i = 0; i < this.enplegatuak.size()-1; i++) {
					System.out.println(this.enplegatuak.get(i).toString());
				}
				

				
				for (int i = 0; i < this.enplegatuak.size(); i++) {
					System.out.println(this.enplegatuak.get(i).toString());
				}
				
					
				
				//this.enplegatuak = Enplegatua.JSONDepartamentuakIrakurri();
				//Enplegatua.EnplegatuakIgo(this.enplegatuak);

			}else {
				JOptionPane.showMessageDialog(null, "Sartutako luzapena txarto dago", "ERROR!", JOptionPane.WARNING_MESSAGE);
				Nagusia.logger.info(("Sartutako luzapena txarto dago"));
			}
			
		}
		
		public boolean FitxategiaErrepikatuta(ArrayList<Departamentua> arrayDepartamentua, ArrayList<Integer> arrayKopFitxero) {
			boolean errepikatuta=false;	
			for (int i = 0; i < arrayDepartamentua.size() - 1; i++) {
				for (int j = 0; j < arrayKopFitxero.size() - 1; j++) {
					if (arrayKopFitxero.get(j).equals(arrayDepartamentua.get(i).getKodea())) {
						errepikatuta=true;
					}
				}
			}
			
			
			return errepikatuta;
		}
		
		//KONTSULTAK
		
		//DEPARTAMENTUAK
		public ArrayList<Departamentua> DepartamentuakSelect() {
			return Departamentua.DepartamentuakSelect();
		}
		public void DepartamentuaKendu(int kodea) {
			Departamentua.DepartamentuBatBakarrikEzabatu(kodea);
		}
		public void DepartamentuaIgo(Departamentua departamentua) {
			Departamentua.DepartamentuBatBakarrikIgo(departamentua);
		}
		public int DepartamentuKodeaAltuena() {
			return Departamentua.KodeAltuenaAtera();
		}
		
		//ENPLEGATUAK
		
		public void EnplegatuaIgo(Enplegatua enplegatua) {
			Enplegatua.EnplegatuBatIgo(enplegatua);
		}
		public int EnplegatuKodeaAltuena() {
			return Enplegatua.KodeAltuenaAtera();
		}
		public void EnplegatuEzabatu(int Zenbaki) {
			Enplegatua.EnplegatuBatBakarrikEzabatu(Zenbaki);
		}
//		public ArrayList<String> ArdurakLista() {
//			ardurak = Enplegatua.ardurakSelect();
//			return ardurak;
//		}
//		public ArrayList<String> ardurakLista(){
//			ardurak = Enplegatua.ardurakSelect();
//			
//			return ardurak;
//		}
		public ArrayList<String> ardurakLista(){
			//ardurak = Enplegatua.ardurakSelect();
			
			return Enplegatua.ardurakSelect();
		}
		
		
}
