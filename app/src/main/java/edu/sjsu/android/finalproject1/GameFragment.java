package edu.sjsu.android.finalproject1;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;


public class GameFragment extends Fragment {

    private LinearLayout[] columns;
    private Board board;
    private boolean player;
    private TextView playerTurn;
    private long time;
    private int moves;

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        time = System.currentTimeMillis();
        moves = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        playerTurn = view.findViewById(R.id.turn);

        player = (new Random()).nextBoolean();
        if(player) playerTurn.setText(R.string.player1Turn);
        else playerTurn.setText(R.string.player2Turn);
        board = new Board();
        columns = new LinearLayout[]{view.findViewById(R.id.column1), view.findViewById(R.id.column2),
                view.findViewById(R.id.column3), view.findViewById(R.id.column4), view.findViewById(R.id.column5),
                view.findViewById(R.id.column6), view.findViewById(R.id.column7)};

        view.findViewById(R.id.reset_button).setOnClickListener(v -> resetGame());

        for(int i = 0; i < columns.length; i++) {
            final int var = i;
            columns[i].setOnClickListener(v -> {
                String color = player ? "R" : "Y";
                if(board.put(var, color)) {
                    moves++;
                    int row = 5;
                    while(board.board[row-1][var] != null) {
                        row--;
                        if(row == 0) break;
                    }
                    ImageView child = (ImageView) columns[var].getChildAt(row);
                    if(color.equals("R"))
                        child.setColorFilter(getResources().getColor(R.color.red));
                    else
                        child.setColorFilter(getResources().getColor(R.color.yellow));
                    checkWin(var, color);
                    checkDraw();
                    switchPlayer();
                }
            });
        }

        return view;
    }

    public void switchPlayer() {
        player = !player;
        if(player) playerTurn.setText(R.string.player1Turn);
        else playerTurn.setText(R.string.player2Turn);
    }

    public void checkDraw(){
        boolean full = true;
        for(int i = 0; i < Board.rows; i++)
            for (int j = 0; j < Board.columns; j++) {
                if (board.board[i][j] == null) {
                    full = false;
                    break;
                }
            }
        if(full){
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Board is Full!");
            builder.setMessage("Play again?");
            builder.setPositiveButton("Yes", (dialog, id) -> resetGame());
            builder.setNegativeButton("No", (dialog, id) -> NavHostFragment.findNavController(this).navigate(R.id.homeFragment));
            builder.create().show();
        }
    }
    public void checkWin(int col, String color){
        if(board.winningMove(col, color)){
            int num = player ? 1 : 2;
            EndGameDialog dialog = EndGameDialog.newInstance(num, moves, time);
            dialog.showNow(getParentFragmentManager(), "id");
            /*
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Player " + num + " Wins!");
            builder.setMessage("Play again?");
            builder.setPositiveButton("Yes", (dialog, id) -> resetGame());
            builder.setNegativeButton("No", (dialog, id) -> NavHostFragment.findNavController(this).navigate(R.id.homeFragment));
            builder.create().show(); */
        }
    }

    public void resetGame(){
        board = new Board();
        for(LinearLayout layout : columns) {
            for(int i =0; i < layout.getChildCount(); i++){
                ImageView img = (ImageView) layout.getChildAt(i);
                img.setColorFilter(getResources().getColor(R.color.white));
            }
        }
    }
}