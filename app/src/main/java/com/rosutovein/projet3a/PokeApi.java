package com.rosutovein.projet3a;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeApi {

    @GET("api/v2/pokemon?limit=807")
    Call<RestPokemonResponse> getPokemonResponse();

    @GET("api/v2/pokemon/{pokeId}")
    Call<RestPokemonInformations> getPokemonInformationsResponse(@Path("pokeId") Integer pokeId);
}
