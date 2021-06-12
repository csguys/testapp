package com.petestapp.app.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.petestapp.app.R;
import com.petestapp.app.adapters.AnswerAdapter;
import com.petestapp.app.adapters.KeyBoardAdapter;
import com.petestapp.app.base.BaseFragment;
import com.petestapp.app.databinding.FragmentQuestionBinding;
import com.petestapp.app.model.QuizQuestion;
import com.petestapp.app.viewmodels.QuizViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 */
public class QuestionFragment extends BaseFragment implements KeyBoardAdapter.KeyBoardContract
        , AnswerAdapter.AnswerContract{


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentQuestionBinding binding;

    private String mParam1;
    private String mParam2;
    private List<String> currentKeyBoard;
    private KeyBoardAdapter keyBoardAdapter;
    private AnswerAdapter answerAdapter;
    private QuizViewModel quizViewModel;
    private int answerCount;
    private List<String> answerTyped = new ArrayList<>();

    public QuestionFragment() {
    }

    public static QuestionFragment newInstance() {
        QuestionFragment fragment = new QuestionFragment();
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
        binding = FragmentQuestionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quizViewModel = getViewModelOfActivity().get(QuizViewModel.class);
        quizViewModel.getQuestionStream().observe(getViewLifecycleOwner(), this::setQuestion);
        quizViewModel.getNextQuestion();
    }

    /*
      Display answer key only when image is loaded to avoid false time increase
     */
    private void setQuestion(final QuizQuestion question){
        Glide.with(this)
                .load(question.getLogoUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable  GlideException e, Object model
                            , Target<Drawable> target, boolean isFirstResource) {
                        quizViewModel.showLoader();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model
                            , Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        quizViewModel.hideLoader();
                        onImageLoaded(question);
                        return false;
                    }
                })
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(binding.ivLogo);
    }

    private void onImageLoaded(QuizQuestion question){
        answerTyped.clear();
        answerTyped.addAll(Arrays.asList(new String[question.getLogoName().length()]));
        setKeyBoard(quizViewModel.getKeyBoardChars());
        if (answerAdapter != null){
            answerAdapter.notifyDataSetChanged();
            return;
        }
        answerAdapter = new AnswerAdapter(this);
        binding.rvAnswer.setLayoutManager(new GridLayoutManager(getContext()
                , 5));
        binding.rvAnswer.setAdapter(answerAdapter);
    }

    private void setKeyBoard(List<String> keys){
        if (currentKeyBoard != null && keyBoardAdapter != null){
            this.currentKeyBoard.clear();
            this.currentKeyBoard.addAll(keys);
            keyBoardAdapter.notifyDataSetChanged();
            return;
        }
        this.currentKeyBoard = keys;
        keyBoardAdapter = new KeyBoardAdapter(this);
        /*
          span count can be calculated dynamically
                or
          can use  integer form resource qualifiers based on screen width
         */
        binding.rvKeyBoard.setLayoutManager(new GridLayoutManager(getContext(), 9));
        binding.rvKeyBoard.setAdapter(keyBoardAdapter);
    }

    @Override
    public int getLength() {
        return currentKeyBoard == null ? 0 : currentKeyBoard.size();
    }

    @Override
    public String getChar(int position) {
        return currentKeyBoard.get(position);
    }

    @Override
    public void onKeyClicker(int position) {
        if (answerCount > answerTyped.size() - 1) {
            StringBuilder builder = new StringBuilder();
            for (String s : answerTyped) {
                builder.append(s);
            }
            quizViewModel.compareAnswer(builder.toString());
            return;
        }
        this.answerTyped.set(answerCount++, this.currentKeyBoard.get(position));
        answerAdapter.notifyDataSetChanged();
    }

    @Override
    public int getAnswerLength() {
        Log.i("Answer size", "Data = " + (answerTyped == null ? 0 : answerTyped.size()));
        return answerTyped == null ? 0 : answerTyped.size();
    }

    @Override
    public String getAnswerChar(int position) {
        return answerTyped.get(position);
    }
}