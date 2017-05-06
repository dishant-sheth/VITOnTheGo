package com.example.dishant.vitonthego;


import android.content.Intent;
import android.os.Bundle;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class AddComplaint extends Fragment {

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    EditText title, authority, details, intensity;
    String t, a , d, i;
    Button submit;
    FirebaseUser user;

    public AddComplaint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_complaint, container, false);

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

        mFirebaseDatabase = mFirebaseInstance.getReference("complaints");

        title = (EditText) view.findViewById(R.id.title);
        authority = (EditText) view.findViewById(R.id.area);
        details = (EditText) view.findViewById(R.id.content);
        intensity = (EditText) view.findViewById(R.id.rating);

        submit = (Button) view.findViewById(R.id.submit_place);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t = title.getText().toString().trim();
                a= authority.getText().toString().trim();
                d = details.getText().toString().trim();
                i = intensity.getText().toString().trim();

                createComplaint(t, a, d, i);
            }
        });

        return view;
    }

    private void createComplaint(String title, String autho, String content, String inten){

        String userId = mFirebaseDatabase.push().getKey();

        Complaint complaint = new Complaint();
        complaint.setComplaint_title(title);
        complaint.setConcerned_authority(autho);
        complaint.setComplaint_details(content);
        complaint.setComplaint_intensity(inten);

        mFirebaseDatabase.child(userId).setValue(complaint);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Complaints places = new Complaints();
        fragmentTransaction.replace(R.id.fragment_container, places);
        fragmentTransaction.commit();

    }

}
