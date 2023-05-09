package com.blogia.modele;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idArticle", nullable = false)
    private int idArticle;
    @Column(name = "titre")
    private String titre;

    @Column(name = "description")
    private String description;

    @Column(name = "dateAjout", insertable = false)
    private Timestamp dateAjout;

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Timestamp dateAjout) {
        this.dateAjout = dateAjout;
    }
}
