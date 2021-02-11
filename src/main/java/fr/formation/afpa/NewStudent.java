package fr.formation.afpa;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import fr.formation.afpa.model.Etudiant;
import fr.formation.afpa.service.EtudiantService;
import fr.formation.afpa.service.IEtudiantService;
import java.awt.Font;

public class NewStudent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frmAjouterEtudiant;
	private final JLabel lblNom = new JLabel("Nom");
	private JTextField tNom;
	private JTextField tPrenom;
	private IEtudiantService service = new EtudiantService();
	JTable jt;
	private JTextField tPhoto;
	JLabel lblImg;
	ImageIcon image;
	DefaultTableModel model;

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

	public NewStudent() {
		initialize();
	}

	private void initialize() {
		frmAjouterEtudiant = new JFrame();
		frmAjouterEtudiant.setTitle("Gestions Etudiants");
		frmAjouterEtudiant.setBounds(100, 100, 912, 617);
		frmAjouterEtudiant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAjouterEtudiant.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 894, 431);
		frmAjouterEtudiant.getContentPane().add(panel);
		panel.setVisible(false);

		JLabel lblPrnom = new JLabel("Prénom");
		lblPrnom.setFont(new Font("Arial", Font.BOLD, 20));
		lblPrnom.setBounds(12, 49, 113, 30);

		tPrenom = new JTextField();
		tPrenom.setBounds(219, 58, 113, 22);
		tPrenom.setColumns(10);

		tNom = new JTextField();
		tNom.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tNom.setBounds(219, 103, 113, 22);
		tNom.setColumns(10);

		lblImg = new JLabel();
		lblImg.setForeground(Color.YELLOW);
		lblImg.setBounds(615, 58, 239, 335);
		panel.add(lblImg);

		JLabel lblDateNais = new JLabel("Date de naissance");
		lblDateNais.setFont(new Font("Arial", Font.BOLD, 20));
		lblDateNais.setBounds(12, 149, 194, 30);

		JLabel lblPhoto = new JLabel("photo");
		lblPhoto.setFont(new Font("Arial", Font.BOLD, 20));
		lblPhoto.setBounds(12, 218, 179, 30);

		tPhoto = new JTextField();
		tPhoto.setBounds(91, 224, 127, 22);
		tPhoto.setColumns(10);

		// Liste des boutons
		JButton btnBrowser = new JButton("Parcourir");
		btnBrowser.setBounds(227, 223, 105, 25);
		JButton btnSave = new JButton("Enregistrer");
		btnSave.setBounds(12, 261, 161, 54);
		JButton btnModif = new JButton("Modifier");
		btnModif.setBounds(12, 261, 122, 54);
		JButton btnAnnul = new JButton("Annuler");
		btnAnnul.setBounds(185, 261, 147, 54);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePanel.setBounds(115, 272, 255, 35);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(219, 149, 113, 30);
		panel.add(datePicker);

		btnBrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				// filtrer les fichiers
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", ".jpg", ".png");
				file.addChoosableFileFilter(filter);
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

		panel.setLayout(null);
		panel.add(lblPrnom);
		panel.add(tPrenom);
		lblNom.setBounds(12, 105, 68, 16);
		lblNom.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblNom);
		panel.add(tNom);
		panel.add(lblDateNais);
		panel.add(lblPhoto);
		panel.add(tPhoto);
		panel.add(btnBrowser);
		panel.add(btnSave);
		panel.add(btnModif);
		panel.add(btnAnnul);

		JLabel lblMsgNom = new JLabel("");
		lblMsgNom.setBounds(12, 328, 331, 22);
		lblMsgNom.setForeground(Color.red);
		panel.add(lblMsgNom);
		
		JLabel lblMsg = new JLabel("");
		lblMsg.setBounds(344, 103, 223, 22);
		lblMsgNom.setForeground(Color.red);
		panel.add(lblMsg);
		
		JLabel lblMsgDate = new JLabel("");
		lblMsgDate.setForeground(Color.RED);
		lblMsgDate.setBounds(344, 149, 223, 30);
		panel.add(lblMsgDate);
		
		JLabel lblMsgPrenom = new JLabel("");
		lblMsgPrenom.setForeground(Color.RED);
		lblMsgPrenom.setBounds(344, 58, 205, 22);
		panel.add(lblMsgPrenom);
		
		JLabel lblMsgPhoto = new JLabel("");
		lblMsgPhoto.setForeground(Color.RED);
		lblMsgPhoto.setBounds(344, 218, 223, 30);
		panel.add(lblMsgPhoto);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 882, 431);
		frmAjouterEtudiant.getContentPane().add(panel_1);
		panel_1.setVisible(false);
		JLabel lbltable = new JLabel("New label");
		lbltable.setBounds(680, 0, 56, 16);
		panel_1.add(lbltable);

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
				tPrenom.setEditable(true);
				tNom.setEditable(true);
				tPhoto.setEditable(true);
				tNom.setText("");
				tPrenom.setText("");
				tPhoto.setText("");

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
				lblMsgNom.setText("");
				List<Etudiant> listEtudiantAff = new ArrayList();
				listEtudiantAff = service.listEtudiant();
				jt = new JTable();
				jt.setModel(new DefaultTableModel(new Object[][] {

				}, new String[] { "Id", "Prenom", "Nom", "Date Naissance", "Photo" }));

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
				jt.getModel().addTableModelListener(jt);
				jt.repaint();
				JScrollPane sp = new JScrollPane(jt);
				panel_1.add(sp);
//				frmAjouterEtudiant.getcon
				
				
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
				lblMsgNom.setText("");
				lblMsgPrenom.setText("");
				lblMsgDate.setText("");
				lblMsgPhoto.setText("");
				lblMsg.setText("");
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
					lblMsg.setText("Vous avez enregistrer un nouvel utilisateur");
				} else {
					if (nbPrenom < 3)
					lblMsgPrenom.setText("le prenom pas assez de caractère");
					if (nbNom < 3)
					lblMsgNom.setText("le nom pas assez de caractère");
					if (nbDate < 3)
					lblMsgDate.setText("il faut saisir une date");
					if (!fichier.exists())
					lblMsgPhoto.setText("la photo n'existe pas");
					JOptionPane.showMessageDialog(frmAjouterEtudiant,
						    "Une ou plusieurs erreur ont été faites",
						    "Inane error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
//		jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//			
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		}); {
			
			
			
				
				



	}
}
