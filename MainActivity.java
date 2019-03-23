package com.example.clash.mv1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public EditText ip;
    private Button done;
    public String IP;
    public TextView result;
    private String pre;
    int f=1;
    private ImageButton info;

    String reply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ip=(EditText)findViewById(R.id.etIp);
        done=(Button)findViewById(R.id.btDone);
        result=(TextView)findViewById(R.id.tvResult);
        info=(ImageButton)findViewById(R.id.btnInfo);

        get();
        ip.setText(pre);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, info.class);
                startActivity(intent);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                IP=ip.getText().toString();
                IP=IP.replace(" ","");

                set();

                result.setText("Connecting...");
                OkHttpClient client=new OkHttpClient();
                String url="http://"+ IP +"/ip";
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
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(myResponce.matches("connected"))
                                    result.setText(myResponce);
                                     reply=myResponce;
                                    f=0;
                                    Intent intent = new Intent(MainActivity.this, option.class);
                                    intent.putExtra("ip", IP);
                                    startActivity(intent);
                                }
                            });
                        }

                                             }
                });
                if(f==1){
                result.setText("Not connected to NODE-MCU");
                    //f=1;
                }

            }
        });

    }

    void set(){
        SharedPreferences sharedPreferences=getSharedPreferences("ip", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("ip",IP);
        editor.apply();
    }

    void get(){
        SharedPreferences sharedPreferences=getSharedPreferences("ip", Context.MODE_PRIVATE);
        pre=sharedPreferences.getString("ip","");

    }

    @Override
    protected void onResume() {
        f=1;
        super.onResume();
    }
}
