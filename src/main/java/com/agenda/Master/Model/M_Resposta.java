package com.agenda.Master.Model;

public class M_Resposta {
    private String mensagem;
    private boolean sucesso;
    private Long id;

    public M_Resposta(String mensagem, boolean sucesso, Long id) {
        this.mensagem = mensagem;
        this.sucesso = sucesso;
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
