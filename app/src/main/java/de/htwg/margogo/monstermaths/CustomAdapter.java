package de.htwg.margogo.monstermaths;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener{

    private ArrayList<DataModel> dataSet;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView score_text;
        TextView score_value;
        TextView description;
        ImageView badge;
    }

    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    // TODO Optional disable locked levels.

    @Override
    public void onClick(View v) {

        int position = (Integer) v.getTag();
        Object object = getItem (position);
        DataModel dataModel = (DataModel) object;

        // overwrite click listener for badge: show highscore instead.
        switch (v.getId())
        {
            case R.id.bagde:

                String text = "";

                if (dataModel.getPersonal_highscore() == 0) {
                    text = "No highscore yet!";
                } else {
                    text = "Highscore for " + dataModel.getName() + " is " + dataModel.getPersonal_highscore()+ " sec";
                }

                Snackbar.make(v, text, Snackbar.LENGTH_SHORT)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.score_text = convertView.findViewById(R.id.score_text);
            viewHolder.score_value = convertView.findViewById(R.id.score_value);
            viewHolder.description = convertView.findViewById(R.id.description);
            viewHolder.badge = convertView.findViewById(R.id.bagde);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.name.setText(dataModel.getName());
        viewHolder.score_text.setText("Score: ");

        viewHolder.score_value.setText(dataModel.getPersonal_highscore() == 0 ? "-" : dataModel.getPersonal_highscore().toString());
        viewHolder.description.setText(dataModel.getDescription());

        updateMedal(dataModel, viewHolder);
        viewHolder.badge.setOnClickListener(this);
        viewHolder.badge.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }

    /**
     * Comment: Java doesn't support switch casing on enums..
     * @param dataModel
     * @param viewHolder
     */
    private void updateMedal(DataModel dataModel, ViewHolder viewHolder) {


        if (dataModel.getPersonal_highscore() < dataModel.getBadgeCheck().getBronze()) {
            viewHolder.badge.setImageResource(R.drawable.medal_bronze_128);
        }
        if (dataModel.getPersonal_highscore() < dataModel.getBadgeCheck().getSilver()) {
            viewHolder.badge.setImageResource(R.drawable.medal_silver_128);
        }
        if (dataModel.getPersonal_highscore() < dataModel.getBadgeCheck().getGold()) {
            viewHolder.badge.setImageResource(R.drawable.medal_gold_128);
        }

        // default is no medal
        if (dataModel.getPersonal_highscore() == 0) {
            viewHolder.badge.setImageResource(0);
        }
    }
}