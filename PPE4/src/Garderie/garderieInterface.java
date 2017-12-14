package Garderie;

import java.awt.* ;
import java.awt.event.* ;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.* ;

class MaFenetre extends JFrame implements ActionListener
{ 
  private JLabel labNom, labTotal, labTauxQF, labNbRepas ;
  private JTextField nom ;
  static private String etiqNom = "Nom responsable légal : ", etiqTotal = "Total facture : ", etiqTauxQF = "Taux QF : ", etiqNbRepas = "Nombre de repas : ";
  private JButton boutonCalcul ;
	
  public MaFenetre ()
  {
	setTitle ("Garderie") ;
    setSize (160, 200) ;
    Container contenu = getContentPane() ;
    contenu.setLayout (new FlowLayout() ) ;

    labNom = new JLabel (etiqNom) ;
    contenu.add(labNom) ;
    nom = new JTextField (10) ;
    contenu.add(nom) ;
    boutonCalcul = new JButton ("CALCUL") ;
    contenu.add(boutonCalcul) ;
    boutonCalcul.addActionListener(this) ;
    labTauxQF = new JLabel (etiqTauxQF) ;
    contenu.add(labTauxQF) ;
    labNbRepas = new JLabel (etiqNbRepas) ;
    contenu.add(labNbRepas) ;
    labTotal = new JLabel (etiqTotal) ;
    contenu.add(labTotal) ;

  }

@Override
public void actionPerformed(ActionEvent e) {
	if (e.getSource() == boutonCalcul)
	    try
	    { 
	    	Garderie test = new Garderie();
	    	test.setNom(nom.getText());
	    	test.listPersons();
	    	
	    	Double total = 0.0, tauxQF=0.0; 
	    	int nbRepas = 0, nbQuartHeureTotal = 0;
	    	total = test.getPrixTotal();
	    	tauxQF = test.getPrixQuartHeure();
	    	nbRepas = test.getNbRepas();
	    	nbQuartHeureTotal = test.getNbQuartHeureTotal();
	    	
	    	labTauxQF.setText (etiqTauxQF + tauxQF) ;
	    	labNbRepas.setText (etiqNbRepas + nbRepas) ;
	    	labTotal.setText (etiqTotal + nbQuartHeureTotal) ;
	    	
	    }
	    catch (NumberFormatException | SQLException ex)
	    { 
	    	nom.setText ("") ;
	    	
	    	labNbRepas.setText (etiqNbRepas) ;
	    	labTauxQF.setText (etiqTauxQF) ;
	    	labTotal.setText (etiqTotal) ;
	    	
	    }
	}
}


public interface garderieInterface {
	 public static void main (String args[]) throws SQLException
	  { 
		 
		 MaFenetre fen = new MaFenetre() ;
		 fen.setVisible(true) ;

	  }
	
	
}

