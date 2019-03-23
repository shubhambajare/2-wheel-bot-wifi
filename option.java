package com.example.clash.mv1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class option extends AppCompatActivity {

    private Button manualControl;
    private Button lineTracing;
    private Button gyro;
    private Button speechControl;
    private Button homeAuto;
    private Button battery;
    String IP;
    String bStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bundle bundle=getIntent().getExtras();
        IP=bundle.getString("ip");

        manualControl=(Button)findViewById(R.id.btnManual);
        lineTracing=(Button)findViewById(R.id.btnLineTracing);
        gyro=(Button)findViewById(R.id.btnGyro);
        speechControl=(Button)findViewById(R.id.btnSpeechControl);
        homeAuto=(Button)findViewById(R.id.btnHomeAuto);
        battery=(Button)findViewById(R.id.btnBattery);


        manualControl.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            OkHttpClient client=new OkHttpClient();
            String url="http://"+ IP +"/battery";
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

                        option.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String d=myResponce;
                                if(d.matches("Battery is charged")||d.matches("Battery Low")){

                                    bStatus=myResponce;
                                    p();
                                    Intent intent=new Intent(option.this,manual.class);
                                    intent.putExtra("ip",IP);
                                    startActivity(intent);


                                }
                                else{
                                bStatus=myResponce;
                                p();
                                }
                            }
                        });
                    }
                }
            });


        }
    });

    lineTracing.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            OkHttpClient client=new OkHttpClient();
            String url="http://"+ IP +"/battery";
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
                        option.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(myResponce.matches("Battery is charged")||myResponce.matches("Battery Low")){
                                    bStatus=myResponce;
                                    p();
                                    Intent intent=new Intent(option.this,lineTracing.class);
                                    intent.putExtra("ip",IP);
                                    startActivity(intent);
                                }
                                else{
                                    bStatus=myResponce;
                                    p();
                                }
                            }
                        });
                    }
                }
            });

        }
    });

    gyro.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            OkHttpClient client=new OkHttpClient();
            String url="http://"+ IP +"/battery";
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
                        option.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(myResponce.matches("Battery is charged")||myResponce.matches("Battery Low")){


                                    bStatus=myResponce;
                                    p();
                                    Intent intent=new Intent(option.this,gyro.class);
                                    intent.putExtra("ip",IP);
                                    startActivity(intent);
                                }
                                else{
                                    bStatus=myResponce;
                                    p();
                                }

                            }
                        });
                    }
                }
            });
        }
    });

    homeAuto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            OkHttpClient client=new OkHttpClient();
            String url="http://"+ IP +"/battery";
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
                        option.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(myResponce.matches("Battery is charged")|| myResponce.matches("Battery Low")){

                                   home();
                                }
                                else{

                                    Intent intent=new Intent(option.this,homeAuto.class);
                                    intent.putExtra("ip",IP);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                }
            });
        }
    });

    speechControl.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            OkHttpClient client=new OkHttpClient();
            String url="http://"+ IP +"/battery";
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
                        option.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(myResponce.matches("Battery is charged")||myResponce.matches("Battery Low")){


                                    bStatus=myResponce;
                                    p();
                                    Intent intent=new Intent(option.this,speechControl.class);
                                    intent.putExtra("ip",IP);
                                    startActivity(intent);
                                }
                                else{
                                    bStatus=myResponce;
                                    p();
                                }

                            }
                        });
                    }
                }
            });
        }
    });

    battery.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            OkHttpClient client=new OkHttpClient();
            String url="http://"+ IP +"/battery";
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
                        bStatus=myResponce;

                        option.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                    bStatus=myResponce;
                                    p();
                            }
                        });
                    }
                }
            });

        }
    });

    }


    void p(){
        Toast.makeText(this, bStatus, Toast.LENGTH_SHORT).show();
    }
    void home(){
        Toast.makeText(this, "Not connected to Home Automation", Toast.LENGTH_SHORT).show();
    }
}
