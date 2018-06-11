package de.htwg.margogo.monstermaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import de.htwg.margogo.monstermaths.levels.DataHolderLevel1;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel2;
import de.htwg.margogo.monstermaths.levels.DataHolderLevel3;

public class ScrollingActivity extends AppCompatActivity {

    ArrayList<DataModel> dataModels;
    ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private void updateList() {
        dataModels.clear();

        DataHolderLevel1 dt1 = DataHolderLevel1.getInstance();
        DataModel dm1 = new DataModel(dt1.getName(), dt1.getId(), false, dt1.getDescription(), dt1.getScore(), dt1.getBadge());

        DataHolderLevel2 dt2 = DataHolderLevel2.getInstance();
        DataModel dm2 = new DataModel(dt2.getName(), dt2.getId(), false, dt2.getDescription(), dt2.getScore(), dt2.getBadge());

        DataHolderLevel3 dt3 = DataHolderLevel3.getInstance();
        DataModel dm3 = new DataModel(dt3.getName(), dt3.getId(), false, dt3.getDescription(), dt3.getScore(), dt3.getBadge());

        dataModels.add(dm1);
        dataModels.add(dm2);
        dataModels.add(dm3);
    }

    /** Needs method refresh !! Only updates data when restart activity. **/

    /**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

     */

    // needs to be connected
    public void update() {
         updateList();
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
