package com.example.gads_leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitActivity extends AppCompatActivity {

    private EditText txtFname,txtLname,txtEmail,txtLink;

    private final static String address = "https://docs.google.com/forms/d/e/";
    private final static String gitLink = "https://github.com/SennayT/GADS-Leaderboard-App";
    private Dialog mDialog;
    ProgressBar pbSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v->finish());

        txtFname = findViewById(R.id.txtFirstName);
        txtLname = findViewById(R.id.txtLastName);
        txtEmail = findViewById(R.id.txtEmail);
        txtLink = findViewById(R.id.txtGitLink);
        pbSubmit = findViewById(R.id.pbSubmit);

        txtLink.setText(gitLink);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        mDialog = new Dialog(this);
        btnSubmit.setOnClickListener(btn->{
            mDialog.setContentView(R.layout.dialog_confirmation);
            ImageView btnCLose = mDialog.findViewById(R.id.btnClose);
            Button btnConfirm = mDialog.findViewById(R.id.btnConfirm);
            mDialog.show();
            btnCLose.setOnClickListener(btnCl-> mDialog.dismiss());
            btnConfirm.setOnClickListener(btnC->submitApp());
        });



    }

    private void submitApp() {
        pbSubmit.setVisibility(View.VISIBLE);
                Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(address)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SubmitApi submitApi = retrofit.create(SubmitApi.class);
        Call<Void> submitCall =submitApi.submit(
                txtEmail.getText().toString(),
                txtFname.getText().toString(),
                txtLname.getText().toString(),
                txtLink.getText().toString()
        );


        submitCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                pbSubmit.setVisibility(View.INVISIBLE);
                if(response.isSuccessful()) {
                    mDialog.setContentView(R.layout.dialog_success);
                    Toast.makeText(SubmitActivity.this,"Project Was Submitted",Toast.LENGTH_LONG).show();
                }
                else{
                    mDialog.setContentView(R.layout.dialog_error);
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                pbSubmit.setVisibility(View.INVISIBLE);
                Log.d("Response Message" , t.getMessage());
                mDialog.setContentView(R.layout.dialog_error);
            }
        });
    }
}