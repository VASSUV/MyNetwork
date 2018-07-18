package ru.mediasoft.mynetwork.domain.ineractor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;
import ru.mediasoft.mynetwork.data.net.TranslaterApi;
import ru.mediasoft.mynetwork.domain.dataclass.DetectModel;

public class DetectInteractor {
    final String YANDEX_KEY = "trnsl.1.1.20180705T120817Z.d02e46cf7b18a375.49894a3fd1b91e8a5ad31175c82150c34744bf56";

    private TranslaterApi translaterApi;

    public DetectInteractor(TranslaterApi translaterApi) {
        this.translaterApi = translaterApi;
    }

    public void run(String text, BiConsumer<DetectModel, Throwable> detectModelThrowableBiConsumer) {
        translaterApi
                .detectLang(YANDEX_KEY, text, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(detectModelThrowableBiConsumer);
    }

    void unsubscribe() {

    }
}
