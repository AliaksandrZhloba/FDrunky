package f.drunky.ui.adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import f.drunky.Entity.DrunkItem;
import f.drunky.Helpers.TimeDiffHelper;
import f.drunky.R;
import f.drunky.ui.fragments.StateFragment;

import static java.security.AccessController.getContext;

public class DrunkItemsAdapter extends RecyclerView.Adapter<DrunkItemsAdapter.DrunkItemViewHolder> {

    private final StateFragment.OnContextMenuItemClickListener _menuItemClickListener;
    private final List<DrunkItem> _drunkItems;

    class DrunkItemViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, PopupMenu.OnMenuItemClickListener  {
        private TextView _txtTitle;
        private TextView _txtTime;
        private ImageView _imgPicture;
        private String _titleStringFormat;


        public DrunkItemViewHolder(View v) {
            super(v);

            v.setOnCreateContextMenuListener(this);

            _titleStringFormat = v.getResources().getString(R.string.DrunkItemTitle);

            _txtTitle = v.findViewById(R.id.txtTitle);
            _txtTime = v.findViewById(R.id.txtTime);
            _imgPicture = v.findViewById(R.id.imgPicture);
        }

        public void setDrink(DrunkItem drink) {
            String title = String.format(_titleStringFormat, drink.getVolume(), drink.getDrink().getTitle());
            String passedTime = TimeDiffHelper.getPassedTime(drink.getUseTime());

            _txtTitle.setText(title);
            _txtTime.setText(passedTime);
            _imgPicture.setImageBitmap(drink.getDrink().getBottlePicture());
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            Context wrapper = new ContextThemeWrapper(view.getContext(), R.style.AlcoApp_DrunkItemContextMenuStyle);
            PopupMenu popup = new PopupMenu(wrapper, view);
            popup.getMenuInflater().inflate(R.menu.drunk_item_context_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(this);
            popup.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            DrunkItem drunkItem = _drunkItems.get(getAdapterPosition());
            _menuItemClickListener.onClicked(drunkItem, menuItem);
            return true;
        }
    }

    public DrunkItemsAdapter(List<DrunkItem> drunkItems, StateFragment.OnContextMenuItemClickListener listener) {
        _drunkItems = drunkItems;
        _menuItemClickListener = listener;
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
