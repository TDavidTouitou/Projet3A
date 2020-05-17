package com.rosutovein.projet3a.presentation.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rosutovein.projet3a.Constants;
import com.rosutovein.projet3a.R;
import com.rosutovein.projet3a.data.PokeApi;
import com.rosutovein.projet3a.presentation.controller.PokedexController;
import com.rosutovein.projet3a.presentation.model.Pokemon;
import com.rosutovein.projet3a.presentation.model.RestPokemonResponse;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexActivity extends AppCompatActivity{

    private ListAdapter myAdapter;
    private PokedexController controller;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Le contenu de la vue activity_main.xml sera celui sur lequels sera appliqué les prochaines lignes
        setContentView(R.layout.activity_pokedex);

        //Ajouter la toolbar sur l'activité
        Toolbar pokedexToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(pokedexToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Instanciation du controller
        controller = new PokedexController(
             this,
            //Créer un objet qui permet de garder en mémoire les données (utiles pour ne pas avoir une application vide par manque de connexion)
            new GsonBuilder()
                    .setLenient()
                    .create(),
            getSharedPreferences(Constants.APPLICATION_NAME, Context.MODE_PRIVATE)
        );
        controller.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pokedex, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }




    /*Afficher la recycleView*/
    public void showList(List<Pokemon> pokemonList){

        //On récupère la recycleView du fichier activity_main
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        //Option permettant d'améliorer les performances
        recyclerView.setHasFixedSize(true);
        //On choisit un disposition linéaire pour notre recycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Défini un adapter
        myAdapter = new ListAdapter(pokemonList);
        recyclerView.setAdapter(myAdapter);
    }

    public void showError(){

        Toast.makeText(getApplicationContext(), "API Error!", Toast.LENGTH_SHORT).show();
    }
}
