package com.rosutovein.projet3a.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rosutovein.projet3a.Constants;
import com.rosutovein.projet3a.R;
import com.rosutovein.projet3a.data.PokeApi;
import com.rosutovein.projet3a.presentation.model.Pokemon;
import com.rosutovein.projet3a.presentation.model.RestPokemonResponse;
import com.rosutovein.projet3a.presentation.view.PokedexActivity;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private PokedexActivity view;
    private List<Pokemon> pokemonList;
    private SwipeRefreshLayout swipeRefreshLayout;

    public PokedexController(PokedexActivity pokedexActivity, Gson gson, SharedPreferences sharedPreferences){
        this.view = pokedexActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;

    }

    public void onStart(){

        pokemonList = getDataFromCache();
        if(pokemonList != null || !haveInternetConnection()){
            view.showList(pokemonList);
        }
        else{
            makeApiCall();
        }

        //Ajouter le swipeRefresh sur l'activité
        swipeRefreshLayout  = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(haveInternetConnection()){
                    makeApiCall();
                    saveList(pokemonList);
                }
                else{
                    Toast.makeText(view.getApplicationContext(), "Connection failed!", Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    //Permet de charger les données depuis le cache
    private List<Pokemon> getDataFromCache(){

        String jsonPokemon = sharedPreferences.getString(Constants.KEY_POKEMON_LIST, null);

        if(jsonPokemon == null){
            return null;
        }
        else{
            Type listType = new TypeToken<List<Pokemon>>(){}.getType();
            return gson.fromJson(jsonPokemon,listType);
        }
    }
    private boolean haveInternetConnection(){

        NetworkInfo network = ((ConnectivityManager) Objects.requireNonNull(view.getSystemService(Context.CONNECTIVITY_SERVICE))).getActiveNetworkInfo();

        return network != null && network.isConnected();
    }
    private void makeApiCall(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PokeApi pokeApi = retrofit.create(PokeApi.class);

        Call<RestPokemonResponse> call = pokeApi.getPokemonResponse();

        //On créer un callback qui va "diagnostiquer" la réponse du serveur.
        //On créer deux méthode, une en cas de réponse du serveur et ...
        //... une en cas d'absence de réponse.
        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(@NonNull Call<RestPokemonResponse> call, @NonNull Response<RestPokemonResponse> response) {

                if(response.isSuccessful() && response.body() != null){
                    pokemonList = response.body().getPokemonList();
                    saveList(pokemonList);
                    view.showList(pokemonList);
                }
                else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestPokemonResponse> call, @NonNull Throwable t) {
                view.showError();
            }
        });
    }
    private void saveList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);

        sharedPreferences.edit()
                .putString(Constants.KEY_POKEMON_LIST, jsonString)
                .apply();
        Toast.makeText(view.getApplicationContext(), "List saved!", Toast.LENGTH_SHORT).show();
    }
}
