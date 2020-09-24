package com.example.khabarnama;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyHolder> {

    Context context;
    ArrayList<String> news_headline,news_description,news_poster,news_source,news_url;
    LayoutInflater layoutInflater;


    CustomAdapter(Context context, ArrayList<String> news_headline,ArrayList<String> description, ArrayList<String> poster, ArrayList<String> source, ArrayList<String> url){

        this.context = context;
        this.news_headline = news_headline;
        this.news_description = description;
        this.news_poster = poster;
        this.news_source = source;
        this.news_url = url;
        layoutInflater = LayoutInflater.from(this.context);

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myview = layoutInflater.inflate(R.layout.card_layout,parent,false);

        MyHolder newHolder = new MyHolder(myview);

        return newHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        final String currentHeadline = news_headline.get(position);
        final String currentDescription = news_description.get(position);
        final String currentSource = news_source.get(position);

        final String currentImageUrl = news_poster.get(position);

        final String currentUrl = news_url.get(position);

        holder.headline.setText(currentHeadline);
        holder.description.setText(currentDescription);
        holder.source.setText(currentSource);

        Picasso.with(context).load(currentImageUrl).into(holder.poster);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, NewsActivity.class);
                i.putExtra("url",currentUrl);
                i.putExtra("headline",currentHeadline);
                i.putExtra("description",currentDescription);
                i.putExtra("poster",currentImageUrl);
                i.putExtra("source",currentSource);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {

        return news_headline.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        TextView headline,description,source;
        ImageView poster;
        LinearLayout card;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            headline = itemView.findViewById(R.id.headline);
            description = itemView.findViewById(R.id.description);
            source = itemView.findViewById(R.id.source);
            poster = itemView.findViewById(R.id.poster);
            card = itemView.findViewById(R.id.card);
        }
    }
}
