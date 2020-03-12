package com.example.twodiepig3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button rollDice;
    Button hold;
    Button newGame;
    TextView p1TotalScore;
    TextView p2TotalScore;
    TextView turnScore;
    TextView currentTurn;
    TextView winningBanner;
    ImageView die01;
    ImageView die02;
    int val01, val02, turnPoints;
    public static final Random RANDOM = new Random();
    public static int gameTrace[] = new int[2];
    public static int goPlay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGame = (Button) findViewById(R.id.btnNewGame);
        hold = (Button) findViewById(R.id.btnHold);
        rollDice = (Button) findViewById((R.id.btnRollDice));
        p1TotalScore = (TextView) findViewById(R.id.txtP1TotalScore);
        p2TotalScore = (TextView) findViewById(R.id.txtP2TotalScore);
        currentTurn = (TextView) findViewById(R.id.txtCurrentTurn);
        turnScore = (TextView) findViewById(R.id.txtTurnScore);
        winningBanner = (TextView) findViewById(R.id.txtWinner);
        die01 = (ImageView) findViewById(R.id.imgDie01);
        die02 = (ImageView) findViewById(R.id.imgDie02);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
        rollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollTheDice();
            }
        });
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdPoints();
            }
        });
        currentTurn.setText("Player 1");

    }



    private void rollTheDice() { //dice rolled and the conditions evaluated

       winningBanner.setText(" ");
        val01 = randomDieValue();
        val02 = randomDieValue();
        changeDie01(val01);
        changeDie02(val02);

        if(val01 == 1|| val02 == 1){

            System.out.println("You have rolled  single '1' \n turn ended");

            System.out.println("it is currently " + goPlay + "'s turn");
           turnScore.setText("0");
           changeTurn();
           System.out.println("it is now " + goPlay + "'s turn");
           turnPoints = 0;


        }
        else if(val01 == 1 && val02 == 1){
            System.out.println("it is currently " + goPlay + "'s turn");
            System.out.println("You have rolled double '1'  \n turn ended");

            //change turn method
            changeTurn();
            System.out.println("it is now " + goPlay + "'s turn");
            turnPoints = 0;


        }else if (val01 == val02){
            // this condition must roll again
            System.out.println("it is currently " + goPlay + "'s turn");
            System.out.println("The values match");
            turnPoints += val01+val02;
            turnScore.setText(" " + turnPoints);
            rollTheDice();


        }
        else{
            System.out.println("it is currently " + goPlay + "'s turn");
            System.out.println("The values are : " + val01 + " and " + val02);
            turnPoints += val01+val02;
            turnScore.setText(" " + turnPoints);


        }

    }//end rollTheDice

    private void checkWinner() {//scores looked at before turn changes to see if there is a winner
        if(gameTrace[0] >= 50 ){
            winningBanner.setText("Player " + (goPlay+1) + " is the winner!!");

        }
        else if(gameTrace[1] >= 50){
            winningBanner.setText("Player " + (goPlay+1) + " is the winner!!");
        }

    }//end checkWinner

    private void changeDie01(int val01) {//changes the image view for the left die
        switch (val01){
            case 1:
                die01.setImageResource(R.drawable.die_face_1);
                break;
            case 2:
                die01.setImageResource(R.drawable.die_face_2);
                break;
            case 3:
                die01.setImageResource(R.drawable.die_face_3);
                break;
            case 4:
                die01.setImageResource(R.drawable.die_face_4);
                break;
            case 5:
                die01.setImageResource(R.drawable.die_face_5);
                break;
            case 6:
                die01.setImageResource(R.drawable.die_face_6);
                break;
                default:
                    //has default image for catching errors..
                    die01.setImageResource(R.drawable.error_die);
                    break;
        }

    }//end changeDie01

    private void changeDie02(int val02) {//changes the image view for the right die
        switch (val02){
            case 1:
                die02.setImageResource(R.drawable.die_face_1);
                break;
            case 2:
                die02.setImageResource(R.drawable.die_face_2);
                break;
            case 3:
                die02.setImageResource(R.drawable.die_face_3);
                break;
            case 4:
                die02.setImageResource(R.drawable.die_face_4);
                break;
            case 5:
                die02.setImageResource(R.drawable.die_face_5);
                break;
            case 6:
                die02.setImageResource(R.drawable.die_face_6);
                break;
            default:
                //has default image for catching errors..
                die02.setImageResource(R.drawable.error_die);
                break;
        }


    }//end changeDie02

    private void changeTurn() {//changes players turn and updates the textViews
        checkWinner();
        if(goPlay == 0){
            goPlay = 1;
            currentTurn.setText("2");
        }else if(goPlay == 1){
            goPlay = 0;
            currentTurn.setText("1");
        }

    }// end changeTurn

    private void holdPoints() {//changes the turn and adds the collected points t that players total score
        gameTrace[goPlay] += turnPoints;
        turnPoints = 0;
        turnScore.setText(" " + turnPoints);
        changeTotalScore(goPlay);
        changeTurn();


    }//end holdPoints
    private void changeTotalScore(int fieldSpot) {
        if(fieldSpot == 0){
            p1TotalScore.setText(" " + gameTrace[goPlay]);
        }
        else if (fieldSpot == 1){
            p2TotalScore.setText(" " + gameTrace[goPlay]);
        }
    }//end changeTotalScore

    private int randomDieValue() {return RANDOM.nextInt(6) + 1;}//rolls the random number for the die

    private void resetGame() {//resets all textViews and score keepers for a new game to begin

        p1TotalScore.setText(" 0");
        p2TotalScore.setText(" 0");
        currentTurn.setText("Player 1");
        winningBanner.setText(" ");
        turnScore.setText(" 0 ");
        goPlay = 0;
        die01.setImageResource(R.drawable.die_face_1);
        die02.setImageResource(R.drawable.die_face_1);
        for(int i = 0; i < 2; i++){
            gameTrace[i]=0;
        }

    }// end resetGame

}
