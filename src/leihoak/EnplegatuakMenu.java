package leihoak;


import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import Diego.prueba;
import eredua.Departamentua;
import eredua.Enplegatua;
import kontroladorea.Kontroladorea;
import kontroladorea.Nagusia;
 
public class EnplegatuakMenu extends JFrame {
	//private ArrayList<Departamentua> arrayDepartamentuak = new ArrayList<Departamentua>();
	private ArrayList<String> ardurak = new ArrayList<String>();
	private ArrayList<Integer> DepartamentuZenbakia = new ArrayList<Integer>(); 
	
	private Kontroladorea kontroladorea;
	private Departamentua departamentua;

	int lerroAukeratu;
	String[] añadir = {null, null, null}; // Cantidad de columnas de la tabla
	int count=0;
	private String zenbaki, izena, abizena,soldata,alta,departamentu_kodea,ardurak_arduraMota=null;

    /**************** ATRIBUTOS ***************************/
    //CONTENEDOR PRINCIPAL
    private JPanel contenedor;
 
    //DEFINICIÓN DE LAS ETIQUETAS
    private JLabel lblZenbaki;
    private JLabel lblIzena;
    private JLabel lblAbizena;
 
    //DEFINICIÓN DE LOS CUADROS DE TEXTO
    protected JTextField txtZenbakia;
    protected JTextField txtIzena;
    protected JTextField txtAbizena;
 
    //DEFINICIÓN DE LOS BOTONES
    protected JButton btnAdd;
    protected JButton btnDel;
    protected JButton btnUpd;
    protected JButton btnAtzera;

 
    //DEFINICIÓN DE LOS OBJETOS PARA LA TABLA
    private JScrollPane scroll; //Panel de scroll que contiene la tabla
    public static Object[][] datos; //Cuerpo de la tabla
	//protected Object[][] datos = new Object[arrayDepartamentuak.size()][3] ;
	//protected Object[][] datos = new Object[10][3] ;


    protected String[] cabecera;    //Cabecera de la tabla
    protected DefaultTableModel dtm;//Unión de la cabecera y la tabla
    protected JTable tabla; //Tabla propiamente dicha
    protected SpringLayout sp;
    protected JTextField txtSoldata;
    protected JLabel lblArdurak;
    protected JLabel lblSoldata = new JLabel("Soldata:");
    protected JLabel lblDepartkodea = new JLabel("Departamentu Kodea:");
    protected JComboBox comboBoxArdurak = new JComboBox();
    protected JComboBox comboBoxDepartamentuZenbakiak = new JComboBox();
    protected JLabel lblMax = new JLabel("Max :");
    protected int MaxEnple ;


    /**************** MÉTODOS ***************************/
    //CONSTRUCTOR
    public EnplegatuakMenu(){
        //Métodos de la JFrame
    	setBounds(525,200,700,600);//Definir las dimensiones de la ventana
        //setTitle("GESTIÓN DE CLIENTES - KADUM");    //Barra de título
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //Acción al pulsar salir
 
        //CREAR EL CONTENEDOR PRINCIPAL Y AÑADIRLO A LA VENTANA
        contenedor = new JPanel();
        getContentPane().add(contenedor);
        
        //INDICAR QUE SE QUIERE USAR SPRINGLAYOUT
        sp = new SpringLayout();
        sp.putConstraint(SpringLayout.WEST, lblMax, 173, SpringLayout.EAST, lblDepartkodea);
        sp.putConstraint(SpringLayout.NORTH, comboBoxArdurak, 26, SpringLayout.SOUTH, lblDepartkodea);
        contenedor.setLayout(sp);
        
      DateFormat hourformat = new SimpleDateFormat("HH:mm");
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      String data = getDia();
      String ordua = getHora();
 
        //Vamos al lío
        /**************** BOF ETIQUETAS  vvvvvvvvvvvvvvvv **/
        //ETIQUETA NOMBRE
        lblZenbaki = new JLabel("Zenbaki:");  //Crear el objeto
        contenedor.add(lblZenbaki);      //Añadirlo al contenedor
        sp.putConstraint(SpringLayout.NORTH, lblZenbaki, 10,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblZenbaki,  10,
                        SpringLayout.WEST, contenedor);
        //ETIQUETA APELLIDOS
        lblIzena = new JLabel("Izena:");
        contenedor.add(lblIzena);
        sp.putConstraint(SpringLayout.NORTH, lblIzena, 50,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblIzena,  10,
                        SpringLayout.WEST, contenedor);
        //ETIQUETA NIF
        lblAbizena = new JLabel("Abizena:");
        contenedor.add(lblAbizena);
        sp.putConstraint(SpringLayout.NORTH, lblAbizena, 90,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblAbizena,  10,
                        SpringLayout.WEST, contenedor);
        /**************** EOF ETIQUETAS  ^^^^^^^^^^^^^^^^ **/
 
        /**************** BOF CUADROS DE  TEXTO vvvvvvvvv **/
        //CUADRO DE TEXTO PARA EL NOMBRE
        txtZenbakia       = new JTextField();
        txtZenbakia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				char validar =evt.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					evt.consume();	
					JOptionPane.showMessageDialog(null, "Kodea zenbaki oso bat izan behar du");
				}
			}
		});
        contenedor.add(txtZenbakia);
        sp.putConstraint(SpringLayout.NORTH, txtZenbakia, 10,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtZenbakia, 100,
                        SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtZenbakia, 300,
                        SpringLayout.WEST, contenedor);
        txtZenbakia.setEditable(false);
        //CUADRO DE TEXTO PARA EL NIF
        txtIzena = new JTextField();
        txtIzena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				char validar =evt.getKeyChar();
				char c=evt.getKeyChar();
				if(Character.isDigit(validar) ) {
					getToolkit().beep();
					evt.consume();	
					JOptionPane.showMessageDialog(null, "Izena kate oso bat izan behar du");
					
				}
