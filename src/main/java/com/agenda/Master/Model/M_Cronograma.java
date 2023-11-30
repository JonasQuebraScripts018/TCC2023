package com.agenda.Master.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Cronogramas")
public class M_Cronograma {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private LocalDateTime dataini;
    private LocalDateTime datafini;
    private Long id_pessoa;
    private boolean ativo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Long getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(Long id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataini() {
        return dataini;
    }

    public void setDataini(LocalDateTime dataini) {
        this.dataini = dataini;
    }

    public LocalDateTime getDatafini() {
        return datafini;
    }

    public void setDatafini(LocalDateTime datafini) {
        this.datafini = datafini;
    }
}
