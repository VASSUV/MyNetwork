package ru.mediasoft.mynetwork.presentation.main;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.BiConsumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mediasoft.mynetwork.App;
import ru.mediasoft.mynetwork.R;
import ru.mediasoft.mynetwork.Test;
import ru.mediasoft.mynetwork.data.net.TranslaterApi;
import ru.mediasoft.mynetwork.domain.dataclass.DetectModel;
import ru.mediasoft.mynetwork.domain.dataclass.LanguagesModel;
import ru.mediasoft.mynetwork.domain.dataclass.TranslateModel;


public class MainActivity extends AppCompatActivity {

    final String YANDEX_KEY = "trnsl.1.1.20180705T120817Z.d02e46cf7b18a375.49894a3fd1b91e8a5ad31175c82150c34744bf56";

    private EditText editText;
    private TextView resultTextView;
    private View loader;

    MyAsyncTask myAsyncTask;

    private TranslaterApi translaterApi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translaterApi = ((App) getApplicationContext()).networkService.translaterApi;

        resultTextView = findViewById(R.id.resultTextView);
        loader = findViewById(R.id.loader);
        editText = findViewById(R.id.editText);

    }

    public void onLoadLangsButtonClick(View view) {
        Call<LanguagesModel> languagesModelCall = translaterApi.getLangs("https://translate.yandex.net/api/v1.5/tr.json/getLangs?key=trnsl.1.1.20180705T120817Z.d02e46cf7b18a375.49894a3fd1b91e8a5ad31175c82150c34744bf56&ui=ru");


        loader.setVisibility(View.VISIBLE);
        languagesModelCall.enqueue(new Callback<LanguagesModel>() {
            @Override
            public void onResponse(Call<LanguagesModel> call, Response<LanguagesModel> response) {
                if (response.isSuccessful()) {
                    resultTextView.setText(response.body().langs.toString());
                } else {
                    showErrorMessage(response.message());
                }

                loader.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<LanguagesModel> call, Throwable t) {
                if (t instanceof UnknownHostException) {
                    showErrorMessage("Нет доступа к интернету");
                } else {
                    showErrorMessage("Произошла ошибка");
                }
                loader.setVisibility(View.GONE);
            }
        });
    }

    private void showErrorMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public void onDetectButtonClick(View view) {
        loader.setVisibility(View.VISIBLE);
        translaterApi.detectLang(YANDEX_KEY, editText.getText().toString(), "")
                .enqueue(new Callback<DetectModel>() {
                    @Override
                    public void onResponse(Call<DetectModel> call, Response<DetectModel> response) {
                        switch (response.code()) {
                            case 200:
                                resultTextView.setText(response.body().lang);
                                break;
                            default:
                                resultTextView.setText("");

                                try {
                                    showErrorMessage(response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                        loader.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<DetectModel> call, Throwable t) {
                        showErrorMessage(t.getMessage());
                        loader.setVisibility(View.GONE);
                    }
                });
    }

    public void onTranslateButtonClick(View view) {
        loader.setVisibility(View.VISIBLE);

        myAsyncTask = new MyAsyncTask(translaterApi, new MyAsyncTask.ResultTranslateTextCallBack() {
            @Override
            public void onResult(String translatedText) {
                loader.setVisibility(View.GONE);
                resultTextView.setText(translatedText);
            }
        });

        myAsyncTask.execute(YANDEX_KEY, editText.getText().toString(), "en", "plain", "");
    }

}
