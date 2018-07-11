package ru.mediasoft.mynetwork.presentation.main

import android.os.AsyncTask

import java.io.IOException

import ru.mediasoft.mynetwork.data.net.TranslaterApi
import ru.mediasoft.mynetwork.domain.dataclass.TranslateModel

internal class MyAsyncTask(private val translaterApi: TranslaterApi, private val translateTextCallBack: ResultTranslateTextCallBack) : AsyncTask<String, Void, TranslateModel>() {

    override fun doInBackground(vararg strings: String): TranslateModel? {
        try {
            return translaterApi.translate(strings[0], strings[1], strings[2], strings[3], strings[4]).execute().body()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    override fun onPostExecute(translateModel: TranslateModel) {
        translateTextCallBack.onResult(translateModel.text[0])
    }


    internal interface ResultTranslateTextCallBack {
        fun onResult(translatedText: String)
    }
}
