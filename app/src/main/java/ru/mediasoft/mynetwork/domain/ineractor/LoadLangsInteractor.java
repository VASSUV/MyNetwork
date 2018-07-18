package ru.mediasoft.mynetwork.domain.ineractor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ru.mediasoft.mynetwork.data.net.TranslaterApi;
import ru.mediasoft.mynetwork.domain.dataclass.LanguagesModel;

public class LoadLangsInteractor {
    private TranslaterApi translaterApi;

    public LoadLangsInteractor(TranslaterApi translaterApi) {
        this.translaterApi = translaterApi;
    }

    public void run(Consumer<Throwable> throwableConsumer, Consumer<LanguagesModel> languagesModelConsumer) {
        translaterApi
                .getLangs("https://translate.yandex.net/api/v1.5/tr.json/getLangs?key=trnsl.1.1.20180705T120817Z.d02e46cf7b18a375.49894a3fd1b91e8a5ad31175c82150c34744bf56&ui=ru")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwableConsumer)
                .subscribe(languagesModelConsumer);
    }
}
