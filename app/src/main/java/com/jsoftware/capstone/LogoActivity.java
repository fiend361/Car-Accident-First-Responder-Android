package com.jsoftware.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LogoActivity extends AppCompatActivity {

    Animation togo, toin, toout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        togo = AnimationUtils.loadAnimation(this,R.anim.togo);
        toin = AnimationUtils.loadAnimation(this,R.anim.toin);
        toout = AnimationUtils.loadAnimation(this,R.anim.toout);

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
            try{
                ImageView imageView = findViewById(R.id.logoImg1);
                imageView.startAnimation(togo);
                sleep(1500);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                Start();
            }
            }
        };
        thread.start();
    }

    private void Start() {
        Intent intent1 = new Intent(LogoActivity.this, DrawerActivity2.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.toout, R.anim.toin);
    }
}
