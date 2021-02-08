package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class EtudiantSwing2 {

	private JFrame frmAjouterEtudiant;
	private final JLabel lblNewLabel = new JLabel("Nom");
	private JTextField tdate;
	private JTextField tnom;
	private JTextField tprenom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EtudiantSwing2 window = new EtudiantSwing2();
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
	public EtudiantSwing2() {
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

		tdate = new JTextField();
		tdate.setColumns(10);
		tdate.setBounds(139, 121, 281, 22);
		frmAjouterEtudiant.getContentPane().add(tdate);

		JLabel lblPrnom = new JLabel("Prénom");
		lblPrnom.setBounds(12, 77, 56, 41);
		frmAjouterEtudiant.getContentPane().add(lblPrnom);

		JLabel lblDateDeNaissance = new JLabel("Date de naissance");
		lblDateDeNaissance.setBounds(12, 108, 115, 41);
		frmAjouterEtudiant.getContentPane().add(lblDateDeNaissance);

		tnom = new JTextField();
		tnom.setColumns(10);
		tnom.setBounds(139, 51, 281, 22);
		frmAjouterEtudiant.getContentPane().add(tnom);

		tprenom = new JTextField();
		tprenom.setColumns(10);
		tprenom.setBounds(139, 86, 281, 22);
		frmAjouterEtudiant.getContentPane().add(tprenom);

		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(30, 204, 97, 25);
		frmAjouterEtudiant.getContentPane().add(btnRetour);
	}
}
