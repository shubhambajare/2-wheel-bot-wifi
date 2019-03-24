package com.example.clash.mv1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class speechControl extends AppCompatActivity {
private Button stop;
private TextView result1; private TextView show;
private String IP;
private Button search;
String Deta1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_control);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            Bundle bundle=getIntent().getExtras();
            IP=bundle.getString("ip");
            stop=(Button)findViewById(R.id.btnStop);
            result1=(TextView)findViewById(R.id.tvResult);
            show=(TextView)findViewById(R.id.tvShow);
            search=(Button)findViewById(R.id.btnSearch);


         search.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                 intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                 intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                 if(intent.resolveActivity(getPackageManager())!=null){
                     startActivityForResult(intent,10);
                 }
             }
         });


    stop.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           result1.setText("Sending...");
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
                       speechControl.this.runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               result1.setText(myResponce);
                           }
                       });
                   }
               }
           });
       }
   });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK && data !=null){
                    ArrayList<String> result= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    show.setText(result.get(0));


                    if(result.get(0).equalsIgnoreCase("stop") ||
                            result.get(0).equalsIgnoreCase("start")||
                            result.get(0).equalsIgnoreCase("go")||
                            result.get(0).equalsIgnoreCase("forward")||
                            result.get(0).equalsIgnoreCase("backward")||
                            result.get(0).equalsIgnoreCase("back")||
                            result.get(0).equalsIgnoreCase("right")||
                            result.get(0).equalsIgnoreCase("turn right")||
                            result.get(0).equalsIgnoreCase("left")||
                            result.get(0).equalsIgnoreCase("turn left")) {

                        result1.setText("Sending...");
                        OkHttpClient client=new OkHttpClient();



                        if(result.get(0).equalsIgnoreCase("stop")){
                            Deta1="/stop";
                        }
                        else if(result.get(0).equalsIgnoreCase("start") ||result.get(0).equalsIgnoreCase("go") ||result.get(0).equalsIgnoreCase("forward") ){
                            Deta1="/forward";
                        }
                        else if(result.get(0).equalsIgnoreCase("backward") ||result.get(0).equalsIgnoreCase("back") ){
                            Deta1="/backward";
                        }
                        else if(result.get(0).equalsIgnoreCase("left") ||result.get(0).equalsIgnoreCase("turn left")  ){
                            Deta1="/turnleft";
                        }
                        else if(result.get(0).equalsIgnoreCase("right") ||result.get(0).equalsIgnoreCase("turn right")  ){
                            Deta1="/turnright";
                        }

                        String url="http://"+ IP +Deta1;
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
                                    speechControl.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            result1.setText(myResponce);
                                        }
                                    });
                                }
                            }
                        });
                    }
                    else{
                        result1.setText("No such command");
                    }

                }
                break;
        }

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
                    speechControl.this.runOnUiThread(new Runnable() {
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
