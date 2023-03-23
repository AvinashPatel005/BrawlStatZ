package com.kal.brawlstatz;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class devActivity extends AppCompatActivity {
    private static final int alphabets =26;
    Button login;
    EditText userId,passWord;
    String[] log = new String[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_dev);

        login=findViewById(R.id.login);
        userId=findViewById(R.id.id);
        passWord=findViewById(R.id.pass);

        FirebaseDatabase.getInstance().getReference().child("devLogin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               log[0]=snapshot.child("userId").getValue(String.class);
               log[1]=snapshot.child("pass").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(log[0]==null||log[1]==null) Toast.makeText(devActivity.this,"Network Error",Toast.LENGTH_SHORT).show();
                else if(decrypt(log[0],11,9).equals(userId.getText().toString())&&decrypt(log[1],11,9).equals(passWord.getText().toString())){
                    Toast.makeText(devActivity.this, "Login Successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(devActivity.this,devtools.class);
                    startActivity(intent);
                }
                else Toast.makeText(devActivity.this, "Incorrect Credentials",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static String decrypt(String encrypted ,int a ,int b){
        String decrypted="";
        int inv=0;
        for(int j = 0; j<= alphabets; j++){
            if((a*j)% alphabets ==1){
                inv=j;
                break;
            }
        }
        for (int i = 0;i<encrypted.length();i++){
            char ch = encrypted.charAt(i);

            if(ch>='a' && ch<='z'){
                decrypted += (char)((((ch- 'a'- b + alphabets)*inv)% alphabets)+'A');
            }
            else if(ch>='A'&&ch<='Z'){
                decrypted += (char)((((ch- 'A' - b + alphabets)*inv)% alphabets)+'a');
            }
            else decrypted += ch;
        }
        return decrypted;
    }
}