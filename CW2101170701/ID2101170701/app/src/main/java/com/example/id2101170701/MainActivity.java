package com.example.id2101170701;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView lowestScorerTextView;
    private TextView highestScorerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeButtons();
        initializeViews(); // Add this line to initialize the new TextView

        loadLowestScorer(); // Add this line to load and display the lowest scorer
        loadHighestScorer();
    }

    private void initializeButtons() {
        // Button to start a new game
        Button newGameButton = findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });

        // Button to view previous games
        Button viewGamesButton = findViewById(R.id.viewGamesButton);
        viewGamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPreviousGames();
            }
        });
    }

    private void initializeViews() {
        lowestScorerTextView = findViewById(R.id.lowestScorerTextView);
        highestScorerTextView = findViewById(R.id.highestScorerTextView);
    }

    private void loadLowestScorer() {
        GolfDataSource dataSource = new GolfDataSource(this);
        dataSource.open();

        Player lowestScorer = dataSource.getLowestScoringPlayer();

        if (lowestScorer != null) {
            String lowestScorerText = "Lowest Scorer: " +
                    lowestScorer.getName() + " - " + lowestScorer.getScore();
            lowestScorerTextView.setText(lowestScorerText);
        } else {
            lowestScorerTextView.setText("No data recorded");
        }

        dataSource.close();
    }

    private void loadHighestScorer() {
        GolfDataSource dataSource = new GolfDataSource(this);
        dataSource.open();

        Player highestScorer = dataSource.getHighestScoringPlayer();

        if (highestScorer != null) {
            String highestScorerText = "Highest Scorer: " +
                    highestScorer.getName() + " - " + highestScorer.getScore();
            highestScorerTextView.setText(highestScorerText);
        } else {
            highestScorerTextView.setText("No data recorded");
        }

        dataSource.close();
    }

    private void startNewGame() {
        Intent intent = new Intent(MainActivity.this, NewGameActivity.class);
        startActivity(intent);
    }

    private void viewPreviousGames() {
        Intent intent = new Intent(MainActivity.this, ViewGamesActivity.class);
        startActivity(intent);
    }

}
