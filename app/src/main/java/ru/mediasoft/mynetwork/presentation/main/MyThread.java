package ru.mediasoft.mynetwork.presentation.main;

import android.app.Activity;

import java.io.IOException;

import ru.mediasoft.mynetwork.data.net.TranslaterApi;
import ru.mediasoft.mynetwork.domain.dataclass.TranslateModel;

class MyThread extends Thread {

    private TranslaterApi translaterApi;
    private String yandexKey;
    private String text;
    private ResultTranslateTextCallBack resultTranslateTextCallBack;
    private Activity context;

    public MyThread(Activity context, TranslaterApi translaterApi, String yandexKey, String text, ResultTranslateTextCallBack resultTranslateTextCallBack)  {
        this.context = context;
        this.translaterApi = translaterApi;
        this.yandexKey = yandexKey;
        this.text = text;
        this.resultTranslateTextCallBack = resultTranslateTextCallBack;
    }


    @Override
    public void run() {
        try {
            final TranslateModel translateModel = translaterApi.translate(yandexKey, text, "en", "plain", "").execute().body();
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    resultTranslateTextCallBack.onResult(translateModel.text.get(0));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    interface ResultTranslateTextCallBack {
        void onResult(String translatedText);
    }
}
