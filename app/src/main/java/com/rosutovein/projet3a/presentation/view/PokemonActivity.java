package com.rosutovein.projet3a.presentation.view;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.rosutovein.projet3a.R;
import com.rosutovein.projet3a.presentation.model.Pokemon;
import com.squareup.picasso.Picasso;

public class PokemonActivity extends AppCompatActivity {

    private ProgressBar pokemonStatATTBar;
    private ProgressBar pokemonStatDEFBar;
    private ProgressBar pokemonStatPVBar;
    private ProgressBar pokemonStatVITBar;
    private ProgressBar pokemonStatATTSPEBar;
    private ProgressBar pokemonStatDEFSPEBar;
    private int pokemonStatBarStatus = 0;
    private int pokemonStatBarATTStatus = 0;
    private int pokemonStatBarDEFStatus = 0;
    private int pokemonStatBarPVStatus = 0;
    private int pokemonStatBarVITStatus = 0;
    private int pokemonStatBarATTSPEStatus = 0;
    private int pokemonStatBarDEFSPEStatus = 0;
    private Pokemon pokemon;
    private Handler handler = new Handler();




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        //Ajouter la toolbar sur l'activité
        Toolbar pokedexToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(pokedexToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);


        Intent pokemonIntent = getIntent();
        if(pokemonIntent != null){
            pokemon = pokemonIntent.getParcelableExtra("pokemon");
        }
        //On récupère la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //On recupere et utilise l'artwork du pokemon
        ImageView poke_artwork = findViewById(R.id.poke_artwork);
        Picasso.get().load(pokemon.getArtwork()).into(poke_artwork);
        //On recupere et utilise le nom de l'abilité
        TextView ability = (TextView) findViewById(R.id.ability);
        ability.setText(pokemon.getAbilityname());
        //On recupere et utilise la description de l'abilité principale du pokemon
        TextView abilityDescription = (TextView) findViewById(R.id.ability_description);
        abilityDescription.setText(pokemon.getAbilitydescription());
        //On recupere et utilise la description de l'abilité caché du pokemon
        TextView hiddenAbility = (TextView) findViewById(R.id.hiddenAbility);
        TextView hiddenAbilityDescription = (TextView) findViewById(R.id.hidden_ability_description);
        hiddenAbility.setText(pokemon.getHiddenabilityname());
        hiddenAbilityDescription.setText(pokemon.getHiddenabilitydescription());
        //On recupere et utilise les types du pokemon
        TextView type1 = (TextView) findViewById(R.id.first_type);
        TextView type2 = (TextView) findViewById(R.id.second_type);
        type1.setText(pokemon.getType().get(0));
        if(pokemon.getType().size() == 2){
            type2.setText(pokemon.getType().get(1));
            type2.setAlpha(1.0f);
        }
        else
            type2.setAlpha(0.0f);

        //On recupere et utilise le poids du pokemon
        TextView weight = (TextView) findViewById(R.id.weight);
        weight.setText(pokemon.getWeight());
        //On recupere et utilise la taille du pokemon
        TextView height = (TextView) findViewById(R.id.height);
        height.setText(pokemon.getHeight());
        //On récupère et utilise les stats du pokemon
        TextView attackValue = (TextView) findViewById(R.id.poke_stat_att_value);
        TextView defenseValue = (TextView) findViewById(R.id.poke_stat_def_value);
        TextView healthValue = (TextView) findViewById(R.id.poke_stat_hp_value);
        TextView attackSpeValue = (TextView) findViewById(R.id.poke_stat_att_spe_value);
        TextView defenseSpeValue = (TextView) findViewById(R.id.poke_stat_def_spe_value);
        TextView vitesseValue = (TextView) findViewById(R.id.poke_stat_vit_value);
        attackValue.setText(Integer.toString(pokemon.getStatatt()));
        defenseValue.setText(Integer.toString(pokemon.getStatdef()));
        healthValue.setText(Integer.toString(pokemon.getStatpv()));
        attackSpeValue.setText(Integer.toString(pokemon.getStatattspe()));
        defenseSpeValue.setText(Integer.toString(pokemon.getStatdefspe()));
        vitesseValue.setText(Integer.toString(pokemon.getStatvit()));

        //Mise en place graphique des stats du pokemon
        pokemonStatATTBar = (ProgressBar) findViewById(R.id.poke_stat_att_bar);
        pokemonStatATTBar.setMax(255);
        pokemonStatDEFBar = (ProgressBar) findViewById(R.id.poke_stat_def_bar);
        pokemonStatDEFBar.setMax(255);
        pokemonStatPVBar = (ProgressBar) findViewById(R.id.poke_stat_pv_bar);
        pokemonStatPVBar.setMax(255);
        pokemonStatVITBar = (ProgressBar) findViewById(R.id.poke_stat_vit_bar);
        pokemonStatVITBar.setMax(255);
        pokemonStatATTSPEBar = (ProgressBar) findViewById(R.id.poke_stat_att_spe_bar);
        pokemonStatATTSPEBar.setMax(255);
        pokemonStatDEFSPEBar = (ProgressBar) findViewById(R.id.poke_stat_def_spe_bar);
        pokemonStatDEFSPEBar.setMax(255);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while(pokemonStatBarStatus < 255){
                    if(pokemonStatBarATTStatus < pokemon.getStatatt())
                        pokemonStatBarATTStatus++;
                    if(pokemonStatBarDEFStatus < pokemon.getStatdef())
                        pokemonStatBarDEFStatus++;
                    if(pokemonStatBarPVStatus < pokemon.getStatpv())
                        pokemonStatBarPVStatus++;
                    if(pokemonStatBarVITStatus < pokemon.getStatvit())
                        pokemonStatBarVITStatus++;
                    if(pokemonStatBarATTSPEStatus < pokemon.getStatattspe())
                        pokemonStatBarATTSPEStatus++;
                    if(pokemonStatBarDEFSPEStatus < pokemon.getStatdefspe())
                        pokemonStatBarDEFSPEStatus++;

                    pokemonStatBarStatus++;

                    android.os.SystemClock.sleep(10);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pokemonStatATTBar.setProgress(pokemonStatBarATTStatus);
                            pokemonStatDEFBar.setProgress(pokemonStatBarDEFStatus);
                            pokemonStatVITBar.setProgress(pokemonStatBarVITStatus);
                            pokemonStatPVBar.setProgress(pokemonStatBarPVStatus);
                            pokemonStatATTSPEBar.setProgress(pokemonStatBarATTSPEStatus);
                            pokemonStatDEFSPEBar.setProgress(pokemonStatBarDEFSPEStatus);
                        }
                    });
                }
            }
        }).start();

    }
}