//				if(Character.isUpperCase(c)) {
//					String cad=(""+c).toLowerCase();
//					c=cad.charAt(0);
//					evt.setKeyChar(c);
//				}

			}
		});
        contenedor.add(txtIzena);    //añadir al contenedor
        sp.putConstraint(SpringLayout.NORTH, txtIzena, 50,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtIzena, 100,
                        SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtIzena, 300,
                        SpringLayout.WEST, contenedor);
        txtIzena.setEditable(false);
        //CUADRO DE TEXTO PARA LOS APELLIDOS
        txtAbizena = new JTextField();
        txtAbizena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				char validar =evt.getKeyChar();
				char c=evt.getKeyChar();
				if(Character.isDigit(validar) ) {
					getToolkit().beep();
					evt.consume();	
					JOptionPane.showMessageDialog(null, "Kokapena kate oso bat izan behar du");
					
				}
//				if(Character.isUpperCase(c)) {
//					String cad=(""+c).toLowerCase();
//					c=cad.charAt(0);
//					evt.setKeyChar(c);
//				}

			}
		});
        contenedor.add(txtAbizena);
        sp.putConstraint(SpringLayout.NORTH, txtAbizena, 90, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtAbizena, 100, SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtAbizena, 300, SpringLayout.WEST, contenedor);
        txtAbizena.setEditable(false);
        
        /**************** BOF BOTONES vvvvvvvvvvvvvvvvvv **/
        //BOTÓN AÑADIR
        btnAdd          = new JButton("Gehitu");
        contenedor.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (count==0) {
					
					btnDel.setEnabled(false);
					btnUpd.setEnabled(false);
					txtZenbakia.setEditable(false);
					txtIzena.setEditable(true);
					txtAbizena.setEditable(true);
					txtSoldata.setEditable(true);
					//txtDepartamentuKodea.setEditable(true);
					
					txtZenbakia.setText(Integer.toString((kontroladorea.EnplegatuKodeaAltuena()+1)));
					
					JOptionPane.showMessageDialog(null, "Sartu gehitu nahi dituzun balioak ");
					
					count=1;
				}else if (count==1) {
					//boolean denaOndo=false;
					
						if (zenbakiaDa(txtZenbakia.getText())==true && zenbakiaDa(txtIzena.getText())==false && zenbakiaDa(txtAbizena.getText())==false && doubleDa(txtSoldata.getText())==true && !txtIzena.getText().equals("") && !txtAbizena.getText().equals("") && !txtSoldata.getText().equals("")) {
							if (Integer.parseInt(txtZenbakia.getText()) > kontroladorea.EnplegatuKodeaAltuena()) {
								
								
								Enplegatua enplegatua = new Enplegatua(Integer.parseInt(txtZenbakia.getText()), txtIzena.getText(), txtAbizena.getText(), /* Integer.parseInt(txtSoldata.getText()) */ Double.parseDouble(txtSoldata.getText()), getDia(), Integer.parseInt((String) comboBoxDepartamentuZenbakiak.getSelectedItem()), (String) comboBoxArdurak.getSelectedItem());
										
				  				kontroladorea.EnplegatuaIgo(enplegatua);
								DefaultTableModel model = (DefaultTableModel) tabla.getModel();
				  				model.addRow(new Object[]{Integer.parseInt(txtZenbakia.getText()), txtIzena.getText(), txtAbizena.getText(),Double.parseDouble(txtSoldata.getText()),getDia(),Integer.parseInt((String) comboBoxDepartamentuZenbakiak.getSelectedItem()) ,(String) comboBoxArdurak.getSelectedItem()});
				  				count=0;
				  				
				  				txtZenbakia.setEditable(false);
								txtIzena.setEditable(false);
								txtAbizena.setEditable(false);
								txtSoldata.setEditable(false);
								
								
								btnAdd.setEnabled(true);
								btnDel.setEnabled(true);
								btnUpd.setEnabled(true);
								
								txtZenbakia.setText("");
								txtIzena.setText("");
								txtAbizena.setText("");
								txtSoldata.setText("");
								//txtDepartamentuKodea.setText("");
								
								Nagusia.logger.info((enplegatua.getZenbaki()+"Enplegatua ondo igo da")); 
				  				
							}else {
								JOptionPane.showMessageDialog(null, "Sartutako kode zenbakia "+kontroladorea.EnplegatuKodeaAltuena()+" baino altuagoa izan behar du", "ERROR!", JOptionPane.WARNING_MESSAGE);
								Nagusia.logger.info(("Sartutako kode zenbakia "+kontroladorea.EnplegatuKodeaAltuena()+" baino txikiagoa izan behar da (handiago izan behar du)")); 
								
							}
							
						}else {
							JOptionPane.showMessageDialog(null, "Sartutako daturen bat txarto dago", "ERROR!", JOptionPane.WARNING_MESSAGE);
						}
						
					
					
					
					
					
				}
				
			}
		});
        btnAdd.setEnabled(true);
        sp.putConstraint(SpringLayout.SOUTH, btnAdd, -10,
                        SpringLayout.SOUTH, contenedor);//colocarlo
        sp.putConstraint(SpringLayout.WEST, btnAdd,   60,
                        SpringLayout.WEST, contenedor);
        //BOTÓN BORRAR
        btnDel          = new JButton("Ezabatu");
        btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabla.getSelectedRow()!=-1) {
					((DefaultTableModel)tabla.getModel()).removeRow(tabla.getSelectedRow());
					kontroladorea.EnplegatuEzabatu(Integer.parseInt(zenbaki));
					
					Nagusia.logger.info((zenbaki+" enplegatua ondo kendu da")); 
					
				}else if (tabla.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "Ez duzu errekadarik aukeratu", "ERROR!", JOptionPane.WARNING_MESSAGE);
				} 
				
				
			}
		});
        btnDel.setEnabled(true);
        contenedor.add(btnDel);
        sp.putConstraint(SpringLayout.SOUTH, btnDel, -10,
                        SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnDel,  190,
                        SpringLayout.WEST, contenedor);
        //BOTÓN MODIFICAR
        btnUpd          = new JButton("Aldatu");
        btnUpd.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent arg0) {
  				DefaultTableModel model = (DefaultTableModel) tabla.getModel();
  				int i = tabla.getSelectedRow();
  				if (tabla.getSelectedRow()!=-1) {
  				
  				if (count==0) {
					
  					btnAdd.setEnabled(false);
					btnDel.setEnabled(false);
					btnUpd.setEnabled(true);
					txtZenbakia.setEditable(false);
					txtIzena.setEditable(true);
					txtAbizena.setEditable(true);
					txtSoldata.setEditable(true);
					//txtDepartamentuKodea.setEditable(true);
					txtZenbakia.setText(zenbaki);
					
					JOptionPane.showMessageDialog(null, "Sartu gehitu nahi dituzun balioak ");
					
					count=1;
				}else if(count == 1 && Enplegatua.KodeAltuenaAtera()>=Integer.parseInt(txtZenbakia.getText()) && Departamentua.KodeAltuenaAtera()>=Integer.parseInt((String) comboBoxDepartamentuZenbakiak.getSelectedItem()) && zenbaki.equals(txtZenbakia.getText()) && zenbakiaDa(txtZenbakia.getText())==true && zenbakiaDa(txtIzena.getText())==false && zenbakiaDa(txtAbizena.getText())==false && doubleDa(txtSoldata.getText())==true && !txtIzena.getText().equals("") && !txtAbizena.getText().equals("") && !txtSoldata.getText().equals("")) {
					
					
					Enplegatua enplegatua = new Enplegatua(Integer.parseInt(txtZenbakia.getText()), txtIzena.getText(), txtAbizena.getText(), /* Integer.parseInt(txtSoldata.getText()) */ Double.parseDouble(txtSoldata.getText()), getDia(), Integer.parseInt((String) comboBoxDepartamentuZenbakiak.getSelectedItem()), (String) comboBoxArdurak.getSelectedItem());
					Enplegatua.EnplegatuBatBakarrikAldatu(enplegatua);
					
					model.setValueAt(Integer.parseInt(txtZenbakia.getText()), i, 0);
					model.setValueAt(txtIzena.getText(), i, 1);
					model.setValueAt(txtAbizena.getText(), i, 2);
					model.setValueAt(txtSoldata.getText(), i, 3);
					model.setValueAt(Integer.parseInt((String) comboBoxDepartamentuZenbakiak.getSelectedItem()), i, 5);
					model.setValueAt((String) comboBoxArdurak.getSelectedItem(), i, 6);
					
					
                   	txtZenbakia.setEditable(false);
					txtIzena.setEditable(false);
					txtAbizena.setEditable(false);
					txtSoldata.setEditable(false);
					//txtDepartamentuKodea.setEditable(false);
					
					btnAdd.setEnabled(true);
					btnDel.setEnabled(true);
					btnUpd.setEnabled(true);
					
					txtZenbakia.setText("");
					txtIzena.setText("");
					txtAbizena.setText("");
					txtSoldata.setText("");
					//txtDepartamentuKodea.setText("");
                   
                   count=0;
                }else{
					JOptionPane.showMessageDialog(null, "Ezin izan da aldatu. Mesedez datuak berraztetu ");
                }
  			}else {
				JOptionPane.showMessageDialog(null, "Ez duzu errekadarik aukeratu", "ERROR!", JOptionPane.WARNING_MESSAGE);
			}
  		}
  		});
        btnUpd.setEnabled(true);
        contenedor.add(btnUpd);
        sp.putConstraint(SpringLayout.SOUTH, btnUpd, -10,
                        SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnUpd,  310,
                        SpringLayout.WEST, contenedor);
        
      //Boton Atzera
        btnAtzera          = new JButton("Atzera");
        btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				



				
				txtZenbakia.setEditable(false);
				txtIzena.setEditable(false);
				txtAbizena.setEditable(false);
				txtSoldata.setEditable(false);
				//txtDepartamentuKodea.setEditable(false);
				
				
				btnAdd.setEnabled(true);
				btnDel.setEnabled(true);
				btnUpd.setEnabled(true);
				
				dispose();
				kontroladorea.fitxategiakaukeratuEnplegLeihoa();
				
			}
		});
        btnAtzera.setEnabled(true);
        contenedor.add(btnAtzera);
        sp.putConstraint(SpringLayout.SOUTH, btnAtzera, -10,
                        SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnAtzera,  520,
                        SpringLayout.WEST, contenedor);
        
        lblSoldata.setFont(new Font("Tahoma", Font.PLAIN, 11));
        sp.putConstraint(SpringLayout.NORTH, lblSoldata, 0, SpringLayout.NORTH, lblZenbaki);
        sp.putConstraint(SpringLayout.EAST, lblSoldata, 0, SpringLayout.EAST, btnUpd);
        contenedor.add(lblSoldata);
        
        txtSoldata = new JTextField();
        sp.putConstraint(SpringLayout.NORTH, txtSoldata, 4, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtSoldata, 23, SpringLayout.EAST, lblSoldata);
        sp.putConstraint(SpringLayout.EAST, txtSoldata, 144, SpringLayout.EAST, lblSoldata);
        contenedor.add(txtSoldata);
        txtSoldata.setColumns(10);
        txtSoldata.setEditable(false);
        txtSoldata.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent evt) {
				char validar =evt.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					evt.consume();	
					JOptionPane.showMessageDialog(null, "Soldata zenbaki oso bat izan behar du");
				}
				if(txtSoldata.getText().length()>=9) {
					evt.consume();	
					JOptionPane.showMessageDialog(null, "Soldata bakarrik 9 digitoraino izan daiteke");
				}
				if(validar == ',') {
					evt.consume();	
					JOptionPane.showMessageDialog(null, "Soldataren koma punto (.) batekin idatzi mesedez");
				}
			}
		});
        sp.putConstraint(SpringLayout.NORTH, lblDepartkodea, 0, SpringLayout.NORTH, lblIzena);
        sp.putConstraint(SpringLayout.WEST, lblDepartkodea, 34, SpringLayout.EAST, txtIzena);
        contenedor.add(lblDepartkodea);
        
        lblArdurak = new JLabel("ardurak:");
        sp.putConstraint(SpringLayout.NORTH, lblArdurak, 0, SpringLayout.NORTH, lblAbizena);
        sp.putConstraint(SpringLayout.WEST, lblArdurak, 0, SpringLayout.WEST, lblSoldata);
        contenedor.add(lblArdurak);
        sp.putConstraint(SpringLayout.WEST, comboBoxArdurak, 33, SpringLayout.EAST, lblArdurak);
        sp.putConstraint(SpringLayout.EAST, comboBoxArdurak, 90, SpringLayout.EAST, txtSoldata);
        contenedor.add(comboBoxArdurak);
        txtSoldata.setEditable(false);
        
        
        
        
        
        
        
        /**************** BOF TABLA  vvvvvvvvvvvvvvvvvvvv **/
        scroll      = new JScrollPane();
        cabecera    = new String[] {"Zenbakia","Izena","Abizena","Soldata","Alta","Departamentu_Kodea","Ardura"};
        dtm         = new DefaultTableModel(datuakSortu(Enplegatua.EnplegatuakSelect()),cabecera);
        tabla       = new JTable(dtm);
        scroll.setViewportView(tabla);
        
       
        /**************** EOF TABLA ^^^^^^^^^^^^^^^^^^^^ **/
      //Mouse action listener idatzi
      		tabla.addMouseListener(new MouseAdapter() {
      			public void mouseClicked(MouseEvent e) {
      				//Informazioa eskuratu
      				//ikusiBotoiak();
      				//ikusiDatuakSartu();
      				//final int row = tabla.rowAtPoint(new Point(e.getX(), e.getY()));
      				int row = tabla.rowAtPoint(new Point(e.getX(), e.getY()));
      				tabla.setRowSelectionInterval(row, row);
      				int row2 = tabla.rowAtPoint(e.getPoint());
      				zenbaki = tabla.getValueAt(row2, 0).toString();
      				izena = tabla.getValueAt(row2, 1).toString();
      				abizena = tabla.getValueAt(row2, 2).toString();
      				soldata = tabla.getValueAt(row2, 3).toString();
      				alta = tabla.getValueAt(row2, 4).toString();
      				departamentu_kodea = tabla.getValueAt(row2, 5).toString();
      				ardurak_arduraMota = tabla.getValueAt(row2, 6).toString();
      				txtZenbakia.setText(zenbaki.toString());
      				
      				System.out.println(zenbaki+" "+izena+" "+abizena+" "+soldata+" "+alta+" "+departamentu_kodea+" "+ardurak_arduraMota);
      				//Betebehar dena	
      			}
      		});
      	//y ahora se coloca el scrollpane...
            contenedor.add(scroll); //añadir al contenedor
            sp.putConstraint(SpringLayout.NORTH, scroll, 120,
                            SpringLayout.NORTH, contenedor);
            sp.putConstraint(SpringLayout.WEST, scroll,   10,
                            SpringLayout.WEST, contenedor);
            sp.putConstraint(SpringLayout.EAST, scroll,  -10,
                            SpringLayout.EAST, contenedor);
            sp.putConstraint(SpringLayout.SOUTH, scroll, -50,
                            SpringLayout.SOUTH, contenedor);
            sp.putConstraint(SpringLayout.SOUTH, lblMax, 0, SpringLayout.SOUTH, lblIzena);
            contenedor.add(lblMax);
            lblMax.setText("Max : "+Departamentua.KodeAltuenaAtera());
            
            sp.putConstraint(SpringLayout.NORTH, comboBoxDepartamentuZenbakiak, 20, SpringLayout.SOUTH, txtSoldata);
            sp.putConstraint(SpringLayout.WEST, comboBoxDepartamentuZenbakiak, 6, SpringLayout.EAST, lblDepartkodea);
            sp.putConstraint(SpringLayout.SOUTH, comboBoxDepartamentuZenbakiak, 0, SpringLayout.SOUTH, lblIzena);
            sp.putConstraint(SpringLayout.EAST, comboBoxDepartamentuZenbakiak, 0, SpringLayout.EAST, comboBoxArdurak);
            contenedor.add(comboBoxDepartamentuZenbakiak);
            
            
 
        //Se hace visible la ventana
        //setVisible(true);
        
        
        
        
