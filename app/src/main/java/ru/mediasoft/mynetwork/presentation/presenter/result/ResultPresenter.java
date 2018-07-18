package ru.mediasoft.mynetwork.presentation.presenter.result;


import ru.mediasoft.mynetwork.presentation.view.result.ResultView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class ResultPresenter extends MvpPresenter<ResultView> {
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showMessage("Нет интернета");
    }
}
