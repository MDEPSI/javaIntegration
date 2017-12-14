package Garderie;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


 
public class Garderie implements garderieInterface {

	private String idRL;
	private Double prixTotal = 0.0;
	private int nbQuartHeure = 0, nbQuartHeureTotal = 0;
	private Double prixQuartHeure = 0.0;
	private String nom;
	private int nbRepas = 0;
	
	public int getNbRepas() {
		return nbRepas;
	}

	public void setNbRepas(int nbRepas) {
		this.nbRepas = nbRepas;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getIdRL() {
		return idRL;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public int getNbQuartHeure() {
		return nbQuartHeure;
	}

	public Double getPrixQuartHeure() {
		return prixQuartHeure;
	}

	public ArrayList<Garderie> getLesResponsablesLegal() {
		return lesResponsablesLegal;
	}

	public void setIdRL(String idRL) {
		this.idRL = idRL;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public void setNbQuartHeure(int nbQuartHeure) {
		this.nbQuartHeure = nbQuartHeure;
	}

	public void setPrixQuartHeure(Double prixQuartHeure) {
		this.prixQuartHeure = prixQuartHeure;
	}

	public void setLesResponsablesLegal(ArrayList<Garderie> lesResponsablesLegal) {
		this.lesResponsablesLegal = lesResponsablesLegal;
	}



	private ArrayList<Garderie> lesResponsablesLegal = new ArrayList();
	
	void loadDriver() throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
	}
	
	Connection newConnection() throws SQLException{
		final String url = "jdbc:mysql://localhost/garderie";
		Connection conn = DriverManager.getConnection(url, "root", "");
		return conn;
		
	}

    public static double arrondie(double NombreDecimal, int NombreApresLaVirgule){
        double PuissancedeDix= Math.pow(10, NombreApresLaVirgule);
        return Math.round(NombreDecimal * PuissancedeDix ) /PuissancedeDix;
    }
	
	public void listPersons() throws SQLException {
		Connection conn = null;
		try {
			conn = newConnection();
			Statement st = conn.createStatement();			
			
			String queryResponsableLegal = "SELECT responsable_legal.id,nom, prenom, QF, prixQuartHeure FROM responsable_legal, tarif where responsable_legal.tarif_id=tarif.id and nom='"+nom+"' ";
			ResultSet rsRL = st.executeQuery(queryResponsableLegal);
			
			while (rsRL.next()){
				idRL = rsRL.getString("id");
				
				System.out.printf("%-20s %-20s %-20s %-20s %-20s \n", rsRL.getString("responsable_legal.id"), rsRL.getString("nom") ,rsRL.getString("prenom") ,rsRL.getString("QF"), rsRL.getString("prixQuartHeure"));			
				prixQuartHeure = rsRL.getDouble("prixQuartHeure");
			
			}
			String queryEnfant = "SELECT enfant.nom, enfant.prenom, heureArriveeMatin , heureDepartSoir , responsable_legal.nom, libelleJour FROM enfant, presence, responsable_legal, jour where enfant.id = presence.enfant_id and responsable_legal.id=enfant.responsable_legal_id and presence.jour_id=jour.id and responsable_legal.id = '"+idRL+"' ";
			ResultSet rsE = st.executeQuery(queryEnfant);
			
			while (rsE.next()){
				System.out.printf("%-20s %-20s %-20s %-20s \n", rsE.getString("nom") ,rsE.getString("prenom"), rsE.getString("heureArriveeMatin") ,rsE.getString("heureDepartSoir"));
				if(((rsE.getTime("heureDepartSoir").getHours()==18) && (rsE.getTime("heureDepartSoir").getMinutes()>=30))||(rsE.getTime("heureDepartSoir").getHours()>=19))
				{
					nbQuartHeure = ((-18+rsE.getTime("heureDepartSoir").getHours())*4) ;
					nbQuartHeure = nbQuartHeure + ((-30+rsE.getTime("heureDepartSoir").getMinutes())/15);
					prixTotal = prixTotal+5*nbQuartHeure;
					nbQuartHeure = -nbQuartHeure;
				}
				
				
				nbQuartHeure= nbQuartHeure +(9-rsE.getTime("heureArriveeMatin").getHours())*4;
				nbQuartHeure = nbQuartHeure + (0-rsE.getTime("heureArriveeMatin").getMinutes()/15);
				if (!rsE.getString("libelleJour").contains("MERCREDI"))
				{
					nbQuartHeure = nbQuartHeure + ((-16+rsE.getTime("heureDepartSoir").getHours())*4) ;
					nbQuartHeure = nbQuartHeure + ((-30+rsE.getTime("heureDepartSoir").getMinutes())/15);
				}
				else
				{
					nbQuartHeure = nbQuartHeure + ((-12+rsE.getTime("heureDepartSoir").getHours())*4) ;
					nbQuartHeure = nbQuartHeure + ((-00+rsE.getTime("heureDepartSoir").getMinutes())/15);
					prixTotal = prixTotal+4; 
					nbRepas = nbRepas+1;
				}
				nbQuartHeureTotal= nbQuartHeureTotal+nbQuartHeure;
				System.out.println(nbQuartHeure);
				prixTotal = prixTotal + prixQuartHeure*nbQuartHeure ;
				prixTotal = arrondie(prixTotal,2);
				nbQuartHeure = 0;
			}			
			
			System.out.println(prixTotal);

			
		}
		finally
		{
			if (conn != null) conn.close();
		}
	}
	


	public int getNbQuartHeureTotal() {
		return nbQuartHeureTotal;
	}

	public void setNbQuartHeureTotal(int nbQuartHeureTotal) {
		this.nbQuartHeureTotal = nbQuartHeureTotal;
	}

	public static void main(String[] args) {
		Garderie test = new Garderie();
		garderieInterface i = new Garderie();
		
		try{
			test.loadDriver();
			test.listPersons();

		}
		catch (ClassNotFoundException e){
			System.err.println("Pilote");
		}
		catch (SQLException e){
			System.out.println("SQL"+e.getMessage());
			System.out.println("SQL state"+e.getSQLState());
			System.out.println("Vendor"+e.getErrorCode());
			e.printStackTrace();
		}

	}

}
