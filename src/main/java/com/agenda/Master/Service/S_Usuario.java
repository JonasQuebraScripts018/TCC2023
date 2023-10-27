package com.agenda.Master.Service;

import com.agenda.Master.Model.M_Usuario;
import com.agenda.Master.Repository.R_Usuario;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class S_Usuario {
    private static R_Usuario r_usuario;

    public S_Usuario(R_Usuario r_usuario){
        this.r_usuario = r_usuario;
    }

    public static M_Usuario varificarLogin(String cpf, String senha){
        cpf = S_Generico.cleanerNumber(cpf);

        if(S_Generico.textoEstaVazio(cpf)){
            return null;
        }else if(S_Generico.textoEstaVazio(senha)){
            return null;
        }
        return r_usuario.BuscarPorCpfSenha(Long.parseLong(cpf), senha);
    }

    public static String cadastrarUsuario(String nome, String idade, String email, String cpf, String senha){
        boolean podeEnviar = true;
        String mensagem = "";
        cpf = S_Generico.cleanerNumber(cpf);
        if(S_Generico.textoEstaVazio(nome)){
            podeEnviar = false;
            mensagem += "O Nome precisa ser informado!!";
        }
        if(S_Generico.textoEstaVazio(idade)){
            podeEnviar = false;
            mensagem += "A Idade precisa ser informada!!";
        }
        if(!S_Generico.validarEmail(email)){
            podeEnviar = false;
            mensagem += "Email Invalido!!";
        }
        if(!S_CpfValidator.validateCPF(cpf)){
            podeEnviar = false;
            mensagem += "CPF Invalido!!";
        }
        if(S_Generico.textoEstaVazio(senha)){
            podeEnviar = false;
            mensagem += "A Idade precisa ser informada!!";
        }
        if(podeEnviar){
            M_Usuario m_usuario = new M_Usuario();
            m_usuario.setNome(nome);
            m_usuario.setIdade(Long.valueOf(idade));
            m_usuario.setEmail(email);
            m_usuario.setCpf(Long.valueOf(cpf));
            m_usuario.setSenha(senha);

            try{
                r_usuario.save(m_usuario);
                mensagem += "Deu bom";
            }catch (DataIntegrityViolationException e){
                mensagem += "Deu ruim";
            }
        }
        return mensagem;
    }

    public static ArrayList<M_Usuario> searchbar(String nome){
        boolean podePesquisar = true;
        String mensagem = "";

        if(S_Generico.textoEstaVazio(nome)){
            try {
                return r_usuario.BuscarPorNome(nome);
            } catch (DataIntegrityViolationException e){
                return null;
            }
        }
        return null;
    }
}
