package fr.formation.afpa;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.formation.afpa.model.Etudiant;
import fr.formation.afpa.service.EtudiantService;
import fr.formation.afpa.service.IEtudiantService;

public class Fenetre extends JFrame implements TableModelListener{
	private JLabel lblPrenom, lblNom, lblDate, lblPhoto, lblBienvenue, lblImg;
	private JPanel jPanelCreationEtudiant, pConnecter,pAffiche, pHome;
	private JTextField tPrenom, tNom, tPhoto, tLogin;
	private JMenuBar menuBar;
	private JMenu MenuEtudiant;
	private JMenuItem menuAdd, menuSearch, menuDeco;
	private JButton btnBrowser, btnSave, btnAnnul, btnModif;
	private JPasswordField tPwd;
	private ImageIcon image;
	int largeurFrame;
	int hauteurFrame;
	private JDatePickerImpl datePicker;
	private JTable jt;
	private IEtudiantService service = new EtudiantService();
	private List<Etudiant> listEtudiantAff;
	private JScrollPane sp;
	DefaultTableModel model;


	public Fenetre() {
		// Création de la fenetre.
		JFrame fenetre = new JFrame();
		fenetre.getContentPane().setBackground(new Color(135, 206, 250));
		fenetre.setForeground(Color.ORANGE);
		fenetre.setBackground(Color.GREEN);

		// titre de la fenetre.
		fenetre.setTitle("Gestions des étudiants : ");

		// Pour ajouter une icone au coin haut gauche de la fenetre
		ImageIcon IconeFrame = new ImageIcon("best/test.png");
		fenetre.setIconImage(IconeFrame.getImage());

		// Configuration de la taille de la fenetre
		largeurFrame = 400;
		hauteurFrame = 300;

		fenetre.setSize(400, 300);

		// Pour centre la fenetre au milieu
		fenetre.setLocationRelativeTo(null);

		// Ferme le programme
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.getContentPane().setLayout(null);

		menuBar = new JMenuBar();
		MenuEtudiant = new JMenu("Etudiant");
		menuBar.add(MenuEtudiant);

		menuAdd = new JMenuItem("Ajouter des étudiants");
		menuAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jPanelCreationEtudiant.setVisible(true);
				largeurFrame = 700;
				hauteurFrame = 350;
				pAffiche.setVisible(false);
				pConnecter.setVisible(false);
				jPanelCreationEtudiant.setBounds(0, 20, largeurFrame, hauteurFrame);
				fenetre.setSize(largeurFrame, hauteurFrame);
				btnModif.setVisible(false);
				btnSave.setVisible(true);
				btnModif.setVisible(false);
				btnBrowser.setVisible(true);
				tPrenom.setEditable(true);
				tNom.setEditable(true);
				tPhoto.setEditable(true);
				tNom.setText("");
				tPrenom.setText("");
				tPhoto.setText("");
				lblImg.setIcon(null);
				lblImg.revalidate();// Obligatoire pour vider l'image
			}
		});
		MenuEtudiant.add(menuAdd);
		menuSearch = new JMenuItem("Lister les étudiants");
		menuSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pConnecter.setVisible(false);
				pAffiche.setVisible(true);
				fenetre.setSize(640, 300);
				pAffiche.setBounds(0, 0, 700, 500);
				jPanelCreationEtudiant.setVisible(false);
				tNom.setText("");
				tPrenom.setText("");
				
			
				jt = new JTable();
				jt.setBounds(0, 0, 700, 500);
				jt.setModel(new DefaultTableModel(new Object[][] {

				}, new String[] { "Id", "Prenom", "Nom", "Date Naissance", "Photo" }));
		        List<Etudiant> listEtudiantAff = service.listEtudiant();
		        	
				DefaultTableModel model = (DefaultTableModel) jt.getModel();
				
				Object rowData[] = new Object[5];
				for (int i = 0; i < listEtudiantAff.size(); i++) {
					Object etudiant = listEtudiantAff.get(i);
					rowData[0] = ((Etudiant) etudiant).getIdEtudiant();
					rowData[1] = ((Etudiant) etudiant).getPrenom();
					rowData[2] = ((Etudiant) etudiant).getNom();
					rowData[3] = ((Etudiant) etudiant).getDateNaissance();
					rowData[4] = ((Etudiant) etudiant).getPhoto();
					model.addRow(rowData);
				}
				jt.setModel(model);
				model.fireTableDataChanged();
				pAffiche.add(jt);
				JScrollPane sp = new JScrollPane(jt);		
		        pAffiche.add(sp);
		        model.fireTableDataChanged();
		     
			}
		});
		MenuEtudiant.add(menuSearch);
		menuDeco = new JMenuItem("Déconnecter");
		menuDeco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pAffiche.setVisible(false);
				jPanelCreationEtudiant.setVisible(false);
				pConnecter.setVisible(false);
				pHome.setVisible(true);
				tLogin.setText("");
				tPwd.setText("");
				fenetre.setSize(400, 300);
			}
		});
		MenuEtudiant.add(menuDeco);
		fenetre.setJMenuBar(menuBar);
		menuBar.setVisible(false);
		
		UtilDateModel modelDate = new UtilDateModel();
		Properties pDate = new Properties();
		pDate.put("text.today", "Today");
		pDate.put("text.month", "Month");
		pDate.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(modelDate, pDate);
		datePanel.setBounds(170, 80, 150, 30);

		pHome = new JPanel();
		pHome.setBackground(new Color(173, 255, 47));
		pHome.setBounds(0, 0, 500, 328);
		fenetre.getContentPane().add(pHome);
		pHome.setLayout(null);

		JLabel lblLogin = new JLabel("Login :");
		lblLogin.setFont(new Font("Arial", Font.BOLD, 20));
		lblLogin.setBounds(70, 70, 150, 30);
		pHome.add(lblLogin);

		tLogin = new JTextField();
		tLogin.setColumns(20);
		tLogin.setBounds(220, 70, 150, 30);
		pHome.add(tLogin);

		JLabel lblPwd = new JLabel("Password :");
		lblPwd.setFont(new Font("Arial", Font.BOLD, 20));
		lblPwd.setBounds(70, 120, 150, 30);
		pHome.add(lblPwd);

		tPwd = new JPasswordField();
		tPwd.setBounds(220, 120, 150, 30);
		pHome.add(tPwd);

		JButton btnConnex = new JButton("Connection");
		btnConnex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codage = tLogin.getText() + tPwd.getText();
				Code.stringToMorse(codage);
				Code pwd = new Code(Code.stringToMorse(codage));
				if (Code.lireCode().equals(Code.stringToMorse(codage))) {
					pHome.setVisible(false);
					pConnecter.setVisible(true);
					pConnecter.setBounds(0, 0, largeurFrame, hauteurFrame);
					lblBienvenue.setText("Bienvenue " + tLogin.getText());
					menuBar.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(pHome, "Login ou mot de passe erronée");
				}

			}
		});
		btnConnex.setBounds(220, 160, 150, 30);
		pHome.add(btnConnex);

		JButton btnCreerPwd = new JButton("Creer mot de passe");
		btnCreerPwd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codage = tLogin.getText() + tPwd.getText();
				Code.stringToMorse(codage);
				Code pwd = new Code(Code.stringToMorse(codage));
				pwd.ecrireCode(Code.stringToMorse(codage));
			}
		});
		btnCreerPwd.setBounds(393, 160, 150, 30);
		pHome.add(btnCreerPwd);
		pHome.setBounds(0, 0, largeurFrame, hauteurFrame);

		jPanelCreationEtudiant = new JPanel();
		jPanelCreationEtudiant.setBounds(10, 313, 681, 250);
		fenetre.getContentPane().add(jPanelCreationEtudiant);
		jPanelCreationEtudiant.setBackground(new Color(135, 206, 250));

		lblNom = new JLabel("Nom");
		lblNom.setBounds(10, 40, 150, 30);
		lblNom.setFont(new Font("Arial", Font.BOLD, 20));

		tNom = new JTextField();
		tNom.setBounds(170, 40, 150, 30);
		tNom.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tNom.setColumns(20);

		tPrenom = new JTextField();
		tPrenom.setBounds(170, 0, 150, 30);
		tPrenom.setColumns(20);

		lblPrenom = new JLabel("Prénom");
		lblPrenom.setBounds(10, 0, 100, 30);
		lblPrenom.setFont(new Font("Arial", Font.BOLD, 20));

		lblPhoto = new JLabel("photo");
		lblPhoto.setBounds(10, 120, 150, 30);
		lblPhoto.setFont(new Font("Arial", Font.BOLD, 20));

		tPhoto = new JTextField();
		tPhoto.setBounds(170, 120, 150, 30);
		tPhoto.setColumns(10);

		lblDate = new JLabel("Date naissance");
		lblDate.setBounds(10, 80, 150, 30);
		lblDate.setFont(new Font("Arial", Font.BOLD, 20));
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBackground(new Color(135, 206, 250));
		datePicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		datePicker.setBounds(170, 80, 150, 30);
		jPanelCreationEtudiant.add(datePicker);

		jPanelCreationEtudiant.setLayout(null);
		jPanelCreationEtudiant.add(lblNom);
		jPanelCreationEtudiant.add(datePicker);
		jPanelCreationEtudiant.add(tNom);
		jPanelCreationEtudiant.add(tPrenom);
		jPanelCreationEtudiant.add(lblPrenom);
		jPanelCreationEtudiant.add(lblPhoto);
		jPanelCreationEtudiant.add(tPhoto);
		jPanelCreationEtudiant.add(lblDate);

		// Liste des boutons
		btnBrowser = new JButton("Parcourir");
		btnBrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				// filtrer les fichiers
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png");
				file.addChoosableFileFilter(filter);
				file.setAcceptAllFileFilterUsed(false);
				file.setDialogTitle("Importation d'une photo");
				int res = file.showSaveDialog(null);
				// si l'utilisateur clique sur enregistrer dans Jfilechooser
				if (res == JFileChooser.APPROVE_OPTION) {
					File selFile = file.getSelectedFile();
					String path = selFile.getAbsolutePath();
					ImageIcon icon = new ImageIcon(path);
					Image img = icon.getImage();
					Image newImg = img.getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH);
					image = new ImageIcon(newImg);
					lblImg.setIcon(image);
					tPhoto.setText(path);
				}
			}
		});
		btnBrowser.setBounds(170, 160, 150, 30);
		jPanelCreationEtudiant.add(btnBrowser);

		btnSave = new JButton("Enregistrer");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int nbPrenom = tPrenom.getText().length();
				int nbNom = tNom.getText().length();
				int nbDate = datePicker.getJFormattedTextField().getText().length();
				File fichier = new File(tPhoto.getText());
						
				if (nbPrenom > 3 && nbNom > 3 && nbDate>0 && fichier.exists()) {
					Etudiant studentAdd = new Etudiant(tNom.getText(), tPrenom.getText(),
							datePicker.getJFormattedTextField().getText(), tPhoto.getText());
					service.ajouterEtudiant(studentAdd);
					tNom.setText("");
					tPrenom.setText("");
					tPhoto.setText("");
					lblImg.setIcon(null);
					datePicker.getJFormattedTextField().setText("");
					lblImg.revalidate();// Obligatoire pour vider l'image
					JOptionPane.showMessageDialog(jPanelCreationEtudiant,
						    "Nouvel étudiant enregistrer",
						    "MsgBox",
						    JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (nbPrenom < 3)
					JOptionPane.showMessageDialog(jPanelCreationEtudiant,
						    "le prenom pas assez de caractère",
						    "Inane error",
						    JOptionPane.ERROR_MESSAGE);
					if (nbNom < 3)
					JOptionPane.showMessageDialog(jPanelCreationEtudiant,
						    "le nom pas assez de caractère",
						    "Inane error",
						    JOptionPane.ERROR_MESSAGE);
					if (nbDate < 3)
					JOptionPane.showMessageDialog(jPanelCreationEtudiant,
						    "il faut saisir une date",
						    "Inane error",
						    JOptionPane.ERROR_MESSAGE);
					if (!fichier.exists())
					JOptionPane.showMessageDialog(jPanelCreationEtudiant,
						    "la photo n'existe pas",
						    "Inane error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSave.setBounds(10, 200, 150, 30);
		jPanelCreationEtudiant.add(btnSave);

		btnModif = new JButton("Modifier");
		btnModif.setBounds(10, 200, 150, 30);
		jPanelCreationEtudiant.add(btnModif);

		btnAnnul = new JButton("Annuler");
		btnAnnul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tNom.setText("");
				tPrenom.setText("");
				tPhoto.setText("");
				lblImg.setIcon(null);
				datePicker.getJFormattedTextField().setText("");
				lblImg.revalidate();
			}
		});
		btnAnnul.setBounds(170, 200, 150, 30);
		jPanelCreationEtudiant.add(btnAnnul);
		
		lblImg = new JLabel("");
		lblImg.setBounds(400, 0, 250, 250);
		jPanelCreationEtudiant.add(lblImg);

		pConnecter = new JPanel();
		pConnecter.setBounds(0, 0, 10, 10);
		fenetre.getContentPane().add(pConnecter);

		lblBienvenue = new JLabel("New label");
		pConnecter.add(lblBienvenue);
		
		pAffiche = new JPanel();
		pAffiche.setForeground(new Color(240, 230, 140));
		pAffiche.setBounds(10, 0, 689, 300);
		fenetre.getContentPane().add(pAffiche);
		pConnecter.setVisible(false);
		pAffiche.setVisible(false);
		jPanelCreationEtudiant.setVisible(false);
		btnCreerPwd.setVisible(false);
		fenetre.setVisible(true);
	}


	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}	
}
