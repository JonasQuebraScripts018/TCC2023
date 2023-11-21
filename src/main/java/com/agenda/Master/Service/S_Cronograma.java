package com.agenda.Master.Service;

import com.agenda.Master.Model.M_Cronograma;
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
    public static String salvarCronograma(String dataini, String datafini, M_Usuario m_usuario) {
        boolean podeSalvar = true;
        String mensagem = "";

        if (S_Generico.textoEstaVazio(dataini)) {
            podeSalvar = false;
            mensagem += "Precisa ser informada uma Data Inicial!";
        }
        if (S_Generico.textoEstaVazio(datafini)) {
            podeSalvar = false;
            mensagem += "Precisa ser informada uma Data final!";
        }

        if (podeSalvar) {
            M_Cronograma m_cronograma = new M_Cronograma();
            m_cronograma.setDataini(LocalDateTime.parse(dataini));
            m_cronograma.setDatafini(LocalDateTime.parse(datafini));
            m_cronograma.setId_pessoa(Integer.parseInt(m_usuario.getId().toString()));
            try {
                r_cronograma.save(m_cronograma);
            } catch (DataIntegrityViolationException e) {
                mensagem += "Deu ruim";
            }
        }
        return mensagem;
    }


    public static ArrayList<M_Cronograma> buscarCronograma(){
        return r_cronograma.BuscarPorCronograma();
    }
}