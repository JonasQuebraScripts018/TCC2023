package com.agenda.Master.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Cronogramas")
public class M_Cronograma {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private int id;
    private Date dataini;
    private Date datafini;
    private int id_pessoa;

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataini() {
        return dataini;
    }

    public void setDataini(Date dataini) {
        this.dataini = dataini;
    }

    public Date getDatafini() {
        return datafini;
    }

    public void setDatafini(Date datafini) {
        this.datafini = datafini;
    }
}