package com.agenda.Master.Controller;

import com.agenda.Master.Model.M_Resposta;
import com.agenda.Master.Service.S_Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class C_Usuario {
    @GetMapping("/")
    public String getLogin(){
        return "Usuario/Login";
    }

    @PostMapping("/")
    @ResponseBody
    public boolean postLogin(@RequestParam("cpf") String cpf,
                            @RequestParam("senha") String senha,
                            HttpSession session){
        session.setAttribute("usuario", S_Usuario.varificarLogin(cpf, senha));
        if(session.getAttribute("usuario") != null){
            return true;
        }
            return false;
    }

    @GetMapping("/Perfil")
    public String getPerfil(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "Usuario/Perfil";
    }
    @PostMapping("/senha")
    @ResponseBody
    public M_Resposta postEditSenha(@RequestParam("nome") String nome,
                                    @RequestParam("idade") String idade,
                                    @RequestParam("email") String email,
                                    @RequestParam("cpf") String cpf,
                                    @RequestParam("senhaAtual") String senhaAtual,
                                    @RequestParam("senhaNova") String senhaNova,
                                    @RequestParam("confSenha") String confSenha){
        return S_Usuario.editSenha(nome,idade,email,cpf,senhaAtual,senhaNova,confSenha);
    }

    @PostMapping("/email")
    @ResponseBody
    public M_Resposta postEditEmail(@RequestParam("nome") String nome,
                                    @RequestParam("idade") String idade,
                                    @RequestParam("emailAtual") String emailAtual,
                                    @RequestParam("emailNovo") String emailNovo,
                                    @RequestParam("cpf") String cpf,
                                    @RequestParam("senha") String senha){
        return S_Usuario.editEmail(nome,idade,emailAtual,emailNovo,cpf,senha);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("usuario", null);
        return "redirect:/";
    }

    @PostMapping("/RecuperacaoDeSenha")
    @ResponseBody
    public M_Resposta recuperacao(@RequestParam("emailR") String emailR){
        return S_Usuario.recuperarSenha(emailR);
    }
}
