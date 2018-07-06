package ru.mediasoft.mynetwork.presentation.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mediasoft.mynetwork.App;
import ru.mediasoft.mynetwork.R;
import ru.mediasoft.mynetwork.data.net.TranslaterApi;
import ru.mediasoft.mynetwork.domain.dataclass.DetectModel;


public class MainActivity extends AppCompatActivity {

    final String YANDEX_KEY = "trnsl.1.1.20180705T120817Z.d02e46cf7b18a375.49894a3fd1b91e8a5ad31175c82150c34744bf56";

    private EditText editText;
    private TextView resultTextView;
    private TranslaterApi translaterApi;
    private View loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        resultTextView = findViewById(R.id.resultTextView);
        loader = findViewById(R.id.loader);

        translaterApi = ((App) getApplicationContext()).networkService.translaterApi;
    }

    public void onLoadButtonClick(View view) {
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
                                    Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                        loader.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<DetectModel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        loader.setVisibility(View.GONE);
                    }
                });
    }

    public void onTranslateButtonClick(View view) {
    }
}
