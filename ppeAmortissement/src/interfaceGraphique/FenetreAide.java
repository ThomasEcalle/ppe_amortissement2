package interfaceGraphique;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
/**
 * Classe FenetreAide represente la procedure pour 
 * l'utilisateur, elle herite de la classe JDialog.
 * @author Jean
 *
 */
public class FenetreAide extends JDialog{
	
	private JPanel panel;
	private JTextArea textArea;
	private JButton bouton, bouton2;
	private JScrollPane scroll;
	
	/**
	 * Constructeur de la classe FenetreAide, appelle la fonction build().
	 */
	public FenetreAide(){
		build();
	}
	
	/**
	 * Initialise la fenetre � la bonne taille et avec les bonnes caract�ristiques.
	 */
	private void build(){
		setTitle("Aide d'utilisation"); 
		setSize(470,240); 
		setLocationRelativeTo(null); 
		setResizable(false); 
		setContentPane(buildContentPane());
		setModal(true);
	}
	
	/**
	 * Permet de creer le JPanel, de definir ses caracteristiques
	 * puis d'ajouter les differents elements a celui ci.
	 * @return panel
	 * 		Retourne le JPanel rempli.
	 */
	private JPanel buildContentPane(){	
		initialiseElements();
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.lightGray);
		panel.add(scroll);
		panel.add(bouton);
		panel.add(bouton2);
		return panel;
	}

	/**
	 * Permet d'initialiser les differents elements de la page.
	 */
	private void initialiseElements(){
		textArea = new JTextArea(getMessageDaide(), 10, 39);		
		textArea.setEditable(false);
		scroll = new JScrollPane(textArea);

		bouton = new JButton("OK");
		bouton2 = new JButton("Documentation");
		bouton.setPreferredSize(new Dimension(130, 27));
		bouton2.setPreferredSize(new Dimension(130, 27));
		bouton.addActionListener(new ActionListener(){
			/**
			 * Action du bouton ok permettant de fermer le JFrame.
			 */
		      public void actionPerformed(ActionEvent arg0) {
		    	  BoutonAide.fenetreAide.setVisible(false);
		          BoutonAide.fenetreAide.dispose();
		      }
		    });
		bouton2.addActionListener(new ActionListener(){
			/**
			 * Action du bouton documentation permettant
			 * d'ouvrir le pdf correspondant a celle ci et contenu
			 * dans les sources.
			 */
		      public void actionPerformed(ActionEvent arg0) {
		    	  	try {
						Desktop.getDesktop().open(new File(new File("").getAbsolutePath() + 
								"\\src\\pdf\\DocumentationUtilisateur.pdf"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		      }
		    });
	}
	/**
	 * Permet de fabriquer le message d'aide en concatenant plusieurs chaines.
	 * @return message
	 * 		Retourne le message complet.
	 */
	private String getMessageDaide(){
		String message;
		message = "Bonjour et bienvenue dans votre application de Gestion de l'amortissement.\n\n" +
		"Pour pouvoir generer un tableau d'amortissement il vous faudra connaitre au\nminimum " +
		"3 valeurs coherentes les unes avec les autres parmis : \n" +
		"  - le montant emprunte\n" +
		"  - le taux d'emprunt\n" +
		"  - la duree du remboursement\n" +
		"  - l'annuitee maximale\n\n" +
		"Cependant, si vous souhaitez generer un tableau a partir de 4 valeurs, \ncelles-ci devront" +
		" etre strictement exactes, au risque de produire une erreur.\n\n" +
		"Veuillez ensuite saisir vos valeurs ainsi que le type de remboursement \nqui vous " +
		"convient puis cliquez sur valider pour generer le tableau \nd'amortissement, ainsi que la " +
		"valeur inconnue si vous n'en aviez saisi que 3.\n\n" +
		"Vous avez de plus la possibilite d'exporter votre tableau d'amortissement au \nformat Excel (.xls)" +
		"grace au bouton Export.";
		return message;
	}
}
