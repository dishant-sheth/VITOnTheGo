package com.example.dishant.vitonthego;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dishant on 6/5/17.
 */

public class Places extends Fragment {

    FloatingActionButton add;
    RecyclerView places;
    PlacesAdapter placesAdapter;
    List<Place> data;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    public Places(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places, container, false);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabase = mFirebaseInstance.getReference("places");

        add = (FloatingActionButton) view.findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AddPlace addPlace = new AddPlace();
                fragmentTransaction.replace(R.id.fragment_container, addPlace);
                fragmentTransaction.commit();
            }
        });

        places = (RecyclerView) view.findViewById(R.id.placesView);
        placesAdapter = new PlacesAdapter(getActivity(), getData(), new PlacesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final Place info) {


            }
        });
        places.setAdapter(placesAdapter);
        places.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public List<Place> getData(){
        data = new ArrayList<>();

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot current: dataSnapshot.getChildren()){

                    Place place = current.getValue(Place.class);

                    data.add(place);
                    placesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return data;
    }
}
