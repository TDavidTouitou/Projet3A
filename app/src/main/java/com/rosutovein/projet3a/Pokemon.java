package com.rosutovein.projet3a;

import java.util.List;

public class Pokemon {

    private Integer id;
    private Integer height;
    private Integer weight;
    private String name;
    private String sprite;
    private String url;
    private List<PokemonInformations> pokemonInformations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public List<PokemonInformations> getPokemonInformations() {
        return pokemonInformations;
    }

    public void setPokemonInformations(List<PokemonInformations> pokemonInformations) {
        this.pokemonInformations = pokemonInformations;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
