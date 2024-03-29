package com.petestapp.app;

import android.os.Bundle;

import com.petestapp.app.base.BaseActivity;
import com.petestapp.app.constants.EventCode;
import com.petestapp.app.model.ViewEvent;
import com.petestapp.app.ui.QuestionFragment;
import com.petestapp.app.ui.ScoreFragment;
import com.petestapp.app.viewmodels.QuizViewModel;

public class MainActivity extends BaseActivity {

    private QuizViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = getViewModel().get(QuizViewModel.class);
        viewModel.init();
        attachViewModel(viewModel);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.viewContainer, QuestionFragment.newInstance())
                .commit();
    }

    @Override
    protected void handleViewEvent(ViewEvent viewEvent) {
        super.handleViewEvent(viewEvent);
        if (viewEvent.getEventCode() == EventCode.QuizView.SHOW_SCORE){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.viewContainer, ScoreFragment.newInstance())
                    .commit();
        }
    }
}