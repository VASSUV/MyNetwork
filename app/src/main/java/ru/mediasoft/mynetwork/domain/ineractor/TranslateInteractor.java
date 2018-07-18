package ru.mediasoft.mynetwork.domain.ineractor;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.mediasoft.mynetwork.data.net.TranslaterApi;
import ru.mediasoft.mynetwork.domain.dataclass.TranslateModel;

public class TranslateInteractor {
    final String YANDEX_KEY = "trnsl.1.1.20180705T120817Z.d02e46cf7b18a375.49894a3fd1b91e8a5ad31175c82150c34744bf56";
    private TranslaterApi translaterApi;

    public TranslateInteractor(TranslaterApi translaterApi) {

        this.translaterApi = translaterApi;
    }

    public void run(String text, SingleObserver<TranslateModel> subscriber) {
        translaterApi
                .translate(YANDEX_KEY, text, "en", "plain", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
