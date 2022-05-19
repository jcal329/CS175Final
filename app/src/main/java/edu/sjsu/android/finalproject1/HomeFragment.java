package edu.sjsu.android.finalproject1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView title = view.findViewById(R.id.title);
        String text = "<font color=" + getResources().getColor(R.color.red) + ">C</font>" +
                "<font color=" + getResources().getColor(R.color.yellow) + ">o</font>" +
                "<font color=" + getResources().getColor(R.color.blue) + ">n</font>" +
                "<font color=" + getResources().getColor(R.color.red) + ">n</font>" +
                "<font color=" + getResources().getColor(R.color.yellow) + ">e</font>" +
                "<font color=" + getResources().getColor(R.color.blue) + ">c</font>" +
                "<font color=" + getResources().getColor(R.color.red) + ">t</font>" +
                "<font color=" + getResources().getColor(R.color.yellow) + ">4</font>" +
                "<font color=" + getResources().getColor(R.color.blue) + ">!</font>";

        title.setText(Html.fromHtml(text));
        Button play = view.findViewById(R.id.playButton);
        play.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.gameFragment));

        return view;
    }
}