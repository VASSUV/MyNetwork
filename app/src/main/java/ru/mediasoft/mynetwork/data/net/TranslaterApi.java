package ru.mediasoft.mynetwork.data.net;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import ru.mediasoft.mynetwork.domain.dataclass.DetectModel;
import ru.mediasoft.mynetwork.domain.dataclass.LanguagesModel;
import ru.mediasoft.mynetwork.domain.dataclass.TranslateModel;

public interface TranslaterApi {

    // https://translate.yandex.net/api/v1.5/tr.json/getLangs?ui=en&key=trnsl.1.1.20180705T120817Z.d02e46cf7b18a375.49894a3fd1b91e8a5ad31175c82150c34744bf56
    @GET
    Call<LanguagesModel> getLangs(@Url String url);

    // https://translate.yandex.net/api/v1.5/tr.json/detect?hint=&text=&key=trnsl.1.1.20180705T120817Z.d02e46cf7b18a375.49894a3fd1b91e8a5ad31175c82150c34744bf56
    @GET("detect")
    Call<DetectModel> detectLang(@Query("key") String key,
                                 @Query("text") String text,
                                 @Query("hint") String hint);

    // https://translate.yandex.net/api/v1.5/tr.json/translate?text=&lang=&format=&options=&key=trnsl.1.1.20180705T120817Z.d02e46cf7b18a375.49894a3fd1b91e8a5ad31175c82150c34744bf56
    @GET("translate")
    Call<TranslateModel> translate(@Query("key") String key,
                                   @Query("text") String text,
                                   @Query("lang") String lang,
                                   @Query("format") String format,
                                   @Query("options") String options);

    final String BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/";
}
