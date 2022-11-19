package leihoak;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
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
import kontroladorea.Kontroladorea;
import kontroladorea.Nagusia;
 
public class DepartamentuakMenu extends JFrame {
	private ArrayList<Departamentua> arrayDepartamentuak = new ArrayList<Departamentua>();
	private ArrayList<String> ardurakVentana = new ArrayList<String>();
	
	private Kontroladorea kontroladorea;
	private Departamentua departamentua;
	
	//final static Logger logger = Logger.getLogger(prueba.class); 
	
	int lerroAukeratu;
	String[] añadir = {null, null, null}; // Cantidad de columnas de la tabla
	int count=0;
	String kodea=null;
	String izena=null;
	String kokapena=null;
    /**************** ATRIBUTOS ***************************/
    //CONTENEDOR PRINCIPAL
    private JPanel contenedor;
 
    //DEFINICIÓN DE LAS ETIQUETAS
    private JLabel lblKodea;
    private JLabel lblIzena;
    private JLabel lblKokapena;
 
    //DEFINICIÓN DE LOS CUADROS DE TEXTO
    protected JTextField txtKodea;
    protected JTextField txtIzena;
    protected JTextField txtKokapena;
 
    //DEFINICIÓN DE LOS BOTONES
    protected JButton btnAdd;
    protected JButton btnDel;
    protected JButton btnUpd;
    protected JButton btnAtzera;

 
    //DEFINICIÓN DE LOS OBJETOS PARA LA TABLA
    private JScrollPane scroll; //Panel de scroll que contiene la tabla
    private Object[][] datos; //Cuerpo de la tabla
	//protected Object[][] datos = new Object[arrayDepartamentuak.size()][3] ;
	//protected Object[][] datos = new Object[10][3] ;


    protected String[] cabecera;    //Cabecera de la tabla
    protected DefaultTableModel dtm;//Unión de la cabecera y la tabla
    protected JTable tabla; //Tabla propiamente dicha
//    private JButton btnAtzera;
//    private JButton btnGehitu;
//    private JButton btnAldatu;
    protected SpringLayout sp;

