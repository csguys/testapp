package com.petestapp.app.base;

interface BaseViewModelContract {

    void showLoader();

    void hideLoader();

    void showError(String message);

    void showMessage(String message);
}
