package com.example.admin.school_bus_tracking.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.school_bus_tracking.Anouncment;
import com.example.admin.school_bus_tracking.R;
import com.example.admin.school_bus_tracking.SuperHeroes;

import java.util.List;

/**
 * Created by Admin on 20/03/2017.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{


    private Context context;

    //List of superHeroes
    List<SuperHeroes> superHeroes;

    public CardAdapter(List<SuperHeroes> superHeroes, Context context){
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        SuperHeroes superHero = superHeroes.get(position);

        holder.textViewWId.setText(superHero.getId());
        holder.textViewWId.setText(superHero.getDate());
        holder.textViewName.setText(superHero.getAnnouncement());


    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewWId;
        public TextView textViewName;
        public TextView textViewAddress;





        public ViewHolder(View itemView) {
            super(itemView);

            textViewWId= (TextView) itemView.findViewById(R.id.textViewId);
            textViewName= (TextView) itemView.findViewById(R.id.textViewName);
            textViewAddress= (TextView) itemView.findViewById(R.id.textViewAddress);


        }
    }




}
