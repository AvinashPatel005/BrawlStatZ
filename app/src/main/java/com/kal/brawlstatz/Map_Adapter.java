package com.kal.brawlstatz;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Map_Adapter extends RecyclerView.Adapter<Map_Adapter.ViewHolder> {
    ArrayList<MapModelClass> mlist;

    int mExpandedPosition = -1;
    int previousExpandedPosition = -1;

    public Map_Adapter(ArrayList<MapModelClass> mlist) {
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public Map_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mapcard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Map_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final MapModelClass temp_mList = mlist.get(position);

        Picasso.get().load(temp_mList.mlayout).networkPolicy(NetworkPolicy.OFFLINE).into(holder.layout, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(temp_mList.mlayout).into(holder.layout);
            }
        });
        Picasso.get().load(temp_mList.mthumb).networkPolicy(NetworkPolicy.OFFLINE).into(holder.thumb, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(temp_mList.mthumb).into(holder.thumb);
            }
        });
        holder.mabout.setText(temp_mList.mabout);

        holder.name.setText(temp_mList.mname);
        holder.type.setText(temp_mList.mtype);

        switch (temp_mList.mtype) {
            case "KNOCKOUT":
                Picasso.get().load(R.drawable.knockout).into(holder.mode_icon);
                holder.typecol.setCardBackgroundColor(Color.parseColor("#f6831c"));
                break;
            case "GEM GRAB":
                Picasso.get().load(R.drawable.gemgrab).into(holder.mode_icon);
                holder.typecol.setCardBackgroundColor(Color.parseColor("#9b3df3"));
                break;
            case "HEIST":
                Picasso.get().load(R.drawable.heist).into(holder.mode_icon);
                holder.typecol.setCardBackgroundColor(Color.parseColor("#d55cd3"));
                break;
            case "BRAWL BALL":
                Picasso.get().load(R.drawable.brawlball).into(holder.mode_icon);
                holder.typecol.setCardBackgroundColor(Color.parseColor("#8c9fdf"));
                break;
            case "HOT ZONE":
                Picasso.get().load(R.drawable.hotzone).into(holder.mode_icon);
                holder.typecol.setCardBackgroundColor(Color.parseColor("#e33c50"));
                break;
            case "BOUNTY":
                Picasso.get().load(R.drawable.bounty).into(holder.mode_icon);
                holder.typecol.setCardBackgroundColor(Color.parseColor("#01cfff"));
                break;
            default:
                Picasso.get().load(R.drawable.bounty).into(holder.mode_icon);
                holder.typecol.setCardBackgroundColor(Color.parseColor("#000000"));
        }

        final boolean isExpanded = position == mExpandedPosition;
        if (isExpanded) {
            holder.mhide.setVisibility(View.VISIBLE);


        } else {
            holder.mhide.setVisibility(View.GONE);
        }

        holder.itemView.setActivated(isExpanded);
        if (isExpanded)
            previousExpandedPosition = position;

        holder.itemView.setOnClickListener(view -> {
            mExpandedPosition = isExpanded ? -1 : position;
            notifyItemChanged(previousExpandedPosition);
            notifyItemChanged(position);
        });

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mhide;
        ImageView thumb , layout , mode_icon;
        TextView name , type,mabout;
        MaterialCardView typecol;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumb = itemView.findViewById(R.id.mthumb);
            layout = itemView.findViewById(R.id.mlayout);
            mode_icon = itemView.findViewById(R.id.modeIco);
            name = itemView.findViewById(R.id.mname);
            type = itemView.findViewById(R.id.mtype);
            typecol = itemView.findViewById(R.id.typecol);
            mhide = itemView.findViewById(R.id.mhide);
            mabout = itemView.findViewById(R.id.mabout);


        }
    }
}
