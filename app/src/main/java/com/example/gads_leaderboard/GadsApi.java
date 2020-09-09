package com.example.gads_leaderboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface GadsApi {

    @GET("/api/hours")
    Call<List<Hour>> getHours();

    @GET("/api/skilliq")
    Call<List<SkillIq>> getScores();

}
