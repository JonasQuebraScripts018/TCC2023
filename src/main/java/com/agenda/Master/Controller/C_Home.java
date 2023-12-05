package com.agenda.Master.Controller;

import com.agenda.Master.Model.M_Resposta;
import com.agenda.Master.Model.M_Usuario;
import com.agenda.Master.Service.S_Cronograma;
import com.agenda.Master.Service.S_Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
public class C_Home {
    @GetMapping("/home")
    public String getHome(HttpSession session, Model model){
        if(session.getAttribute("usuario") != null){
            model.addAttribute("cronogramas",S_Cronograma.buscarCronograma((M_Usuario) session.getAttribute("usuario")));
            model.addAttribute("usuario", session.getAttribute("usuario"));
            return "Home/home";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/home")
    @ResponseBody
    public M_Resposta postHome(@RequestParam("dataini") LocalDateTime dataini,
                               @RequestParam("datafini") LocalDateTime datafini,
                               HttpSession session,
                               @RequestParam("nomeModal") String nome
                           ){
        return S_Cronograma.salvarCronograma(dataini, datafini, (M_Usuario) session.getAttribute("usuario"), nome);
    }

    @PostMapping("/delataCronograma")
    @ResponseBody
    public M_Resposta postCronograma(@RequestParam("nome") String nome,
                                    HttpSession session, Model model){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return S_Cronograma.deletarCronograma(nome, (M_Usuario) session.getAttribute("usuario"));
    }

    @GetMapping("/hominha")
    public String getHominha(Model model, HttpSession session){
        if(session.getAttribute("usuario") != null){
            model.addAttribute("cronogramas",S_Cronograma.buscarCronograma((M_Usuario) session.getAttribute("usuario")));
            model.addAttribute("usuario", session.getAttribute("usuario"));
            return "Home/hominha";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/searchBar")
    public ArrayList<M_Usuario> setSearchBar(@RequestParam("nome") String nome){
        return S_Usuario.searchbar(nome);
    }
}
