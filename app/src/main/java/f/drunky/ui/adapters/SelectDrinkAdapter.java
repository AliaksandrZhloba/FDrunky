package f.drunky.ui.adapters;

import android.os.Bundle;
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
    private static final double K_Fill  = 0.7;

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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drink_details_item, parent, false);
        DrinkDetailsViewHolder vh = new DrinkDetailsViewHolder(view);

        int width = parent.getWidth();
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = (int)(width * K_Fill);
        view.setLayoutParams(params);

        return new DrinkDetailsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DrinkDetailsViewHolder holder, int position) {
        holder.setDrink(_drinks.get(position));

        if (position == 0 || position == _drinks.size() - 1) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            double parentWidth = params.width / K_Fill;
            double space = parentWidth * ((1.0 - K_Fill) / 2.0);

            if (position == 0) {
                params.setMarginStart(5 + (int) space);
            } else {
                params.setMarginEnd(5 + (int) space);
            }

            holder.itemView.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return _drinks.size();
    }
}