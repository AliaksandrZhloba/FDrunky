package f.drunky.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import f.drunky.Entity.Drink;
import f.drunky.R;


public class SelectDrinkAdapter extends RecyclerView.Adapter<SelectDrinkAdapter.DrinkDetailsViewHolder> {
    private List<Drink> _drinks;

    public static class DrinkDetailsViewHolder extends RecyclerView.ViewHolder {
        private TextView _txtTitle;
        private ImageView _imgPicture;


        public DrinkDetailsViewHolder(View v) {
            super(v);
            _txtTitle = v.findViewById(R.id.txtTitle);
            _imgPicture = v.findViewById(R.id.imgPicture);
        }

        public void setDrink(Drink drink) {
            _txtTitle.setText(drink.getTitle());
            _imgPicture.setImageBitmap(drink.getImage());
        }
    }

    public SelectDrinkAdapter(List<Drink> drinks) {
        _drinks = drinks;
    }

    @Override
    public SelectDrinkAdapter.DrinkDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drink_details_item, parent, false);
        DrinkDetailsViewHolder vh = new DrinkDetailsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(DrinkDetailsViewHolder holder, int position) {
        holder.setDrink(_drinks.get(position));
    }

    @Override
    public int getItemCount() {
        return _drinks.size();
    }
}