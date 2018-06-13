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
    Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(2000); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in

        btnStart = findViewById(R.id.btnStartGame);
        btnStart.startAnimation(animation); // not only on create
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
