package sg.edu.nus.iss.paf_day28Pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_day28Pokemon.model.Pokemon;
import sg.edu.nus.iss.paf_day28Pokemon.repository.PokeRepo;

@Service
public class PokeService {
    
    @Autowired
    PokeRepo pokeRepo;

    public List<String> getAllTypes(){
        return pokeRepo.getTypes();
    }

    public List<Pokemon> getPokemonByType(String type){

        return pokeRepo.getPokemonByType(type).stream()
            .map(Utility :: toPokemon)
            .toList();
    }
}
