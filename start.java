package com.example.clash.mv1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class start extends AppCompatActivity {

    String IP;
    Button stop;
    private TextView result;
    int a=0,b=1;

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroScopeEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bundle bundle = getIntent().getExtras();
        IP = bundle.getString("ip");
        result=(TextView) findViewById(R.id.tvResult);

        stop = (Button) findViewById(R.id.btnStop);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        if (gyroscopeSensor == null) {
            Toast.makeText(this, "DEVICE HAS NO GYROSCOPE", Toast.LENGTH_SHORT).show();
            finish();
        }

        gyroScopeEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                b=a;

                double x=sensorEvent.values[1];
                double y=sensorEvent.values[0];

                double xangle = (x)/(Math.PI/180);
                double yangle = (y)/(Math.PI/180);



                if(xangle>100 && yangle<100 && yangle>-100){
                a=3;
                }
                else if(xangle<-100&& yangle<100 && yangle>-100){
                a=4;
                }
                if(yangle>100&& xangle<100 && xangle>-100){
                    a=2;
                }
                else if(yangle<-100&& xangle<100 && xangle>-100){
                    a=1;
                }


                if (a==1 && b!=1) {
                    result.setText("Sending...");
                    OkHttpClient client=new OkHttpClient();
                    String url="http://"+ IP +"/forward";
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
                                start.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        result.setText(myResponce);
                                    }
                                });
                            }
                        }
                    });


                } else if (a==2 && b!=2) {
                    result.setText("Sending...");
                    OkHttpClient client=new OkHttpClient();
                    String url="http://"+ IP +"/backward";
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
                                start.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        result.setText(myResponce);
                                    }
                                });
                            }
                        }
                    });

                } else if (a==3&& b!=3) {
                    result.setText("Sending...");
                    OkHttpClient client=new OkHttpClient();
                    String url="http://"+ IP +"/turnright";
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
                                start.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        result.setText(myResponce);
                                    }
                                });
                            }
                        }
                    });
                } else if (a==4&& b!=4) {
                    result.setText("Sending...");
                    OkHttpClient client=new OkHttpClient();
                    String url="http://"+ IP +"/turnleft";
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
                                start.this.runOnUiThread(new Runnable() {
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

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                            start.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                }
                            });
                        }
                    }
                });

                finish();
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroScopeEventListener,gyroscopeSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(gyroScopeEventListener);
    }


}

