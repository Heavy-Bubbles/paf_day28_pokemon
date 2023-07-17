package sg.edu.nus.iss.paf_day28Pokemon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.paf_day28Pokemon.model.Pokemon;
import sg.edu.nus.iss.paf_day28Pokemon.service.PokeService;

@Controller
@RequestMapping("/")
public class PokeController {
    
    @Autowired
    PokeService pokeService;

    @GetMapping
    public String getTypes(Model model){
        
        List<String> types = pokeService.getAllTypes();

        model.addAttribute("types", types);

        return "types";
    }

    @GetMapping("/pokemon/{type}")
    public String getPokemonByTypes(@PathVariable String type, Model model){

        List<Pokemon> mons = pokeService.getPokemonByType(type);

        model.addAttribute("type", type);

        model.addAttribute("mons", mons);

        return "pokemon";
    }

}
