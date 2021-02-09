package fr.formation.afpa;

import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import fr.formation.afpa.model.Etudiant;
import fr.formation.afpa.service.EtudiantService;
import fr.formation.afpa.service.IEtudiantService;
import javafx.application.Application;
import javafx.stage.Stage;

public class NewStudent {

	private JFrame frmAjouterEtudiant;
	private final JLabel lblNewLabel = new JLabel("Nom");
	private JTextField tDate;
	private JTextField tNom;
	private JTextField tPrenom;
	private IEtudiantService service = new EtudiantService();
	private JTable table;
	private JTextField tPhoto;
	private Etudiant student = new Etudiant();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewStudent window = new NewStudent();
					window.frmAjouterEtudiant.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public NewStudent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAjouterEtudiant = new JFrame();
		frmAjouterEtudiant.setTitle("Ajouter Etudiant");
		frmAjouterEtudiant.setBounds(100, 100, 619, 482);
		frmAjouterEtudiant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAjouterEtudiant.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 601, 396);
		frmAjouterEtudiant.getContentPane().add(panel);
		panel.setVisible(false);

		JLabel lblPrnom = new JLabel("Prénom");
		lblPrnom.setBounds(170, 95, 44, 16);

		tPrenom = new JTextField();
		tPrenom.setBounds(225, 92, 116, 22);
		tPrenom.setColumns(10);

		tNom = new JTextField();
		tNom.setBounds(225, 127, 116, 22);
		tNom.setColumns(10);

		JLabel lblDateNais = new JLabel("Date de naissance");
		lblDateNais.setBounds(110, 171, 104, 16);

		tDate = new JTextField();
		tDate.setBounds(225, 168, 116, 22);
		tDate.setColumns(10);

		JLabel lblPhoto = new JLabel("photo");
		lblPhoto.setBounds(169, 233, 32, 16);

		tPhoto = new JTextField();
		tPhoto.setBounds(224, 230, 116, 22);
		tPhoto.setColumns(10);

		// Liste des boutons
		JButton btnBrowser = new JButton("Parcourir");
		btnBrowser.setBounds(352, 229, 85, 25);
		JButton btnSave = new JButton("Enregistrer");
		btnSave.setBounds(132, 290, 95, 25);
		JButton btnModif = new JButton("Modifier");
		btnModif.setBounds(256, 290, 79, 25);
		JButton btnAnnul = new JButton("Annuler");
		btnAnnul.setBounds(371, 290, 77, 25);

		btnBrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser dialogue = new JFileChooser(".");
				PrintWriter sortie = null;
				File fichier;

				if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					fichier = dialogue.getSelectedFile();
					try {
						sortie = new PrintWriter(new FileWriter(fichier.getPath(), true));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					tPhoto.setText(fichier.getPath());

					sortie.close();
				}
			}
		});
		panel.setLayout(null);
		panel.add(lblPrnom);
		panel.add(tPrenom);
		lblNewLabel.setBounds(170, 130, 26, 16);
		panel.add(lblNewLabel);
		panel.add(tNom);
		panel.add(lblDateNais);
		panel.add(tDate);
		panel.add(lblPhoto);
		panel.add(tPhoto);
		panel.add(btnBrowser);
		panel.add(btnSave);
		panel.add(btnModif);
		panel.add(btnAnnul);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 601, 396);
		frmAjouterEtudiant.getContentPane().add(panel_1);
		panel_1.setVisible(false);



		JMenuBar menuBar_1 = new JMenuBar();
		frmAjouterEtudiant.setJMenuBar(menuBar_1);
		JMenu mnNewMenu = new JMenu("Etudiant");
		menuBar_1.add(mnNewMenu);

		JMenuItem menuAdd = new JMenuItem("Ajouter");
		menuAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				btnModif.setVisible(false);
				panel_1.setVisible(false);
				btnSave.setVisible(true);
				btnModif.setVisible(false);
				btnBrowser.setVisible(true);
				tNom.setText("");
				tPrenom.setText("");
				tDate.setText("");
			}
		});
		mnNewMenu.add(menuAdd);

		JMenuItem menuAffiche = new JMenuItem("Afficher");
		menuAffiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(true);
				tNom.setText("");
				tPrenom.setText("");
				tDate.setText("");
				
				ArrayList<Etudiant> student1 = new ArrayList();
				
				student1 = (ArrayList) service.listEtudiant();
				System.out.println(student1.toString());	
				JTable jt = new JTable();
				jt.setModel(new DefaultTableModel(
				        new Object [][] {
				
				        },
				        new String [] {
				        		"Id", "Prenom", "Nom", "Date Naissance", "Photo"
				        }
				    ));
	
				DefaultTableModel model = (DefaultTableModel) jt.getModel();
				Object rowData[] = new Object[5];
				for(int i = 0; i < student1.size(); i++)
				{
					Object etudiant = student1.get(i);
				    rowData[0] = ((Etudiant) etudiant).getIdEtudiant();
				    rowData[1] = ((Etudiant) etudiant).getPrenom();
				    rowData[2] = ((Etudiant) etudiant).getNom();
				    rowData[3] = ((Etudiant) etudiant).getDateNaissance();
				    rowData[4] = ((Etudiant) etudiant).getPhoto();
				    model.addRow(rowData);
				    
				}
				JScrollPane sp = new JScrollPane(jt);
				panel_1.add(sp);

			}
		});
		mnNewMenu.add(menuAffiche);

		JMenuItem menuModif = new JMenuItem("Modifier");
		menuModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel_1.setVisible(false);
				btnSave.setVisible(false);
				btnModif.setVisible(true);
				btnBrowser.setVisible(false);
				tPrenom.setEditable(false);
				tNom.setEditable(false);
				tDate.setEditable(false);
				tPhoto.setEditable(false);
			}
		});
		mnNewMenu.add(menuModif);

		btnModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel_1.setVisible(false);
				btnSave.setVisible(true);
				btnModif.setVisible(false);
				btnBrowser.setVisible(true);
				tPrenom.setEditable(true);
				tNom.setEditable(true);
				tDate.setEditable(true);
				tPhoto.setEditable(true);

			}
		});

		btnAnnul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(false);
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Etudiant student = new Etudiant(tNom.getText(), tPrenom.getText(), tDate.getText(), tPhoto.getText());
				student = new Etudiant(tNom.getText(), tPrenom.getText(), tDate.getText(), tPhoto.getText());
				service.ajouterEtudiant(student);
				
				tNom.setText("");
				tPrenom.setText("");
				tDate.setText("");
				tPhoto.setText("");
			}
		});

	}

}
