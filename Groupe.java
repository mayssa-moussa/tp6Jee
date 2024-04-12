package metier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "GROUPES")
public class Groupe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGroupe;
    private String nomGroupe;
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @OneToMany(mappedBy = "groupe")
    private List<Etudiant> etudiants;

    public Groupe() {
        super();
    }

    public Groupe(String nomGroupe, Date dateCreation) {
        super();
        this.nomGroupe = nomGroupe;
        this.dateCreation = dateCreation;
    }

    public Long getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(Long idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
}

