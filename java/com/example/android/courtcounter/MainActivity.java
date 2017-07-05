package com.example.android.courtcounter;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

//additional imported classes
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.concurrent.TimeUnit;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {
    int scoreTeamA = 0;
    int scoreTeamB = 0; //placeholder variable for scores

    //Declare a variable to hold count down timer's paused status
    private boolean isPaused = false;
    //Declare a variable to hold count down timer's paused status
    private boolean isCanceled = false;

    //Declare a variable to hold CountDownTimer remaining time
    private long timeRemaining = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Get reference of the XML layout's widgets
        final TextView tView = (TextView) findViewById(R.id.tv);
        final Button btnStart = (Button) findViewById(R.id.btn_start);
        final Button btnPause = (Button) findViewById(R.id.btn_pause);
        final Button btnResume = (Button) findViewById(R.id.btn_resume);
        final Button btnCancel = (Button) findViewById(R.id.btn_cancel);
        final Button three_points = (Button) findViewById(R.id.three_points);
        final Button two_points = (Button) findViewById(R.id.two_points);
        final Button one_points = (Button) findViewById(R.id.one_points);
        final Button three_pointsB = (Button) findViewById(R.id.three_pointsB);
        final Button two_pointsB = (Button) findViewById(R.id.one_pointsB);
        final Button one_pointsB = (Button) findViewById(R.id.two_pointsB);
        final EditText time = (EditText) findViewById(R.id.time);


        //Initially disabled the pause, resume, cancel and points buttons
        btnPause.setEnabled(false);
        btnResume.setEnabled(false);
        btnCancel.setEnabled(false);
        three_points.setEnabled(false);
        one_points.setEnabled(false);
        two_points.setEnabled(false);
        three_pointsB.setEnabled(false);
        one_pointsB.setEnabled(false);
        two_pointsB.setEnabled(false);


        //Set a Click Listener for start button
        btnStart.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){

                isPaused = false;
                isCanceled = false;
                //Disable the start and pause button

                //Enabled other buttona

                btnStart.setEnabled(false);
                btnResume.setEnabled(false);
                btnPause.setEnabled(true);
                btnCancel.setEnabled(true);
                three_points.setEnabled(false);
                one_points.setEnabled(false);
                two_points.setEnabled(false);
                three_pointsB.setEnabled(false);
                one_pointsB.setEnabled(false);
                two_pointsB.setEnabled(false);

                CountDownTimer timer;


                int millisInFuture = Integer.parseInt( time.getText().toString()) * 60000; //converts the minute into ms
                long countDownInterval = 1000; //1 second


                //Initialize a new CountDownTimer instance
                timer = new CountDownTimer(millisInFuture,countDownInterval){
                    public void onTick(long millisUntilFinished){
                        //do something in every tick
                        if(isPaused || isCanceled)
                        {
                            //If the user request to cancel or paused the
                            //CountDownTimer we will cancel the current instance
                            cancel();
                        }
                        else {
                            //Display the remaining seconds to app interface
                            //1 second = 1000 milliseconds
                            tView.setText("" + millisUntilFinished / 1000 + " seconds");
                            //Put count down timer remaining time in a variable
                            timeRemaining = millisUntilFinished;
                        }
                    }
                    public void onFinish(){
                        //Do something when count down finished
                        tView.setText("Done");

                        //Enable the start button
                        btnStart.setEnabled(true);
                        //Disable other buttons
                        btnPause.setEnabled(false);
                        btnResume.setEnabled(false);
                        btnCancel.setEnabled(false);
                        three_points.setEnabled(false);
                        one_points.setEnabled(false);
                        two_points.setEnabled(false);
                        three_pointsB.setEnabled(false);
                        one_pointsB.setEnabled(false);
                        two_pointsB.setEnabled(false);
                    }
                }.start();
            }
        });

        //Set a Click Listener for pause button
        btnPause.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                //When user request to pause the CountDownTimer
                isPaused = true;

                //Enable the other buttons
                btnResume.setEnabled(true);
                btnCancel.setEnabled(true);
                three_points.setEnabled(true);
                one_points.setEnabled(true);
                two_points.setEnabled(true);
                three_pointsB.setEnabled(true);
                one_pointsB.setEnabled(true);
                two_pointsB.setEnabled(true);

                //Disable the start and pause button
                btnStart.setEnabled(false);
                btnPause.setEnabled(false);

            }
        });

        //Set a Click Listener for resume button
        btnResume.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                //Disable the other buttons
                btnStart.setEnabled(false);
                btnResume.setEnabled(false);
                three_points.setEnabled(false);
                one_points.setEnabled(false);
                two_points.setEnabled(false);
                three_pointsB.setEnabled(false);
                one_pointsB.setEnabled(false);
                two_pointsB.setEnabled(false);
                //Enable the pause and cancel button
                btnPause.setEnabled(true);
                btnCancel.setEnabled(true);

                //Specify the current state is not paused and canceled.
                isPaused = false;
                isCanceled = false;

                //Initialize a new CountDownTimer instance
                long millisInFuture = timeRemaining;
                long countDownInterval = 1000;
                new CountDownTimer(millisInFuture, countDownInterval){


                    public void onTick(long millisUntilFinished){
                        //Do something in every tick
                        if(isPaused || isCanceled)
                        {
                            //If user requested to pause or cancel the count down timer
                            cancel();
                        }
                        else {
                            tView.setText("" + (millisUntilFinished) / 1000 + " seconds");
                            //Put count down timer remaining time in a variable
                            timeRemaining = millisUntilFinished;
                        }
                    }
                    public void onFinish(){
                        //Do something when count down finished
                        //Disable other buttons
                        btnPause.setEnabled(false);
                        btnResume.setEnabled(false);
                        btnCancel.setEnabled(false);
                        three_points.setEnabled(false);
                        one_points.setEnabled(false);
                        two_points.setEnabled(false);
                        three_pointsB.setEnabled(false);
                        one_pointsB.setEnabled(false);
                        two_pointsB.setEnabled(false);

                        //Enable the start button
                        btnStart.setEnabled(true);



                        if(scoreTeamA > scoreTeamB) {
                            tView.setText("Aka won");
                        }
                        if (scoreTeamA == scoreTeamB){
                            tView.setText("Draw!");
                        }
                        if(scoreTeamB > scoreTeamA){
                            tView.setText("Ao won");
                        }
                    }
                }.start();

                //Set a Click Listener for cancel/stop button
                //i had to do this twice intentioannly for a bug
                btnCancel.setOnClickListener(new OnClickListener(){
                    @Override
                    public void onClick(View v){
                        //When user request to cancel the CountDownTimer
                        isCanceled = true;

                        //Disable the cancel, pause and resume button
                        three_points.setEnabled(false);
                        one_points.setEnabled(false);
                        two_points.setEnabled(false);
                        three_pointsB.setEnabled(false);
                        one_pointsB.setEnabled(false);
                        two_pointsB.setEnabled(false);
                        btnPause.setEnabled(false);
                        btnResume.setEnabled(false);
                        btnCancel.setEnabled(false);
                        //Enable the start button
                        btnStart.setEnabled(true);


                        //if else statement for choosing the winner incase users force stops
                        if(scoreTeamA > scoreTeamB) {
                            tView.setText("Aka won");
                        }
                        if (scoreTeamA == scoreTeamB){
                            tView.setText("Draw!");
                        }
                        if(scoreTeamB > scoreTeamA){
                            tView.setText("Ao won");
                        }
                    }
                });
            }
        });

        //Set a Click Listener for cancel/stop button
        btnCancel.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                //When user request to cancel the CountDownTimer
                isCanceled = true;

                //Disable the cancel, pause and resume button
                three_points.setEnabled(false);
                one_points.setEnabled(false);
                two_points.setEnabled(false);
                three_pointsB.setEnabled(false);
                one_pointsB.setEnabled(false);
                two_pointsB.setEnabled(false);

                btnPause.setEnabled(false);
                btnResume.setEnabled(false);
                btnCancel.setEnabled(false);
                //Enable the start button
                btnStart.setEnabled(true);

                if(scoreTeamA > scoreTeamB) {
                    tView.setText("Aka won");
                }
                if (scoreTeamA == scoreTeamB){
                    tView.setText("Draw!");
                }
                if(scoreTeamB > scoreTeamA){
                    tView.setText("Ao won");
                }
            }
        });

    }

    public void threePointsTeamA(View v) {
        scoreTeamA = scoreTeamA + 3;
        displayForTeamA(scoreTeamA);
    }

    public void twoPointsTeamA(View v) {
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
    }

    public void onePointTeamA(View v) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void threePointsTeamB(View v) {
        scoreTeamB = scoreTeamB + 3;
        displayForTeamB(scoreTeamB);
    }

    public void twoPointsTeamB(View v) {
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
    }

    public void onePointTeamB(View v) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }

    public void resetScore(View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }
}
