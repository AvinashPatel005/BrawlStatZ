package com.kal.brawlstatz;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.card.MaterialCardView;

public class statsFragment extends Fragment {
    private WebView webView;
    private MaterialCardView materialCardView;
    private ImageButton taghide,unhide, search;
    private EditText tag;
    private MaterialToolbar mtb;
    static String t1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        tag=view.findViewById(R.id.tagId);
        search=view.findViewById(R.id.searchTag);
        webView=view.findViewById(R.id.web);
        materialCardView =view.findViewById(R.id.tag);
        taghide=view.findViewById(R.id.tagHide);
        unhide=view.findViewById(R.id.unhide);
        webView.getSettings().setJavaScriptEnabled(true);

        SharedPreferences sharedPref2 = getActivity().getPreferences(Context.MODE_PRIVATE);
        t1 = sharedPref2.getString("tagSaved","");

        tag.setText(t1);
        mtb =view.findViewById(R.id.materialToolbar4);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(mtb);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView webView, String url)
            {
                webView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('_73D4tMj9dScm5fs6dRDjO')[0].style.display='none';" +
                        "})()");
                webView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('_5asXE9QXJIj4NfHXSxkfE')[0].style.display='none';" +
                        "})()");
                webView.setVisibility(View.VISIBLE);
            }
        });
        if(!t1.equals("")){
            materialCardView.setVisibility(View.GONE);
        }
        webView.loadUrl("https://brawlstats.com/profile/"+t1);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tagid=tag.getText().toString();
                webView.loadUrl("https://brawlstats.com/profile/"+tagid);

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("tagSaved",tagid);
                editor.apply();
                materialCardView.setVisibility(View.GONE);
                Toast.makeText(getContext(),"Fetching Data",Toast.LENGTH_SHORT).show();
            }
        });
        taghide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialCardView.setVisibility(View.GONE);
                unhide.setVisibility(View.VISIBLE);
            }
        });
        unhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialCardView.setVisibility(View.VISIBLE);
                unhide.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.stats_menu,menu);
        MenuItem item1=menu.findItem(R.id.sync);
        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                SharedPreferences sharedPref3 = getActivity().getPreferences(Context.MODE_PRIVATE);
                t1 = sharedPref3.getString("tagSaved","");
                webView.loadUrl("https://brawlstats.com/profile/"+t1);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);

    }
}