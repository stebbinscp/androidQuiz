package com.example.myapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class WelcomeFragment extends Fragment {

    private EditText editName;
    private Button startButtonLatin;
    private Button startButtonGreek;
    private Button startButtonMixed;
    private Button exitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        editName = view.findViewById(R.id.editName);
        startButtonLatin = view.findViewById(R.id.buttonLatin);
        startButtonGreek = view.findViewById(R.id.buttonGreek);
        startButtonMixed = view.findViewById(R.id.buttonMixed);

        startButtonLatin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //get the value from the editText
                //assign the name to the singleton
                //navigate to the playing fragment
                String name = editName.getText().toString();
                QuizTracker.getInstance().start(name);
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_latinFragment);

            }

        });
        startButtonGreek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the value from the editText
                //assign the name to the singleton
                //navigate to the playing fragment
                String name = editName.getText().toString();
                QuizTracker.getInstance().start(name);
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_greekFragment);

            }
        });
        startButtonMixed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //get the value from the editText
                //assign the name to the singleton
                //navigate to the playing fragment
                String name = editName.getText().toString();
                QuizTracker.getInstance().start(name);
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_mixedFragment);

            }
        });

        exitButton = view.findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //exit
                System.exit(0);
            }
        });


        return view;
    }
}
