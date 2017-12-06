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

import f.drunky.Entity.Drink;
import f.drunky.R;

/**
 * Created by AZhloba on 9/27/2017.
 */

public class DrinkAutoCompleteAdapter extends BaseAdapter implements Filterable {
    private static final int MAX_RESULTS = 10;

    private List<Drink> _drinks;
    private List<String> _categories;

    private final Context mContext;
    private List<String> mResults;


    public DrinkAutoCompleteAdapter(Context context, List<String> categories, List<Drink> drinks) {
        mContext = context;
        mResults = new ArrayList<String>();
        mResults.addAll(categories);

        _categories = categories;
        _drinks = drinks;
    }


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
                    List<String> hints = findDrinks(constraint.toString());
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


    private List<String> findDrinks(String input) {
        if (input.length() == 0)
            return _categories;

        ArrayList<String> result = new ArrayList<String>();
        for (String category:_categories) {
            if (category.toUpperCase().contains(input.toUpperCase()))
                result.add(category);
        }

        if (result.size() > 0) {
            return result;
        }

        for (Drink drink:_drinks) {
            if (drink.getTitle().toUpperCase().contains(input.toUpperCase()))
                result.add(drink.getTitle());
        }

        return result;
    }
}