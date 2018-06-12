package de.htwg.margogo.monstermaths;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    // TODO Add a main menu with play, settings, highscores, exit etc..
    // Or different navigation system. settings could be extracted somewhere else.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Remove this soon. Create something like subway surfer -> tap to run -> tap to continue
        Button btnStartGame = findViewById(R.id.btnStartGame);
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                //startActivity(intent);

            }
        });

        Button btnBla = findViewById(R.id.btnBla);
        btnBla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
                startActivity(intent);

            }
        });

        Button btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create intent and start activity
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                //intent.putExtra("key", targetGoal);
                startActivity(intent);

            }
        });

    }

}
