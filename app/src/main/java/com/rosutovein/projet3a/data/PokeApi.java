package com.rosutovein.projet3a.data;

import com.rosutovein.projet3a.presentation.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {

    @GET("Rosutovein/Projet3A/master/pokedex.json")
    Call<RestPokemonResponse> getPokemonResponse();
}
