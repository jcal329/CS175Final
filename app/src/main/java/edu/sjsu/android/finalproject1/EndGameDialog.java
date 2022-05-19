package edu.sjsu.android.finalproject1;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.fragment.NavHostFragment;

public class EndGameDialog extends DialogFragment {

    private int player;
    private int moves;
    private long time;


    public EndGameDialog() {
        //empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        player = getArguments().getInt("player", 0);
        moves = getArguments().getInt("moves", 0);
        time = System.currentTimeMillis() - getArguments().getLong("time", 0);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_end_game_dialog, container, false);

        TextView winner = view.findViewById(R.id.winner);
        TextView numMoves = view.findViewById(R.id.numMoves);
        TextView timer = view.findViewById(R.id.timer);
        Button newGame = view.findViewById(R.id.newGame);
        Button keepPlaying = view.findViewById(R.id.keepPlaying);
        Button home = view.findViewById(R.id.homeButton);
        int seconds = (int) time / 1000;
        int minutes = seconds / 60;

        if(player == 1) winner.setText(R.string.player1win);
        else if (player == 2) winner.setText(R.string.player2win);
        numMoves.setText(getResources().getString(R.string.numMoves, moves));
        timer.setText(getResources().getString(R.string.timer, minutes, seconds % 60));

        newGame.setOnClickListener(v -> {
            NavHostFragment.findNavController(getParentFragment()).navigate(R.id.gameFragment);
            dismiss();
        });
        keepPlaying.setOnClickListener(v -> dismiss());
        home.setOnClickListener(v -> {
            NavHostFragment.findNavController(getParentFragment()).navigate(R.id.homeFragment);
            dismiss();
        });

        return view;
    }

    public static EndGameDialog newInstance(int player, int moves, long time) {
        EndGameDialog d = new EndGameDialog();

        Bundle args = new Bundle();
        args.putInt("player", player);
        args.putInt("moves", moves);
        args.putLong("time", time);

        d.setArguments(args);
        return d;
    }

}
