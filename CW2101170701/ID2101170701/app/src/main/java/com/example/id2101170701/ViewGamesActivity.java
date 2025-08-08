package com.example.id2101170701;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ViewGamesActivity extends AppCompatActivity {

    private TextView gamesDataTextView;
    private GolfDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_games);

        gamesDataTextView = findViewById(R.id.gamesDataTextView);
        dataSource = new GolfDataSource(this);

        // Load and display games data when the activity is created
        loadAndDisplayGamesData();
    }

    private void loadAndDisplayGamesData() {
        // Open the database
        dataSource.open();

        // Retrieve all players from the database
        List<Player> players = dataSource.getAllPlayers();

        // Display the games data in the TextView
        StringBuilder gamesData = new StringBuilder();
        for (Player player : players) {
            gamesData.append(player.getName()).append(": ").append(player.getScore()).append("\n");
        }

        gamesDataTextView.setText(gamesData.toString());

        // Close the database
        dataSource.close();
    }
}
