package com.kal.brawlstatz;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class vpAdapter extends FragmentStateAdapter {
    public vpAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:return new mapFragment();
            case 2:return new MetaFragment();
            default:return new BrawlerFragment();
        }
    }
    @Override
    public int getItemCount() {
        return 3;
    }
}
