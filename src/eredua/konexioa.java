package eredua;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class konexioa {
	private String  maquina="localhost/";
    private String  usuario="root";
    private String  clave="";
    private int puerto          = 3306;
    private String  servidor="127.0.0.1";
    private static Connection conexion  = null;
 
    //CONSTRUCTOR
    //Recibe el nombre de la base de datos
    /**
     * 
     * @param baseDatos
     */
    public konexioa(String baseDatos){
       
    		String server="jdbc:mysql://";
    	
    	this.servidor="jdbc:mysql://"+this.maquina+baseDatos+":"+ this.puerto+"/"+baseDatos;
    	//String Servidor2="jdbc:mysql://localhost/filmeak:3306/filmeak";
        //Registrar el driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR AL REGISTRAR EL DRIVER");
            System.exit(0); //parar la ejecución
        }
 
        //Establecer la conexión con el servidor
        try {
           conexion = DriverManager.getConnection("jdbc:mysql://"+this.maquina+baseDatos+"",this.usuario, this.clave);
        } catch (SQLException e) {
            System.err.println("ERROR AL CONECTAR CON EL SERVIDOR");
            System.exit(0); //parar la ejecución
        }
        System.out.println("Conectado a "+baseDatos);
    }
 
/**
 * 
 * @return conexion
 */
    //Devuelve el objeto Connection que se usará en la clase Controller
    public static Connection getConnection() {
        return conexion;
    }
 
}