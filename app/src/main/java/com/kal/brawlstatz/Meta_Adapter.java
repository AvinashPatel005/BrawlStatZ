package com.kal.brawlstatz;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Meta_Adapter extends RecyclerView.Adapter<Meta_Adapter.ViewHolder> {

    ArrayList<MetaModelClass> Metalist;
    public Meta_Adapter(ArrayList<MetaModelClass> metalist) {
        this.Metalist = metalist;
    }

    @NonNull
    @Override
    public Meta_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.metacard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Meta_Adapter.ViewHolder holder, int position) {
        final MetaModelClass t_element = Metalist.get(position);

        holder.name.setText(t_element.bname);

        Picasso.get().load(t_element.bpro).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.placeholder4).into(holder.mpro, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(t_element.bpro).placeholder(R.drawable.placeholder4).into(holder.mpro);
            }
        });

        holder.mrare.setText(t_element.brare);
        switch (holder.mrare.getText().toString()) {
            case "LEGENDARY":
                holder.mrare.setTextColor(Color.parseColor("#EDCA40"));
                break;
            case "RARE":
                holder.mrare.setTextColor(Color.parseColor("#2cde18"));
                break;
            case "STARTING":
                holder.mrare.setTextColor(Color.parseColor("#8accfb"));
                break;
            case "CHROMATIC":
                holder.mrare.setTextColor(Color.parseColor("#f3902d"));
                break;
            case "SUPER RARE":
                holder.mrare.setTextColor(Color.parseColor("#117af4"));
                break;
            case "EPIC":
                holder.mrare.setTextColor(Color.parseColor("#a91edd"));
                break;
            case "MYTHIC":
                holder.mrare.setTextColor(Color.parseColor("#f33f41"));
                break;
            default:
                holder.mrare.setTextColor(Color.parseColor("#000100"));
        }

        if(t_element.tier!=null) {
            if(t_element.tier.charAt(0)>='0'&&t_element.tier.charAt(0)<='9') holder.tier.setText("S");
            else holder.tier.setText(String.valueOf(t_element.tier.charAt(0)));
        }
        else holder.tier.setText(R.string.unranked);

        switch (holder.tier.getText().toString()){
            case "S":holder.tier.setTextColor(Color.parseColor("#ff7e7e"));
                break;
            case "A":holder.tier.setTextColor(Color.parseColor("#ffbf7f"));
                break;
            case "B":holder.tier.setTextColor(Color.parseColor("#ffde7f"));
                break;
            case "C":holder.tier.setTextColor(Color.parseColor("#feff7f"));
                break;
            case "D":holder.tier.setTextColor(Color.parseColor("#beff7d"));
                break;
            case "F":holder.tier.setTextColor(Color.parseColor("#7eff80"));
                break;
            default: holder.tier.setTextColor(Color.parseColor("#000000"));
        }

        holder.rank.setText(String.valueOf(position+1));
        if(t_element.bstarpower !=null) {
            Log.d(TAG, t_element.bstarpower);
            switch (t_element.bstarpower) {
                case "1":
                    Picasso.get().load(R.drawable.s1).into(holder.bstar);
                    break;
                case "2":
                    Picasso.get().load(R.drawable.s2).into(holder.bstar);
                    break;
            }
        }

        if(t_element.bstarpower !=null) {
            Log.d(TAG, t_element.bgadget);
            switch (t_element.bgadget) {
                case "1":
                    Picasso.get().load(R.drawable.g1).into(holder.bgad);
                    break;
                case "2":
                    Picasso.get().load(R.drawable.g2).into(holder.bgad);
                    break;
            }
        }


        Picasso.get().load(t_element.bgear1).networkPolicy(NetworkPolicy.OFFLINE).into(holder.bg1, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(t_element.bgear1).into(holder.bg1);
            }
        });
        Picasso.get().load(t_element.bgear2).networkPolicy(NetworkPolicy.OFFLINE).into(holder.bg2, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(t_element.bgear2).into(holder.bg2);
            }
        });
        Picasso.get().load(t_element.bgear3).networkPolicy(NetworkPolicy.OFFLINE).into(holder.bg3, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(t_element.bgear3).into(holder.bg3);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Metalist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,tier,rank,mrare;
        ImageView mpro,bstar,bgad,bg1,bg2,bg3;
        MaterialCardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.metaCard);
            mpro = itemView.findViewById(R.id.mpro);
            name = itemView.findViewById(R.id.name);
            tier = itemView.findViewById(R.id.tier);
            rank = itemView.findViewById(R.id.rank);
            mrare = itemView.findViewById(R.id.mrare);
            bstar = itemView.findViewById(R.id.bstar);
            bgad = itemView.findViewById(R.id.bgad);
            bg1 = itemView.findViewById(R.id.bg1);
            bg2 = itemView.findViewById(R.id.bg2);
            bg3 = itemView.findViewById(R.id.bg3);
        }
    }
}
