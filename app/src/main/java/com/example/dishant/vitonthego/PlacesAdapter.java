package com.example.dishant.vitonthego;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by dishant on 6/5/17.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Place> data = Collections.EMPTY_LIST;

    public interface OnItemClickListener{
        public void onItemClick(Place info);
    }

    private OnItemClickListener listener;

    public PlacesAdapter(Context context, List<Place> data, PlacesAdapter.OnItemClickListener listener){

        layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        this.listener = listener;

    }


    @Override
    public PlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.places_view, parent, false);
        PlacesViewHolder placesViewHolder = new PlacesViewHolder(view);
        return placesViewHolder;
    }

    @Override
    public void onBindViewHolder(PlacesViewHolder holder, int position) {

        Place place = data.get(position);
       holder.bind(place, listener);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PlacesViewHolder extends RecyclerView.ViewHolder{

        TextView title, location, details, rating, up;
        ImageView like;

        public PlacesViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            location = (TextView) itemView.findViewById(R.id.location);
            details = (TextView) itemView.findViewById(R.id.desc);
            rating = (TextView) itemView.findViewById(R.id.rating);



        }

        public void bind(final Place info, final PlacesAdapter.OnItemClickListener listener){

            title.setText(info.getPlace_name());
            location.setText(info.getPlace_location());
            details.setText(info.getPlace_details());
            rating.setText(info.getPlace_rating());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(info);
                }
            });

        }
    }
}
