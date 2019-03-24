package com.example.clash.mv1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class manual extends AppCompatActivity {

    private Button forward;
    private Button left;
    private Button right;
    private Button backward;
    private TextView result;

    String IP;
    int cur,last=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bundle bundle=getIntent().getExtras();
        IP=bundle.getString("ip");

        result=(TextView)findViewById(R.id.tvResult);
        forward=(Button)findViewById(R.id.btnForward);
        backward=(Button)findViewById(R.id.btnBackward);
        left=(Button)findViewById(R.id.btnLeft);
        right=(Button)findViewById(R.id.btnRight);




        forward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    last=cur;
                    cur=1;
                    if(cur!=last) {
                        result.setText("Sending...");
                        OkHttpClient client = new OkHttpClient();
                        String url = "http://" + IP + "/forward";
                        final Request request = new Request.Builder()
                                .url(url)
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                if (response.isSuccessful()) {
                                    final String myResponce = response.body().string();
                                    manual.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            result.setText(myResponce);
                                        }
                                    });
                                }
                            }
                        });

                    }

                }
                else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    last=cur;
                    cur=5;
                    if(cur!=last) {
                        result.setText("Sending...");
                        OkHttpClient client = new OkHttpClient();
                        String url = "http://" + IP + "/stop";
                        final Request request = new Request.Builder()
                                .url(url)
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                if (response.isSuccessful()) {
                                    final String myResponce = response.body().string();
                                    manual.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            result.setText(myResponce);
                                        }
                                    });
                                }
                            }
                        });

                    }

                }


                return true;
            }
        });

        backward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    last=cur;
                    cur=2;
                    if(cur!=last) {
                        result.setText("Sending...");
                        OkHttpClient client = new OkHttpClient();
                        String url = "http://" + IP + "/backward";
                        final Request request = new Request.Builder()
                                .url(url)
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                if (response.isSuccessful()) {
                                    final String myResponce = response.body().string();
                                    manual.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            result.setText(myResponce);
                                        }
                                    });
                                }
                            }
                        });

                    }

                }
                else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    last=cur;
                    cur=5;
                    if(cur!=last) {
                        result.setText("Sending...");
                        OkHttpClient client = new OkHttpClient();
                        String url = "http://" + IP + "/stop";
                        final Request request = new Request.Builder()
                                .url(url)
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                if (response.isSuccessful()) {
                                    final String myResponce = response.body().string();
                                    manual.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            result.setText(myResponce);
                                        }
                                    });
                                }
                            }
                        });

                    }

                }


                return true;
            }
        });


        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    last=cur;
                    cur=3;
                    if(cur!=last) {
                        result.setText("Sending...");
                        OkHttpClient client = new OkHttpClient();
                        String url = "http://" + IP + "/turnright";
                        final Request request = new Request.Builder()
                                .url(url)
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                if (response.isSuccessful()) {
                                    final String myResponce = response.body().string();
                                    manual.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            result.setText(myResponce);
                                        }
                                    });
                                }
                            }
                        });

                    }

                }
                else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    last=cur;
                    cur=5;
                    if(cur!=last) {
                        result.setText("Sending...");
                        OkHttpClient client = new OkHttpClient();
                        String url = "http://" + IP + "/stop";
                        final Request request = new Request.Builder()
                                .url(url)
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                if (response.isSuccessful()) {
                                    final String myResponce = response.body().string();
                                    manual.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            result.setText(myResponce);
                                        }
                                    });
                                }
                            }
                        });

                    }

                }


                return true;
            }
        });

        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    last=cur;
                    cur=4;
                    if(cur!=last) {
                        result.setText("Sending...");
                        OkHttpClient client = new OkHttpClient();
                        String url = "http://" + IP + "/turnleft";
                        final Request request = new Request.Builder()
                                .url(url)
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                if (response.isSuccessful()) {
                                    final String myResponce = response.body().string();
                                    manual.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            result.setText(myResponce);
                                        }
                                    });
                                }
                            }
                        });

                    }

                }
                else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    last=cur;
                    cur=5;
                    if(cur!=last) {
                        result.setText("Sending...");
                        OkHttpClient client = new OkHttpClient();
                        String url = "http://" + IP + "/stop";
                        final Request request = new Request.Builder()
                                .url(url)
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                if (response.isSuccessful()) {
                                    final String myResponce = response.body().string();
                                    manual.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            result.setText(myResponce);
                                        }
                                    });
                                }
                            }
                        });

                    }

                }


                return true;
            }
        });


    }


}
