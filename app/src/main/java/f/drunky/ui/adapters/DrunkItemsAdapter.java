package f.drunky.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import f.drunky.Entity.DrunkItem;
import f.drunky.Helpers.TimeDiffHelper;
import f.drunky.R;

public class DrunkItemsAdapter extends RecyclerView.Adapter<DrunkItemsAdapter.DrunkItemViewHolder> {

    private List<DrunkItem> _drunkItems;

    public static class DrunkItemViewHolder extends RecyclerView.ViewHolder {
        private TextView _txtTitle;
        private TextView _txtTime;
        private ImageView _imgPicture;
        private String _titleStringformat;


        public DrunkItemViewHolder(View v) {
            super(v);

            _titleStringformat = v.getResources().getString(R.string.DrunkItemTitle);

            _txtTitle = v.findViewById(R.id.txtTitle);
            _txtTime = v.findViewById(R.id.txtTime);
            _imgPicture = v.findViewById(R.id.imgPicture);
        }

        public void setDrink(DrunkItem drink) {
            String title = String.format(_titleStringformat, drink.getVolume(), drink.getDrink().getTitle());
            String passedTime = TimeDiffHelper.getPassedTime(drink.getUseTime());

            _txtTitle.setText(title);
            _txtTime.setText(passedTime);
            _imgPicture.setImageBitmap(drink.getDrink().getBottlePicture());
        }
    }

    public DrunkItemsAdapter(List<DrunkItem> drunkItems) {
        _drunkItems = drunkItems;
    }

    @Override
    public DrunkItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drunk_item, parent, false);
        DrunkItemsAdapter.DrunkItemViewHolder vh = new DrunkItemsAdapter.DrunkItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(DrunkItemViewHolder holder, int position) {
        holder.setDrink(_drunkItems.get(position));
    }

    @Override
    public int getItemCount() {
        return _drunkItems.size();
    }
}
