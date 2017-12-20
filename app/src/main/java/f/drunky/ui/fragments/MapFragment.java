package f.drunky.ui.fragments;


import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import f.drunky.Navigation.ChainFragment;
import f.drunky.R;
import f.drunky.mvp.presenters.MapPresenter;
import f.drunky.mvp.views.MapView;
import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.map.MapEvent;
import ru.yandex.yandexmapkit.map.OnMapListener;


public class MapFragment extends ChainFragment implements MapView {

    @InjectPresenter
    MapPresenter presenter;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        ru.yandex.yandexmapkit.MapView mapView = getView().findViewById(R.id.map);
        mapView.showFindMeButton(true);
        mapView.showZoomButtons(true);
        mapView.showJamsButton(false);
    }
}