    /**************** MÉTODOS ***************************/
    //CONSTRUCTOR
    public DepartamentuakMenu(){
        //Métodos de la JFrame
    	setBounds(525,200,700,600);//Definir las dimensiones de la ventana
        //setTitle("GESTIÓN DE CLIENTES - KADUM");    //Barra de título
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //Acción al pulsar salir
 
        //CREAR EL CONTENEDOR PRINCIPAL Y AÑADIRLO A LA VENTANA
        contenedor = new JPanel();
        getContentPane().add(contenedor);
        
        //INDICAR QUE SE QUIERE USAR SPRINGLAYOUT
        sp = new SpringLayout();
        contenedor.setLayout(sp);
 
        //Vamos al lío
        /**************** BOF ETIQUETAS  vvvvvvvvvvvvvvvv **/
        //ETIQUETA NOMBRE
        lblKodea = new JLabel("Kodea:");  //Crear el objeto
        contenedor.add(lblKodea);      //Añadirlo al contenedor
        sp.putConstraint(SpringLayout.NORTH, lblKodea, 10,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblKodea,  10,
                        SpringLayout.WEST, contenedor);
        //ETIQUETA APELLIDOS
        lblIzena = new JLabel("Izena:");
        contenedor.add(lblIzena);
        sp.putConstraint(SpringLayout.NORTH, lblIzena, 50,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblIzena,  10,
                        SpringLayout.WEST, contenedor);
        //ETIQUETA NIF
        lblKokapena = new JLabel("Kokapena:");
        contenedor.add(lblKokapena);
        sp.putConstraint(SpringLayout.NORTH, lblKokapena, 90,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblKokapena,  10,
                        SpringLayout.WEST, contenedor);
        /**************** EOF ETIQUETAS  ^^^^^^^^^^^^^^^^ **/
 
        /**************** BOF CUADROS DE  TEXTO vvvvvvvvv **/
        //CUADRO DE TEXTO PARA EL NOMBRE
        txtKodea       = new JTextField();
        txtKodea.addKeyListener(new KeyAdapter() {
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
        contenedor.add(txtKodea);
        sp.putConstraint(SpringLayout.NORTH, txtKodea, 10,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtKodea, 100,
                        SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtKodea, 300,
                        SpringLayout.WEST, contenedor);
        txtKodea.setEditable(false);
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
        txtKokapena = new JTextField();
        txtKokapena.addKeyListener(new KeyAdapter() {
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
        contenedor.add(txtKokapena);
        sp.putConstraint(SpringLayout.NORTH, txtKokapena, 90, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtKokapena, 100, SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtKokapena, 300, SpringLayout.WEST, contenedor);
        txtKokapena.setEditable(false);
        
        /**************** BOF BOTONES vvvvvvvvvvvvvvvvvv **/
        //BOTÓN AÑADIR
        btnAdd          = new JButton("Gehitu");
        contenedor.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (count==0) {
					
					btnDel.setEnabled(false);
					btnUpd.setEnabled(false);
					txtKodea.setEditable(false);
					txtIzena.setEditable(true);
					txtKokapena.setEditable(true);
					txtKodea.setText(Integer.toString((kontroladorea.DepartamentuKodeaAltuena()+1)));
					
					JOptionPane.showMessageDialog(null, "Sartu gehitu nahi dituzun balioak ");
					
					count=1;
				}else if (count==1) {
					//boolean denaOndo=false;
					
						if (zenbakiaDa(txtKodea.getText())==true && zenbakiaDa(txtIzena.getText())==false && zenbakiaDa(txtKokapena.getText())==false) {
							if (Integer.parseInt(txtKodea.getText()) > kontroladorea.DepartamentuKodeaAltuena()) {
								//kodea = Integer.toString(kontroladorea.zbkRamdomDepart());
				  				Departamentua departamentua = new Departamentua(Integer.parseInt(txtKodea.getText()), txtIzena.getText(), txtKokapena.getText());
				  				//kontrol.DepartamentuBatSartu(dep);
				  				kontroladorea.DepartamentuaIgo(departamentua);
								DefaultTableModel model = (DefaultTableModel) tabla.getModel();
				  				model.addRow(new Object[]{Integer.parseInt(txtKodea.getText()), txtIzena.getText(), txtKokapena.getText()});
				  				count=0;
				  				
				  				txtKodea.setEditable(false);
								txtIzena.setEditable(false);
								txtKokapena.setEditable(false);
								
								btnAdd.setEnabled(true);
								btnDel.setEnabled(true);
								btnUpd.setEnabled(true);
								
								txtKodea.setText("");
								txtIzena.setText("");
								txtKokapena.setText("");
								
								Nagusia.logger.info((departamentua.getKodea()+" departamentua ondo gehitu da"));  
				  				
							}else {
								JOptionPane.showMessageDialog(null, "Sartutako kode zenbakia "+kontroladorea.DepartamentuKodeaAltuena()+" baino altuagoa izan behar du", "ERROR!", JOptionPane.WARNING_MESSAGE);
								Nagusia.logger.info(("Sartutako kode zenbakia "+kontroladorea.DepartamentuKodeaAltuena()+" baino txikiagoa sartu du(handiagoa izan behar du."));  
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
					//kontrol.departamentuBatEzabatu(Integer.parseInt(kodea));
					((DefaultTableModel)tabla.getModel()).removeRow(tabla.getSelectedRow());
					kontroladorea.DepartamentuaKendu(Integer.parseInt(kodea));
					
					Nagusia.logger.info((kodea+" departamentua ondo ezabatu da"));  
					
				}else if (tabla.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, "Ez duzu errekadarik aukeratu", "ERROR!", JOptionPane.WARNING_MESSAGE);
					Nagusia.logger.info(("Errorea. Ez duzu errekadarik aukeratu eta ezin izan da datu basetik kendu."));  

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
  				//kontrol.updateDepart(txtKodea.getText() ,txtIzena.getText() , txtKokapena.getText());
  				DefaultTableModel model = (DefaultTableModel) tabla.getModel();
  				int i = tabla.getSelectedRow();
  				if (tabla.getSelectedRow()!=-1) {
					
				
  				if (count==0) {
					
  					btnAdd.setEnabled(false);
					btnDel.setEnabled(false);
					btnUpd.setEnabled(true);
					txtKodea.setEditable(false);
					txtIzena.setEditable(true);
					txtKokapena.setEditable(true);
					txtKodea.setText(kodea);
					
					JOptionPane.showMessageDialog(null, "Sartu gehitu nahi dituzun balioak ");

					count=1;
				}else if(count == 1 && Departamentua.KodeAltuenaAtera()>=Integer.parseInt(txtKodea.getText()) && kodea.equals(txtKodea.getText())) {
					model.setValueAt(Integer.parseInt(txtKodea.getText()), i, 0);
					model.setValueAt(txtIzena.getText(), i, 1);
					model.setValueAt(txtKokapena.getText(), i, 2);
					
					Departamentua departamentua = new Departamentua(Integer.parseInt(txtKodea.getText()), txtIzena.getText(), txtKokapena.getText());
					Departamentua.DepartamentuBatBakarrikAldatu(departamentua);
					
                   	txtKodea.setEditable(false);
					txtIzena.setEditable(false);
					txtKokapena.setEditable(false);
					
					btnAdd.setEnabled(true);
					btnDel.setEnabled(true);
					btnUpd.setEnabled(true);
					
					txtKodea.setText("");
					txtIzena.setText("");
					txtKokapena.setText("");
					
					Nagusia.logger.info(("Departamentua aldatuta. Kodea: "+departamentua.getKodea()));  
                   
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
				
				



				
//				txtKodea.setEditable(false);
//				txtIzena.setEditable(false);
//				txtKokapena.setEditable(false);
//				
//				btnAdd.setEnabled(true);
//				btnDel.setEnabled(true);
//				btnUpd.setEnabled(true);
				
				dispose();
				kontroladorea.fitxategiakaukeratuDepartLeihoa();
				

				
			}
		});
        btnAtzera.setEnabled(true);
        contenedor.add(btnAtzera);
        sp.putConstraint(SpringLayout.SOUTH, btnAtzera, -10,
                        SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnAtzera,  520,
                        SpringLayout.WEST, contenedor);
        
        
        
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
        /**************** BOF TABLA  vvvvvvvvvvvvvvvvvvvv **/
        
        scroll      = new JScrollPane();
        cabecera    = new String[] {"Kodea","Izena","Kokapena"};
        dtm         = new DefaultTableModel(datuakSortu(Departamentua.DepartamentuakSelect()),cabecera);//Funciona bien
        //dtm         = new DefaultTableModel(datuakSortu(arrayDepartamentuak),cabecera);

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
      				kodea = tabla.getValueAt(row2, 0).toString();
      				izena = tabla.getValueAt(row2, 1).toString();
      				kokapena = tabla.getValueAt(row2, 2).toString();
      				txtKodea.setText(kodea.toString());
      				System.out.println(kodea+" "+izena+" "+kokapena);
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
 
        //Se hace visible la ventana
        //setVisible(true);
// 
    }
    
    public void objectBidimensionaToDepartamentuak (ArrayList<Departamentua> arrayDepartamentuaKontrolador) {
    	//kontroladorea.departamentuak = arrayDepartamentuaKontrolador;
    	arrayDepartamentuaKontrolador = kontroladorea.DepartamentuakSelect();
    	Object[][] datos = new Object[arrayDepartamentuaKontrolador.size()][3] ;
    	
	    	for (int i = 0; i < arrayDepartamentuaKontrolador.size(); i++) {
				datos[i][0]= Integer.toString(arrayDepartamentuaKontrolador.get(i).getKodea());
				datos[i][1]= arrayDepartamentuaKontrolador.get(i).getIzena();
				datos[i][2]= arrayDepartamentuaKontrolador.get(i).getKokapena();
//				datos[i][0]= "pepe";
//				datos[i][1]= "pepito";
//				datos[i][2]= "pedro";
			}
	    	for (int i = 0; i < datos.length; i++) {
				System.out.println(datos[i][1].toString());
			}
	    	//Jtable(datos);
	    	//return datos;
	    }
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
    
    public void FitxategikoaJTableraGehituDepartamentuak() {
    	
    		for (int i = 0; i < kontroladorea.departamentuakFitxero.size(); i++) {
    			kontroladorea.DepartamentuaIgo(kontroladorea.departamentuakFitxero.get(i));
    			DefaultTableModel model = (DefaultTableModel) tabla.getModel();
    			model.addRow(new Object[]{kontroladorea.departamentuakFitxero.get(i).getKodea(), kontroladorea.departamentuakFitxero.get(i).getIzena(), kontroladorea.departamentuakFitxero.get(i).getKokapena()});
			}
			
    	
    }
    
    
    
    
    

    public void sortuTaulaDepart(ArrayList<Departamentua> dep) {
        ///////////////////////////////////////////////////////////////
    	arrayDepartamentuak = dep;
	}


	public Object[][] datuakSortu(ArrayList<Departamentua> deplist) {
		Object[][] emaitza = new Object[deplist.size()][3];
		String Kodea, Izena, Kokapena=null;

		 for (int n = 0; n < emaitza.length;n++) {
			 Kodea = Integer.toString(deplist.get(n).getKodea());
			 Izena = deplist.get(n).getIzena();
			Kokapena = deplist.get(n).getKokapena();
			
			 for(int i = 0;i < emaitza[0].length;i++) {
				 if (i==0) {
					 emaitza[n][i]= Kodea;
				}
				else if (i==1) {
					emaitza[n][i]= Izena;
				} 
				else if (i==2) {
					emaitza[n][i]= Kokapena;
				} 
				 				 
			 }
		 }
			
		return emaitza;
	
	}
	
	public void botoiakOndoJarriDepart() {
		
		txtKodea.setEditable(false);
		txtIzena.setEditable(false);
		txtKokapena.setEditable(false);
		
		//txtKodea.setText("");
		txtIzena.setText("");
		txtKokapena.setText("");
		
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
 