package com.example.admin.school_bus_tracking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 25/03/2017.
 */
public class CardAdapterf extends RecyclerView.Adapter<CardAdapterf.ViewHolder> {


    private Context context;



    //List of superHeroes
    List<Heroes> Heroes;

    public CardAdapterf(List<Heroes> Heroes, Context context){
        super();
        //Getting all the heroes
        this.Heroes = Heroes;
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
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Heroes Hero = Heroes.get(position);

        //imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        //imageLoader.get(Hero.getImage(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        holder.textViewBusno.setText(Hero.getBusno());
        holder.textViewLocation.setText(Hero.getLocationAddress());
       ;
        holder.textViewTime.setText(Hero.getDate());



    }

    @Override
    public int getItemCount() {
        return Heroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewBusno;
        public TextView textViewLocation;
        public TextView textViewTime;





        public ViewHolder(View itemView) {
            super(itemView);


            textViewBusno= (TextView) itemView.findViewById(R.id.bus_no);
            textViewLocation = (TextView) itemView.findViewById(R.id.textViewAddress);
            textViewTime = (TextView) itemView.findViewById(R.id.date);


        }
    }


}

