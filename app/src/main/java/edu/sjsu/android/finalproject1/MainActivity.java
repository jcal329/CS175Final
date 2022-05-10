package edu.sjsu.android.finalproject1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private LinearLayout[] columns;
    private Board board;
    private boolean player;
    private TextView playerTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerTurn = findViewById(R.id.turn);
        player = (new Random()).nextBoolean();
        if(player) playerTurn.setText("Player 1's Turn");
        else playerTurn.setText("Player 2's Turn");
        board = new Board();
        columns = new LinearLayout[]{findViewById(R.id.column1), findViewById(R.id.column2),
                findViewById(R.id.column3), findViewById(R.id.column4), findViewById(R.id.column5),
                findViewById(R.id.column6), findViewById(R.id.column7)};

        for(int i = 0; i < columns.length; i++) {
           final int var = i;
            columns[i].setOnClickListener(v -> {
                String color = player ? "R" : "Y";

                if(board.put(var, color)) {
                    int row = 5;
                    while(board.board[row-1][var] != null) {
                        row--;
                        if(row == 0) break;
                    }
                    ImageView child = (ImageView) columns[var].getChildAt(row);
                    if(color.equals("R"))
                        child.setImageResource(R.drawable.red_piece);
                    else
                        child.setImageResource(R.drawable.yellow_piece);
                    checkWin(var, color);
                    switchPlayer();
                }
            });
        }
    }

    public void switchPlayer() {
        player = !player;
        int num = player ? 1 : 2;
        playerTurn.setText("Player " + num + "'s Turn");
    }

    public void checkWin(int col, String color){
        if(board.checkWinner(col, color)){
            int num = player ? 1 : 2;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Player " + num + " Wins!");
            builder.setMessage("Play again?");
            builder.setPositiveButton("Yes", (dialog, id) -> {
                board = new Board();
                for(LinearLayout layout : columns) {
                    for(int i =0; i < layout.getChildCount(); i++){
                        ImageView img = (ImageView) layout.getChildAt(i);
                        img.setImageResource(R.drawable.holder);
                    }
                }
            });
            builder.setNegativeButton("No", (dialog, id) ->{});
            builder.create().show();
        }
    }
}