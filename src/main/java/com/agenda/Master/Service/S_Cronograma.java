package com.agenda.Master.Service;

import com.agenda.Master.Model.M_Cronograma;
import com.agenda.Master.Model.M_Resposta;
import com.agenda.Master.Model.M_Usuario;
import com.agenda.Master.Repository.R_Cronograma;
import jakarta.servlet.http.HttpSession;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class S_Cronograma {
    private static R_Cronograma r_cronograma;
    private static M_Usuario m_usuario;

    public S_Cronograma(R_Cronograma r_cronograma){
        this.r_cronograma = r_cronograma;
    }

    // Função para salvar o cronograma associado ao usuário da sessão
    public static M_Resposta salvarCronograma(LocalDateTime dataini, LocalDateTime datafini, M_Usuario m_usuario) {
        boolean podeSalvar = true;
        String mensagem = "";
        Long idNovo = null;
        boolean ativo = true;

        if (S_Generico.textoEstaVazio(String.valueOf(dataini))) {
            podeSalvar = false;
            mensagem += "Precisa ser informada uma Data Inicial!";
        }
        if (S_Generico.textoEstaVazio(String.valueOf(datafini))) {
            podeSalvar = false;
            mensagem += "Precisa ser informada uma Data final!";
        }

        if (podeSalvar) {
            M_Cronograma m_cronograma = new M_Cronograma();
            m_cronograma.setDataini(dataini);
            m_cronograma.setDatafini(datafini);
            m_cronograma.setId_pessoa(m_usuario.getId());
            m_cronograma.setAtivo(ativo);
            try {
                M_Cronograma novoCronograma = r_cronograma.save(m_cronograma);
                idNovo = Long.valueOf(novoCronograma.getId());
            } catch (DataIntegrityViolationException e) {
                podeSalvar = false;
                mensagem += "Deu ruim";
            }
        }
        return new M_Resposta(mensagem,podeSalvar, idNovo);
    }

    public static M_Resposta deletarCronograma(String nome){
        boolean podeDeleta = true;
        String mensagem = "";

        if(S_Generico.textoEstaVazio(nome)){
            podeDeleta = false;
            mensagem = "Nome invalido!";
        }

        if(podeDeleta){
            try{
                for(M_Cronograma cronograma : r_cronograma.buscarNome(nome))
                r_cronograma.deleteById(cronograma.getId());
            }catch (Exception e){

            }
        }
        return new M_Resposta(mensagem,podeDeleta,null);
    }

    public static ArrayList<M_Cronograma> buscarCronograma(){
        return r_cronograma.BuscarPorCronograma();
    }
}