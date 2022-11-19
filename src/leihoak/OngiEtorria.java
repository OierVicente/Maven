package leihoak;

import java.awt.Font;  
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import eredua.konexioa;

//import com.itextpdf.text.log.Level;

import kontroladorea.Kontroladorea;
//import net.sf.jasperreports.engine.JRDataSource;
//import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
//import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
//import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class OngiEtorria extends JFrame {


	private static final long serialVersionUID = 1L;
	private JButton btnDepartamentuak = new JButton("Departamentuak");
	private JButton btnEnplegatuak = new JButton("Enplegatuak");
	private JButton btnTxostena = new JButton("Formularioa edo txostena");
	
	//Leihoak
	private Kontroladorea kontroladorea;
	

	public OngiEtorria() {

		this.setBounds(525,200,700,600);
		this.setBackground(SystemColor.control);
		getContentPane().setLayout(null);
		btnDepartamentuak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				String DepartamentuaDa="Departamentuak";
				kontroladorea.fitxategiakaukeratuDepartLeihoa();
				
				
			}
		});
		
		btnDepartamentuak.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnDepartamentuak.setBounds(140, 29, 381, 90);
		getContentPane().add(btnDepartamentuak);
		btnTxostena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Txostena
				
//				JasperReport reporte;
//				
//				String path = ".\\src\\Departamentuak.jasper";
//				
//				try {
//					reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
//					JasperPrint jprint = JasperFillManager.fillReport(path, null);
//					JasperViewer viewer = new JasperViewer(jprint,false);
//					viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//					viewer.setVisible(true);
//				} catch (JRException e2) {
//					//Logger.getLogger(Diego.prueba.class.getName()).log(Level.SEVERE, null,ex);
//					e2.printStackTrace();
//					//hay que poner el log
//				}
				
//				try {
					
					sortuPdf();
					
//				} catch (JRException | SQLException e1) {
//					JOptionPane.showMessageDialog(null, "Datu basearen taularen bat hutsik dago eta ezin izan da reporta erakutsi", "ERROR!", JOptionPane.WARNING_MESSAGE);
//				}

//				JRDataSource dataSource = new JREmptyDataSource(1000);
//				
//				Map parameters = new HashMap();
//				parameters.put("id", 1500);
//				
//				JasperReport report = null;
//				try {
//					report = (JasperReport) JRLoader.loadObjectFromFile(".\\src\\Departamentuak.jasper");
//				} catch (JRException e2) {
//					e2.printStackTrace();
//				}
//
//				JasperPrint jasperPrint = null;
//				try {
//					jasperPrint = JasperFillManager.fillReport(report, parameters,dataSource);
//				} catch (JRException e1) {
//					e1.printStackTrace();
//				}
//
//				JFrame frame = new JFrame("Report");
//				frame.getContentPane().add(new JRViewer(jasperPrint));
//				frame.pack();
//				frame.setVisible(true);
				
				//JOptionPane.showMessageDialog(null, "Ez du ezer egiten");
				
			}
		});
		
		btnTxostena.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnTxostena.setBounds(140, 352, 381, 65);
		getContentPane().add(btnTxostena);
		
		btnEnplegatuak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				kontroladorea.fitxategiakaukeratuEnplegLeihoa();
				
				
			}
		});
		btnEnplegatuak.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnEnplegatuak.setBounds(140, 196, 381, 79);
		getContentPane().add(btnEnplegatuak);

	}
	
	public void sortuPdf(){
		// DEPARTAMENTUA
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(".\\src\\Departamentuak.jasper", null,konexioa.getConnection());
			JRPdfExporter exp = new JRPdfExporter();
			exp.setExporterInput(new SimpleExporterInput(jasperPrint));
			exp.setExporterOutput(new SimpleOutputStreamExporterOutput("departamentuak.pdf"));
			SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
			exp.setConfiguration(conf);
			exp.exportReport();

			// se muestra en una ventana aparte para su descarga
			JasperPrint jasperPrintWindow = JasperFillManager.fillReport(".\\src\\Departamentuak.jasper", null,konexioa.getConnection());
			JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow,false);
			jasperViewer.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Datu basean, Departamentu taula hutsik dago eta ezin izan da reporta erakutsi", "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		
		
		
		
		
		
		//ENPLEGATUA
		try {
			JasperPrint jasperPrint2 = JasperFillManager.fillReport(".\\src\\Enplegatuak.jasper", null,konexioa.getConnection());
			JRPdfExporter exp2 = new JRPdfExporter();
			exp2.setExporterInput(new SimpleExporterInput(jasperPrint2));
			exp2.setExporterOutput(new SimpleOutputStreamExporterOutput("enplegatuak.pdf"));
			SimplePdfExporterConfiguration conf2 = new SimplePdfExporterConfiguration();
			exp2.setConfiguration(conf2);
			exp2.exportReport();

			// se muestra en una ventana aparte para su descarga
			JasperPrint jasperPrintWindow2 = JasperFillManager.fillReport(".\\src\\Enplegatuak.jasper", null,konexioa.getConnection());
			JasperViewer jasperViewer2 = new JasperViewer(jasperPrintWindow2,false);
			jasperViewer2.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Datu basean, Enplegatu taula hutsik dago eta ezin izan da reporta erakutsi", "ERROR!", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}

	
	
	//Para que las ventanas aparezcan

		public void Kontroladorea(Kontroladorea kontroladorea) {
			this.kontroladorea = kontroladorea;
		}
}