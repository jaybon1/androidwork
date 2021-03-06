package com.jaybon.retrofitcallbacktest;

import android.icu.text.StringSearch;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private TextView tv;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        button = findViewById(R.id.button);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kr.api.riotgames.com/lol/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RiotService service = retrofit.create(RiotService.class);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Call<Summoner> call = service.getSummonerByName("hideonbush");

                new Thread(new Runnable() {
                    @SneakyThrows
                    @Override
                    public void run() {
                        Summoner summoner = call.execute().body();

                        Log.d(TAG, "onClick: "+summoner);

                    }
                }).start();





            }


//                Orianna.setRiotAPIKey("RGAPI-8f2ab161-b201-4d25-a846-17abf656e8e7");
//                Orianna.setDefaultRegion(Region.KOREA);


//                Summoner summoner = Orianna.summonerNamed("원모타임").get();

//                Log.d(TAG, "onClick: "+summoner.getName() + summoner.getLevel());


//
//                // 1단계
//                Call<Summoner> call = service.getSummonerByName("hideonbush");
//                call.enqueue(new Callback<Summoner>() {
//                    @Override
//                    public void onResponse(Call<Summoner> call, Response<Summoner> response) {
//
//                        if (!response.isSuccessful()) {
//                            tv.setText("Code: " + response.code());
//                            return;
//                        }
//
//                        Summoner summoner = response.body();
//                        Log.d(TAG, "onResponse: "+summoner.getSummonerLevel());
//
//                        // 2단계
//                        Call<Matchlist> call1 = service.getMatchListByAccountId(summoner.getAccountId());
//                        call1.enqueue(new Callback<Matchlist>() {
//                            @Override
//                            public void onResponse(Call<Matchlist> call, Response<Matchlist> response) {
//
//                                if (!response.isSuccessful()) {
//                                    tv.setText("Code: " + response.code());
//                                    return;
//                                }
//
//                                Matchlist matchlist = response.body();
//
//                                // 3단계
//                                Call<MatchSpec> call1 = service.getMatchSpecByMatchId(Long.toString(matchlist.getMatches().get(0).getGameId()));
//                                call1.enqueue(new Callback<MatchSpec>() {
//                                    @Override
//                                    public void onResponse(Call<MatchSpec> call, Response<MatchSpec> response) {
//
//                                        if (!response.isSuccessful()) {
//                                            tv.setText("Code: " + response.code());
//                                            return;
//                                        }
//
//                                        MatchSpec matchSpec = response.body();
//
//
//                                        tv.setText(matchSpec.getParticipantIdentities().get(1).getPlayer().summonerName);
//
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<MatchSpec> call, Throwable t) {
//
//                                        Log.d(TAG, "onFailure: " + t.getMessage());
//                                        tv.setText(t.getMessage());
//                                    }
//                                });
//
//
//
//                            }
//
//                            @Override
//                            public void onFailure(Call<Matchlist> call, Throwable t) {
//
//                                Log.d(TAG, "onFailure: " + t.getMessage());
//                                tv.setText(t.getMessage());
//                            }
//                        });
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<Summoner> call, Throwable t) {
//
//                        Log.d(TAG, "onFailure: " + t.getMessage());
//                        tv.setText(t.getMessage());
//                    }
//                });
//            }
//        });


        });
    }
}