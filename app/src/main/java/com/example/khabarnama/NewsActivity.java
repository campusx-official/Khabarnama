package com.example.khabarnama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class NewsActivity extends AppCompatActivity {

    WebView webView;
    String url,headline,description,poster,source;
    FirebaseDatabase database;
    DatabaseReference myRef;
    HashMap<String,String> info = new HashMap<String,String>();
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        url = getIntent().getStringExtra("url");
        headline = getIntent().getStringExtra("headline");
        description = getIntent().getStringExtra("description");
        poster = getIntent().getStringExtra("poster");
        source = getIntent().getStringExtra("source");

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        info.put("url",url);
        info.put("headline",headline);
        info.put("description",description);
        info.put("poster",poster);
        info.put("source",source);

        webView = findViewById(R.id.browser);

        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.share_news){
            // Share the news
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.putExtra(Intent.EXTRA_TEXT,url);
            share.setType("text/plain");
            startActivity(share);

        }else {
            // Bookmark item
            myRef.child(userId).push().setValue(info);
            Toast.makeText(this, "Bookmark added successfully", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}