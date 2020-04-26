package com.rosutovein.projet3a;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {

    @GET("/api/v2/pokemon")
    Call<RestPokemonResponse> getPokemonResponse();

    //@GET("/api/v2/ability")
    //Call<RestPokemonResponse> getPokemonAbility();
}
