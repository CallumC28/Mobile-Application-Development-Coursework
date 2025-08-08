package com.example.id2101170701;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class GameActivity extends AppCompatActivity {

    private TextView currentPlayerTextView;
    private TextView currentScoreTextView;
    private Button makeShotButton;
    private Button nextPlayerButton;
    private Button prevPlayerButton;

    private Button saveGameButton;

    private int currentHole = 1; // Track the current hole number
    private int[] holeScores;    // Array to store scores for each hole

    private int currentScore = 0; // Placeholder for the current player's score
    private int currentPlayerIndex = 0; // Placeholder for the current player's index

    private Button nextHoleButton;
    private String[] playerNames; // Placeholder for player names
    private Map<String, Integer> playerScores; // Store scores for each player

    private GolfDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        currentPlayerTextView = findViewById(R.id.currentPlayerTextView);
        currentScoreTextView = findViewById(R.id.currentScoreTextView);
        makeShotButton = findViewById(R.id.makeShotButton);
        nextPlayerButton = findViewById(R.id.nextPlayerButton);
        prevPlayerButton = findViewById(R.id.prevPlayerButton);
        saveGameButton = findViewById(R.id.saveGameButton);

        playerNames = getIntent().getStringArrayListExtra("playerNames").toArray(new String[0]);
        playerScores = new HashMap<>();
        dataSource = new GolfDataSource(this); // Initialize the data source

        updateUI();


        makeShotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentScore += 1; // Increment the current score for the current player
                currentScoreTextView.setText(getString(R.string.current_score, currentScore)); // Update the current score in the UI
            }
        });


        nextPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveScore();
                currentScore = 0; // Reset current score
                nextPlayer(); // Move to the next player
            }
        });

        prevPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveScore();
                currentScore = 0; // Reset current score
                prevPlayer(); // Move to the previous player
            }
        });

        saveGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGame();
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveScore() {
        String currentPlayerName = playerNames[currentPlayerIndex];
        playerScores.put(currentPlayerName, playerScores.getOrDefault(currentPlayerName, 0) + currentScore);
    }

    private void saveGame() {
        dataSource.open();
        for (String playerName : playerNames) {
            int playerScore = playerScores.getOrDefault(playerName, 0);
            dataSource.insertPlayer(playerName, playerScore);
        }
        dataSource.close();
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % playerNames.length;
        updateUI(); // Update UI to show the next player
    }

    private void prevPlayer() {
        currentPlayerIndex = (currentPlayerIndex - 1 + playerNames.length) % playerNames.length;
        updateUI(); // Update UI to show the previous player
    }

    private void updateUI() {
        String currentPlayerName = playerNames[currentPlayerIndex];
        int playerScore = playerScores.getOrDefault(currentPlayerName, 0);

        Log.d("GameActivity", "updateUI: Current Player = " + currentPlayerName);
        Log.d("GameActivity", "updateUI: Current Score = " + playerScore);

        currentPlayerTextView.setText(getString(R.string.current_player, currentPlayerName));
        currentScoreTextView.setText(getString(R.string.current_score, playerScore));
    }

}
