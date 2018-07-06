package ru.mediasoft.mynetwork.data.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    final private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(TranslaterApi.BASE_URL)
            .client(new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build())
            .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
            .build();

    //Создаем объект, при помощи которого будем выполнять запросы
    final public TranslaterApi translaterApi = retrofit.create(TranslaterApi.class);

}
