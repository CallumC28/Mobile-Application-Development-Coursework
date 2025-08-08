package com.example.id2101170701;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class GolfDataSource {
    private SQLiteDatabase database;
    private GolfDatabaseHelper dbHelper;

    public GolfDataSource(Context context) {
        dbHelper = new GolfDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertPlayer(String name, int score) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("score", score);

        return database.insert("players", null, values);
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();

        Cursor cursor = database.query("players", null, null, null, null, null, "score DESC");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Player player = new Player();
                player.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                player.setName(cursor.getString(cursor.getColumnIndex("name")));
                player.setScore(cursor.getInt(cursor.getColumnIndex("score")));
                players.add(player);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return players;
    }

    // Example of using GolfDataSource in an activity
    public void exampleUsage() {
        // Open the database
        open();

        // Insert a player
        long playerId = insertPlayer("Player1", 25);

        // Retrieve all players
        List<Player> players = getAllPlayers();

        // Close the database when done
        close();
    }

    // Inside the GolfDataSource class

    public Player getLowestScoringPlayer() {
        Player lowestScorer = null;

        Cursor cursor = database.query("players", null, null, null, null, null, "score ASC", "1");

        if (cursor != null && cursor.moveToFirst()) {
            lowestScorer = new Player();
            lowestScorer.setId(cursor.getLong(cursor.getColumnIndex("_id")));
            lowestScorer.setName(cursor.getString(cursor.getColumnIndex("name")));
            lowestScorer.setScore(cursor.getInt(cursor.getColumnIndex("score")));

            cursor.close();
        }

        return lowestScorer;
    }
// Inside the GolfDataSource class

    public Player getHighestScoringPlayer() {
        Player highestScorer = null;

        Cursor cursor = database.query("players", null, null, null, null, null, "score DESC", "1");

        if (cursor != null && cursor.moveToFirst()) {
            highestScorer = new Player();
            highestScorer.setId(cursor.getLong(cursor.getColumnIndex("_id")));
            highestScorer.setName(cursor.getString(cursor.getColumnIndex("name")));
            highestScorer.setScore(cursor.getInt(cursor.getColumnIndex("score")));

            cursor.close();
        }

        return highestScorer;
    }

}

