package de.htwg.margogo.monstermaths;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class MainActivity extends Activity {

    Button btnStart;
    Animation animation = new AlphaAnimation(1, 0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animation = new AlphaAnimation(1, 0);
        animation.setDuration(2000);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);

        btnStart = findViewById(R.id.btnStartGame);
        btnStart.startAnimation(animation);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.clearAnimation();
                Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
                startActivity(intent);

            }
        });

    }

    protected void onRestart() {
        super.onRestart();
        btnStart.startAnimation(animation);
    }

}
