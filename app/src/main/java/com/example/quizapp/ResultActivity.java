package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private int score;
    private int questionCount;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        score = getIntent().getIntExtra("score", 0);
        questionCount = getIntent().getIntExtra("questionCount", 0);

        sharedPreferences = getSharedPreferences("QuizStats", MODE_PRIVATE);
        updateStatistics();

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(v -> restartQuiz());
    }

    @SuppressLint("SetTextI18n")
    private void updateStatistics() {
        int highScore = sharedPreferences.getInt("highScore", 0);
        if (score > highScore) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("highScore", score);
            editor.apply();
        }

        TextView scoreView = findViewById(R.id.scoreView);
        scoreView.setText("Score: " + score + "/" + questionCount);

        TextView highScoreView = findViewById(R.id.highScoreView);
        highScoreView.setText("High Score: " + highScore);
    }

    private void restartQuiz() {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
