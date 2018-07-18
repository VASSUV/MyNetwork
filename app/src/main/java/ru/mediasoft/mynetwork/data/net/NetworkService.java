package ru.mediasoft.mynetwork.data.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    final private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(TranslaterApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    //Создаем объект, при помощи которого будем выполнять запросы
    final public TranslaterApi translaterApi = retrofit.create(TranslaterApi.class);


    // TODO: 06.07.2018 Добавить свой OkHttpClient
}
