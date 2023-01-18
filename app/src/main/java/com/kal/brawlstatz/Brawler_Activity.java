package com.kal.brawlstatz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Brawler_Activity extends AppCompatActivity {
    TextView t;
    ImageView iv;
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_brawler);

        final ArrayList<BrawlerModelClass> list= (ArrayList<BrawlerModelClass>) getIntent().getSerializableExtra("list");
        final int position = (Integer) getIntent().getSerializableExtra("position");
        final BrawlerModelClass brawler = list.get(position);

        t = findViewById(R.id.nameMain);
        t.setText(brawler.bname);

        iv=findViewById(R.id.iv);

        Picasso.get().load(brawler.bmodel).networkPolicy(NetworkPolicy.OFFLINE).into(iv, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Picasso.get().load(brawler.bmodel).into(iv);
            }
        });
    }
}