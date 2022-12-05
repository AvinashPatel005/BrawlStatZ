package com.kal.brawlstatz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class BrawlerFragment extends Fragment {

    ArrayList<BrawlerModelClass> list = new ArrayList<>();
    ArrayList<BrawlerModelClass> back_up = new ArrayList<>();
    
    private Brawler_Adapter brawlerAdapter;
    private RecyclerView recyclerView;

    MaterialToolbar materialToolbar;
    ShimmerFrameLayout shimmerFrameLayout;

    boolean isRarityDecrease = true;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true); // Enables toolbar in fragment
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brawler, container, false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        
        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        materialToolbar =view.findViewById(R.id.materialToolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(materialToolbar);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
        
        loadDatabase();
        
        return view;
    }

    @Override
    public void onResume() {
        shimmerFrameLayout.startShimmer();
        super.onResume();
    }

    @Override
    public void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.brawlers_menu, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setQueryHint("Search Eg.Name ,Rarity");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                if (back_up.size() != 0) {
                    list.clear();
                    for (int i = 0; i < back_up.size(); i++) {
                        if (back_up.get(i).bname.toLowerCase().startsWith(s) || back_up.get(i).brare.toLowerCase().startsWith(s)) {
                            list.add(back_up.get(i));
                        }
                    }
                    brawlerAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.sort_Legendary:
                list.clear();
                for (int i = 0; i < back_up.size(); i++) {
                    if (back_up.get(i).brare.equals("LEGENDARY")) {
                        list.add(back_up.get(i));
                    }
                }
                brawlerAdapter.notifyDataSetChanged();
                break;

            case R.id.sort_Chromatic:
                list.clear();
                for (int i = 0; i < back_up.size(); i++) {
                    if (back_up.get(i).brare.equals("CHROMATIC")) {
                        list.add(back_up.get(i));
                    }
                }
                brawlerAdapter.notifyDataSetChanged();
                break;

            case R.id.sort_Mythic:
                list.clear();
                for (int i = 0; i < back_up.size(); i++) {
                    if (back_up.get(i).brare.equals("MYTHIC")) {
                        list.add(back_up.get(i));
                    }
                }
                brawlerAdapter.notifyDataSetChanged();
                break;

            case R.id.sort_Epic:
                list.clear();
                for (int i = 0; i < back_up.size(); i++) {
                    if (back_up.get(i).brare.equals("EPIC")) {
                        list.add(back_up.get(i));
                    }
                }
                brawlerAdapter.notifyDataSetChanged();
                break;

            case R.id.sort_Super:
                list.clear();
                for (int i = 0; i < back_up.size(); i++) {
                    if (back_up.get(i).brare.equals("SUPER RARE")) {
                        list.add(back_up.get(i));
                    }
                }
                brawlerAdapter.notifyDataSetChanged();
                break;

            case R.id.sort_Rare:
                list.clear();
                for (int i = 0; i < back_up.size(); i++) {
                    if (back_up.get(i).brare.equals("RARE")) {
                        list.add(back_up.get(i));
                    }
                }
                brawlerAdapter.notifyDataSetChanged();
                break;

            case R.id.sort_Trophie:
                list.clear();
                for (int i = 0; i < back_up.size(); i++) {
                    if (back_up.get(i).brare.equals("TROPHIE ROAD")) {
                        list.add(back_up.get(i));
                    }
                }
                brawlerAdapter.notifyDataSetChanged();
                break;

            case R.id.sort_Reset:
                list.clear();
                list.addAll(back_up);
                brawlerAdapter.notifyDataSetChanged();
                break;

            case R.id.sort_rarity:
                sortRarity();
                brawlerAdapter.notifyDataSetChanged();
                break;

            case R.id.sort_name:
                sortName();
                brawlerAdapter.notifyDataSetChanged();
                break;

            case R.id.refresh:
                loadDatabase();
                Toast.makeText(getContext(), "Database Refreshed", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadDatabase() {
        FirebaseDatabase.getInstance().getReference().child("brawlers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // adding data to Arraylist
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    BrawlerModelClass brawler = ds.getValue(BrawlerModelClass.class);
                    list.add(brawler);

                }
                brawlerAdapter = new Brawler_Adapter(list);
                back_up = new ArrayList(list);

                recyclerView.setAdapter(brawlerAdapter);
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sortName() {
        if (list.equals(back_up)) {
            list.clear();
            list.addAll(back_up);
            Collections.reverse(list);
            Toast.makeText(getContext(), "Sorted by Name in Descending Order", Toast.LENGTH_SHORT).show();
        } else {
            list.clear();
            list.addAll(back_up);
            Toast.makeText(getContext(), "Sorted by Name in Ascending Order", Toast.LENGTH_SHORT).show();
        }
    }

    private void sortRarity() {
        if (isRarityDecrease) {
            list.clear();
            for (int i = 0; i < back_up.size(); i++) {
                if (back_up.get(i).brare.equals("TROPHIE ROAD")) {
                    list.add(back_up.get(i));
                }
            }
            for (int i = 0; i < back_up.size(); i++) {
                if (back_up.get(i).brare.equals("RARE")) {
                    list.add(back_up.get(i));
                }
            }
            for (int i = 0; i < back_up.size(); i++) {
                if (back_up.get(i).brare.equals("SUPER RARE")) {
                    list.add(back_up.get(i));
                }
            }
            for (int i = 0; i < back_up.size(); i++) {
                if (back_up.get(i).brare.equals("EPIC")) {
                    list.add(back_up.get(i));
                }
            }
            for (int i = 0; i < back_up.size(); i++) {
                if (back_up.get(i).brare.equals("MYTHIC")) {
                    list.add(back_up.get(i));
                }
            }
            for (int i = 0; i < back_up.size(); i++) {
                if (back_up.get(i).brare.equals("CHROMATIC")) {
                    list.add(back_up.get(i));
                }
            }
            for (int i = 0; i < back_up.size(); i++) {
                if (back_up.get(i).brare.equals("LEGENDARY")) {
                    list.add(back_up.get(i));
                }
            }
            Toast.makeText(getContext(), "Sorted by Least Rarity", Toast.LENGTH_SHORT).show();
            isRarityDecrease = false;
        }
        else {
            list.clear();
            for (int i = 0; i < back_up.size(); i++) {
                if (back_up.get(i).brare.equals("LEGENDARY")) {
                    list.add(back_up.get(i));
                }
            }
            for (int i = 0; i < back_up.size(); i++) {
                if (back_up.get(i).brare.equals("CHROMATIC")) {
                    list.add(back_up.get(i));
                }
            }
            for (int i = 0; i < back_up.size(); i++) {
                if (back_up.get(i).brare.equals("MYTHIC")) {
                    list.add(back_up.get(i));
                }
            }
            for (int i = 0; i < back_up.size(); i++) {
                if (back_up.get(i).brare.equals("EPIC")) {
                    list.add(back_up.get(i));
                }
            }
            for (int i = 0; i < back_up.size(); i++) {
                if (back_up.get(i).brare.equals("SUPER RARE")) {
                    list.add(back_up.get(i));
                }
            }
            for (int i = 0; i < back_up.size(); i++) {
                if (back_up.get(i).brare.equals("RARE")) {
                    list.add(back_up.get(i));
                }
            }
            for (int i = 0; i < back_up.size(); i++) {
                if (back_up.get(i).brare.equals("TROPHIE ROAD")) {
                    list.add(back_up.get(i));
                }
            }
            Toast.makeText(getContext(), "Sorted by Most Rarity", Toast.LENGTH_SHORT).show();
            isRarityDecrease = true;
        }
    }

}