package sg.edu.nus.iss.paf_day28Pokemon.service;

import org.bson.Document;

import sg.edu.nus.iss.paf_day28Pokemon.model.Pokemon;

public class Utility {

    public static Pokemon toPokemon (Document doc){
        Pokemon pokemon = new Pokemon();
        pokemon.setName(doc.getString("name"));
        pokemon.setImage(doc.getString("img"));
        return pokemon;
    }
    
}
