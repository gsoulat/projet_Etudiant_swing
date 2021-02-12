package fr.formation.afpa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.formation.afpa.model.Etudiant;
import fr.formation.afpa.service.EtudiantService;
import fr.formation.afpa.service.IEtudiantService;

public class Fenetre extends JFrame implements TableModelListener {
	private JLabel lblPrenom, lblNom, lblDate, lblPhoto, lblImg;
	private JPanel pAjout, pAffiche, pHome, pNote;
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
	private JButton btnModifier;
	private JButton btnAjoutNote;
	private JLabel lblID;
	private int row;
	private JLabel lblNewLabel;

	public Fenetre() {
		/**
		 * Création de la fenetre.
		 */
		JFrame fenetre = new JFrame();

		/**
		 * titre de la fenetre.
		 */
		fenetre.setTitle("Gestions des étudiants : ");

		/**
		 * Pour ajouter une icone au coin haut gauche de la fenetre
		 */
		ImageIcon IconeFrame = new ImageIcon("best/test.png");
		fenetre.setIconImage(IconeFrame.getImage());

		/**
		 * Configuration de la taille de la fenetre
		 */
		largeurFrame = 800;
		hauteurFrame = 400;

		/**
		 * Pour centre la fenetre au milieu
		 */
		fenetre.setLocationRelativeTo(null);

		/**
		 * Design du Panel Connection
		 */
		pHome = new JPanel();
		pHome.setBackground(new Color(102, 205, 170));
//		fenetre.getContentPane().add(pHome);
		pHome.setLayout(null);

		JLabel lblLogin = new JLabel("Login :");
		lblLogin.setFont(new Font("Arial", Font.BOLD, 20));
		lblLogin.setBounds(300, 121, 150, 30);
		pHome.add(lblLogin);

		tLogin = new JTextField();
		tLogin.setColumns(20);
		tLogin.setBounds(450, 123, 150, 30);
		pHome.add(tLogin);

		JLabel lblPwd = new JLabel("Password :");
		lblPwd.setFont(new Font("Arial", Font.BOLD, 20));
		lblPwd.setBounds(300, 176, 150, 30);
		pHome.add(lblPwd);

		tPwd = new JPasswordField();
		tPwd.setBounds(450, 178, 150, 30);
		pHome.add(tPwd);

		JButton btnCreerPwd = new JButton("Creer mot de passe");
		btnCreerPwd.setBounds(615, 287, 150, 30);
		pHome.add(btnCreerPwd);

		JButton btnConnex = new JButton("Connection");
		btnConnex.setBounds(450, 235, 150, 30);
		pHome.add(btnConnex);
		pHome.setBounds(0, 0, largeurFrame, hauteurFrame);

		JLabel lblConnection = new JLabel("Bienvenue sur l'application de gestions des étudiants");
		lblConnection.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnection.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblConnection.setBounds(0, 13, 788, 35);
		pHome.add(lblConnection);

		JLabel lblPhotoAccueil = new JLabel("New label");
		lblPhotoAccueil.setBounds(42, 78, 200, 200);
		pHome.add(lblPhotoAccueil);
		ImageIcon icon = new ImageIcon("best\\profil.png");
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(lblPhotoAccueil.getWidth(), lblPhotoAccueil.getHeight(),
				Image.SCALE_SMOOTH);
		image = new ImageIcon(newImg);
		lblPhotoAccueil.setIcon(image);

		/**
		 * Design du MenuBar avec MenuItem
		 */
		menuBar = new JMenuBar();
		MenuEtudiant = new JMenu("Etudiant");
		menuBar.add(MenuEtudiant);
		// Ajout des sous Menu
		menuAdd = new JMenuItem("Ajouter des étudiants");
		MenuEtudiant.add(menuAdd);
		menuSearch = new JMenuItem("Lister les étudiants");
		MenuEtudiant.add(menuSearch);
		menuDeco = new JMenuItem("Déconnecter");
		MenuEtudiant.add(menuDeco);
		fenetre.setJMenuBar(menuBar);
		// Cacher la MenuBar tant que pas connecter
		menuBar.setVisible(false);

		/**
		 * Design du menu ajouter un etudiant
		 */

		/**
		 * Création du calendrier
		 */
		UtilDateModel modelDate = new UtilDateModel();
		Properties pDate = new Properties();
		pDate.put("text.today", "Today");
		pDate.put("text.month", "Month");
		pDate.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(modelDate, pDate);
		datePanel.setBounds(170, 80, 150, 30);

		btnCreerPwd.setVisible(false);
		fenetre.setVisible(true);
		fenetre.setSize(800, 463);
		pHome.setBounds(0, 00, largeurFrame, hauteurFrame);

		pAffiche = new JPanel();
		pAffiche.setForeground(new Color(240, 230, 140));
		pAffiche.setVisible(true);
		pAffiche.setBounds(0, 00, largeurFrame, hauteurFrame);
		pAffiche.setLayout(null);
		btnModifier = new JButton("Modifier");
		btnModifier.setBounds(12, 5, 79, 25);
		pAffiche.add(btnModifier);
		
		btnAjoutNote = new JButton("Ajouter notes");
		btnAjoutNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Onglets.set
			}
		});
		btnAjoutNote.setBounds(103, 5, 109, 25);
		pAffiche.add(btnAjoutNote);

		/**
		 * Instantiation de la Jtable pour afficher la base de données étudiant
		 */
		jt = new JTable();
		jt.setBounds(0, 0, largeurFrame, hauteurFrame - 150);
		model = new DefaultTableModel();
		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(-2, 35, 800, 325);
		sp.setPreferredSize(new Dimension(largeurFrame, hauteurFrame - 75));
		pAffiche.add(sp);

		// Créer le conteneur des onglets
		JTabbedPane onglets = new JTabbedPane();
		// Définir la position de conteneur d'onglets
		onglets.setBounds(0, 0, largeurFrame, hauteurFrame);
		// Associer chaque panneau à l'onglet correspondant
		onglets.add("Connection", pHome);
		
		lblNewLabel = new JLabel("Pour tester : Login : guillaume et password : soulat");
		lblNewLabel.setBounds(288, 61, 500, 47);
		pHome.add(lblNewLabel);
		pAjout = new JPanel();
		pAjout.setBackground(new Color(173, 216, 230));
		pAjout.setBounds(10, 313, 681, 250);
		// fenetre.getContentPane().add(pAjout);

		lblNom = new JLabel("Nom");
		lblNom.setBounds(10, 40, 150, 30);
		pAjout.add(lblNom);

		tNom = new JTextField();
		tNom.setBounds(170, 40, 150, 30);
		tNom.setColumns(20);
		pAjout.add(tNom);

		tPrenom = new JTextField();
		tPrenom.setBounds(170, 0, 150, 30);
		tPrenom.setColumns(20);
		pAjout.add(tPrenom);

		lblPrenom = new JLabel("Prénom");
		lblPrenom.setBounds(10, 0, 100, 30);
		pAjout.add(lblPrenom);

		lblPhoto = new JLabel("photo");
		lblPhoto.setBounds(10, 120, 150, 30);
		pAjout.add(lblPhoto);

		tPhoto = new JTextField();
		tPhoto.setBounds(170, 120, 150, 30);
		tPhoto.setColumns(10);
		pAjout.add(tPhoto);

		lblDate = new JLabel("Date naissance");
		lblDate.setBounds(10, 80, 150, 30);
		pAjout.add(lblDate);

		JDatePickerImpl datePicker_1 = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker_1.setBackground(new Color(173, 216, 230));
		datePicker_1.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		datePicker_1.setBounds(170, 80, 150, 30);
		pAjout.add(datePicker_1);
		pAjout.setLayout(null);

		btnBrowser = new JButton("Parcourir");
		btnBrowser.setBounds(170, 160, 150, 30);
		pAjout.add(btnBrowser);
		btnSave = new JButton("Enregistrer");
		btnSave.setBounds(10, 200, 150, 30);
		pAjout.add(btnSave);
		btnModif = new JButton("Modifier");

		btnModif.setBounds(10, 200, 150, 30);
		pAjout.add(btnModif);
		btnAnnul = new JButton("Annuler");
		btnAnnul.setBounds(170, 200, 150, 30);
		pAjout.add(btnAnnul);
		lblImg = new JLabel("");
		lblImg.setBounds(400, 0, 250, 250);
		pAjout.add(lblImg);
		pAjout.setVisible(false);
		pAjout.setBounds(0, 0, largeurFrame, hauteurFrame);

		onglets.add("Ajouter un Etudiant", pAjout);
		
		JLabel lblFakeDate = new JLabel("New label");
		lblFakeDate.setBounds(170, 80, 150, 30);
		pAjout.add(lblFakeDate);
		
		lblID = new JLabel("");
		lblID.setBounds(332, 7, 56, 16);
		pAjout.add(lblID);
		lblID.setVisible(false);
		lblFakeDate.setVisible(false);
		onglets.setEnabledAt(1, true);

		onglets.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (e.getSource() instanceof JTabbedPane) {
					JTabbedPane pane = (JTabbedPane) e.getSource();
					if (pane.getSelectedIndex() == 2) {
						updateTableau();
					}
				}
			}
		});

		// Liste des boutons

		btnBrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File("C:\\Users\\afpa\\Documents\\etudiant"));
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

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int nbPrenom = tPrenom.getText().length();
				int nbNom = tNom.getText().length();
				int nbDate = datePicker_1.getJFormattedTextField().getText().length();
				File fichier = new File(tPhoto.getText());

				if (nbPrenom > 3 && nbNom > 3 && nbDate > 0 && fichier.exists()) {
					Etudiant studentAdd = new Etudiant(numAuto(), tNom.getText(), tPrenom.getText(),
							datePicker_1.getJFormattedTextField().getText(), tPhoto.getText());
					service.ajouterEtudiant(studentAdd);
					tNom.setText("");
					tPrenom.setText("");
					tPhoto.setText("");
					lblImg.setIcon(null);
					datePicker_1.getJFormattedTextField().setText("");
					lblImg.revalidate();// Obligatoire pour vider l'image
					JOptionPane.showMessageDialog(pAjout, "Nouvel étudiant enregistrer", "MsgBox",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (nbPrenom < 3)
						JOptionPane.showMessageDialog(pAjout, "le prenom pas assez de caractère", "Inane error",
								JOptionPane.ERROR_MESSAGE);
					if (nbNom < 3)
						JOptionPane.showMessageDialog(pAjout, "le nom pas assez de caractère", "Inane error",
								JOptionPane.ERROR_MESSAGE);
					if (nbDate < 3)
						JOptionPane.showMessageDialog(pAjout, "il faut saisir une date", "Inane error",
								JOptionPane.ERROR_MESSAGE);
					if (!fichier.exists())
						JOptionPane.showMessageDialog(pAjout, "la photo n'existe pas", "Inane error",
								JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnAnnul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tNom.setText("");
				tPrenom.setText("");
				tPhoto.setText("");
				lblImg.setIcon(null);
				datePicker_1.getJFormattedTextField().setText("");
				lblImg.revalidate();
			}
		});

		pNote = new JPanel();
		pNote.setBackground(new Color(102, 205, 170));

		onglets.add("Affichage BDD", pAffiche);
		onglets.setEnabledAt(1, false);
		onglets.add("Notes", pNote);
		onglets.setEnabledAt(2, false);
		onglets.setEnabledAt(3, false);
		fenetre.getContentPane().add(onglets);

		// Ajouter les onglets au frame
		fenetre.getContentPane().add(onglets);

		/**
		 * Ferme le programme
		 */
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.getContentPane().setLayout(null);

		menuAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onglets.setSelectedIndex(1);
			}
		});

		menuSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onglets.setSelectedIndex(2);
				updateTableau();
			}
		});

		menuDeco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuBar.setVisible(false);
				onglets.setEnabledAt(1, false);
				onglets.setEnabledAt(2, false);
				lblLogin.setText("Login :");
				lblLogin.setFont(new Font("Arial", Font.BOLD, 20));
				lblLogin.setBounds(300, 121, 150, 30);
				tLogin.setVisible(true);
				tPwd.setVisible(true);
				lblPwd.setVisible(true);
				JOptionPane.showMessageDialog(pHome, "Au revoir : " + tLogin.getText());
				btnConnex.setText("Connection");
				tLogin.setText("");
				tPwd.setText("");
				onglets.setSelectedIndex(0);
			}
		});

		btnConnex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnConnex.getText() == "Connection") {
					String codage = tLogin.getText() + tPwd.getText();
					Code.stringToMorse(codage);
					Code pwd = new Code(Code.stringToMorse(codage));
					if (Code.lireCode().equals(Code.stringToMorse(codage))) {
						menuBar.setVisible(true);
						onglets.setEnabledAt(1, true);
						onglets.setEnabledAt(2, true);
						JOptionPane.showMessageDialog(pHome, "Vous êtes connecté : " + tLogin.getText());
						tLogin.setVisible(false);
						tPwd.setVisible(false);
						lblPwd.setVisible(false);
						lblLogin.setText("Vous êtes connecter en tant que : " + tLogin.getText());
						lblLogin.resize(500, 30);
						btnConnex.setText("Déconnection");
					} else {
						JOptionPane.showMessageDialog(pHome, "Login ou mot de passe erronée");
					}
				} else {
					menuBar.setVisible(false);
					onglets.setEnabledAt(1, false);
					onglets.setEnabledAt(2, false);
					lblLogin.setText("Login :");
					lblLogin.setFont(new Font("Arial", Font.BOLD, 20));
					lblLogin.setBounds(300, 121, 150, 30);
					tLogin.setVisible(true);
					tPwd.setVisible(true);
					lblPwd.setVisible(true);
					JOptionPane.showMessageDialog(pHome, "Au revoir : " + tLogin.getText());
					btnConnex.setText("Connection");
					tLogin.setText("");
					tPwd.setText("");
					onglets.setSelectedIndex(0);
				}

			}
		});

		btnCreerPwd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codage = tLogin.getText() + tPwd.getText();
				Code.stringToMorse(codage);
				Code pwd = new Code(Code.stringToMorse(codage));
				pwd.ecrireCode(Code.stringToMorse(codage));
			}
		});

		onglets.repaint();

		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 0;
				row = jt.getSelectedRow();
				String value = jt.getModel().getValueAt(row, column).toString();
				onglets.setSelectedIndex(1);
				btnSave.setVisible(false);
				btnModif.setVisible(true);
				lblFakeDate.setVisible(true);
				tPrenom.setEditable(false);
				tNom.setEditable(false);
				tPhoto.setEditable(false);
				btnBrowser.setVisible(false);
				datePicker_1.setVisible(false);
				btnAnnul.setVisible(false);

				try {
					Etudiant etudiant = service.trouverEtudiant(Integer.parseInt(value));
					tPrenom.setText(etudiant.getPrenom());
					lblID.setText(value);
					tNom.setText(etudiant.getNom());
					lblFakeDate.setText(etudiant.getDateNaissance());
					datePicker_1.getJFormattedTextField().setText(etudiant.getDateNaissance());
					tPhoto.setText(etudiant.getPhoto());
					ImageIcon icon = new ImageIcon(etudiant.getPhoto());
					Image img = icon.getImage();
					Image newImg = img.getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH);
					image = new ImageIcon(newImg);
					lblImg.setIcon(image);
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		btnModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnModif.getText() == "Modifier") {
					btnModif.setText("Enregistrer");
					lblFakeDate.setVisible(false);
					tPrenom.setEditable(true);
					tNom.setEditable(true);
					tPhoto.setEditable(true);
					btnBrowser.setVisible(true);
					datePicker_1.setVisible(true);
					btnAnnul.setVisible(false);
					
				} else {
					model.setValueAt(lblID.getText(), row, 0);
					model.setValueAt(tPrenom.getText(), row, 1);
					model.setValueAt(tNom.getText(), row, 2);
					model.setValueAt(tPhoto.getText(), row, 4);
					model.setValueAt(datePicker_1.getJFormattedTextField().getText(), row, 3);
					btnModif.setText("Modifier");
					tNom.setText("");
					tPrenom.setText("");
					tPhoto.setText("");
					lblImg.setIcon(null);
					datePicker_1.getJFormattedTextField().setText("");
					lblImg.revalidate();// Obligatoire pour vider l'image
					
					List<Etudiant> listEtudiantAff = new ArrayList<Etudiant>();
					List<Etudiant> listEtudiantModif = new ArrayList<Etudiant>();
					listEtudiantAff = service.listEtudiant();

					for (int i = 0; i < listEtudiantAff.size(); i++) {
						String idmodif = jt.getModel().getValueAt(i, 0).toString();
						String idPrenom = jt.getModel().getValueAt(i, 1).toString();
						String idNom = jt.getModel().getValueAt(i, 2).toString();
						String idDate = jt.getModel().getValueAt(i, 3).toString();
						String idPhoto = jt.getModel().getValueAt(i, 4).toString();
						Etudiant studentModif = new Etudiant(Integer.parseInt(idmodif), idNom, idPrenom,idDate,idPhoto);
						listEtudiantModif.add(studentModif);
						
					}
					service.modifierEtudiant(listEtudiantModif);
					
					JOptionPane.showMessageDialog(pAjout, "Enregistrement effectué");
				}
				
			}
		});

	}

	/**
	 * Trouver le dernier Identifiant unique enregistrer
	 * 
	 * @return
	 */
	public int numAuto() {
		List<Etudiant> listEtudiantAff = new ArrayList<Etudiant>();
		listEtudiantAff = service.listEtudiant();

		if (listEtudiantAff.size() == 0) {
			return 1;
		} else {
			Etudiant etudiant = listEtudiantAff.get(listEtudiantAff.size() - 1);
			return ((Etudiant) etudiant).getIdEtudiant() + 1;
		}
	}

	public void updateTableau() {
		List<Etudiant> listEtudiantAff = new ArrayList<Etudiant>();
		listEtudiantAff = service.listEtudiant();
		model.removeTableModelListener(jt);
		Object[] columnNames = { "Id", "Prenom", "Nom", "Date Naissance", "Photo" };
		
		model.setColumnIdentifiers(columnNames);
		model.fireTableDataChanged();
		Object[] rowData = new Object[5];
		for (int i = 0; i < listEtudiantAff.size(); i++) {
			Etudiant etudiant = listEtudiantAff.get(i);
			rowData[0] = ((Etudiant) etudiant).getIdEtudiant();
			rowData[1] = ((Etudiant) etudiant).getPrenom();
			rowData[2] = ((Etudiant) etudiant).getNom();
			rowData[3] = ((Etudiant) etudiant).getDateNaissance();
			rowData[4] = ((Etudiant) etudiant).getPhoto();
			model.addRow(rowData);
		}
		jt.setModel(model);

	}
	
}
