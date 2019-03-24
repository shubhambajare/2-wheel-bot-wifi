package com.example.clash.mv1;

import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class homeAuto extends AppCompatActivity {

    private Button port1;
    private Button port2;
    private Button port3;
    String IP;
    TextView result,timer;
    EditText th;
    EditText tm;
    EditText ts;
    TextToSpeech textToSpeech;
    int r,lastTime,total;
    String text;
    String tHour;
    String tMin;
    String tSec;
    String time;
    float counter=0;
    int h=0,m=0,s=0,totalmillis=0;
    boolean o=false,o1=true;
    CountDownTimer c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_auto);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            Bundle bundle=getIntent().getExtras();
            IP=bundle.getString("ip");

        port1=(Button)findViewById(R.id.btnPort1);
        port2=(Button)findViewById(R.id.btnPort2);
        port3=(Button)findViewById(R.id.btnPort3);
        result=(TextView)findViewById(R.id.tvResult);
        timer=(TextView)findViewById(R.id.tvtimer);
        th=(EditText)findViewById(R.id.etHour);
        tm=(EditText)findViewById(R.id.etMin);
        ts=(EditText)findViewById(R.id.etSec);

        textToSpeech=new TextToSpeech(homeAuto.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i==TextToSpeech.SUCCESS){
                    r=textToSpeech.setLanguage(Locale.UK);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Not supported to ur phone",Toast.LENGTH_SHORT).show();
                }

            }
        });



        port1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Sending...");
                OkHttpClient client=new OkHttpClient();
                String url="http://"+ IP +"/switch1";
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
                            homeAuto.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    result.setText(myResponce);

                                    if(r==TextToSpeech.LANG_MISSING_DATA || r==TextToSpeech.LANG_NOT_SUPPORTED)
                                    {
                                        Toast.makeText(getApplicationContext(),"Not supported to ur phone",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        text=myResponce;
                                        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                                    }


                                }
                            });
                        }
                    }
                });

            }


        });

        port2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Sending...");
                OkHttpClient client=new OkHttpClient();
                String url="http://"+ IP +"/switch2";
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
                            homeAuto.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    result.setText(myResponce);
                                    if(r==TextToSpeech.LANG_MISSING_DATA || r==TextToSpeech.LANG_NOT_SUPPORTED)
                                    {
                                        Toast.makeText(getApplicationContext(),"Not supported to ur phone",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        text=myResponce;
                                        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                                    }


                                }
                            });
                        }
                    }
                });
            }
        });

        port3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                tHour=th.getText().toString();

                if(tHour.matches("")){
                    tHour="0";
                }

                tMin=tm.getText().toString();

                if(tMin.matches("")){
                    tMin="0";
                }
                tSec=ts.getText().toString();

                if(tSec.matches("")){
                    tSec="0";
                }

                h=Integer.parseInt(tHour);
                m=Integer.parseInt(tMin);
                s=Integer.parseInt(tSec);

                total=(h*3600)+(m*60)+(s);


                result.setText("Sending...");
                OkHttpClient client=new OkHttpClient();
                String url;

                if(total==0 || o==true  ) {
                    url = "http://" + IP + "/switch3";
                        timer.setText("");
                }
                else
                    {
                    url = "http://" + IP + "/genericArgs?time=" + total;
                    totalmillis=total*1000;
                    counter=total-1;
                    c = new CountDownTimer(totalmillis,1_000){
                        @Override
                        public void onTick(long l) {
                            if(o==true) {
                                 timer.setText("Timer is on");
                            }else
                                timer.setText("");
                            if(o1==true) {
                                counter--;
                                o1=false;
                            }
                            else counter-=0.5;
                            if(o==false)
                                cancel();
                        }

                        @Override
                        public void onFinish() {
                            if(o==true) {
                                result.setText("Switch 3 is off");
                                timer.setText("");
                                if(r==TextToSpeech.LANG_MISSING_DATA || r==TextToSpeech.LANG_NOT_SUPPORTED)
                                {
                                    Toast.makeText(getApplicationContext(),"Not supported to ur phone",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    text="Switch 3 is off";
                                    textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                                }
                                o=false;
                            }
                        }

                    }.start();
                }
                lastTime=total;
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
                            homeAuto.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    result.setText(myResponce);
                                    if(r==TextToSpeech.LANG_MISSING_DATA || r==TextToSpeech.LANG_NOT_SUPPORTED)
                                    {
                                        Toast.makeText(getApplicationContext(),"Not supported to ur phone",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        text=myResponce;
                                        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                                    }
                                }
                            });
                        }
                    }
                });

                if(o==false)
                    o=true;
                else
                    o=false;

            }
        });
    }
    @Override
    protected void onDestroy() {
        OkHttpClient client=new OkHttpClient();
        String url="http://"+ IP +"/switchoff";
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
                    homeAuto.this.runOnUiThread(new Runnable() {
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
