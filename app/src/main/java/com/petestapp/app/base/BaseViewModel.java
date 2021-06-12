package com.petestapp.app.base;

import androidx.lifecycle.ViewModel;

import com.petestapp.app.constants.EventCode;
import com.petestapp.app.model.ViewEvent;
import com.petestapp.app.util.SingleLiveEvent;

public abstract class BaseViewModel extends ViewModel implements BaseViewModelContract{

    private SingleLiveEvent<ViewEvent> liveEvent = new SingleLiveEvent<>();

    public SingleLiveEvent<ViewEvent> getLiveEvent() {
        return liveEvent;
    }

    protected abstract void init();

    @Override
    public void showLoader() {
        liveEvent.setValue(new ViewEvent(EventCode.BaseEvent.SHOW_LOADER));
    }

    @Override
    public void hideLoader() {
        liveEvent.setValue(new ViewEvent(EventCode.BaseEvent.HIDE_LOADER));
    }

    @Override
    public void showError(String message) {
        liveEvent.setValue(new ViewEvent(EventCode.BaseEvent.SHOW_ERROR, message));
    }

    @Override
    public void showMessage(String message) {
        liveEvent.setValue(new ViewEvent(EventCode.BaseEvent.SHOW_MESSAGE, message));
    }

    protected void sentViewEvent(final int eventCode){
        sentViewEvent(new ViewEvent(eventCode));
    }

    protected void sentViewEvent(final ViewEvent viewEvent){
        liveEvent.setValue(viewEvent);
    }
}