//        for (int i = 0; i < kontroladorea.ardurakLista().size(); i++) {
//			comboBoxArdurak.addItem(kontroladorea.ardurakLista().get(i).toString());
//		}
//        comboBoxArdurak.addItem("atezaina");
//        comboBoxArdurak.addItem("idazkariEnplegatua");
//        comboBoxArdurak.addItem("irakaslea");
//        comboBoxArdurak.addItem("tutorea");
//        comboBoxArdurak.addItem("z_ikasketaBurua");
//        comboBoxArdurak.addItem("z_mintegiBurua");
//        comboBoxArdurak.addItem("z_zuzendariOrdea");
//        comboBoxArdurak.addItem("z_zuzendariOrokorra");

        
        

        

        
        
        /**************** EOF BOTONES ^^^^^^^^^^^^^^^^^^^^ **/
        /**************** EOF CUADROS DE  TEXTO ^^^^^^^^^ **/
 
//        /**************** BOF TABLA  vvvvvvvvvvvvvvvvvvvv **/
//        objectBidimensionaToDepartamentuak(arrayDepartamentuak);
//        
//        for (int i = 0; i < arrayDepartamentuak.size(); i++) {
//        	System.out.println("pepe");
//			System.out.println(arrayDepartamentuak.get(i).toString());
//		}
////        scroll      = new JScrollPane();
////        cabecera    = new String[] {"Kodea","Izena","Kokapena"};
////        dtm         = new DefaultTableModel(datos,cabecera);
////        tabla       = new JTable(dtm);
////        scroll.setViewportView(tabla);
//        //tabla.setCellEditor(true);
//        //y ahora se coloca el scrollpane...
//        contenedor.add(scroll); //añadir al contenedor
//        sp.putConstraint(SpringLayout.NORTH, scroll, 120,
//                        SpringLayout.NORTH, contenedor);
//        sp.putConstraint(SpringLayout.WEST, scroll,   10,
//                        SpringLayout.WEST, contenedor);
//        sp.putConstraint(SpringLayout.EAST, scroll,  -10,
//                        SpringLayout.EAST, contenedor);
//        sp.putConstraint(SpringLayout.SOUTH, scroll, -50,
//                        SpringLayout.SOUTH, contenedor);
//        /**************** EOF TABLA ^^^^^^^^^^^^^^^^^^^^ **/
// 
//        /**************** BOF BOTONES vvvvvvvvvvvvvvvvvv **/
//        //BOTÓN AÑADIR
//        btnAdd = new JButton("Gehitu");
//        sp.putConstraint(SpringLayout.WEST, btnAdd,   246,
//                        SpringLayout.WEST, contenedor);
//        btnAdd.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		
//        			
//            		txtKodea.setEditable(true);
//            		txtIzena.setEditable(true);
//            		txtKokapena.setEditable(true);
//            		btnGehitu.setEnabled(true);
//            		
//            		btnDel.setEnabled(false);
//            		btnUpd.setEnabled(false);
//            		btnAdd.setEnabled(false);
//            		
//    				JOptionPane.showMessageDialog(null, "Sartu gehitu nahi dituzun datuak");
//				
//        		
//        	}
//        });
//        contenedor.add(btnAdd);
//        //BOTÓN BORRAR
//        btnDel = new JButton("Kendu");
//        btnDel.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		
//        		try {
//        			int lerroAukeratu = tabla.getSelectedRow();
//            		((DefaultTableModel)tabla.getModel()).removeRow(lerroAukeratu);
//				} catch (Exception e2) {
//					JOptionPane.showMessageDialog(null, "Ez duzu lerrorik aukeratu");
//				}
//        		
//        		
//        		
//        		Departamentua.DepartamentuBatBakarrikEzabatu(Integer.parseInt(kodea));
//				JOptionPane.showMessageDialog(null, "Datuak ondo atxindu dira");
//        		
//        	}
//        });
//        sp.putConstraint(SpringLayout.WEST, btnDel, 395, SpringLayout.WEST, contenedor);
//        sp.putConstraint(SpringLayout.SOUTH, btnDel, -10, SpringLayout.SOUTH, contenedor);
//        sp.putConstraint(SpringLayout.NORTH, btnAdd, 0, SpringLayout.NORTH, btnDel);
//        sp.putConstraint(SpringLayout.EAST, btnAdd, -48, SpringLayout.WEST, btnDel);
//        tabla.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				final int row = tabla.rowAtPoint(new Point(e.getX(), e.getY()));
//				tabla.setRowSelectionInterval(row, row);
//				int row2 = tabla.rowAtPoint(e.getPoint());
//				kodea = tabla.getValueAt(row2, 0).toString();
//				izena = tabla.getValueAt(row2, 1).toString();
//				kokapena = tabla.getValueAt(row2, 2).toString();
//				System.out.println(kodea+" "+izena+" "+kokapena);
//				
//				
//			}
//		});
//        contenedor.add(btnDel);
//        //BOTÓN MODIFICAR
//        btnUpd          = new JButton("Editatu");
//        btnUpd.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		
//        		
//        		txtKodea.setEditable(true);
//        		txtIzena.setEditable(true);
//        		txtKokapena.setEditable(true);
//        		btnAldatu.setEnabled(true);
//        		
//        		btnDel.setEnabled(false);
//        		btnUpd.setEnabled(false);
//        		
//        		
//        		
//        		
//        		
//        	}
//        });
//        sp.putConstraint(SpringLayout.EAST, btnDel, -50, SpringLayout.WEST, btnUpd);
//        sp.putConstraint(SpringLayout.WEST, btnUpd, -138, SpringLayout.EAST, contenedor);
//        sp.putConstraint(SpringLayout.SOUTH, btnUpd, -10, SpringLayout.SOUTH, contenedor);
//        sp.putConstraint(SpringLayout.EAST, btnUpd, -37, SpringLayout.EAST, contenedor);
//        contenedor.add(btnUpd);
//        
//        btnAtzera = new JButton("Atzera");
//        btnAtzera.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		
//        		dispose();
//        		kontroladorea.AtzeraFitxategiakDepart();
//        		
//        		
//        	}
//        });
//        sp.putConstraint(SpringLayout.WEST, btnAtzera, 21, SpringLayout.WEST, contenedor);
//        sp.putConstraint(SpringLayout.SOUTH, btnAtzera, -10, SpringLayout.SOUTH, contenedor);
//        sp.putConstraint(SpringLayout.EAST, btnAtzera, 122, SpringLayout.WEST, contenedor);
//        contenedor.add(btnAtzera);
//        
//        btnGehitu = new JButton("gehitu");
//        sp.putConstraint(SpringLayout.NORTH, btnGehitu, 21, SpringLayout.NORTH, contenedor);
//        sp.putConstraint(SpringLayout.WEST, btnGehitu, -203, SpringLayout.EAST, contenedor);
//        sp.putConstraint(SpringLayout.SOUTH, btnGehitu, 54, SpringLayout.NORTH, contenedor);
//        sp.putConstraint(SpringLayout.EAST, btnGehitu, -78, SpringLayout.EAST, contenedor);
//        btnGehitu.setEnabled(false);
//        btnGehitu.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		
//        		añadir[0] = txtKodea.getText();
//        		añadir[1] = txtIzena.getText();
//        		añadir[2] = txtKokapena.getText();
//        		
//        		if (zenbakiaDa(añadir[0])==true && Integer.parseInt(añadir[0])>Departamentua.KodeAltuenaAtera() && zenbakiaDa(añadir[1])==false && zenbakiaDa(añadir[2])==false) {
//        			((DefaultTableModel)tabla.getModel()).addRow(añadir);
//            		
//            		Departamentua departamentua = new Departamentua(Integer.parseInt(añadir[0]), añadir[1], añadir[2]);
//            		Departamentua.DepartamentuBatBakarrikIgo(departamentua);
//            		
//            		txtKodea.setEditable(false);
//            		txtIzena.setEditable(false);
//            		txtKokapena.setEditable(false);
//            		btnGehitu.setEnabled(false);
//            		
//            		btnDel.setEnabled(true);
//            		btnUpd.setEnabled(true);
//            		btnAdd.setEnabled(true);
//            		
//            		txtKodea.setText("");
//            		txtIzena.setText("");
//            		txtKokapena.setText("");
//            		
//    				JOptionPane.showMessageDialog(null, "Ondo gehitu da ");
//
//				}else if (zenbakiaDa(añadir[0])==false || Integer.parseInt(añadir[0])<=Departamentua.KodeAltuenaAtera()) {
//    				JOptionPane.showMessageDialog(null, "Kodea zenbaki oso bat izan behar du eta kodea "+Departamentua.KodeAltuenaAtera()+" baino altuago izan behar du");
//				}else if (zenbakiaDa(añadir[1])==true) {
//    				JOptionPane.showMessageDialog(null, "Izena kate bat izan behar du");
//				}else if (zenbakiaDa(añadir[2])==true) {
//    				JOptionPane.showMessageDialog(null, "Kokapena kate bat izan behar du");
//				}
//        		
//        		
//        		
//        		
//        		
//        		
//        		
//        	}
//        });
//        btnGehitu.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        contenedor.add(btnGehitu);
//        
//        btnAldatu = new JButton("Aldatu");
//        btnAldatu.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		
//        		
//        		
//        		
//        		
//        		
//        	}
//        });
//        sp.putConstraint(SpringLayout.NORTH, btnAldatu, 17, SpringLayout.SOUTH, btnGehitu);
//        sp.putConstraint(SpringLayout.WEST, btnAldatu, 0, SpringLayout.WEST, btnGehitu);
//        sp.putConstraint(SpringLayout.SOUTH, btnAldatu, -16, SpringLayout.NORTH, scroll);
//        sp.putConstraint(SpringLayout.EAST, btnAldatu, 0, SpringLayout.EAST, btnGehitu);
//        btnAldatu.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        btnAldatu.setEnabled(false);
//        contenedor.add(btnAldatu);
//        /**************** EOF BOTONES ^^^^^^^^^^^^^^^^^^^^ **/
//
//        //Se hace visible la ventana
//        //setVisible(true);
// 
    }
    
