package com.petestapp.app.constants;

public interface EventCode {

    interface BaseEvent{
        int SHOW_LOADER = 1001;
        int HIDE_LOADER = 1002;
        int SHOW_ERROR = 1003;
        int SHOW_MESSAGE = 1004;
        // we can define basic event here which is needed my every view
    }
}
