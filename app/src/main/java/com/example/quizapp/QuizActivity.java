package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicInteger;

public class QuizActivity extends AppCompatActivity {

    private int questionIndex = 0;
    private int questionCount;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        String difficulty = getIntent().getStringExtra("difficulty");
        assert difficulty != null;
        setupDifficultyLevel(difficulty);

        displayQuestion();

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> checkAnswer());
    }

    private void setupDifficultyLevel(String difficulty) {
        // ใช้สำหรับเก็บเวลาของแต่ละระดับความยาก
        AtomicInteger timeLimit = new AtomicInteger(10);  // ค่าเริ่มต้น 10 วินาทีต่อคำถาม

        switch (difficulty) {
            case "easy":
                questionCount = 3;
                break;
            case "medium":
                questionCount = 4;
                timeLimit.set(15);  // 15 วินาทีต่อคำถามสำหรับระดับ medium
                break;
            case "hard":
                questionCount = 5;
                timeLimit.set(5);  // 5 วินาทีต่อคำถามสำหรับระดับ hard
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + difficulty);
        }
    }

    private void displayQuestion() {
        TextView questionText = findViewById(R.id.question_text);
        RadioButton option1 = findViewById(R.id.option_1);
        RadioButton option2 = findViewById(R.id.option_2);
        RadioButton option3 = findViewById(R.id.option_3);

        String[] questions = {
                "What is the capital of France?",
                "What is the sum of 2 + 2?",
                "Which planet is known as the Red Planet?"
        };
        String[][] options = {
                {"Paris", "London", "Berlin"},
                {"3", "4", "5"},
                {"Mars", "Venus", "Earth"}
        };

        if (questionIndex < questions.length) {
            questionText.setText(questions[questionIndex]);
            option1.setText(options[questionIndex][0]);
            option2.setText(options[questionIndex][1]);
            option3.setText(options[questionIndex][2]);
        } else {
            endQuiz();
        }
    }

    public void checkAnswer() {
        RadioGroup answerGroup = findViewById(R.id.answer_group);
        int selectedId = answerGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {
            RadioButton selectedAnswer = findViewById(selectedId);

            if (questionIndex == 0 && selectedAnswer.getText().equals("Paris")) {
                score++;
            } else if (questionIndex == 1 && selectedAnswer.getText().equals("4")) {
                score++;
            } else if (questionIndex == 2 && selectedAnswer.getText().equals("Mars")) {
                score++;
            }
        }

        questionIndex++;

        if (questionIndex < questionCount) {
            displayQuestion();
        } else {
            endQuiz();
        }
    }

    private void endQuiz() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("questionCount", questionCount);
        startActivity(intent);
    }
}