//    public void objectBidimensionaToDepartamentuak (ArrayList<Departamentua> arrayDepartamentuaKontrolador) {
//    	//kontroladorea.departamentuak = arrayDepartamentuaKontrolador;
//    	kontroladorea.departamentuak = kontroladorea.DepartamentuakSelect();
//    	Object[][] datos = new Object[kontroladorea.departamentuak.size()][3] ;
//    	
//	    	for (int i = 0; i < kontroladorea.departamentuak.size(); i++) {
//				datos[i][0]= Integer.toString(kontroladorea.departamentuak.get(i).getKodea());
//				datos[i][1]= kontroladorea.departamentuak.get(i).getIzena();
//				datos[i][2]= kontroladorea.departamentuak.get(i).getKokapena();
////				datos[i][0]= "pepe";
////				datos[i][1]= "pepito";
////				datos[i][2]= "pedro";
//			}
//	    	for (int i = 0; i < datos.length; i++) {
//				System.out.println(datos[i][1].toString());
//			}
//	    	//Jtable(datos);
//	    	//return datos;
//	    }
//    public void añadirFilas() {
//    	String [] arrayañadir = new String [3];
//    	for (int i = 0; i < arrayDepartamentuak.size(); i++) {
//    		arrayañadir[0] = Integer.toString(arrayDepartamentuak.get(i).getKodea());
//    		arrayañadir[1] = arrayDepartamentuak.get(i).getIzena();
//    		arrayañadir[2] = arrayDepartamentuak.get(i).getKokapena();
//			((DefaultTableModel)tabla.getModel()).addRow(arrayañadir);
//		}
//    }
    
