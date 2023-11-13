package com.agenda.Master.Service;

import com.agenda.Master.Model.M_Cronograma;
import com.agenda.Master.Repository.R_Cronograma;
import com.agenda.Master.Repository.R_Usuario;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class S_Cronograma {
    private static R_Cronograma r_cronograma;

    public S_Cronograma(R_Cronograma r_cronograma){
        this.r_cronograma = r_cronograma;
    }
    public static String salvarCronograma(String dataini, String datafini){
        boolean podeSalvar = true;
        String mensagem = "";
        if(S_Generico.textoEstaVazio(dataini)){
            podeSalvar = false;
            mensagem += "Pricisa ser informado um Data Inicial!";
        }
        if(S_Generico.textoEstaVazio(datafini)){
            podeSalvar = false;
            mensagem += "Precisa ser informado uma Data final!";
        }
        if(podeSalvar){
            M_Cronograma m_cronograma = new M_Cronograma();
            m_cronograma.setDataini(Date.valueOf(dataini));
            m_cronograma.setDatafini(Date.valueOf(datafini));
            try{
                r_cronograma.save(m_cronograma);
            }catch (DataIntegrityViolationException e){
                mensagem += "Deu ruim";
            }
        }
        return mensagem;
    }
}
