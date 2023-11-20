package javaProjectCnx;

public class Article {
	int idArticle;
	String designation;
	float prixUnitaire;
	int quantite;
	int idCat;
	
	
	public Article(int idArticle, String designation, float prixUnitaire, int quantite, int idCat) {
		super();
		this.idArticle = idArticle;
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
		this.idCat = idCat;
	}
	public Article() {
		super();
		this.idArticle = 0;
		this.designation = null;
		this.prixUnitaire = 0;
		this.quantite = 0;
		this.idCat = 0;
	}
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public float getPrixUnitaire() {
		return prixUnitaire;
	}
	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public int getIdCat() {
		return idCat;
	}
	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}
	
}
