package com.example.clash.mv1;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class lineTracing extends AppCompatActivity {

    private Button start;
    private Button stop;
    private TextView result;
    String IP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_tracing);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bundle bundle=getIntent().getExtras();
        IP=bundle.getString("ip");

        start=(Button)findViewById(R.id.btnStart);
        stop=(Button)findViewById(R.id.btnStop);
        result=(TextView)findViewById(R.id.tvResult);
        start.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            result.setText("Sending...");
            OkHttpClient client=new OkHttpClient();
            String url="http://"+ IP +"/line";
            final Request request=new Request.Builder()
                    .url(url)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    if(response.isSuccessful()){
                        final String myResponce=response.body().string();
                        lineTracing.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                result.setText(myResponce);
                            }
                        });
                    }
                }
            });
        }
    });

    stop.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            result.setText("Sending...");
            OkHttpClient client=new OkHttpClient();
            String url="http://"+ IP +"/stop";
            final Request request=new Request.Builder()
                    .url(url)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    if(response.isSuccessful()){
                        final String myResponce=response.body().string();
                        lineTracing.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                result.setText(myResponce);
                            }
                        });
                    }
                }
            });
        }
    });
    }
    @Override
    protected void onDestroy() {
        OkHttpClient client=new OkHttpClient();
        String url="http://"+ IP +"/stop";
        final Request request=new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(response.isSuccessful()){
                    final String myResponce=response.body().string();
                    lineTracing.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                }
            }
        });

        super.onDestroy();
    }
}
