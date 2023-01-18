package com.kal.brawlstatz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class Brawler_Adapter extends RecyclerView.Adapter<Brawler_Adapter.ViewHolder> {
    ArrayList<BrawlerModelClass> list;
    Activity activity;
    // for Expandable cards.
    int mExpandedPosition = -1;
    int previousExpandedPosition = -1;

    //getting list from BrawlerFragment
    public Brawler_Adapter(ArrayList<BrawlerModelClass> list, Activity a) {
        this.list = list;
        this.activity=a;
    }

    @NonNull
    @Override
    public Brawler_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brawlercard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Brawler_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final BrawlerModelClass temp_List = list.get(position);

        holder.bname.setText(temp_List.bname);
        holder.brare.setText(temp_List.brare);
        holder.babout.setText(temp_List.babout);

        holder.c1n.setText(temp_List.c1n);
        holder.c2n.setText(temp_List.c2n);
        holder.c3n.setText(temp_List.c3n);

        holder.battack.setText(temp_List.battack);
        holder.bsuper.setText(temp_List.bsuper);

        if(temp_List.battack.length()>18) holder.battack.setTextSize(9);
        else holder.battack.setTextSize(14);
        if(temp_List.bsuper.length()>18) holder.bsuper.setTextSize(9);
        else holder.bsuper.setTextSize(14);

        holder.ab.setOnClickListener(v -> {
            holder.helper.setText(temp_List.battackt);
            holder.helper.setVisibility(View.VISIBLE);
           holder.atkb.setVisibility(View.VISIBLE);
           holder.supb.setVisibility(View.INVISIBLE);
            holder.a1.setVisibility(View.INVISIBLE);
            holder.a2.setVisibility(View.INVISIBLE);
            holder.a3.setVisibility(View.INVISIBLE);
            holder.a4.setVisibility(View.INVISIBLE);
        });
        holder.sb.setOnClickListener(v -> {
            holder.helper.setText(temp_List.bsupert);
            holder.helper.setVisibility(View.VISIBLE);
            holder.supb.setVisibility(View.VISIBLE);
            holder.atkb.setVisibility(View.INVISIBLE);
            holder.a1.setVisibility(View.INVISIBLE);
            holder.a2.setVisibility(View.INVISIBLE);
            holder.a3.setVisibility(View.INVISIBLE);
            holder.a4.setVisibility(View.INVISIBLE);
        });


//        holder.bpro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent n = new Intent(activity,Brawler_Activity.class);
//                n.putExtra("list",list);
//                n.putExtra("position",position);
//                activity.startActivity(n);
//            }
//        });

        Picasso.get().load(temp_List.bpro).placeholder(R.drawable.placeholder1).networkPolicy(NetworkPolicy.OFFLINE).into(holder.bpro, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(temp_List.bpro).placeholder(R.drawable.placeholder1).into(holder.bpro);
            }
        });

        Picasso.get().load(temp_List.c1).placeholder(R.drawable.placeholder2).networkPolicy(NetworkPolicy.OFFLINE).into(holder.c1, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(temp_List.c1).placeholder(R.drawable.placeholder2).into(holder.c1);
            }
        });

        Picasso.get().load(temp_List.c2).placeholder(R.drawable.placeholder4).networkPolicy(NetworkPolicy.OFFLINE).into(holder.c2, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(temp_List.c2).placeholder(R.drawable.placeholder4).into(holder.c2);
            }
        });

        Picasso.get().load(temp_List.c3).placeholder(R.drawable.placeholder3).networkPolicy(NetworkPolicy.OFFLINE).into(holder.c3, new Callback() {
            @Override
            public void onSuccess(){}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(temp_List.c3).placeholder(R.drawable.placeholder3).into(holder.c3);
            }
        });

        Picasso.get().load(temp_List.g1).networkPolicy(NetworkPolicy.OFFLINE).into(holder.g1, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(temp_List.g1).into(holder.g1);
            }
        });

        Picasso.get().load(temp_List.g2).networkPolicy(NetworkPolicy.OFFLINE).into(holder.g2, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(temp_List.g2).into(holder.g2);
            }
        });

        Picasso.get().load(temp_List.s1).networkPolicy(NetworkPolicy.OFFLINE).into(holder.s1, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(temp_List.s1).into(holder.s1);
            }
        });

        Picasso.get().load(temp_List.s2).networkPolicy(NetworkPolicy.OFFLINE).into(holder.s2, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(temp_List.s2).into(holder.s2);
            }
        });

        //changing color of cardStroke and Rarity text.
        switch (holder.brare.getText().toString()) {
            case "LEGENDARY":
                holder.cardView.setCardBackgroundColor(Color.parseColor("#EDCA40"));
                holder.brare.setTextColor(Color.parseColor("#EDCA40"));
                break;
            case "RARE":
                holder.cardView.setCardBackgroundColor(Color.parseColor("#2cde18"));
                holder.brare.setTextColor(Color.parseColor("#2cde18"));
                break;
            case "STARTING":
                holder.cardView.setCardBackgroundColor(Color.parseColor("#8accfb"));
                holder.brare.setTextColor(Color.parseColor("#8accfb"));
                break;
            case "CHROMATIC":
                holder.cardView.setCardBackgroundColor(Color.parseColor("#f3902d"));
                holder.brare.setTextColor(Color.parseColor("#f3902d"));
                break;
            case "SUPER RARE":
                holder.cardView.setCardBackgroundColor(Color.parseColor("#117af4"));
                holder.brare.setTextColor(Color.parseColor("#117af4"));
                break;
            case "EPIC":
                holder.cardView.setCardBackgroundColor(Color.parseColor("#a91edd"));
                holder.brare.setTextColor(Color.parseColor("#a91edd"));
                break;
            case "MYTHIC":
                holder.cardView.setCardBackgroundColor(Color.parseColor("#f33f41"));
                holder.brare.setTextColor(Color.parseColor("#f33f41"));
                break;
            default:
                holder.cardView.setCardBackgroundColor(Color.parseColor("#000100"));
                holder.brare.setTextColor(Color.parseColor("#000100"));
        }

        //click event on gadgets and star power.
        holder.g1.setOnClickListener(v -> {
            holder.helper.setText(temp_List.g1t);
            holder.helper.setVisibility(View.VISIBLE);
            holder.a1.setVisibility(View.VISIBLE);
            holder.a2.setVisibility(View.INVISIBLE);
            holder.a3.setVisibility(View.INVISIBLE);
            holder.a4.setVisibility(View.INVISIBLE);
            holder.atkb.setVisibility(View.INVISIBLE);
            holder.supb.setVisibility(View.INVISIBLE);
        });
        holder.g2.setOnClickListener(v -> {
            holder.helper.setText(temp_List.g2t);
            holder.helper.setVisibility(View.VISIBLE);
            holder.a1.setVisibility(View.INVISIBLE);
            holder.a2.setVisibility(View.VISIBLE);
            holder.a3.setVisibility(View.INVISIBLE);
            holder.a4.setVisibility(View.INVISIBLE);
            holder.atkb.setVisibility(View.INVISIBLE);
            holder.supb.setVisibility(View.INVISIBLE);
        });
        holder.s1.setOnClickListener(v -> {
            holder.helper.setText(temp_List.s1t);
            holder.helper.setVisibility(View.VISIBLE);
            holder.a1.setVisibility(View.INVISIBLE);
            holder.a2.setVisibility(View.INVISIBLE);
            holder.a3.setVisibility(View.VISIBLE);
            holder.a4.setVisibility(View.INVISIBLE);
            holder.atkb.setVisibility(View.INVISIBLE);
            holder.supb.setVisibility(View.INVISIBLE);
        });
        holder.s2.setOnClickListener(v -> {
            holder.helper.setText(temp_List.s2t);
            holder.helper.setVisibility(View.VISIBLE);
            holder.a1.setVisibility(View.INVISIBLE);
            holder.a2.setVisibility(View.INVISIBLE);
            holder.a3.setVisibility(View.INVISIBLE);
            holder.a4.setVisibility(View.VISIBLE);
            holder.atkb.setVisibility(View.INVISIBLE);
            holder.supb.setVisibility(View.INVISIBLE);
        });


        //Expandable card
        final boolean isExpanded = position == mExpandedPosition;
        if (isExpanded) {
            holder.hidden.setVisibility(View.VISIBLE);
            holder.downA.setVisibility(View.GONE);
            holder.helper.setText("");
            holder.c1n.setVisibility(View.VISIBLE);
            holder.c2n.setVisibility(View.VISIBLE);
            holder.c3n.setVisibility(View.VISIBLE);

            holder.atkb.setVisibility(View.INVISIBLE);
            holder.supb.setVisibility(View.INVISIBLE);

            holder.a1.setVisibility(View.INVISIBLE);
            holder.a2.setVisibility(View.INVISIBLE);
            holder.a3.setVisibility(View.INVISIBLE);
            holder.a4.setVisibility(View.INVISIBLE);

            holder.helper.setVisibility(View.INVISIBLE);
            holder.bpro.getLayoutParams().width = 300;
            holder.bpro.getLayoutParams().height = 300;

            holder.bpcard.getLayoutParams().width = 300;
            holder.bpcard.getLayoutParams().height = 300;

        } else {
            holder.hidden.setVisibility(View.GONE);
            holder.downA.setVisibility(View.VISIBLE);

            holder.c1n.setVisibility(View.GONE);
            holder.c2n.setVisibility(View.GONE);
            holder.c3n.setVisibility(View.GONE);

            holder.bpro.getLayoutParams().width = 220;
            holder.bpro.getLayoutParams().height = 220;

            holder.bpcard.getLayoutParams().width = 224;
            holder.bpcard.getLayoutParams().height = 224;
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
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView bname, brare, c1n, c2n, c3n, babout, helper,battack,bsuper;
        public ImageView bpro, c1, c2, c3, g1, g2, s1, s2;

        public MaterialCardView cardView, bpcard;

        public ImageView downA, upA,a1,a2,a3,a4,atkb,supb,ab,sb;
        public ConstraintLayout hidden;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //All details
            bpro = itemView.findViewById(R.id.bpro);
            bname = itemView.findViewById(R.id.bname);
            brare = itemView.findViewById(R.id.brare);
            babout = itemView.findViewById(R.id.babout);
            c1 = itemView.findViewById(R.id.c1);
            c2 = itemView.findViewById(R.id.c2);
            c3 = itemView.findViewById(R.id.c3);
            c1n = itemView.findViewById(R.id.c1n);
            c2n = itemView.findViewById(R.id.c2n);
            c3n = itemView.findViewById(R.id.c3n);
            g1 = itemView.findViewById(R.id.g1);
            g2 = itemView.findViewById(R.id.g2);
            s1 = itemView.findViewById(R.id.s1);
            s2 = itemView.findViewById(R.id.s2);
            helper = itemView.findViewById(R.id.helper);
            //stroke color
            cardView = itemView.findViewById(R.id.cardView);
            //hide functionality
            hidden = itemView.findViewById(R.id.hidden);
            downA = itemView.findViewById(R.id.downA);
            upA = itemView.findViewById(R.id.upA);
            bpcard = itemView.findViewById(R.id.bpro_card);

            a1=itemView.findViewById(R.id.a1);
            a2=itemView.findViewById(R.id.a2);
            a3=itemView.findViewById(R.id.a3);
            a4=itemView.findViewById(R.id.a4);

            battack=itemView.findViewById(R.id.attack);
            bsuper=itemView.findViewById(R.id.attack_super);

            atkb = itemView.findViewById(R.id.atkb);
            supb=itemView.findViewById(R.id.supb);

            ab = itemView.findViewById(R.id.attackback);
            sb=itemView.findViewById(R.id.superback);
        }
    }
}
