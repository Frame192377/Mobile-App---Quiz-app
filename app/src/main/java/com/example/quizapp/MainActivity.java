package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button easyButton = findViewById(R.id.easyButton);
        Button mediumButton = findViewById(R.id.mediumButton);
        Button hardButton = findViewById(R.id.hardButton);

        easyButton.setOnClickListener(v -> startQuiz("easy"));
        mediumButton.setOnClickListener(v -> startQuiz("medium"));
        hardButton.setOnClickListener(v -> startQuiz("hard"));
    }

    private void startQuiz(String difficulty) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("difficulty", difficulty);
        startActivity(intent);
    }
}
