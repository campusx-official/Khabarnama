package com.example.khabarnama;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentTrending extends Fragment {

    RecyclerView recyclerView1;
    String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=ff4fdc1a967240ca9d03b810d90e64ff";
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trending,container,false);

        recyclerView1 = view.findViewById(R.id.recycler1);
        context = getActivity();

        AppData appData = new AppData();

        appData.fetch(url,recyclerView1,context);

        return view;
    }
}
