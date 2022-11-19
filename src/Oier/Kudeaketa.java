package Oier;
 
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import kontroladorea.Kontroladorea;
 
public class Kudeaketa extends JFrame {
	
	//Leihoak
	private Kontroladorea kontroladorea;
	
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
 
    //DEFINICIÓN DE LOS OBJETOS PARA LA TABLA
    private JScrollPane scroll; //Panel de scroll que contiene la tabla
    protected Object[][] datos; //Cuerpo de la tabla
    protected String[] cabecera;    //Cabecera de la tabla
    protected DefaultTableModel dtm;//Unión de la cabecera y la tabla
    protected JTable tabla; //Tabla propiamente dicha
 
    /**************** MÉTODOS ***************************/
    public Kudeaketa(){
        //Métodos de la JFrame
        setBounds(525,200,700,600);//Definir las dimensiones de la ventana
        setTitle("GESTIÓN DE CLIENTES - KADUM");    //Barra de título
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //Acción al pulsar salir
 
        //CREAR EL CONTENEDOR PRINCIPAL Y AÑADIRLO A LA VENTANA
        contenedor = new JPanel();
        getContentPane().add(contenedor);
 
        //INDICAR QUE SE QUIERE USAR SPRINGLAYOUT
        SpringLayout sp = new SpringLayout();
        contenedor.setLayout(sp);
 
        //Vamos al lío
        /**************** BOF ETIQUETAS  vvvvvvvvvvvvvvvv **/
        //ETIQUETA NOMBRE
        lblKodea = new JLabel("Kodea:");  //Crear el objeto
        lblKodea.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contenedor.add(lblKodea);      //Añadirlo al contenedor
        sp.putConstraint(SpringLayout.NORTH, lblKodea, 10,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblKodea,  10,
                        SpringLayout.WEST, contenedor);
        //ETIQUETA APELLIDOS
        lblIzena = new JLabel("Izena:");
        lblIzena.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contenedor.add(lblIzena);
        sp.putConstraint(SpringLayout.NORTH, lblIzena, 50,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblIzena,  10,
                        SpringLayout.WEST, contenedor);
        //ETIQUETA NIF
        lblKokapena = new JLabel("Kokapena:");
        lblKokapena.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contenedor.add(lblKokapena);
        sp.putConstraint(SpringLayout.NORTH, lblKokapena, 90,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblKokapena,  10,
                        SpringLayout.WEST, contenedor);
        /**************** EOF ETIQUETAS  ^^^^^^^^^^^^^^^^ **/
 
        /**************** BOF CUADROS DE  TEXTO vvvvvvvvv **/
        //CUADRO DE TEXTO PARA EL NOMBRE
        txtKodea       = new JTextField();
        sp.putConstraint(SpringLayout.EAST, txtKodea, 365,
                        SpringLayout.WEST, contenedor);
        contenedor.add(txtKodea);
        sp.putConstraint(SpringLayout.NORTH, txtKodea, 10,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtKodea, 100,
                        SpringLayout.WEST, contenedor);
        //CUADRO DE TEXTO PARA EL NIF
        txtIzena = new JTextField();
        sp.putConstraint(SpringLayout.EAST, txtIzena, 0,
                        SpringLayout.EAST, txtKodea);
        contenedor.add(txtIzena);    //añadir al contenedor
        sp.putConstraint(SpringLayout.NORTH, txtIzena, 50,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtIzena, 100,
                        SpringLayout.WEST, contenedor);
        //CUADRO DE TEXTO PARA LOS APELLIDOS
        txtKokapena      = new JTextField();
        sp.putConstraint(SpringLayout.EAST, txtKokapena, 0, SpringLayout.EAST, txtKodea);
        contenedor.add(txtKokapena);
        sp.putConstraint(SpringLayout.NORTH, txtKokapena, 90, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtKokapena, 100, SpringLayout.WEST, contenedor);
        /**************** EOF CUADROS DE  TEXTO ^^^^^^^^^ **/
 
        /**************** BOF TABLA  vvvvvvvvvvvvvvvvvvvv **/
        scroll      = new JScrollPane();
        cabecera    = new String[] {"Kodea","Izena","Kokapena"};
        dtm         = new DefaultTableModel(datos,cabecera);
        tabla       = new JTable(dtm);
        scroll.setViewportView(tabla);
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
        /**************** EOF TABLA ^^^^^^^^^^^^^^^^^^^^ **/
 
        /**************** BOF BOTONES vvvvvvvvvvvvvvvvvv **/
        //BOTÓN AÑADIR
        btnAdd          = new JButton("Añadir");
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
        sp.putConstraint(SpringLayout.NORTH, btnAdd, 6, SpringLayout.SOUTH, scroll);
        sp.putConstraint(SpringLayout.WEST, btnAdd, 33, SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.SOUTH, btnAdd, -10,
                        SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.EAST, btnAdd, 128, SpringLayout.WEST, contenedor);
        contenedor.add(btnAdd);
        //BOTÓN BORRAR
        btnDel          = new JButton("Borrar");
        sp.putConstraint(SpringLayout.NORTH, btnDel, 6, SpringLayout.SOUTH, scroll);
        sp.putConstraint(SpringLayout.WEST, btnDel, 173, SpringLayout.EAST, btnAdd);
        sp.putConstraint(SpringLayout.SOUTH, btnDel, 0, SpringLayout.SOUTH, btnAdd);
        btnDel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contenedor.add(btnDel);
        //BOTÓN MODIFICAR
        btnUpd          = new JButton("Editar");
        sp.putConstraint(SpringLayout.EAST, btnDel, -176, SpringLayout.WEST, btnUpd);
        btnUpd.setFont(new Font("Tahoma", Font.PLAIN, 13));
        sp.putConstraint(SpringLayout.NORTH, btnUpd, 6, SpringLayout.SOUTH, scroll);
        sp.putConstraint(SpringLayout.WEST, btnUpd, -120, SpringLayout.EAST, contenedor);
        sp.putConstraint(SpringLayout.SOUTH, btnUpd, 40, SpringLayout.SOUTH, scroll);
        sp.putConstraint(SpringLayout.EAST, btnUpd, -40, SpringLayout.EAST, contenedor);
        btnUpd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        contenedor.add(btnUpd);
        /**************** EOF BOTONES ^^^^^^^^^^^^^^^^^^^^ **/
 
        //Se hace visible la ventana
        setVisible(true);
 
    }
    public void Kontroladorea(Kontroladorea kontroladorea) {
		this.kontroladorea = kontroladorea;
	}
 
//    public void conectaControlador(  Controller c  ){
// 
//        btnAdd.addActionListener(c);
//        btnAdd.setActionCommand("INSERTAR");
// 
//        btnDel.addActionListener(c);
//        btnDel.setActionCommand("BORRAR");
// 
//        btnUpd.addActionListener(c);
//        btnUpd.setActionCommand("MODIFICAR");
// 
//        tabla.addMouseListener(c);
//        //sólo se permite pulsar una fila a la vez.
//        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//    }
}