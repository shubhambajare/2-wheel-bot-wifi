package com.example.clash.mv1;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class info extends AppCompatActivity {

    Button brochure;
    Button youtube;
    Button facebook;
    Button insta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


    brochure=(Button)findViewById(R.id.btnB);
    youtube=(Button)findViewById(R.id.btnYt);
    facebook=(Button)findViewById(R.id.btnFb);
    insta=(Button)findViewById(R.id.btnInsta);

    brochure.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=404+not+found&client=ubuntu&hs=1lU&channel=fs&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjh3JyG-N3cAhUMwI8KHQueA0wQ_AUICigB&biw=1301&bih=670#imgrc=wGld-A8Oq-c4eM:"));
            startActivity(intent);
        }
    });


    youtube.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCwPosr8EptrGNRphmLsSlVg"));
            startActivity(intent);
        }
    });

    facebook.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/meteorpune/?tsid=0.9782088033871599&source=result"));
            startActivity(intent);

        }
    });

    insta.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.instagram.com/teamrobotics_skncoe/"));
            startActivity(intent);
        }
    });
    }
}
