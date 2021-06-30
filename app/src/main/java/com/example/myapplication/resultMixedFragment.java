package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;


public class resultMixedFragment extends Fragment {

    private Button anotherQuizButton, resetButton;
    private TextView resultHeader, correctText, incorrectText, scoreText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View containerView =  inflater.inflate(R.layout.fragment_result_mixed, container, false);


        anotherQuizButton = containerView.findViewById(R.id.anotherQuizButton);
        anotherQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizTracker.getInstance().again();
                Navigation.findNavController(containerView).navigate(R.id.action_resultMixedFragment_to_mixedFragment);
            }
        });
        resetButton = containerView.findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizTracker.getInstance().reset();
                Navigation.findNavController(containerView).navigate(R.id.action_resultMixedFragment_to_welcomeFragment);
            }
        });


        resultHeader = containerView.findViewById(R.id.resultHeader);
        String name = QuizTracker.getInstance().getName();
        resultHeader.setText(name + " your results are:");

        correctText = containerView.findViewById(R.id.correctText);
        int numCorrect = QuizTracker.getInstance().getCorrectAnswerNum();
        correctText.setText("Correct: " + numCorrect);

        incorrectText = containerView.findViewById(R.id.incorrectText);
        int numIncorrect = QuizTracker.getInstance().getIncorrectAnswerNum();
        incorrectText.setText("Incorrect: " + numIncorrect);

        DecimalFormat df2 = new DecimalFormat("#");
        scoreText = containerView.findViewById(R.id.scoreText);
        double score = (double) numCorrect / (numCorrect + numIncorrect) ;
        scoreText.setText("Score: " + df2.format(score * 100) + "%");

        return containerView;
    }
}