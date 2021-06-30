package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;


public class greekFragment extends Fragment {

    private Random random;

    private static final int ENGLISH = 0;
    private static final int LATIN = 1;
    private static final int GREEK = 2;

    private static final String PIPE = "\\|";
    private Question question;

    private TextView questionNumber, questionText;
    private RadioGroup radioAnswers;
    private Button submitButton, finishButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View containerView = inflater.inflate(R.layout.fragment_greek, container, false);

        random = new Random();
        questionNumber = containerView.findViewById(R.id.questionNumber);
        questionText = containerView.findViewById(R.id.questionText);
        radioAnswers = containerView.findViewById(R.id.radioAnswers);
        submitButton = containerView.findViewById(R.id.submitButton);
        finishButton = containerView.findViewById(R.id.finishButton);

        radioAnswers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                submitButton.setEnabled(true);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //capture value from the radioGroup and check for correct answer, if correct, increment correct
                //answers else increment the incorrect answers.
                //go to next question
                submit(containerView);


            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to results screen.
                Navigation.findNavController(view).navigate(R.id.action_greekFragment_to_resultGreekFragment);
            }
        });

        fireQuestion();

        return containerView;
    }

    private void fireQuestion(){
        question = getQuestion();
        populateUserInterface();

    }

    private void addRadioButton(RadioGroup group, String text){
        RadioButton button = new RadioButton(this.getActivity());
        button.setText(text);
        button.setTextColor(Color.WHITE);
        button.setButtonDrawable(android.R.drawable.btn_radio);
        group.addView(button);
    }

    private String[] getRandomWord(){
        String[] array = getResources().getStringArray(R.array.classic_words);
        int index = random.nextInt(array.length);
        return array[index].split(PIPE);

    }

    private Question getQuestion() {
        //generate corr answer
        String[] strAnswers = getRandomWord();
        String englishWord = strAnswers[ENGLISH];
        Question question = new Question(strAnswers[ENGLISH], strAnswers[LATIN], strAnswers[GREEK]);

        //generates 5 wrong answers
        while (question.getWrongAnswers().size() < 5) {
            String[] greekWord = getRandomWord();

            //if the one we picked is equal to the answer OR
            //if is not from the same region as the answer OR
            //if we already picked this one
            while (greekWord[ENGLISH].equals(englishWord) ||
                    question.getWrongAnswers().contains(strAnswers[GREEK])) {
                //then we need pick another one
                greekWord = getRandomWord();
            }

            question.addWrongAnswer(greekWord[GREEK]);
        }
        return question;
    }

    private void submit(View view) {
        Button checkedButton = view.findViewById(radioAnswers.getCheckedRadioButtonId());

        String guess = checkedButton.getText().toString();
        if (guess.equals(question.getGreek())) {
            QuizTracker.getInstance().setCorrectAnswerNum(QuizTracker.getInstance().getCorrectAnswerNum() + 1);

        } else {
            QuizTracker.getInstance().setIncorrectAnswerNum(QuizTracker.getInstance().getIncorrectAnswerNum() + 1);
        }

        QuizTracker.getInstance().setQuestionNum(QuizTracker.getInstance().getQuestionNum() + 1);
        fireQuestion();

    }

    private void populateUserInterface() {
        //take care of button first
        submitButton.setEnabled(false);

        //populate the QuestionNumber textview
        String questionNumberText = getResources().getString(R.string.questionNumberText);
        int number = QuizTracker.getInstance().getQuestionNum();
        questionNumber.setText(String.format(questionNumberText, number));

        //set question text
        questionText.setText(question.getQuestionText());

        //will generate a number 0-4 inclusive
        int randomPosition = random.nextInt(5);
        int counter = 0;
        radioAnswers.removeAllViews();
        //for each of the 5 wrong answers
        for (String wrongAnswer : question.getWrongAnswers()) {
            if (counter == randomPosition) {
                //insert the cor answer
                addRadioButton(radioAnswers, question.getGreek());
            } else {
                addRadioButton(radioAnswers, wrongAnswer);
            }
            counter++;
        }
    }



    }