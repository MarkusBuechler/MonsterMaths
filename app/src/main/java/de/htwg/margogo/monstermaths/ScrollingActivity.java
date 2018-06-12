package de.htwg.margogo.monstermaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        DataHolderLevel4 dt4 = DataHolderLevel4.getInstance();
        DataModel dm4 = new DataModel(dt4.getName(), dt4.getId(), false, dt4.getDescription(), dt4.getScore(), dt4.getBadge());

        DataHolderLevel5 dt5 = DataHolderLevel5.getInstance();
        DataModel dm5 = new DataModel(dt5.getName(), dt5.getId(), false, dt5.getDescription(), dt5.getScore(), dt5.getBadge());

        DataHolderLevel6 dt6 = DataHolderLevel6.getInstance();
        DataModel dm6 = new DataModel(dt6.getName(), dt6.getId(), false, dt6.getDescription(), dt6.getScore(), dt6.getBadge());

        DataHolderLevel7 dt7 = DataHolderLevel7.getInstance();
        DataModel dm7 = new DataModel(dt7.getName(), dt7.getId(), false, dt7.getDescription(), dt7.getScore(), dt7.getBadge());

        DataHolderLevel8 dt8 = DataHolderLevel8.getInstance();
        DataModel dm8 = new DataModel(dt8.getName(), dt8.getId(), false, dt8.getDescription(), dt8.getScore(), dt8.getBadge());

        DataHolderLevel9 dt9 = DataHolderLevel9.getInstance();
        DataModel dm9 = new DataModel(dt9.getName(), dt9.getId(), false, dt9.getDescription(), dt9.getScore(), dt9.getBadge());

        DataHolderLevel10 dt10 = DataHolderLevel10.getInstance();
        DataModel dm10 = new DataModel(dt10.getName(), dt10.getId(), false, dt10.getDescription(), dt10.getScore(), dt10.getBadge());

        DataHolderLevel11 dt11 = DataHolderLevel11.getInstance();
        DataModel dm11 = new DataModel(dt11.getName(), dt11.getId(), false, dt11.getDescription(), dt11.getScore(), dt11.getBadge());

        DataHolderLevel12 dt12 = DataHolderLevel12.getInstance();
        DataModel dm12 = new DataModel(dt12.getName(), dt12.getId(), false, dt12.getDescription(), dt12.getScore(), dt12.getBadge());

        DataHolderLevel13 dt13 = DataHolderLevel13.getInstance();
        DataModel dm13 = new DataModel(dt13.getName(), dt13.getId(), false, dt13.getDescription(), dt13.getScore(), dt13.getBadge());

        DataHolderLevel14 dt14 = DataHolderLevel14.getInstance();
        DataModel dm14 = new DataModel(dt14.getName(), dt14.getId(), false, dt14.getDescription(), dt14.getScore(), dt14.getBadge());

        DataHolderLevel15 dt15 = DataHolderLevel15.getInstance();
        DataModel dm15 = new DataModel(dt15.getName(), dt15.getId(), false, dt15.getDescription(), dt15.getScore(), dt15.getBadge());

        DataHolderLevel16 dt16 = DataHolderLevel16.getInstance();
        DataModel dm16 = new DataModel(dt16.getName(), dt16.getId(), false, dt16.getDescription(), dt16.getScore(), dt16.getBadge());

        DataHolderLevel17 dt17 = DataHolderLevel17.getInstance();
        DataModel dm17 = new DataModel(dt17.getName(), dt17.getId(), false, dt17.getDescription(), dt17.getScore(), dt17.getBadge());

        DataHolderLevel18 dt18 = DataHolderLevel18.getInstance();
        DataModel dm18 = new DataModel(dt18.getName(), dt18.getId(), false, dt18.getDescription(), dt18.getScore(), dt18.getBadge());

        DataHolderLevel19 dt19 = DataHolderLevel19.getInstance();
        DataModel dm19 = new DataModel(dt19.getName(), dt19.getId(), false, dt19.getDescription(), dt19.getScore(), dt19.getBadge());


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
