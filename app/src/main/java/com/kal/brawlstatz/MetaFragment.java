package com.kal.brawlstatz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MetaFragment extends Fragment {
    ArrayList<MetaModelClass> metalist = new ArrayList<>();
    private Meta_Adapter metaAdapter;
    private RecyclerView metaRecycler;

    ShimmerFrameLayout shimmerFlm;
    MaterialToolbar tool;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meta, container, false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        metaRecycler = view.findViewById(R.id.metarecycler);
        metaRecycler.setHasFixedSize(true);
        metaRecycler.setLayoutManager(layoutManager);

        tool =view.findViewById(R.id.materialToolbar3);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(tool);

        shimmerFlm = view.findViewById(R.id.mshimmer);

        FirebaseDatabase.getInstance().getReference().child("brawlers").orderByChild("tier").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // adding data to Arraylist
                metalist.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    MetaModelClass meta = ds.getValue(MetaModelClass.class);
                    assert meta != null;
                    meta.bpro = "https://firebasestorage.googleapis.com/v0/b/brawlstatz.appspot.com/o/brawlers%2F"+meta.bname.toLowerCase()+"%2F"+ ds.getKey() +".webp?alt=media";
                    meta.bgear1 ="https://firebasestorage.googleapis.com/v0/b/brawlstatz.appspot.com/o/gears%2F"+meta.bgear1.toLowerCase()+".webp?alt=media";
                    meta.bgear2 ="https://firebasestorage.googleapis.com/v0/b/brawlstatz.appspot.com/o/gears%2F"+meta.bgear2.toLowerCase()+".webp?alt=media";
                    if(meta.bgear3!=null) meta.bgear3 ="https://firebasestorage.googleapis.com/v0/b/brawlstatz.appspot.com/o/gears%2F"+meta.bgear3.toLowerCase()+".webp?alt=media";
                    metalist.add(meta);

                }
                metaAdapter = new Meta_Adapter(metalist);


                metaRecycler.setAdapter(metaAdapter);
                shimmerFlm.stopShimmer();
                shimmerFlm.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        shimmerFlm.startShimmer();
        super.onResume();
    }

    @Override
    public void onPause() {
        shimmerFlm.stopShimmer();
        super.onPause();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.meta_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}