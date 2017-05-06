package com.example.dishant.vitonthego;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
 * A simple {@link Fragment} subclass.
 */
public class Complaints extends Fragment {

    FloatingActionButton add;
    RecyclerView complaints;
    ComplaintsAdapter complaintsAdapter;
    List<Complaint> data;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    public Complaints() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_complaints, container, false);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabase = mFirebaseInstance.getReference("complaints");

        add = (FloatingActionButton) view.findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AddComplaint addPlace = new AddComplaint();
                fragmentTransaction.replace(R.id.fragment_container, addPlace);
                fragmentTransaction.commit();
            }
        });

        complaints = (RecyclerView) view.findViewById(R.id.complaintsView);
        complaintsAdapter = new ComplaintsAdapter(getActivity(), getData(), new ComplaintsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Complaint info) {

            }
        });

        complaints.setAdapter(complaintsAdapter);
        complaints.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public List<Complaint> getData(){
        data = new ArrayList<>();

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot current: dataSnapshot.getChildren()){

                    Complaint complaint = current.getValue(Complaint.class);

                    data.add(complaint);
                    complaintsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return data;
    }

}
