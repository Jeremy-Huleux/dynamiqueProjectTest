package javaProjectCnx;

import javax.persistence.*;

@Entity
public class Article1 {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int idArticle;
	String designation;
	float prixUnitaire;
	int quantite;
	int idCat;
	
	/*
	 * public Article1(int idArticle, String designation, float prixUnitaire, int
	 * quantite, int idCat) { super(); this.idArticle = idArticle; this.designation
	 * = designation; this.prixUnitaire = prixUnitaire; this.quantite = quantite;
	 * this.idCat = idCat; }
	 */
	public Article1() {
		super();
		this.idArticle = 0;
		this.designation = null;
		this.prixUnitaire = 0;
		this.quantite = 0;
		this.idCat = 0;
	}
	public Article1(String designation, float prixUnitaire, int quantite, int idCat) {

		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
		this.idCat = idCat;
	}
	public int getIdArticle1() {
		return idArticle;
	}
	public void setIdArticle1(int idArticle) {
		this.idArticle = idArticle;
	}
	public String getDesignation1() {
		return designation;
	}
	public void setDesignation1(String designation) {
		this.designation = designation;
	}
	public float getPrixUnitaire1() {
		return prixUnitaire;
	}
	public void setPrixUnitaire1(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
	public int getQuantite1() {
		return quantite;
	}
	public void setQuantite1(int quantite) {
		this.quantite = quantite;
	}
	public int getIdCat1() {
		return idCat;
	}
	public void setIdCat1(int idCat) {
		this.idCat = idCat;
	}
	
}
