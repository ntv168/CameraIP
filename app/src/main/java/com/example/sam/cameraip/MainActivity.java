package com.example.sam.cameraip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sam.cameraip.facetracker.FaceTrackerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startCamera(View view) {
        startActivity(new Intent(this, FaceTrackerActivity.class));
    }
}
