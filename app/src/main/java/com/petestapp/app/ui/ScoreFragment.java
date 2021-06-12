package com.petestapp.app.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petestapp.app.R;
import com.petestapp.app.base.BaseFragment;
import com.petestapp.app.databinding.FragmentScoreBinding;
import com.petestapp.app.viewmodels.QuizViewModel;

public class ScoreFragment extends BaseFragment {

    private FragmentScoreBinding binding;
    private QuizViewModel quizViewModel;

    public ScoreFragment() {
        // Required empty public constructor
    }

    public static ScoreFragment newInstance() {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quizViewModel = getViewModelOfActivity().get(QuizViewModel.class);
        binding.tvScore.setText(String.format("Your score is %s", quizViewModel.getScore()));
    }
}