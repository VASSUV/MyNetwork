package ru.mediasoft.mynetwork.presentation.main;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.mediasoft.mynetwork.R;
import ru.mediasoft.mynetwork.presentation.translater.TranslaterFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new TranslaterFragment())
                .addToBackStack(null)
                .commit();
    }

}
