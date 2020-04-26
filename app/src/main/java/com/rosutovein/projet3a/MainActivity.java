package com.rosutovein.projet3a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Le contenu de la vue activity_main.xml sera celui sur lequels sera appliquer les prochaines lignes
        setContentView(R.layout.activity_main);

        showList();
        makeApiCall();
    }




    /*Afficher la recycleView*/
    private void showList(){

        //On récupère la recycleView du fichier activity_main
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //Option permettant d'améliorer les performances
        recyclerView.setHasFixedSize(true);
        //On choisit un disposition linéaire pour notre recycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Créer une fausse liste de String
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            input.add("Test" + i);
        }
        //Défini un adapter
        ListAdapter myAdapter = new ListAdapter(input);
        recyclerView.setAdapter(myAdapter);
    }




    /*Appeler l'API pokeapi*/
    private static final String BASE_URL = "https://pokeapi.co/";
    private void makeApiCall(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
                    List<Pokemon> pokemonList = response.body().getResults();
                    Toast.makeText(getApplicationContext(), "API Success!", Toast.LENGTH_SHORT).show();

                }
                else{
                    showError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestPokemonResponse> call, @NonNull Throwable t) {
                showError();
            }
        });
    }

    private void showError(){

        Toast.makeText(getApplicationContext(), "API Error!", Toast.LENGTH_SHORT).show();
    }
}
