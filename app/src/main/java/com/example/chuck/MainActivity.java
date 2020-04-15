package com.example.chuck;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private TextView mValue;
    private Button mButton;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mValue = findViewById(R.id.txtFact);
        mButton = findViewById(R.id.btnFact);
        mImage = findViewById(R.id.chuckView);
        mImage.setImageResource(getResources().getIdentifier("chuck", "drawable", "com.example.chuck"));
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getFact();
            }
        });
    }

    public void getFact(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.chucknorris.io").addConverterFactory(GsonConverterFactory.create()).build();
        FactService service = retrofit.create(FactService.class);
        Call<Fact> factCall = service.getFacts();
        factCall.enqueue(new Callback<Fact>() {
            @Override
            public void onResponse(Call<Fact> call, Response<Fact> response) {
                Log.d(TAG, "OnResponse: SUCCESS");
                mValue.setText(response.body().getValue());
            }

            @Override
            public void onFailure(Call<Fact> call, Throwable t) {
                Log.d(TAG, "onFailure: FAILURE");

            }
        });

    }

}
