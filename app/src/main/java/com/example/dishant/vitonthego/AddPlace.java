package com.example.dishant.vitonthego;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddPlace extends Fragment {

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    EditText title, location, details, rating;
    String t, l , d, r;
    Button submit;
    FirebaseUser user;

    public AddPlace() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_place, container, false);

        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(getActivity(), Login.class));
                    getActivity().finish();
                }
            }
        };


        mFirebaseInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabase = mFirebaseInstance.getReference("places");

        title = (EditText) view.findViewById(R.id.title);
        location = (EditText) view.findViewById(R.id.area);
        details = (EditText) view.findViewById(R.id.content);
        rating = (EditText) view.findViewById(R.id.rating);

        submit = (Button) view.findViewById(R.id.submit_place);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t = title.getText().toString().trim();
                l = location.getText().toString().trim();
                d = details.getText().toString().trim();
                r = rating.getText().toString().trim();

                createPlace(t, l, d, r);
            }
        });



        return view;
    }

    private void createPlace(String title, String location, String content, String rating){

        String userId = mFirebaseDatabase.push().getKey();

        Place place = new Place();
        place.setPlace_name(title);
        place.setPlace_location(location);
        place.setPlace_details(content);
        place.setPlace_rating(rating);

        mFirebaseDatabase.child(userId).setValue(place);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Places places = new Places();
        fragmentTransaction.replace(R.id.fragment_container, places);
        fragmentTransaction.commit();

    }

}
