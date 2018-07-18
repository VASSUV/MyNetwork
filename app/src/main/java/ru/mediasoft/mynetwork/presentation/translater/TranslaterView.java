package ru.mediasoft.mynetwork.presentation.translater;

interface TranslaterView {
    void showLoader();
    void hideLoader();
    void showErrorMessage(String message);
    void setResultText(String result);
}
