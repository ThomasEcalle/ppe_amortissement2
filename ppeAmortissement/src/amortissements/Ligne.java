package amortissements;


/**
 * Ligne du tableau d'amortissement d'un credit.
 * @author thomas
 *
 */
public class Ligne 
{
	
	private int annee;
	private double capitalInitial,interets,amortissements,annuite,capitalFinal;

	
	/**
	 * Cree une ligne a partir des valeurs passees en parametres.
	 * @param annee
	 * @param capitalInitial
	 * @param interets
	 * @param amortissements
	 * @param annuite
	 * @param capitalFinal
	 */
	Ligne(int annee, double capitalInitial, double interets, 
			double amortissements, double annuite, double capitalFinal)
	{
		
		this.annee = annee;
		this.capitalInitial = capitalInitial;
		this.interets = interets;
		this.amortissements = amortissements;
		this.annuite = annuite;
		this.capitalFinal = capitalFinal;
	}

	
	/**
	 * Retourne l'indice de l'annee concernee par la ligne.
	 * Les indices commencent a 1.
	 * @return int
	 */
	public int getAnnee()
	{
		return annee + 1;
	}
	
	
	/**
	 * Retourne le montant du capital restant au debut 
	 * de la periode.
	 * @return double
	 */
	public double getCapitalInitial()
	{
		return capitalInitial;
	}
	
	/**
	 * Retourne le montant des interets verses pendant la periode.
	 * @return double
	 */
	public double getInterets()
	{
		return interets;
	}

	
	/**
	 *  Retourne le montant du capital amorti pendant la periode.
	 * @return double
	 */
	public double getAmortissements()
	{
		return amortissements;
	}

	
	/**
	 * Retourne le montant de l'annuite pendant la periode.
	 * @return double
	 */
	public double getAnnuite()
	{
		return annuite;
	}
	
	
	/**
	 * Retourne le montant du capital restant a la fin
	 * de la periode.
	 * @return double
	 */
	public double getCapitalFinal()
	{
		return capitalFinal;
	}
	
	
	
	
	
	/**
	 *  Retourne la premiere ligne du tableau d'amortissement pour
	 * le credit passe en parametre.
	 * @param credit
	 * @return Ligne
	 */
	public static Ligne premiereLigne(Credit credit)
	{
		double amortissement = credit.annuiteMaximale()-(credit.montantEmprunte()*credit.taux());
		return new Ligne (0, credit.montantEmprunte(), credit.montantEmprunte()*credit.taux(), 
		amortissement, credit.annuiteMaximale(),credit.montantEmprunte()-amortissement);
	}

	
	

	
	
	/**
	 * Retourne la ligne suivant la ligne courante pour le 
	 * passe en parametre. Retoure null si la ligne courante est
	 * la derniere ligne.
	 * @param credit
	 * @return Ligne
	 */
	
	public Ligne ligneSuivante(Credit credit)
	{
		int annee = this.annee + 1;
		if (annee < credit.duree()){
			if (credit.typeCredit() == Credit.AMORTISSEMENT_CONSTANTS){
				double capitalInital = this.capitalFinal;
				double interets = capitalInital * credit.taux();
				double amortissement = this.amortissements;
				double annuite = interets + amortissement;
				double capiFinal = capitalInital - amortissement;
				if((annee == credit.duree()-1) && (capiFinal != 0)){
					amortissement = capitalInital;
					annuite = interets + amortissement;
					capiFinal = capitalInital - amortissement;
				}
					
						
				
				return new Ligne (annee,capitalInital,interets,amortissement,annuite,capiFinal);
			}
			else{
				double 	annuiteFutur = annuite;
				double initial = (double)Math.round(this.capitalFinal * 100) / 100 ;
				double interet = (double)Math.round((this.capitalFinal*credit.taux()) * 100) / 100;
				double amort = (double)Math.round((this.annuite - interet) * 100) / 100;
				double capifinal = (double)Math.round((initial - amort) * 100) / 100;
				if ((annee == credit.duree()-1) && (capifinal != 0))
					
					if (capifinal > 0){
						amort = amort - capifinal;
						annuiteFutur = amort + interet;
						capifinal = 0;
					}
					else{
						amort = amort + capifinal;
						annuiteFutur = amort + interet;
						capifinal = 0;
					}
				return new Ligne (annee,initial,interet,
						amort,annuiteFutur,capifinal);
			}	
		}
		else{
			return null;
		}
	}
	public String toString() {
		return "| "+getAnnee()+ "    |"+ (double)Math.round(getCapitalInitial() * 100) / 100 + "    |"+ (double)Math.round(getInterets() * 100) / 100 + "    |"
	+ (double)Math.round(getAmortissements() * 100) / 100 + "    |"+ (double)Math.round(getAnnuite() * 100) / 100 + "    |"+ (double)Math.round(getCapitalFinal() * 100) / 100 + "    |";
	}
}
