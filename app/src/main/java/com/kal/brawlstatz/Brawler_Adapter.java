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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Brawler_Adapter extends RecyclerView.Adapter<Brawler_Adapter.ViewHolder> {
    ArrayList<BrawlerModelClass> list;

    // for Expandable cards.
    int mExpandedPosition = -1;
    int previousExpandedPosition = -1;

    //getting list from BrawlerFragment
    public Brawler_Adapter(ArrayList<BrawlerModelClass> list) {
        this.list = list;
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

        if (holder.brare.getText().equals("LEGENDARY")) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#EDCA40"));
            holder.brare.setTextColor(Color.parseColor("#EDCA40"));
        } else if (holder.brare.getText().equals("RARE")) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#2cde18"));
            holder.brare.setTextColor(Color.parseColor("#2cde18"));
        } else if (holder.brare.getText().equals("TROPHIE ROAD")) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#8accfb"));
            holder.brare.setTextColor(Color.parseColor("#8accfb"));
        } else if (holder.brare.getText().equals("CHROMATIC")) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#f3902d"));
            holder.brare.setTextColor(Color.parseColor("#f3902d"));
        } else if (holder.brare.getText().equals("SUPER RARE")) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#117af4"));
            holder.brare.setTextColor(Color.parseColor("#117af4"));
        } else if (holder.brare.getText().equals("EPIC")) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#a91edd"));
            holder.brare.setTextColor(Color.parseColor("#a91edd"));
        } else if (holder.brare.getText().equals("MYTHIC")) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#f33f41"));
            holder.brare.setTextColor(Color.parseColor("#f33f41"));
        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#000100"));
            holder.brare.setTextColor(Color.parseColor("#000100"));
        }


        holder.g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.helper.setText(temp_List.g1t);
            }
        });
        holder.g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.helper.setText(temp_List.g2t);
            }
        });
        holder.s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.helper.setText(temp_List.s1t);
            }
        });
        holder.s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.helper.setText(temp_List.s2t);
            }
        });


        if (temp_List.bpro != null) {
            Picasso.get().load(temp_List.bpro).placeholder(R.drawable.placeholder1).networkPolicy(NetworkPolicy.OFFLINE).into(holder.bpro, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(temp_List.bpro).placeholder(R.drawable.placeholder1).into(holder.bpro);
                }
            });
        } else {
            Picasso.get().load(R.drawable.placeholder1).placeholder(R.drawable.placeholder1).into(holder.bpro);
        }
        if (temp_List.bmodel != null) {
            Picasso.get().load(temp_List.bmodel).networkPolicy(NetworkPolicy.OFFLINE).into(holder.bmodel, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(temp_List.bmodel).into(holder.bmodel);
                }
            });
        } else {
            Picasso.get().load(R.drawable.round_rectangle).into(holder.bmodel);
        }
        if (temp_List.c1 != null) {
            Picasso.get().load(temp_List.c1).placeholder(R.drawable.placeholder2).networkPolicy(NetworkPolicy.OFFLINE).into(holder.c1, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {


                    Picasso.get().load(temp_List.c1).placeholder(R.drawable.placeholder2).into(holder.c1);
                }
            });
        } else {
            Picasso.get().load(R.drawable.placeholder2).placeholder(R.drawable.placeholder2).into(holder.c1);
        }
        if (temp_List.c2 != null) {
            Picasso.get().load(temp_List.c2).placeholder(R.drawable.placeholder4).networkPolicy(NetworkPolicy.OFFLINE).into(holder.c2, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(temp_List.c2).placeholder(R.drawable.placeholder4).into(holder.c2);
                }
            });
        } else {
            Picasso.get().load(R.drawable.placeholder4).placeholder(R.drawable.placeholder4).into(holder.c2);
        }
        if (temp_List.c3 != null) {
            Picasso.get().load(temp_List.c3).placeholder(R.drawable.placeholder3).networkPolicy(NetworkPolicy.OFFLINE).into(holder.c3, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(temp_List.c3).placeholder(R.drawable.placeholder3).into(holder.c3);
                }
            });
        } else {
            Picasso.get().load(R.drawable.placeholder3).placeholder(R.drawable.placeholder3).into(holder.c3);
        }


        Picasso.get().load(temp_List.g1).networkPolicy(NetworkPolicy.OFFLINE).into(holder.g1, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(temp_List.g1).into(holder.g1);
            }
        });
        Picasso.get().load(temp_List.g2).networkPolicy(NetworkPolicy.OFFLINE).into(holder.g2, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(temp_List.g2).into(holder.g2);
            }
        });
        Picasso.get().load(temp_List.s1).networkPolicy(NetworkPolicy.OFFLINE).into(holder.s1, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(temp_List.s1).into(holder.s1);
            }
        });
        Picasso.get().load(temp_List.s2).networkPolicy(NetworkPolicy.OFFLINE).into(holder.s2, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(temp_List.s2).into(holder.s2);
            }
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : position;
                notifyItemChanged(previousExpandedPosition);
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        
        public TextView bname, brare, c1n, c2n, c3n, babout, helper;
        public ImageView bpro, c1, c2, c3, bmodel, g1, g2, s1, s2;

        public MaterialCardView cardView, bpcard;
        
        public ImageView downA, upA;
        public ConstraintLayout hidden;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            
            //All details
            bpro = itemView.findViewById(R.id.bpro);
            bname = itemView.findViewById(R.id.bname);
            brare = itemView.findViewById(R.id.brare);
            bmodel = itemView.findViewById(R.id.bmodel);
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
            
        }
    }
}
