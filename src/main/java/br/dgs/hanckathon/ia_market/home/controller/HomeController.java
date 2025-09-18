package br.dgs.hanckathon.ia_market.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String index() {
        return "index"; // busca index.html em /templates
    }
}
