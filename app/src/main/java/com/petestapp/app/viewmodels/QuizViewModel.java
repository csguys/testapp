package com.petestapp.app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.petestapp.app.base.BaseApplication;
import com.petestapp.app.base.BaseViewModel;
import com.petestapp.app.model.QuizQuestion;
import com.petestapp.app.util.AppUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizViewModel extends BaseViewModel {

    private final String[] ALPHABETS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"
            , "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static final int KEYBOARD_LENGTH = 18;

    private ArrayList<QuizQuestion> quizQuestions;
    private MutableLiveData<QuizQuestion> questionStream = new MutableLiveData<>();
    private int questionIndex = -1;
    private int[] score;


    @Override
    public void init() {
        try {
            String json = AppUtils.loadJSONFromAsset(BaseApplication.getAppContext()
                    , "data.json");
            Type type = new TypeToken<ArrayList<QuizQuestion>>(){}.getType();
            quizQuestions = new Gson().fromJson(json, type);
            score = new int[quizQuestions.size()];
        } catch (IOException e) {
            e.printStackTrace();
            showError("Something went wrong");
        }
    }

    public LiveData<QuizQuestion> getQuestionStream() {
        return questionStream;
    }

    public void getNextQuestion(){
        if (questionIndex == quizQuestions.size()){
            //todo
        }
        questionIndex++;
        questionStream.postValue(quizQuestions.get(questionIndex));
    }

    public boolean compareAnswer(String answer){
        boolean result = answer != null && answer.equals(quizQuestions
                .get(questionIndex).getLogoName());
        if (result){
            //todo calculate score
        }
        return result;
    }

    public List<String> getKeyBoardChars(){
        List<String> strList = Arrays.asList(ALPHABETS);
        Collections.shuffle(strList);
        String answerKey = quizQuestions.get(questionIndex).getLogoName();
        List<String> finalText = new ArrayList<>(KEYBOARD_LENGTH);
        for (char c : answerKey.toCharArray()) {
            finalText.add(String.valueOf(c));
        }
        finalText.addAll(strList.subList(0, KEYBOARD_LENGTH - answerKey.length()));
        Collections.shuffle(finalText);
        return finalText;
    }
}
