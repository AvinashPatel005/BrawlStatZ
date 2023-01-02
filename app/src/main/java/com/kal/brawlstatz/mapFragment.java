package com.kal.brawlstatz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mapFragment extends Fragment {
    ArrayList<MapModelClass> mlist = new ArrayList<>();
    private Map_Adapter mapAdapter;
    private RecyclerView mapRecycler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        mapRecycler = view.findViewById(R.id.mapRecycler);
        mapRecycler.setHasFixedSize(true);
        mapRecycler.setLayoutManager(layoutManager);

        FirebaseDatabase.getInstance().getReference().child("maps").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // adding data to Arraylist
                mlist.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    MapModelClass map = ds.getValue(MapModelClass.class);
                    mlist.add(map);

                }
                mapAdapter = new Map_Adapter(mlist);


                mapRecycler.setAdapter(mapAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}