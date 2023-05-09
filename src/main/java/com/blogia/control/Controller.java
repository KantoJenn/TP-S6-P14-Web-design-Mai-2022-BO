package com.blogia.control;

import com.blogia.hibernate.HibernateDAO;
import com.blogia.modele.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    HibernateDAO dao;

    @PostMapping("/Connecter")
    public String Login(@RequestParam("nom") String nom, @RequestParam("mdp") String mdp) throws IOException {
        int i = dao.Connecter(nom, mdp);
        if (i==0) {
            return "../../index";
        }else {
            return "Ajouter";
        }
    }

    @GetMapping("Deconnecter")
    public String Logout( HttpSession session){
        session.invalidate();
        return "../../index";
    }

    @GetMapping("PageAjout")
    public String PageAjout(){
        return "Ajouter";
    }

    @PostMapping("/Ajouter")
    public String Add(@ModelAttribute Article article, HttpSession session) throws IOException {
        dao.create(article);
        return "Ajouter";
    }

    @GetMapping("/modifierPage")
    private String ModifierPage(@RequestParam("id") int id, Model model){
        List<Article> liste = Collections.singletonList(dao.findById(Article.class, id));
        model.addAttribute("liste",liste);
        return "Modifier";
    }

    @PostMapping("/Modifier")
    public String Modifier(@ModelAttribute Article article, @RequestParam("id") int id, @RequestParam("daty") String date, Model model) throws IOException {
        Timestamp timestamp = Timestamp.valueOf(date);
        article.setDateAjout(timestamp);
        article.setIdArticle(id);
        dao.update(article);
        model.addAttribute("liste",dao.findAll(Article.class));
        return "Liste";
    }

    @GetMapping("DeleteArticle")
    private String DeleteArticle(@RequestParam("id") int id,Model model) {
        dao.deleteById(Article.class,id);
        model.addAttribute("liste",dao.findAll(Article.class));
        return "Liste";
    }

    @GetMapping("Liste")
    private String DeleteArticle(Model model) {
        model.addAttribute("liste",dao.findAll(Article.class));
        return "Liste";
    }


}
