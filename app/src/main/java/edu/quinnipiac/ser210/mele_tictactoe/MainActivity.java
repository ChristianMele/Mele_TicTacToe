package edu.quinnipiac.ser210.mele_tictactoe;

/*
Author: Christian Mele
Title: TicTacToe
Submission Date: Feb 14, 2020
Main Class
*/

// Imports
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Instance variables
    private Button[][] board = new Button[3][3];
    private int player = 0;
    private int round = 0;
    private TextView boardText;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gets the player's name from the home page
        boardText = findViewById(R.id.text_view_player);
        name = getIntent().getExtras().getString("Name");
        boardText.setText("Welcome, " + name);

        // Uses a 2D loop to create the TicTacToe board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                board[i][j] = findViewById(resID);
                board[i][j].setOnClickListener(this);
            }
        }

        // Adds a reset button to reset the board
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });
    }

    @Override
    public void onClick(View v) {
        // Checks if the clicked spot is open
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        // The player's move
        if (player == 0) {
            boardText.setText("It's your turn, " + name);
            ((Button) v).setText("X");
        }
        round++;

        // Decides if the player or computer has won the game
        if (checkForWinner()) {
            if (player == 0) {
                playerWin();
            } else {
                computerWin();
            }
            // Ends the game in a draw if the board is full
        } else if (round == 9) {
            draw();
        } else {
            if (player == 0) player = 1;
            else player = 0;
        }

        computerMove();
        round++;

        if (checkForWinner()) {
            if (player == 0) {

                playerWin();
            } else {
                computerWin();
            }
        } else if (round == 9) {
            draw();
        } else {
            if (player == 0) player = 1;
            else player = 0;
        }
    }

    private boolean checkForWinner() {
        // Checks for a game winner
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = board[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    // Player win
    private void playerWin() {
        Toast.makeText(this, "Win", Toast.LENGTH_SHORT).show();
        boardText.setText("You've won, " + name);
        resetBoard();
    }

    // Computer win
    private void computerWin() {
        Toast.makeText(this, "Loss", Toast.LENGTH_SHORT).show();
        boardText.setText("You've lost, " + name);
        resetBoard();
    }

    // Draw
    private void draw() {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        boardText.setText("It's a draw, " + name);
        resetBoard();
    }

    // Resets the board by recreating an empty board with a 2D array loop
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
            }
        }
        round = 0;
        player = 0;
    }

    // Computer move AI
    private void computerMove() {
        if (player == 1) {
            // The game displays that the computer is moving while it is the computer's turn...
            // ...however the computer moves instantly so you never see this string
            boardText.setText("The computer is moving.");
            // Simple blocking AI by checking the status of the board
            if (board[1][0].equals(board[0][0])
                    && !board[1][0].equals("")
                    && board[2][0].equals("")) {
                board[2][0].setText("O");
                boardText.setText("It's your turn, " + name);
            } else if (board[0][1].equals(board[0][0])
                    && !board[0][1].equals("")
                    && board[0][2].equals("")) {
                board[0][2].setText("O");
                boardText.setText("It's your turn, " + name);
            } else if (board[1][1].equals(board[0][2])
                    && !board[1][1].equals("")
                    && board[1][2].equals("")) {
                board[1][2].setText("O");
                boardText.setText("It's your turn, " + name);
                // Random movement AI based on previous blocking attempts and open board space
            } else if (board[0][0].getText() == "") {
                board[0][0].setText("O");
                boardText.setText("It's your turn, " + name);
            } else if (board[0][1].getText() == "") {
                board[0][1].setText("O");
                boardText.setText("It's your turn, " + name);
            } else if (board[1][0].getText() == "") {
                board[1][0].setText("O");
                boardText.setText("It's your turn, " + name);
            } else if (board[1][1].getText() == "") {
                board[1][1].setText("O");
                boardText.setText("It's your turn, " + name);
            } else if (board[1][2].getText() == "") {
                board[1][2].setText("O");
                boardText.setText("It's your turn, " + name);
            }  else if (board[0][2].getText() == "") {
                board[0][2].setText("O");
                boardText.setText("It's your turn, " + name);
            } else if (board[2][1].getText() == "") {
                board[2][1].setText("O");
                boardText.setText("It's your turn, " + name);
            } else if (board[2][0].getText() == "") {
                board[2][0].setText("O");
                boardText.setText("It's your turn, " + name);
            } else if (board[2][2].getText() == "") {
                board[2][2].setText("O");
                boardText.setText("It's your turn, " + name);
            }
        }
    }

    // Saves the game state (for device rotation)
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("round", round);
        outState.putInt("player", player);
    }

    // Restores the game state after rotation
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        round = savedInstanceState.getInt("round");
        player = savedInstanceState.getInt("player");
    }
}