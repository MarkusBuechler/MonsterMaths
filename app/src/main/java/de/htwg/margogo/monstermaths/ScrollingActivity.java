package de.htwg.margogo.monstermaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

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

        DataHolderLevel1 dt1 = DataHolderLevel1.getInstance();
        DataModel dm1 = new DataModel(dt1.getName(), 1, false, "1eee", (double) dt1.getTime(), Badge.Silver);

        DataHolderLevel2 dt2 = DataHolderLevel2.getInstance();
        DataModel dm2 = new DataModel(dt2.getName(), 2, false, "2er", (double) dt1.getTime(), Badge.Gold);

        dataModels.add(dm1);
        dataModels.add(dm2);

        /*
        dataModels.add(new DataModel("Level 1", 1, false,"Basic Introduction to the game concept", 10.0 , Badge.Gold));
        dataModels.add(new DataModel("Level 2", 2, false,"Introduction to Addition", 100.0 , Badge.Silver));
        dataModels.add(new DataModel("Level 3", 3, false,"Addition 2", 0.0 , Badge.Bronze));
        dataModels.add(new DataModel("Level 4", 4, false,"Addition 3", 0.0 , Badge.Bronze));
        dataModels.add(new DataModel("Level 5", 5, true,"Introduction to Subtraction", 0.0 , Badge.Gold));
        dataModels.add(new DataModel("Level 6", 1, true,"Subtraction 2", 0.0 , Badge.Gold));
        dataModels.add(new DataModel("Level 7", 2, true,"Subtraction 3", 0.0 , Badge.Silver));
        dataModels.add(new DataModel("Level 8", 3, true,"Introduction to Multiplication", 0.0 , Badge.Bronze));
        dataModels.add(new DataModel("Level 9", 4, true,"Multiplication 2", 0.0 , Badge.Bronze));
        dataModels.add(new DataModel("Level 10", 5, true,"Introduction to Division", 0.0 , Badge.Gold));
        */


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

    /** Needs method refresh !! Only updates data when restart activity. **/

    /**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

     **/

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