//    public void arrayDepartamentuaArtu(ArrayList<Departamentua> arrayqueseiguala) {
//    	kontroladorea.departamentuak = arrayqueseiguala;
//    	
//	}
//    public void Jtable(Object[][] datos) {
//    	scroll      = new JScrollPane();
//        cabecera    = new String[] {"Kodea","Izena","Kokapena"};
//        dtm         = new DefaultTableModel(datos,cabecera);
//        tabla       = new JTable(dtm);
//        scroll.setViewportView(tabla);
//    }
//    
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }
    
    public boolean zenbakiaDa(String Katea) {
    	boolean zenbakiaDa=false;
    	try {
    		Integer.parseInt(Katea);
    		zenbakiaDa=true;
		} catch (Exception e) {
			zenbakiaDa=false;
		}
    	return zenbakiaDa;
    }
    
    public boolean doubleDa(String Katea) {
    	boolean doubleDa=false;
    	try {
    		Double.parseDouble(Katea);
    		doubleDa=true;
		} catch (Exception e) {
			doubleDa=false;
		}
    	return doubleDa;
    }
    
    public void FitxategikoaJTableraGehituEnplegatuak() {
    	
		for (int i = 0; i < kontroladorea.enplegatuakFitxero.size(); i++) {
			kontroladorea.EnplegatuaIgo(kontroladorea.enplegatuakFitxero.get(i));
			DefaultTableModel model = (DefaultTableModel) tabla.getModel();
			model.addRow(new Object[]{kontroladorea.enplegatuakFitxero.get(i).getZenbaki(), kontroladorea.enplegatuakFitxero.get(i).getIzena(), kontroladorea.enplegatuakFitxero.get(i).getAbizenak(), kontroladorea.enplegatuakFitxero.get(i).getSoldata(),kontroladorea.enplegatuakFitxero.get(i).getAlta(),kontroladorea.enplegatuakFitxero.get(i).getDepKod(),kontroladorea.enplegatuakFitxero.get(i).getArduraMota()});
		}
		
	
}
    
    public void SetMaxDepart() {
    	
        lblMax.setText("Max : "+Departamentua.KodeAltuenaAtera());

    }
    
    public void MaxEnple() {
    	MaxEnple = Enplegatua.KodeAltuenaAtera();
    }
    
    public void ComboboxForArdura() {
    	ardurak = Enplegatua.ardurakSelect();
        //ardurak = kontroladorea.ardurakLista();
    	
    	comboBoxArdurak.removeAllItems();
        for (int i = 0; i < ardurak.size(); i++) {
			comboBoxArdurak.addItem(ardurak.get(i).toString());
		}
    }
    public void ComboboxForDepartamentuZenbakia() {
    	DepartamentuZenbakia = Departamentua.DepartamentuenZenbakiak();
        //ardurak = kontroladorea.ardurakLista();
    	
    	comboBoxDepartamentuZenbakiak.removeAllItems();
        for (int i = 0; i < DepartamentuZenbakia.size(); i++) {
			comboBoxDepartamentuZenbakiak.addItem(DepartamentuZenbakia.get(i).toString());
		}
    }
    

    public void sortuTaulaEnple(ArrayList<Enplegatua> enple) {
        /////////////////////////////////////////////////////////////////
	}


	public Object[][] datuakSortu(ArrayList<Enplegatua> enplelist) {
		Object[][] emaitza = new Object[enplelist.size()][7];
		String zenbaki, izena, abizena,soldata,alta,departamentu_kodea,ardurak_arduraMota=null;
		
		 for (int n = 0; n < emaitza.length;n++) {
			 zenbaki = Integer.toString(enplelist.get(n).getZenbaki());
			 izena = enplelist.get(n).getIzena();
			 abizena = enplelist.get(n).getAbizenak();
			 soldata = Double.toString(enplelist.get(n).getSoldata());
			 alta = enplelist.get(n).getAlta();
			 departamentu_kodea = Integer.toString(enplelist.get(n).getDepKod());
			 ardurak_arduraMota = enplelist.get(n).getArduraMota();
			
			 for(int i = 0;i < emaitza[0].length;i++) {
				 if (i==0) {
					 emaitza[n][i]= zenbaki;
				}
				else if (i==1) {
					emaitza[n][i]= izena;
				} 
				else if (i==2) {
					emaitza[n][i]= abizena;
				} 
				else if (i==3) {
					emaitza[n][i]= soldata;
				} 
				else if (i==4) {
					emaitza[n][i]= alta;
				} 
				else if (i==5) {
					emaitza[n][i]= departamentu_kodea;
				} 
				else if (i==6) {
					emaitza[n][i]= ardurak_arduraMota;
				}
				 				 
			 }
		 }
			
		return emaitza;
	
	}
    
	public String getDia() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	    Date now = new Date();
	    String Dia = sdfDate.format(now);
	    return Dia;
	}
	public String getHora() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
	    Date now = new Date();
	    String Hora = sdfDate.format(now);
	    return Hora;
	}
	
	public void botoiakOndoJarriEnple() {
		
		txtZenbakia.setEditable(false);
		txtIzena.setEditable(false);
		txtAbizena.setEditable(false);
		txtSoldata.setEditable(false);
		//comboBoxArdurak.setEditable(false);
		
		txtZenbakia.setText("");
		txtIzena.setText("");
		txtAbizena.setText("");
		txtSoldata.setText("");
		
		btnAtzera.setEnabled(true);
		btnAdd.setEnabled(true);
		btnDel.setEnabled(true);
		btnUpd.setEnabled(true);
		
	}

    
    
    
  //Para que las ventanas aparezcan

	public void Kontroladorea(Kontroladorea kontroladorea) {
		this.kontroladorea = kontroladorea;
	}
}
 