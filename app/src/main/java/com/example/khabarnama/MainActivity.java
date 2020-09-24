package com.example.khabarnama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab;
    NavigationView navigationView;
    ImageView imageView;
    TextView textView,textView1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView =  findViewById(R.id.bottom_nav);
        fab = findViewById(R.id.fab);
        navigationView = findViewById(R.id.navigation_view);

        View headerView = navigationView.getHeaderView(0);

        imageView = headerView.findViewById(R.id.user_profile_picture);
        textView = headerView.findViewById(R.id.user_name);
        textView1 = headerView.findViewById(R.id.user_email);

        UserInfo userInfo = FirebaseAuth.getInstance().getCurrentUser();
        textView.setText(userInfo.getDisplayName());
        textView1.setText(userInfo.getEmail());
        Picasso.with(MainActivity.this).load(userInfo.getPhotoUrl()).into(imageView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.nav_bookmarks){
                    Intent i = new Intent(MainActivity.this,BookmarkActivity.class);
                    startActivity(i);
                }else if(menuItem.getItemId() == R.id.nav_settings){
                    Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseAuth.getInstance().signOut();
                    Intent i = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(i);
                }
                return true;
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(i);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment myFragment = null;

                if(menuItem.getItemId() == R.id.trending){
                    myFragment = new FragmentTrending();
                }else if(menuItem.getItemId() == R.id.business){
                    myFragment = new FragmentBusiness();
                }else if(menuItem.getItemId() == R.id.tech){
                    myFragment = new FragmentTechnology();
                }else if(menuItem.getItemId() == R.id.entertainment){
                    myFragment = new FragmentEntertainement();
                }else{
                    myFragment = new FragmentSports();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,myFragment).commit();
                return true;
            }
        });

        if(savedInstanceState == null){
            bottomNavigationView.setSelectedItemId(R.id.trending);
        }

    }


}