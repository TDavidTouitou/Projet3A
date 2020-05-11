package com.rosutovein.projet3a;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeApi {

    @GET("Rosutovein/Projet3A/master/pokedex.json")
    Call<RestPokemonResponse> getPokemonResponse();
}
