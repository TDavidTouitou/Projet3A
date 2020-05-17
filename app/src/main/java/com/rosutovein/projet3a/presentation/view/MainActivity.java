package com.rosutovein.projet3a.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.rosutovein.projet3a.R;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Le contenu de la vue activity_main.xml sera celui sur lequel sera appliquer les prochaines lignes
        setContentView(R.layout.activity_main);

        //On récupère la toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button pokedexButton = (Button)findViewById(R.id.pokedex_access_button);
        pokedexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPokedexActivity = new Intent(MainActivity.this, PokedexActivity.class);
                startActivity(goToPokedexActivity);
            }
        });

        final ImageView pokeballImage = (ImageView) findViewById(R.id.pokeball_main_activity);
        Button animatePokeballButton = (Button) findViewById(R.id.animation_pokeball_button);
        animatePokeballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.FlipInX)
                        .duration(1000)
                        .playOn(pokeballImage);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if(id == R.id.action_open_in_bowser){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Rosutovein/Projet3A"));
            startActivity(intent);
            return true;
        }

        if(id == R.id.action_share){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "https://github.com/Rosutovein/Projet3A");
            startActivity(Intent.createChooser(intent, "Share Github!"));
            return true;
        }

        if(id == R.id.action_about){
            Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }
}
