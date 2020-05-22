package com.rosutovein.projet3a.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.rosutovein.projet3a.R;
import com.rosutovein.projet3a.Singletons;
import com.rosutovein.projet3a.presentation.controller.PokedexController;
import com.rosutovein.projet3a.presentation.model.Pokemon;

import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;


public class PokedexActivity extends AppCompatActivity{

    private ListAdapter myAdapter;


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
        PokedexController controller = new PokedexController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferencesInstance(getApplicationContext())
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
