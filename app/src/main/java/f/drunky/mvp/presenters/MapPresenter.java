package f.drunky.mvp.presenters;

import android.location.LocationManager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import f.drunky.mvp.views.MapView;

/**
 * Created by AZhloba on 12/9/2017.
 */

@InjectViewState
public class MapPresenter extends MvpPresenter<MapView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
   }
}
