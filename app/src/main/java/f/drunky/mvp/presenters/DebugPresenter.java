package f.drunky.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import f.drunky.mvp.views.DebugView;

@InjectViewState
public class DebugPresenter extends MvpPresenter<DebugView> {
    @Override
    protected void onFirstViewAttach() {
        getViewState().showDebugInfo();
    }
}
