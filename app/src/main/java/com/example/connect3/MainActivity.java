package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Red = 0, Yellow = 1, Empty = 2;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};
    int activePlayer = 0;
    boolean gameActive = true;


    public void dropIn(View view){

        ImageView counter = (ImageView) view;


        if (gameState[Integer.parseInt(counter.getTag().toString())] == 2 && gameActive) {

            Log.i("Tag", counter.getTag().toString());

            gameState[Integer.parseInt(counter.getTag().toString())] = activePlayer;

            counter.setTranslationY(-1600);
            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            }
            counter.animate().translationYBy(1600).rotation(3600).setDuration(600);

            String activePlayerString = activePlayer > 0 ? "Red" : "Yellow";

            for (int[] winningPositions : winningPositions) {
                if (gameState[winningPositions[0]] == gameState[winningPositions[1]] && gameState[winningPositions[1]] == gameState[winningPositions[2]] && gameState[winningPositions[0]] != 2) {

                    Toast.makeText(this, activePlayerString + " player won!", Toast.LENGTH_SHORT).show();

                    gameActive = false;

                    Button playAgainButton =(Button)findViewById(R.id.playAgainButton);

                    playAgainButton.setVisibility(View.VISIBLE);

                    TextView winnerTextView =(TextView)findViewById(R.id.winnerTextView);

                    winnerTextView.setText("Winner is "+activePlayerString);

                    winnerTextView.setTextSize(20);

                    winnerTextView.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void playAgain(View view){

        Button playAgainButton =(Button)findViewById(R.id.playAgainButton);

        TextView winnerTextView =(TextView)findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout myGrid = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0 ; i < myGrid.getChildCount(); i++ ){

            ImageView counter =(ImageView)myGrid.getChildAt(i);

            counter.setImageDrawable(null);
        }
        activePlayer = 0;
        gameActive = true;

        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
