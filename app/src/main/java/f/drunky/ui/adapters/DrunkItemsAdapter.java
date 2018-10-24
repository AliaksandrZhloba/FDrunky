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

    public void remove(int swipedPosition) {
        _drunkItems.remove(swipedPosition);
    }

    public static class DrunkItemViewHolder extends RecyclerView.ViewHolder {
        private TextView _txtTitle;
        private TextView _txtTime;
        private ImageView _imgPicture;
        private String _titleStringFormat;


        public DrunkItemViewHolder(View v) {
            super(v);

            _titleStringFormat = v.getResources().getString(R.string.DrunkItemTitle);

            _txtTitle = v.findViewById(R.id.txtTitle);
            _txtTime = v.findViewById(R.id.txtTime);
            _imgPicture = v.findViewById(R.id.imgPicture);
        }

        public void setDrink(DrunkItem drink) {
            String title = String.format(_titleStringFormat, drink.getVolume(), drink.getDrink());
            String passedTime = TimeDiffHelper.getPassedTime(drink.getUseTime());

            _txtTitle.setText(title);
            _txtTime.setText(passedTime);
            _imgPicture.setImageBitmap(drink.getImage());
        }
    }

    public DrunkItemsAdapter(List<DrunkItem> drunkItems) {
        _drunkItems = drunkItems;
    }

    public void removeItem(int position) {
        _drunkItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, _drunkItems.size());
    }

    public void restoreItem(DrunkItem item, int position) {
        _drunkItems.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
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
        DrunkItem item = _drunkItems.get(position);
        holder.setDrink(item);
    }

    @Override
    public int getItemCount() {
        return _drunkItems.size();
    }
}
