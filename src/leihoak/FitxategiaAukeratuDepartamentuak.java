package leihoak;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import kontroladorea.Kontroladorea;
import kontroladorea.Nagusia;

public class FitxategiaAukeratuDepartamentuak extends JFrame {
	
	private JTextField textfieldFitxategia = new JTextField();
	private JLabel lblFitxategiaKargatu = new JLabel("Departamentuen fitxategia kargatu");
	private JButton btnAukeratu = new JButton("Aukeratu");
	private JButton btnAtzera = new JButton("Atzera");
	//Leihoak
	private Kontroladorea kontroladorea;
	private JButton btnHurrengoa = new JButton("Hurrengoa (Igo datu Basera)");
	private File archivo = new File("");
	private JButton btnEzEgin = new JButton("Ez egin");

	public FitxategiaAukeratuDepartamentuak() {


		this.setBounds(525,200,700,600);
		this.setBackground(SystemColor.control);
		getContentPane().setLayout(null);
		
		lblFitxategiaKargatu.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblFitxategiaKargatu.setBounds(75, 24, 519, 60);
		getContentPane().add(lblFitxategiaKargatu);
		

		textfieldFitxategia.setBounds(0, 140, 499, 39);
		getContentPane().add(textfieldFitxategia);
		textfieldFitxategia.setColumns(10);
		textfieldFitxategia.setEditable(false);
		btnAukeratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try {
//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//						| UnsupportedLookAndFeelException e1) {
//					e1.printStackTrace();
//				}
				JFileChooser fc = new JFileChooser(".\\src\\");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fc.setMultiSelectionEnabled(false);
				
				FileNameExtensionFilter filtroCSV = new FileNameExtensionFilter("*.CSV", "csv");
				FileNameExtensionFilter filtroXML = new FileNameExtensionFilter("*.XML", "xml");
				FileNameExtensionFilter filtroJSON = new FileNameExtensionFilter("*.JSON", "json");
				fc.setFileFilter(filtroJSON);
				fc.setFileFilter(filtroXML);
				fc.setFileFilter(filtroCSV);
				

				
				fc.showOpenDialog(fc);
				archivo = fc.getSelectedFile();
				

				if (archivo != null) {
					textfieldFitxategia.setText(archivo.getAbsolutePath());
					btnHurrengoa.setEnabled(true);
				}
				
				
			}
		});
		
		btnAukeratu.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnAukeratu.setBounds(509, 140, 153, 39);
		getContentPane().add(btnAukeratu);
		
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				kontroladorea.AteraOngietorria();
				
			}
		});
		btnAtzera.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnAtzera.setBounds(33, 471, 184, 47);
		getContentPane().add(btnAtzera);
		btnHurrengoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				kontroladorea.fitxategiaGorde(archivo);
				try {
					kontroladorea.fitxategiaIgoDepartamentua(archivo);
					  
				} catch (Exception e2) {
					
					e2.printStackTrace();
				}
				
				//kontroladorea.depatamentuMenuraHurrengoBotoia();
				
			}
		});
		btnHurrengoa.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnHurrengoa.setBounds(276, 471, 364, 47);
		
		getContentPane().add(btnHurrengoa);
		btnHurrengoa.setEnabled(false);
		btnEzEgin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				kontroladorea.depatamentuMenuraEzEginBotoia();
				
				
			}
		});
		btnEzEgin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnEzEgin.setBounds(394, 392, 246, 47);
		
		getContentPane().add(btnEzEgin);
		
		
		
		
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//						| UnsupportedLookAndFeelException e) {
//					e.printStackTrace();
//				}
//				//new FitxategiaAukeratu().setVisible(true);
//			}
//		});
		

	}
	//Para que las ventanas aparezcan
	
	public void Kontroladorea(Kontroladorea kontroladorea) {
		this.kontroladorea = kontroladorea;
	}
	
	
	
}
