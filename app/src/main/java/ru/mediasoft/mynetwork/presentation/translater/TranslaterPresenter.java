package ru.mediasoft.mynetwork.presentation.translater;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import java.net.UnknownHostException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import ru.mediasoft.mynetwork.App;
import ru.mediasoft.mynetwork.data.net.TranslaterApi;
import ru.mediasoft.mynetwork.domain.dataclass.TranslateModel;
import ru.mediasoft.mynetwork.domain.ineractor.DetectInteractor;
import ru.mediasoft.mynetwork.domain.ineractor.LoadLangsInteractor;
import ru.mediasoft.mynetwork.domain.ineractor.TranslateInteractor;
import ru.mediasoft.mynetwork.other.MyTextWatcher;

public class TranslaterPresenter {
    private TranslaterApi translaterApi;
    private TranslaterView translaterView;
    private String text;
    private LoadLangsInteractor loadLangsInteractor = new LoadLangsInteractor(translaterApi);
    private DetectInteractor detectInteractor = new DetectInteractor(translaterApi);
    private TranslateInteractor translateInteractor = new TranslateInteractor(translaterApi);

    public void onCreate(App applicationContext, TranslaterView translaterView) {
        translaterApi = applicationContext.networkService.translaterApi;
        this.translaterView = translaterView;
    }

    public void onLoadLangsButtonClick(View view) {
        translaterView.showLoader();
        loadLangsInteractor.run(throwable -> {
            translaterView.hideLoader();
        }, languagesModel -> {
            translaterView.hideLoader();
            translaterView.setResultText(languagesModel.langs.toString());
        });
    }

    public void onDetectButtonClick(View view) {
        translaterView.showLoader();

        detectInteractor.run(text, (detectModel, throwable) -> {
            if (throwable == null) {
                translaterView.hideLoader();
                translaterView.setResultText(detectModel.lang);
            } else {
                translaterView.hideLoader();
                if (throwable instanceof UnknownHostException) {
                    translaterView.showErrorMessage("Нет интернета");
                }
            }
        });
    }

    public void onTranslateButtonClick(View view) {
        translaterView.showLoader();

        translateInteractor.run(text, new SingleObserver<TranslateModel>() {
            @Override
            public void onSubscribe(Disposable d) {   }

            @Override
            public void onSuccess(TranslateModel translateModel) {
                translaterView.hideLoader();
                translaterView.setResultText(translateModel.text.get(0));
            }

            @Override
            public void onError(Throwable e) {
                translaterView.hideLoader();
            }
        });

    }

    public TextWatcher getTextWatcher() {
        return new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text = charSequence.toString();
            }
        };
    }
}
