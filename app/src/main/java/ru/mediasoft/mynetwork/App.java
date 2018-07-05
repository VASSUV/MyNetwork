package ru.mediasoft.mynetwork;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    final public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://translate.yandex.net/api/v1.5/tr.json") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
            .build();

    final public TranslaterApi translaterApi= retrofit.create(TranslaterApi.class); //Создаем объект, при помощи которого будем выполнять запросы
}
