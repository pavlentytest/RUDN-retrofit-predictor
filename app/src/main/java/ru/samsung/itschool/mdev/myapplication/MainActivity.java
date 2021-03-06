package ru.samsung.itschool.mdev.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://predictor.yandex.net/";
    public static final String KEY = "pdct.1.1.20210415T120704Z.ef4aec433f848b62.84f154e6713e4e640b65dd4a5ddb384fe8edb11f";
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                doRequest();
            }
        });
    }
    public void doRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        YandexAPI api = retrofit.create(YandexAPI.class);
        Call<Model> call = api.predictor(KEY,editText.getText().toString(),"en");
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                try {
                    if (response.code() == 200) {
                        String[] str = response.body().text;
                        textView.setText(str[0]);
                    }
                } catch (Exception e) {
                    Log.d("RRR",e.getMessage());
                }
            }
            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d("RRR",t.getMessage());
            }
        });

    }
}