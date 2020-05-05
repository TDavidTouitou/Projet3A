package com.rosutovein.projet3a;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {

    @GET("api/v2/pokemon?limit=807")
    Call<RestPokemonResponse> getPokemonResponse();

    @GET("https://raw.githubusercontent.com/PokeAPI")
    Call<RestPokemonSpritesResponse> getPokemonSpritesResponse();
}
