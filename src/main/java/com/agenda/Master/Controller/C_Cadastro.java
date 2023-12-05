package com.agenda.Master.Controller;

import com.agenda.Master.Model.M_Resposta;
import com.agenda.Master.Service.S_Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class C_Cadastro {
    @GetMapping("/cadastro")
    public String getCadastro(){
        return "Cadastro/cadastro";
    }

    @PostMapping("/cadastro")
    @ResponseBody
    public M_Resposta postCadastro(@RequestParam("nome") String nome,
                                   @RequestParam("idade") String idade,
                                   @RequestParam("email") String email,
                                   @RequestParam("cpf") String cpf,
                                   @RequestParam("senha") String senha){
        return S_Usuario.cadastrarUsuario(nome, idade, email, cpf, senha);
    }
}
