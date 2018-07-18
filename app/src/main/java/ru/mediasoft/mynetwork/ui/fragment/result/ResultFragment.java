package ru.mediasoft.mynetwork.ui.fragment.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mediasoft.mynetwork.presentation.view.result.ResultView;
import ru.mediasoft.mynetwork.presentation.presenter.result.ResultPresenter;

import ru.mediasoft.mynetwork.R;

import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class ResultFragment extends MvpFragment implements ResultView {
    public static final String TAG = "ResultFragment";

    @InjectPresenter
    ResultPresenter mResultPresenter;

    public static ResultFragment newInstance() {
        ResultFragment fragment = new ResultFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void showMessage(String message) {

    }
}
