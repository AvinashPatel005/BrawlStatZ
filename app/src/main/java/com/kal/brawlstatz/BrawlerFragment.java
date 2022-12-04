package com.kal.brawlstatz;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

public class BrawlerFragment extends Fragment {

    ArrayList<BrawlerModelClass> list = new ArrayList<>();
    ArrayList<BrawlerModelClass> filter = new ArrayList<>();
    private Adapter adapter;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    boolean isloaded = false;
    boolean f = true;
    MaterialToolbar materialToolbar;
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brawler, container, false);

        Log.d(TAG, "onDataChange: "+isloaded);
        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        materialToolbar =view.findViewById(R.id.materialToolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(materialToolbar);

        shimmerFrameLayout = view.findViewById(R.id.shimmer);
        loadDatabase();

        return view;
    }

    private void loadDatabase() {
        FirebaseDatabase.getInstance().getReference().child("brawlers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // adding data to Arraylist
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    BrawlerModelClass brawler = ds.getValue(BrawlerModelClass.class);
                    list.add(brawler);

                }
                adapter = new Adapter(list);
                filter = new ArrayList(list);

                recyclerView.setAdapter(adapter);
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
        inflater.inflate(R.menu.home_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Eg.Name ,Rarity");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (filter.size() != 0) {
                    list.clear();
                    adapter.notifyDataSetChanged();
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).bname.toString().toLowerCase().startsWith(s) || filter.get(i).brare.toString().toLowerCase().startsWith(s)) {
                            list.add(filter.get(i));
                        }
                    }
                    adapter.notifyDataSetChanged();

                    Log.d(TAG, String.valueOf(list.size()));
                }
                return false;
            }
        });

        MenuItem menuItem1 = menu.findItem(R.id.sort_name);
        MenuItem menuItem2 = menu.findItem(R.id.sort_rarity);
        MenuItem menuItem5 = menu.findItem(R.id.refresh);

        menuItem5.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                loadDatabase();
                Toast.makeText(getContext(), "Database Refreshed", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        menuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (list.equals(filter)) {
                    list.clear();
                    list.addAll(filter);
                    Collections.reverse(list);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Sorted by Name in Descending Order", Toast.LENGTH_SHORT).show();
                } else {
                    list.clear();
                    list.addAll(filter);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Sorted by Name in Ascending Order", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        menuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                if (f) {
                    list.clear();
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).brare.toString().equals("TROPHIE ROAD")) {
                            list.add(filter.get(i));
                        }
                    }
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).brare.toString().equals("RARE")) {
                            list.add(filter.get(i));
                        }
                    }
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).brare.toString().equals("SUPER RARE")) {
                            list.add(filter.get(i));
                        }
                    }
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).brare.toString().equals("EPIC")) {
                            list.add(filter.get(i));
                        }
                    }
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).brare.toString().equals("MYTHIC")) {
                            list.add(filter.get(i));
                        }
                    }
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).brare.toString().equals("CHROMATIC")) {
                            list.add(filter.get(i));
                        }
                    }
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).brare.toString().equals("LEGENDARY")) {
                            list.add(filter.get(i));
                        }
                    }
                    Toast.makeText(getContext(), "Sorted by Least Rarity", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                    f = false;
                } else {
                    list.clear();
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).brare.toString().equals("LEGENDARY")) {
                            list.add(filter.get(i));
                        }
                    }
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).brare.toString().equals("CHROMATIC")) {
                            list.add(filter.get(i));
                        }
                    }
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).brare.toString().equals("MYTHIC")) {
                            list.add(filter.get(i));
                        }
                    }
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).brare.toString().equals("EPIC")) {
                            list.add(filter.get(i));
                        }
                    }
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).brare.toString().equals("SUPER RARE")) {
                            list.add(filter.get(i));
                        }
                    }
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).brare.toString().equals("RARE")) {
                            list.add(filter.get(i));
                        }
                    }
                    for (int i = 0; i < filter.size(); i++) {
                        if (filter.get(i).brare.toString().equals("TROPHIE ROAD")) {
                            list.add(filter.get(i));
                        }
                    }
                    Toast.makeText(getContext(), "Sorted by Most Rarity", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                    f = true;
                }
                return false;
            }
        });

        MenuItem leg = menu.findItem(R.id.sort_Legendary);
        MenuItem chro = menu.findItem(R.id.sort_Chromatic);
        MenuItem myth = menu.findItem(R.id.sort_Mythic);
        MenuItem epic = menu.findItem(R.id.sort_Epic);
        MenuItem sup = menu.findItem(R.id.sort_Super);
        MenuItem rare = menu.findItem(R.id.sort_Rare);
        MenuItem road = menu.findItem(R.id.sort_Trophie);
        MenuItem reset = menu.findItem(R.id.sort_Reset);

        reset.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                list.clear();
                list.addAll(filter);
                adapter.notifyDataSetChanged();
                return false;
            }
        });


        leg.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                list.clear();
                for (int i = 0; i < filter.size(); i++) {
                    if (filter.get(i).brare.toString().equals("LEGENDARY")) {
                        list.add(filter.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        chro.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                list.clear();
                for (int i = 0; i < filter.size(); i++) {
                    if (filter.get(i).brare.toString().equals("CHROMATIC")) {
                        list.add(filter.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        myth.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                list.clear();
                for (int i = 0; i < filter.size(); i++) {
                    if (filter.get(i).brare.toString().equals("MYTHIC")) {
                        list.add(filter.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        epic.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                list.clear();
                for (int i = 0; i < filter.size(); i++) {
                    if (filter.get(i).brare.toString().equals("EPIC")) {
                        list.add(filter.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        sup.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                list.clear();
                for (int i = 0; i < filter.size(); i++) {
                    if (filter.get(i).brare.toString().equals("SUPER RARE")) {
                        list.add(filter.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        rare.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                list.clear();
                for (int i = 0; i < filter.size(); i++) {
                    if (filter.get(i).brare.toString().equals("RARE")) {
                        list.add(filter.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        road.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                list.clear();
                for (int i = 0; i < filter.size(); i++) {
                    if (filter.get(i).brare.toString().equals("TROPHIE ROAD")) {
                        list.add(filter.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}