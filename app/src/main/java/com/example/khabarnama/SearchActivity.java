package com.example.khabarnama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView6;
    EditText searchTerm;
    Button searchBtn;
    TextView searchMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView6 = findViewById(R.id.recycler6);
        searchBtn = findViewById(R.id.search_btn);
        searchTerm = findViewById(R.id.search_term);
        searchMsg = findViewById(R.id.search_msg);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 1. Fetch the search term
                String url = "https://newsapi.org/v2/everything?q=" + searchTerm.getText().toString() +"&apiKey=ff4fdc1a967240ca9d03b810d90e64ff";

                // 2. Create an object of AppData
                AppData appData = new AppData();

                // 3. Pass valid arguments
                int numResponse = appData.fetch(url,recyclerView6,SearchActivity.this);

                // 5. Search message
                searchMsg.setText(numResponse+ " Search results for " + searchTerm.getText().toString());

                // 4. clearing the edit text
                searchTerm.setText("");

            }
        });
    }
}