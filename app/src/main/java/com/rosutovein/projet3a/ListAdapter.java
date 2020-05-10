package com.rosutovein.projet3a;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Pokemon> values;

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
            pokeId = (TextView) v.findViewById(R.id.pokemon_index);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            imageHeader = (ImageView)v.findViewById(R.id.icon);
        }
    }

    public void add(int position, Pokemon item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    private void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    ListAdapter(List<Pokemon> myDataset) {
        values = myDataset;
    }

    // Create new views
    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Pokemon currentPokemon = values.get(position);
       if(currentPokemon.getId() < 10)
            holder.pokeId.setText("#00"+currentPokemon.getId());
        else if(currentPokemon.getId() < 100)
            holder.pokeId.setText("#0"+currentPokemon.getId());
        else if(currentPokemon.getId() < 1000)
            holder.pokeId.setText("#"+currentPokemon.getId());
        holder.txtHeader.setText(currentPokemon.getName());
        holder.txtFooter.setText(currentPokemon.getUrl());
        Picasso.get().load(currentPokemon.getSprite()).into(holder.imageHeader);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}