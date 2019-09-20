package com.example.admin.school_bus_tracking;

/**
 * Created by Admin on 24/03/2017.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{


    Context context;

    //List of superHeroes
    List<Heroes> superHeroes;

    public CardAdapter(List<Heroes> superHeroes, Context context){
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_card_view_location, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Heroes superHero = superHeroes.get(position);
        holder.bus_no.setText(superHero.getBusno());
        holder.textViewAddress.setText(superHero.getLocationAddress());

        holder.date.setText(superHero.getDate());


    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{


        public TextView bus_no;
        public TextView textViewAddress;


        public TextView date;




        public ViewHolder(View itemView) {
            super(itemView);


            bus_no= (TextView) itemView.findViewById(R.id.bus_no);
            textViewAddress = (TextView) itemView.findViewById(R.id.textViewAddress);

            date= (TextView) itemView.findViewById(R.id.date);


        }
    }




}
