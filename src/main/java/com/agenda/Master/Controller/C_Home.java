package com.agenda.Master.Controller;

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

import java.util.ArrayList;

@Controller
public class C_Home {
    @GetMapping("/home")
    public String getHome(HttpSession session, Model model){
        if(session.getAttribute("usuario") != null){
            model.addAttribute("cronogramas",S_Cronograma.buscarCronograma());
            model.addAttribute("usuario", session.getAttribute("usuario"));
            return "Home/home";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/home")
    @ResponseBody
    public String postHome(@RequestParam("dataini") String dataini,
                           @RequestParam("datafini") String datafini,
                           HttpSession session
                           ){
        return S_Cronograma.salvarCronograma(dataini, datafini, (M_Usuario) session.getAttribute("usuario"));
    }

    @GetMapping("/hominha")
    public String getHominha(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "Home/hominha";
    }

    @PostMapping("/searchBar")
    public ArrayList<M_Usuario> setSearchBar(@RequestParam("nome") String nome){
        return S_Usuario.searchbar(nome);
    }
}
