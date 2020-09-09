package com.example.gads_leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Splasher extends AppCompatActivity {

    private ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splasher);

        pbLoading = findViewById(R.id.pbLoading);
        pbLoading.setVisibility(View.VISIBLE);

        ApiUtil util = ApiUtil.getInstance();
        util.fetchHours(new Callback<List<Hour>>() {
            @Override
            public void onResponse(Call<List<Hour>> call, Response<List<Hour>> response) {

            }

            @Override
            public void onFailure(Call<List<Hour>> call, Throwable t) {

            }
        });

        util.fetchScores(new Callback<List<SkillIq>>() {
            @Override
            public void onResponse(Call<List<SkillIq>> call, Response<List<SkillIq>> response) {
                if(response.isSuccessful()) {
                    pbLoading.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(Splasher.this,MainActivity.class);
                    Splasher.this.startActivity(intent);

                }
                else {

                }
                Splasher.this.finish();
            }

            @Override
            public void onFailure(Call<List<SkillIq>> call, Throwable t) {

            }
        });


    }
}