package de.htwg.margogo.monstermaths;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import de.htwg.margogo.monstermaths.levels.*;

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
        DataModel dm1 = new DataModel(dt1.getName(), dt1.getId(), false, dt1.getDescription(), getHighscore(1), dt1.getBadgeCheck());

        DataHolderLevel2 dt2 = DataHolderLevel2.getInstance();
        DataModel dm2 = new DataModel(dt2.getName(), dt2.getId(), false, dt2.getDescription(), getHighscore(2), dt2.getBadgeCheck());

        DataHolderLevel3 dt3 = DataHolderLevel3.getInstance();
        DataModel dm3 = new DataModel(dt3.getName(), dt3.getId(), false, dt3.getDescription(), getHighscore(3), dt3.getBadgeCheck());

        DataHolderLevel4 dt4 = DataHolderLevel4.getInstance();
        DataModel dm4 = new DataModel(dt4.getName(), dt4.getId(), false, dt4.getDescription(), getHighscore(4), dt4.getBadgeCheck());

        DataHolderLevel5 dt5 = DataHolderLevel5.getInstance();
        DataModel dm5 = new DataModel(dt5.getName(), dt5.getId(), false, dt5.getDescription(), getHighscore(5), dt5.getBadgeCheck());

        DataHolderLevel6 dt6 = DataHolderLevel6.getInstance();
        DataModel dm6 = new DataModel(dt6.getName(), dt6.getId(), false, dt6.getDescription(), getHighscore(6), dt6.getBadgeCheck());

        DataHolderLevel7 dt7 = DataHolderLevel7.getInstance();
        DataModel dm7 = new DataModel(dt7.getName(), dt7.getId(), false, dt7.getDescription(), getHighscore(7), dt7.getBadgeCheck());

        DataHolderLevel8 dt8 = DataHolderLevel8.getInstance();
        DataModel dm8 = new DataModel(dt8.getName(), dt8.getId(), false, dt8.getDescription(), getHighscore(8), dt8.getBadgeCheck());

        DataHolderLevel9 dt9 = DataHolderLevel9.getInstance();
        DataModel dm9 = new DataModel(dt9.getName(), dt9.getId(), false, dt9.getDescription(), getHighscore(9), dt9.getBadgeCheck());

        DataHolderLevel10 dt10 = DataHolderLevel10.getInstance();
        DataModel dm10 = new DataModel(dt10.getName(), dt10.getId(), false, dt10.getDescription(), getHighscore(10), dt10.getBadgeCheck());

        DataHolderLevel11 dt11 = DataHolderLevel11.getInstance();
        DataModel dm11 = new DataModel(dt11.getName(), dt11.getId(), false, dt11.getDescription(), getHighscore(11), dt11.getBadgeCheck());

        DataHolderLevel12 dt12 = DataHolderLevel12.getInstance();
        DataModel dm12 = new DataModel(dt12.getName(), dt12.getId(), false, dt12.getDescription(), getHighscore(12), dt12.getBadgeCheck());

        DataHolderLevel13 dt13 = DataHolderLevel13.getInstance();
        DataModel dm13 = new DataModel(dt13.getName(), dt13.getId(), false, dt13.getDescription(), getHighscore(13), dt13.getBadgeCheck());

        DataHolderLevel14 dt14 = DataHolderLevel14.getInstance();
        DataModel dm14 = new DataModel(dt14.getName(), dt14.getId(), false, dt14.getDescription(), getHighscore(14), dt14.getBadgeCheck());

        DataHolderLevel15 dt15 = DataHolderLevel15.getInstance();
        DataModel dm15 = new DataModel(dt15.getName(), dt15.getId(), false, dt15.getDescription(), getHighscore(15), dt15.getBadgeCheck());

        DataHolderLevel16 dt16 = DataHolderLevel16.getInstance();
        DataModel dm16 = new DataModel(dt16.getName(), dt16.getId(), false, dt16.getDescription(), getHighscore(16), dt16.getBadgeCheck());

        DataHolderLevel17 dt17 = DataHolderLevel17.getInstance();
        DataModel dm17 = new DataModel(dt17.getName(), dt17.getId(), false, dt17.getDescription(), getHighscore(17), dt17.getBadgeCheck());

        DataHolderLevel18 dt18 = DataHolderLevel18.getInstance();
        DataModel dm18 = new DataModel(dt18.getName(), dt18.getId(), false, dt18.getDescription(), getHighscore(18), dt18.getBadgeCheck());

        DataHolderLevel19 dt19 = DataHolderLevel19.getInstance();
        DataModel dm19 = new DataModel(dt19.getName(), dt19.getId(), false, dt19.getDescription(), getHighscore(19), dt19.getBadgeCheck());

        DataHolderLevel20 dt20 = DataHolderLevel20.getInstance();
        DataModel dm20 = new DataModel(dt20.getName(), dt20.getId(), false, dt20.getDescription(), getHighscore(20), dt20.getBadgeCheck());

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
