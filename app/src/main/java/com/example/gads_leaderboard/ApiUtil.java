package com.example.gads_leaderboard;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ApiUtil {

    public final ArrayList<Hour> hours = new ArrayList<>();
    public final ArrayList<SkillIq> scores = new ArrayList<>();
    private static ApiUtil myInstance;
    Retrofit retrofit;
    GadsApi gadsApi;

    private ApiUtil() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gadsApi = retrofit.create(GadsApi.class);

    }

    public static ApiUtil getInstance() {
        if(myInstance == null) {
            myInstance = new ApiUtil();
        }
        return myInstance;
    }

    public void fetchHours(@NonNull final Callback<List<Hour>> callback) {

        getInstance();

        Call<List<Hour>> hoursCall = gadsApi.getHours();
        hoursCall.enqueue(new Callback<List<Hour>>() {
            @Override
            public void onResponse(Call<List<Hour>> call, Response<List<Hour>> response) {
                if(response.isSuccessful()) {
                    List<Hour> hourList = response.body();
                    hours.clear();;
                    hours.addAll(hourList);
                }
                callback.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<List<Hour>> call, Throwable t) {
                //TODO add your own code and execute callers code
                callback.onFailure(call,t);
            }
        });

    }

    public void fetchScores(@NonNull final Callback<List<SkillIq>> callback) {

        getInstance();

        Call<List<SkillIq>> scoreCall = gadsApi.getScores();
        scoreCall.enqueue(new Callback<List<SkillIq>>() {
            @Override
            public void onResponse(Call<List<SkillIq>> call, Response<List<SkillIq>> response) {
                if(response.isSuccessful()) {
                    List<SkillIq> scoresList = response.body();
                    scores.clear();
                    scores.addAll(scoresList);
                }
                callback.onResponse(call,response);
            }

            @Override
            public void onFailure(Call<List<SkillIq>> call, Throwable t) {
                //TODO add your own code and execute callers code
                callback.onFailure(call,t);
            }
        });

    }

}
