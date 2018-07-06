package ru.mediasoft.mynetwork;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mediasoft.mynetwork.data.net.NetworkService;
import ru.mediasoft.mynetwork.data.net.TranslaterApi;

public class App extends Application {

    final public NetworkService networkService = new NetworkService();



    // TODO: 06.07.2018 вынести создание и получение метода в отдельный метод
}
