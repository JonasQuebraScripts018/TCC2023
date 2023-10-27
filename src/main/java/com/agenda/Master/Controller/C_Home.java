package com.agenda.Master.Controller;

import com.agenda.Master.Model.M_Usuario;
import com.agenda.Master.Service.S_Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class C_Home {
    @GetMapping("/home")
    public String getHome(HttpSession session, Model model){
        if(session.getAttribute("usuario") != null){
            model.addAttribute("usuario", session.getAttribute("usuario"));
            return "Home/home";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/searchBar")
    public ArrayList<M_Usuario> setSearchBar(@RequestParam("nome") String nome){
        return S_Usuario.searchbar(nome);
    }
}
