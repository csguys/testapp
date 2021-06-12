package com.petestapp.app.base;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class BaseFragment extends Fragment {

    protected ViewModelProvider getViewModel(){
        return new ViewModelProvider(this);
    }

    protected ViewModelProvider getViewModelOfActivity(){
        if (getActivity() == null) {
            throw new IllegalStateException("Accessing view model" +
                    " provider before activity is attached");
        }
        return new ViewModelProvider(getActivity());
    }
}
