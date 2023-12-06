package com.agenda.Master.Service;

import com.agenda.Master.Model.M_Resposta;
import com.agenda.Master.Model.M_Usuario;
import com.agenda.Master.Repository.R_Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class S_Usuario {
    private static R_Usuario r_usuario;
    @Autowired
    private static JavaMailSender mailSender;


    public S_Usuario(R_Usuario r_usuario, JavaMailSender mailSender) {
        this.r_usuario = r_usuario;
        this.mailSender = mailSender;
    }

    public static M_Usuario varificarLogin(String cpf, String senha) {
        cpf = S_Generico.cleanerNumber(cpf);

        if (S_Generico.textoEstaVazio(cpf)) {
            return null;
        } else if (S_Generico.textoEstaVazio(senha)) {
            return null;
        }
        return r_usuario.BuscarPorCpfSenha(Long.parseLong(cpf), senha);
    }

    public static M_Resposta cadastrarUsuario(String nome, String idade, String email, String cpf, String senha) {
        boolean podeEnviar = true;
        String mensagem = "";
        cpf = S_Generico.cleanerNumber(cpf);
        if (S_Generico.textoEstaVazio(nome)) {
            podeEnviar = false;
            mensagem += "O Nome precisa ser informado!!";
        }
        if (S_Generico.textoEstaVazio(idade)) {
            podeEnviar = false;
            mensagem += "A Idade precisa ser informada!!";
        }
        if (!S_Generico.validarEmail(email)) {
            podeEnviar = false;
            mensagem += "Email Invalido!!";
        }
        if (!S_CpfValidator.validateCPF(cpf)) {
            podeEnviar = false;
            mensagem += "CPF Invalido!!";
        }
        if (S_Generico.textoEstaVazio(senha)) {
            podeEnviar = false;
            mensagem += "A Idade precisa ser informada!!";
        }
        if (podeEnviar) {
            M_Usuario m_usuario = new M_Usuario();
            m_usuario.setNome(nome);
            m_usuario.setIdade(Long.valueOf(idade));
            m_usuario.setEmail(email);
            m_usuario.setCpf(Long.valueOf(cpf));
            m_usuario.setSenha(senha);

            try {
                r_usuario.save(m_usuario);
                mensagem += "Deu bom";
            } catch (DataIntegrityViolationException e) {
                mensagem += "Deu ruim";
            }
        }
        return new M_Resposta(mensagem, podeEnviar, null);
    }

    public static ArrayList<M_Usuario> searchbar(String nome) {
        boolean podePesquisar = true;
        String mensagem = "";

        if (S_Generico.textoEstaVazio(nome)) {
            try {
                return r_usuario.BuscarPorNome(nome);
            } catch (DataIntegrityViolationException e) {
                return null;
            }
        }
        return null;
    }


    public static M_Resposta recuperarSenha(String emailR) {
        String mensagem = "";
        boolean podeRecuperar = true;

        if (!S_Generico.validarEmail(emailR)) {
            podeRecuperar = false;
            mensagem = "E-mail Invalido";
        }

        if (podeRecuperar) {
            M_Usuario usuario = r_usuario.BuscarPorEmail(emailR);

            if (usuario != null) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(emailR);
                message.setSubject("Recuperação de Senha Agenda Master");
                message.setText("Sua senha é: " + usuario.getSenha());
                mailSender.send(message);
            } else {
                mensagem += "Error ao inviar";
            }
        }
        return new M_Resposta(mensagem, podeRecuperar, null);
    }

    public static M_Resposta editSenha(String nome, String idade, String email, String cpf, String senhaAtual, String senhaNova, String confSenha) {
        boolean podeEnviar = true;
        String mensagem = "";
        cpf = S_Generico.cleanerNumber(cpf);
        if (S_Generico.textoEstaVazio(nome)) {
            podeEnviar = false;
            mensagem += "O Nome precisa ser informado!!";
        }
        if (S_Generico.textoEstaVazio(idade)) {
            podeEnviar = false;
            mensagem += "A Idade precisa ser informada!!";
        }
        if (!S_Generico.validarEmail(email)) {
            podeEnviar = false;
            mensagem += "Email Invalido!!";
        }
        if (!S_CpfValidator.validateCPF(cpf)) {
            podeEnviar = false;
            mensagem += "CPF Invalido!!";
        }
        if (S_Generico.textoEstaVazio(senhaAtual)) {
            podeEnviar = false;
            mensagem += "A senha atual precisa ser informada!!";
        }
        if (S_Generico.textoEstaVazio(senhaNova)) {
            podeEnviar = false;
            mensagem += "A nova senha precisa ser informada!!";
        }
        if (S_Generico.textoEstaVazio(confSenha)) {
            podeEnviar = false;
            mensagem += "A confirmação da nova senha precisa ser informada!!";
        }
        Optional<M_Usuario> usuarioOptional = Optional.ofNullable(r_usuario.BuscarPorCpf(Long.parseLong(cpf)));

        if (usuarioOptional.isPresent()) {
            M_Usuario m_usuario = usuarioOptional.get();
            m_usuario.setNome(nome);
            m_usuario.setIdade(Long.parseLong(idade));
            m_usuario.setEmail(email);
            m_usuario.setSenha(senhaNova);

            try {
                r_usuario.save(m_usuario); // Atualiza o registro existente
                mensagem += "Atualização bem-sucedida!";
            } catch (DataIntegrityViolationException e) {
                mensagem += "Erro ao atualizar os dados!";
            }
        } else {
            mensagem += "Usuário não encontrado!";
            podeEnviar = false;
        }

        return new M_Resposta(mensagem, podeEnviar, null);
    }

    public static M_Resposta editEmail(String nome, String idade, String emailAtual, String emailNovo, String cpf, String senha) {
        boolean podeEnviar = true;
        String mensagem = "";
        cpf = S_Generico.cleanerNumber(cpf);

        if (S_Generico.textoEstaVazio(nome) || S_Generico.textoEstaVazio(idade) ||
                !S_Generico.validarEmail(emailAtual) || !S_Generico.validarEmail(emailNovo) ||
                !S_CpfValidator.validateCPF(cpf) || S_Generico.textoEstaVazio(senha)) {
            // Se algum dos campos estiver vazio ou inválido, não continue
            podeEnviar = false;
            mensagem += "Dados inválidos ou incompletos!";
        } else {
            // Busque o usuário existente pelo CPF
            Optional<M_Usuario> usuarioOptional = Optional.ofNullable(r_usuario.BuscarPorCpf(Long.parseLong(cpf)));

            if (usuarioOptional.isPresent()) {
                M_Usuario m_usuario = usuarioOptional.get();
                m_usuario.setNome(nome);
                m_usuario.setIdade(Long.parseLong(idade));
                m_usuario.setEmail(emailNovo);
                m_usuario.setSenha(senha);

                try {
                    r_usuario.save(m_usuario); // Atualiza o registro existente
                    mensagem += "Atualização bem-sucedida!";
                } catch (DataIntegrityViolationException e) {
                    mensagem += "Erro ao atualizar os dados!";
                }
            } else {
                mensagem += "Usuário não encontrado!";
                podeEnviar = false;
            }
        }

        return new M_Resposta(mensagem, podeEnviar, null);
    }

}
