package com.petestapp.app.base;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.petestapp.app.constants.EventCode;

public class BaseActivity extends AppCompatActivity {

    /**
     * This method must be called by Every activity to bind view model for basic communication
     * @param baseViewModel {@link BaseViewModel} object
     */
    protected void attachViewModel(BaseViewModel baseViewModel){
        if (baseViewModel == null) return;
        baseViewModel.getLiveEvent().observe(this, viewEvent -> {
            switch (viewEvent.getEventCode()){
                case EventCode.BaseEvent.SHOW_ERROR:
                case EventCode.BaseEvent.SHOW_MESSAGE:
                    Toast.makeText(this, viewEvent.getMessageIfAny(), Toast.LENGTH_SHORT).show();
                    break;
                case EventCode.BaseEvent.SHOW_LOADER:
                    Toast.makeText(this, "Showing loader", Toast.LENGTH_SHORT).show();
                    break;
                case EventCode.BaseEvent.HIDE_LOADER:
                    Toast.makeText(this, "Hiding loader", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    protected ViewModelProvider getViewModel(){
        return new ViewModelProvider(this);
    }
}
