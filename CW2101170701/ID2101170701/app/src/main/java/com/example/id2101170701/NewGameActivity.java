package com.example.id2101170701;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewGameActivity extends AppCompatActivity {

    private EditText[] playerNamesEditTexts;
    private Spinner numberOfPlayersSpinner;

    private static final int MIN_PLAYERS = 1;
    private static final int MAX_PLAYERS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        playerNamesEditTexts = new EditText[]{
                findViewById(R.id.playerName1),
                findViewById(R.id.playerName2),
                findViewById(R.id.playerName3),
                findViewById(R.id.playerName4)
        };

        numberOfPlayersSpinner = findViewById(R.id.numberOfPlayersSpinner);

        Integer[] numberOfPlayersOptions = new Integer[MAX_PLAYERS - MIN_PLAYERS + 1];
        for (int i = MIN_PLAYERS; i <= MAX_PLAYERS; i++) {
            numberOfPlayersOptions[i - MIN_PLAYERS] = i;
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numberOfPlayersOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberOfPlayersSpinner.setAdapter(adapter);

        Button startGameButton = findViewById(R.id.startGameButton);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        // Update visibility initially
        updateVisibility();

        // Set a listener for changes in the spinner to update visibility
        numberOfPlayersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateVisibility();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    private void startGame() {
        int numberOfPlayers = (int) numberOfPlayersSpinner.getSelectedItem();

        ArrayList<String> playerNames = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            String playerName = playerNamesEditTexts[i].getText().toString().trim();
            if (playerName.isEmpty()) {
                playerNamesEditTexts[i].setError("Enter player name");
                return;
            }
            playerNames.add(playerName);
        }

        if (playerNames.size() == numberOfPlayers) {
            Intent intent = new Intent(NewGameActivity.this, GameActivity.class);
            intent.putStringArrayListExtra("playerNames", playerNames);
            intent.putExtra("numberOfPlayers", numberOfPlayers);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please enter names for all players", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateVisibility() {
        int numberOfPlayers = (int) numberOfPlayersSpinner.getSelectedItem();

        for (int i = 0; i < MAX_PLAYERS; i++) {
            if (i < numberOfPlayers) {
                playerNamesEditTexts[i].setVisibility(View.VISIBLE);
            } else {
                playerNamesEditTexts[i].setVisibility(View.GONE);
            }
        }
    }
}
