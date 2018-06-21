package de.htwg.margogo.monstermaths;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import de.htwg.margogo.monstermaths.database.AppDatabase;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel1;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel10;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel11;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel12;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel13;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel14;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel15;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel16;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel17;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel18;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel19;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel2;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel20;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel3;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel4;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel5;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel6;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel7;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel8;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel9;

public class ScrollingActivity extends AppCompatActivity {

    ArrayList<DataModel> dataModels;
    ListView listView;
    private static CustomAdapter adapter;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name-2").build();

        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) getSupportActionBar().setTitle("MonsterMaths");


        listView = findViewById(R.id.list);

        dataModels = new ArrayList<>();

        updateList();

        adapter = new CustomAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataModel dataModel = dataModels.get(position);

                Intent intent = new Intent(ScrollingActivity.this, GameActivity.class);
                intent.putExtra("id", dataModel.id.toString());
                startActivity(intent);

            }
        });

    }

    private int getHighscore(int level) {

        Integer result = 0;

        //TODO: https://stackoverflow.com/questions/44309241/warning-this-asynctask-class-should-be-static-or-leaks-might-occur
        @SuppressLint("StaticFieldLeak") AsyncTask<Integer, Void, Integer> a = new AsyncTask<Integer, Void, Integer>() {

            @Override
            protected Integer doInBackground(Integer... integers) {
                return db.highscoreDao().getHighscoreLevelX(integers[0]);
            }

        };

        try {
            result = a.execute(level).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private void updateList() {
        dataModels.clear();

        DataHolderLevel1 dt1 = DataHolderLevel1.getInstance();
        DataModel dm1 = new DataModel(getString(R.string.Level1Name), dt1.getId(), false, getString(R.string.Level1Desc), getHighscore(1), dt1.getBadgeCheck());

        DataHolderLevel2 dt2 = DataHolderLevel2.getInstance();
        DataModel dm2 = new DataModel(getString(R.string.Level2Name), dt2.getId(), false, getString(R.string.Level2Desc), getHighscore(2), dt2.getBadgeCheck());

        DataHolderLevel3 dt3 = DataHolderLevel3.getInstance();
        DataModel dm3 = new DataModel(getString(R.string.Level3Name), dt3.getId(), false, getString(R.string.Level3Desc), getHighscore(3), dt3.getBadgeCheck());

        DataHolderLevel4 dt4 = DataHolderLevel4.getInstance();
        DataModel dm4 = new DataModel(getString(R.string.Level4Name), dt4.getId(), false, getString(R.string.Level4Desc), getHighscore(4), dt4.getBadgeCheck());

        DataHolderLevel5 dt5 = DataHolderLevel5.getInstance();
        DataModel dm5 = new DataModel(getString(R.string.Level5Name), dt5.getId(), false, getString(R.string.Level5Desc), getHighscore(5), dt5.getBadgeCheck());

        DataHolderLevel6 dt6 = DataHolderLevel6.getInstance();
        DataModel dm6 = new DataModel(getString(R.string.Level6Name), dt6.getId(), false, getString(R.string.Level6Desc), getHighscore(6), dt6.getBadgeCheck());

        DataHolderLevel7 dt7 = DataHolderLevel7.getInstance();
        DataModel dm7 = new DataModel(getString(R.string.Level7Name), dt7.getId(), false, getString(R.string.Level7Desc), getHighscore(7), dt7.getBadgeCheck());

        DataHolderLevel8 dt8 = DataHolderLevel8.getInstance();
        DataModel dm8 = new DataModel(getString(R.string.Level8Name), dt8.getId(), false, getString(R.string.Level8Desc), getHighscore(8), dt8.getBadgeCheck());

        DataHolderLevel9 dt9 = DataHolderLevel9.getInstance();
        DataModel dm9 = new DataModel(getString(R.string.Level9Name), dt9.getId(), false, getString(R.string.Level9Desc), getHighscore(9), dt9.getBadgeCheck());

        DataHolderLevel10 dt10 = DataHolderLevel10.getInstance();
        DataModel dm10 = new DataModel(getString(R.string.Level10Name), dt10.getId(), false, getString(R.string.Level10Desc), getHighscore(10), dt10.getBadgeCheck());

        DataHolderLevel11 dt11 = DataHolderLevel11.getInstance();
        DataModel dm11 = new DataModel(getString(R.string.Level11Name), dt11.getId(), false, getString(R.string.Level11Desc), getHighscore(11), dt11.getBadgeCheck());

        DataHolderLevel12 dt12 = DataHolderLevel12.getInstance();
        DataModel dm12 = new DataModel(getString(R.string.Level12Name), dt12.getId(), false, getString(R.string.Level12Desc), getHighscore(12), dt12.getBadgeCheck());

        DataHolderLevel13 dt13 = DataHolderLevel13.getInstance();
        DataModel dm13 = new DataModel(getString(R.string.Level13Name), dt13.getId(), false, getString(R.string.Level13Desc), getHighscore(13), dt13.getBadgeCheck());

        DataHolderLevel14 dt14 = DataHolderLevel14.getInstance();
        DataModel dm14 = new DataModel(getString(R.string.Level14Name), dt14.getId(), false, getString(R.string.Level14Desc), getHighscore(14), dt14.getBadgeCheck());

        DataHolderLevel15 dt15 = DataHolderLevel15.getInstance();
        DataModel dm15 = new DataModel(getString(R.string.Level15Name), dt15.getId(), false, getString(R.string.Level15Desc), getHighscore(15), dt15.getBadgeCheck());

        DataHolderLevel16 dt16 = DataHolderLevel16.getInstance();
        DataModel dm16 = new DataModel(getString(R.string.Level16Name), dt16.getId(), false, getString(R.string.Level16Desc), getHighscore(16), dt16.getBadgeCheck());

        DataHolderLevel17 dt17 = DataHolderLevel17.getInstance();
        DataModel dm17 = new DataModel(getString(R.string.Level17Name), dt17.getId(), false, getString(R.string.Level17Desc), getHighscore(17), dt17.getBadgeCheck());

        DataHolderLevel18 dt18 = DataHolderLevel18.getInstance();
        DataModel dm18 = new DataModel(getString(R.string.Level18Name), dt18.getId(), false, getString(R.string.Level18Desc), getHighscore(18), dt18.getBadgeCheck());

        DataHolderLevel19 dt19 = DataHolderLevel19.getInstance();
        DataModel dm19 = new DataModel(getString(R.string.Level19Name), dt19.getId(), false, getString(R.string.Level19Desc), getHighscore(19), dt19.getBadgeCheck());

        DataHolderLevel20 dt20 = DataHolderLevel20.getInstance();
        DataModel dm20 = new DataModel(getString(R.string.Level20Name), dt20.getId(), false, getString(R.string.Level20Desc), getHighscore(20), dt20.getBadgeCheck());

        dataModels.add(dm1);
        dataModels.add(dm2);
        dataModels.add(dm3);
        dataModels.add(dm4);
        dataModels.add(dm5);
        dataModels.add(dm6);
        dataModels.add(dm7);
        dataModels.add(dm8);
        dataModels.add(dm9);
        dataModels.add(dm10);
        dataModels.add(dm11);
        dataModels.add(dm12);
        dataModels.add(dm13);
        dataModels.add(dm14);
        dataModels.add(dm15);
        dataModels.add(dm16);
        dataModels.add(dm17);
        dataModels.add(dm18);
        dataModels.add(dm19);
        dataModels.add(dm20);


    }

    @Override
    public void onResume() {
        super.onResume();
        updateList();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.menuShare:

                shareGame();
                return true;

            case R.id.ShareLevel:
                Toast.makeText(this, "Berühre ein Level lang, um dies zu teilen",
                        Toast.LENGTH_LONG).show();
                return true;

            case R.id.About:
                Intent intent = new Intent(ScrollingActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }

    private void shareGame() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "\nProbiere diese tolle App aus\n\n";
        shareBody = shareBody + "https://play.google.com/store/apps/details?id=de.htwg.margogo.monstermath \n\n";
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "MonsterMaths");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "MonsterMaths empfehlen via"));
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
