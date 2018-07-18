package ru.mediasoft.mynetwork.presentation.translater;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpFragment;

import ru.mediasoft.mynetwork.App;
import ru.mediasoft.mynetwork.R;

public class TranslaterFragment extends MvpFragment implements TranslaterView {

    TranslaterPresenter presenter = new TranslaterPresenter();

    private EditText editText;
    private TextView resultTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate((App) getActivity().getApplicationContext(), this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_translater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        resultTextView = view.findViewById(R.id.resultTextView);
        editText = view.findViewById(R.id.editText);

        view.findViewById(R.id.buttonLoadLangs).setOnClickListener(presenter::onLoadLangsButtonClick);
        view.findViewById(R.id.buttonDetectLang).setOnClickListener(presenter::onDetectButtonClick);
        view.findViewById(R.id.buttonTranslate).setOnClickListener(presenter::onTranslateButtonClick);

        editText.addTextChangedListener(presenter.getTextWatcher());
    }

    @Override
    public void hideLoader() {
        getActivity().findViewById(R.id.loader).setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setResultText(String result) {
        resultTextView.setText(result);
    }

    @Override
    public void showLoader() {
        getActivity().findViewById(R.id.loader).setVisibility(View.VISIBLE);
    }
}
