package com.kal.brawlstatz;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mapFragment extends Fragment {
    ArrayList<MapModelClass> mlist = new ArrayList<>();
    private Map_Adapter mapAdapter;
    private RecyclerView mapRecycler;
    ShimmerFrameLayout shimmerFlmap;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);

        mapRecycler = view.findViewById(R.id.mapRecycler);
        mapRecycler.setHasFixedSize(true);
        mapRecycler.setLayoutManager(layoutManager);

        shimmerFlmap = view.findViewById(R.id.mapshimmer);

        FirebaseDatabase.getInstance().getReference().child("maps").orderByChild("mtype").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // adding data to Arraylist
                mlist.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    MapModelClass map = ds.getValue(MapModelClass.class);
                    assert map != null;
                    map.mlayout = "https://firebasestorage.googleapis.com/v0/b/brawlstatz.appspot.com/o/maps%2F"+map.mtype.toLowerCase().replace(" ","%20")+"%2F"+ds.getKey()+"-Map.webp?alt=media";
                    map.mthumb = "https://firebasestorage.googleapis.com/v0/b/brawlstatz.appspot.com/o/maps%2FzEnv%2F"+map.menv+"-Environment.webp?alt=media";
                    mlist.add(map);

                }
                mapAdapter = new Map_Adapter(mlist);


                mapRecycler.setAdapter(mapAdapter);

                shimmerFlmap.stopShimmer();
                shimmerFlmap.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
    @Override
    public void onResume() {
        shimmerFlmap.startShimmer();
        super.onResume();
    }

    @Override
    public void onPause() {
        shimmerFlmap.stopShimmer();
        super.onPause();
    }

}