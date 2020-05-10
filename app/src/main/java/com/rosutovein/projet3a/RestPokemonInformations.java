package com.rosutovein.projet3a;

import java.util.ArrayList;
import java.util.List;

public class RestPokemonInformations {

    private Integer height;
    private Integer weight;
    private List<PokemonInformations> types;


    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public List<PokemonInformations> getInformationsResults() {
        return types;
    }
}