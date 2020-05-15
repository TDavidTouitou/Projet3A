package com.rosutovein.projet3a;
import android.content.Intent;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements Serializable , Filterable {
    private List<Pokemon> pokemonList;
    private List<Pokemon> pokemonListFull;

    @Override
    public Filter getFilter() {
        return pokemonListFilter;
    }

    private Filter pokemonListFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Pokemon> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(pokemonListFull);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Pokemon currentPokemon : pokemonListFull){
                    if(currentPokemon.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(currentPokemon);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            pokemonList.clear();
            pokemonList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView txtHeader;
        TextView txtFooter;
        TextView pokeId;
        ImageView imageHeader;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            pokeId = v.findViewById(R.id.pokemon_index);
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            imageHeader = v.findViewById(R.id.icon);
        }
    }

    public void add(int position, Pokemon item) {
        pokemonList.add(position, item);
        notifyItemInserted(position);
    }

    private void remove(int position) {
        pokemonList.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    ListAdapter(List<Pokemon> pokemonList) {

        this.pokemonList = pokemonList;
        pokemonListFull = new ArrayList<>(pokemonList);
    }

    // Create new views
    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Pokemon currentPokemon = pokemonList.get(position);
        holder.pokeId.setText(currentPokemon.getId());
        holder.txtHeader.setText(currentPokemon.getName());
        if(currentPokemon.getType().size() == 2){
            holder.txtFooter.setText(currentPokemon.getType().get(0) + "    " + currentPokemon.getType().get(1));
        }
        else{
            holder.txtFooter.setText(currentPokemon.getType().get(0));
        }
        Picasso.get().load(currentPokemon.getArtwork()).into(holder.imageHeader);
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPokemonActivity = new Intent(v.getContext(), PokemonActivity.class);
                goToPokemonActivity.putExtra("pokemon", currentPokemon);
                v.getContext().startActivity(goToPokemonActivity);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

}