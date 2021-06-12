package com.petestapp.app.model;

public class ViewEvent {

    private int eventCode;
    private Object data;

    public ViewEvent(int eventCode) {
        this.eventCode = eventCode;
    }

    public ViewEvent(int eventCode, Object data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    public int getEventCode() {
        return eventCode;
    }

    public Object getData() {
        return data;
    }

    public String getMessageIfAny(){
        return data != null && data instanceof String ? (String) data : "";
    }
}
