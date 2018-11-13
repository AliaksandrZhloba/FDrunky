package f.drunky.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import f.drunky.R;

/**
 * Created by AZhloba on 9/27/2017.
 */

public abstract class DrinkAutoCompleteAdapter extends BaseAdapter implements Filterable {
    private static final int MAX_RESULTS = 10;

    private final Context mContext;
    private List<String> mResults;


    public DrinkAutoCompleteAdapter(Context context) {
        mContext = context;
        mResults = new ArrayList<String>();
        mResults.addAll(getSearchDrinkHints(""));
    }

    public abstract ArrayList<String> getSearchDrinkHints(String input);


    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public String getItem(int index) {
        return mResults.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.drink_dropdown_item, parent, false);
        }
        String hint = getItem(position);
        ((TextView) convertView.findViewById(R.id.txtTitle)).setText(hint);

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    List<String> hints = getSearchDrinkHints(constraint.toString());
                    // Assign the data to the FilterResults
                    filterResults.values = hints;
                    filterResults.count = hints.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    mResults = (List<String>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }};

        return filter;
    }
}