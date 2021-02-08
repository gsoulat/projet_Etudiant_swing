package fr.formation.afpa;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.formation.afpa.model.Etudiant;

public class NewStudent {

	private JFrame frmAjouterEtudiant;
	private final JLabel lblNewLabel = new JLabel("Nom");
	private JTextField tDate;
	private JTextField tNom;
	private JTextField tPrenom;

	Map<String, Etudiant> listeMapEtudiantMap = new HashMap<>();

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
		frmAjouterEtudiant.setBounds(100, 100, 450, 300);
		frmAjouterEtudiant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAjouterEtudiant.getContentPane().setLayout(null);
		lblNewLabel.setBounds(12, 42, 56, 41);
		frmAjouterEtudiant.getContentPane().add(lblNewLabel);

		JButton btnSave = new JButton("Enregistrer");
		btnSave.setBounds(139, 204, 172, 25);
		frmAjouterEtudiant.getContentPane().add(btnSave);

		JButton btnAnnul = new JButton("Annuler");
		btnAnnul.setBounds(323, 204, 97, 25);
		frmAjouterEtudiant.getContentPane().add(btnAnnul);

		tDate = new JTextField();
		tDate.setColumns(10);
		tDate.setBounds(139, 121, 281, 22);
		frmAjouterEtudiant.getContentPane().add(tDate);

		JLabel lblPrnom = new JLabel("Prénom");
		lblPrnom.setBounds(12, 77, 56, 41);
		frmAjouterEtudiant.getContentPane().add(lblPrnom);

		JLabel lblDateDeNaissance = new JLabel("Date de naissance");
		lblDateDeNaissance.setBounds(12, 108, 115, 41);
		frmAjouterEtudiant.getContentPane().add(lblDateDeNaissance);

		tNom = new JTextField();
		tNom.setColumns(10);
		tNom.setBounds(139, 51, 281, 22);
		frmAjouterEtudiant.getContentPane().add(tNom);

		tPrenom = new JTextField();
		tPrenom.setColumns(10);
		tPrenom.setBounds(139, 86, 281, 22);
		frmAjouterEtudiant.getContentPane().add(tPrenom);

		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(30, 204, 97, 25);
		frmAjouterEtudiant.getContentPane().add(btnRetour);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Etudiant students = new Etudiant();
				students = new Etudiant(tNom.getText(), tPrenom.getText(), tDate.getText());
				listeMapEtudiantMap.put(tNom.getText(), students);
				tNom.setText("");
				tPrenom.setText("");
				tDate.setText("");

				try {
					OutputStream os = new FileOutputStream("best/obj.txt");
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.writeUTF("Etudiant");
					oos.writeObject(new Etudiant());
					oos.writeObject(students);
					oos.close();
					os.close();

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

	
			}
		});

		btnAnnul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = null;
				for (Map.Entry mapentry : listeMapEtudiantMap.entrySet()) {
					message += (("clé: " + mapentry.getKey() + " | valeur: " + mapentry.getValue() + "\n"));
				}
				System.out.println(message);
			}
		});

	}

}
