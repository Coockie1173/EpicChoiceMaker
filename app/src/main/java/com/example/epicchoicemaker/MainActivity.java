package com.example.epicchoicemaker;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*tv = findViewById(R.id.TitleCard);
        Shader TextShader = new LinearGradient(0,0,20,20, new int[]{Color.GREEN, Color.BLUE}, new float[]{0,1}, Shader.TileMode.CLAMP);
        tv.getPaint().setShader(TextShader);*/

        getSupportActionBar().hide();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public void SettingsPress(View view)
    {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
        
    }

    public void ChoiceThingPress(View view)
    {
        Intent intent = new Intent(MainActivity.this, ChoiceMakerActivity.class);
        startActivity(intent);
    }

    public void PastChoicesButton(View view)
    {
        Intent intent = new Intent(MainActivity.this, PastChoices.class);
        startActivity(intent);
    }
}