package com.kal.brawlstatz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class devtools extends AppCompatActivity {
    TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_devtools);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("devLogin").child("lastLogin");
        SimpleDateFormat sdf = new SimpleDateFormat("'Date\n'dd-MM-yyyy '\n\nand\n\nTime\n'HH:mm:ss z");
        String currentDateAndTime = sdf.format(new Date());
        myRef.setValue(currentDateAndTime);

        time = findViewById(R.id.time);
        String greet= new SimpleDateFormat("HH").format(new Date());
        int currTime= Integer.parseInt(greet);
        if(currTime>=5&&currTime<12) time.setText("Good Morning");
        else if(currTime<18) time.setText("Good Afternoon");
        else if(currTime<19) time.setText("Good Evening");
        else time.setText("Good Night");
    }
}