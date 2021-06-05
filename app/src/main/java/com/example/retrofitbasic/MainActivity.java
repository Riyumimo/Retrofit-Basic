package com.example.retrofitbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.retrofitbasic.Retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG="MainActivity";
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private List<MainModel.Result> results= new ArrayList<>();
    private MainAdapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        setupRecycleView();
        getDataFromAPI();
    }

    private void setupView() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar= findViewById(R.id.progressBar);

    }

    private void setupRecycleView() {
        mainAdapter=  new MainAdapter(results, new MainAdapter.OnAdapterListener() {
            @Override
            public void onclick(MainModel.Result result) {
                Toast.makeText(MainActivity.this ,result.getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("intent_title",result.getTitle());
                intent.putExtra("intent_image",result.getImage());
                startActivity(intent);

            }
        });
        RecyclerView.LayoutManager layoutManager=  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);

    }


    private void getDataFromAPI(){
        progressBar.setVisibility(View.VISIBLE);
     ApiService.endPoint().getdata()
             .enqueue(new Callback<MainModel>() {
         @Override
         public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                Log.d(TAG, response.toString());
             progressBar.setVisibility(View.GONE);
            if (response.isSuccessful()){
                List<MainModel.Result> results=response.body().getResult();
                Log.d(TAG,results.toString());
                mainAdapter.setData(results);
            }
         }

         @Override
         public void onFailure(Call<MainModel> call, Throwable t) {
             progressBar.setVisibility(View.GONE);
             Log.d(TAG,t.toString());
         }
     });
    }
}