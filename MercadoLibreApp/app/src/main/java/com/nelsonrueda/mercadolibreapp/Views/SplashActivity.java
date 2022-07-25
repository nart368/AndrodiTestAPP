package com.nelsonrueda.mercadolibreapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.nelsonrueda.mercadolibreapp.R;

/**
 * Activity para emular un splash con previa visualizalizacion de logo
 */
public class SplashActivity extends AppCompatActivity {

    final String TAG = "SplashActivity";
    final int seconds = 5;
    final int milisegundos = seconds * 1000;
    final int delay = 2;
    ProgressBar progressBarControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        progressBarControl = findViewById(R.id.splash_progress);
        startAnimation();
        progressBarControl.setMax(maximumProgress());
        continueNextView();
    }

    private void startAnimation(){
        try{
            Animation zoomInAnimantion = AnimationUtils.loadAnimation(this, R.anim.zoom_forward_in);
            progressBarControl.startAnimation(zoomInAnimantion);
        }catch (Exception ex){
            Log.e(TAG,String.format("%s - Excepcion: %s","startAnimation",ex.getMessage()));
        }
    }

    private void continueNextView(){
        try
        {
            new CountDownTimer(milisegundos,1000){

                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    Intent mainActivity = new Intent(SplashActivity.this, CountriesActivity.class);
                    startActivity(mainActivity);
                    overridePendingTransition(R.anim.zoom_forward_in,R.anim.zoom_forward_out);
                    finish();
                }
            }.start();
        }
        catch (Exception ex){
            Log.e(TAG,String.format("%s - Excepcion: %s","continueNextView",ex.getMessage()));
        }

    }

    private int maximumProgress(){
        return seconds - delay;
    }
}