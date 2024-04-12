package metier.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "ETUDIANTS")
public class Etudiant implements Serializable{
@Id
@Column (name="ID_ETUDIANT")
@GeneratedValue (strategy=GenerationType.IDENTITY)	
private Long idEtudiant;
private String nom;
private String prenom;
private String dateNaiss;
private String adresse;
private String email;
@Column (name="ANNEE_ETUDES")
private int anneeEtudes;
@Column (name="DEPARTEMENT")
private String dep;
@ManyToOne
@JoinColumn(name = "GROUPE_IDGROUPE")
private Groupe groupe;
public Etudiant() {
super();
}
public Etudiant(String nom, String prenom,Groupe gp) {

super();
this.nom = nom;
this.prenom = prenom;
this.setGroupe(gp);
}
public Groupe getGroupe() {
return groupe;
}
public void setGroupe(Groupe groupe) {
this.groupe = groupe;
}
public Etudiant(String nom,String prenom,String dateNaiss,String adresse,String email,
		int anneeEtudes,String dep) {
super();
this.setNom(nom);
this.setPrenom(prenom);
this.setDateNaiss(dateNaiss);
this.setAdresse(adresse);
this.setEmail(email);
this.setAnneeEtudes(anneeEtudes);
this.setDep(dep);
}
public Long getIdEtudiant() {
	return idEtudiant;
}
public void setIdEtudiant(Long idEtudiant) {
	this.idEtudiant = idEtudiant;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getDateNaiss() {
	return dateNaiss;
}
public void setDateNaiss(String dateNaiss) {
	this.dateNaiss = dateNaiss;
}
public String getAdresse() {
	return adresse;
}
public void setAdresse(String adresse) {
	this.adresse = adresse;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getAnneeEtudes() {
	return anneeEtudes;
}
public void setAnneeEtudes(int anneeEtudes) {
	this.anneeEtudes = anneeEtudes;
}
public String getDep() {
	return dep;
}
public void setDep(String dep) {
	this.dep = dep;
}
@Override
public String toString() {
	return "Etudiant [idEtudiant=" + idEtudiant + ", nom=" + nom + ", prenom=" + prenom + ", dateNaiss=" + dateNaiss
			+ "\n, adresse=" + adresse + ", email=" + email + ", anneeEtudes=" + anneeEtudes + ", dep=" + dep + "]";
}

}
