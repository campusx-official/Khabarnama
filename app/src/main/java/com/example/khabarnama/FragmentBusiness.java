package com.example.khabarnama;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.StringRequest;

public class FragmentBusiness extends Fragment {

    RecyclerView recyclerView2;
    Context context;
    String url = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=ff4fdc1a967240ca9d03b810d90e64ff";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business,container,false);

        recyclerView2 = view.findViewById(R.id.recycler2);
        context = getActivity();

        AppData appData = new AppData();

        appData.fetch(url,recyclerView2,context);

        return view;
    }
}
