package com.happycoding.upgradedquizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private Button btnTrue, btnFalse;
    private TextView txtAnswer;
    ProgressBar progressBar;
    private int currentQuestionIndex = 0;
    int userScore=0;
    private Button  imgBtnNext;



    private Question[] questionBank = new Question[]{
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_temp, true),
            new Question(R.string.question_body, false),
            new Question(R.string.question_electron, false),
            new Question(R.string.question_human, false),
            new Question(R.string.question_water, false),
            new Question(R.string.question_spider, false),
            new Question(R.string.question_physics, true),
            new Question(R.string.question_shark, false),
            new Question(R.string.question_ocean, false),
            new Question(R.string.question_planet, false)


    };

    final int USER_PROGRESS=(int)Math.ceil(100/questionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        txtAnswer = findViewById(R.id.questionTextView);
        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);
        imgBtnNext = findViewById(R.id.imgBtnNext);

        progressBar=findViewById(R.id.progressBar);


        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);

        imgBtnNext.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnTrue:
                checkAnswer(true);
                break;

            case R.id.btnFalse:
                checkAnswer(false);
                break;

            case R.id.imgBtnNext:
                    currentQuestionIndex++;
                    currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
                    updateQuestion();
            break;
        }

    }
    private void updateQuestion(){
          Log.d("Current","Onclick"+currentQuestionIndex);
        txtAnswer.setText(questionBank[currentQuestionIndex].getAnswerResId());
        progressBar.incrementProgressBy(USER_PROGRESS);

        if(currentQuestionIndex==0){
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle("Quiz has been finished");
            alert.setMessage("Final score:"+userScore);
            alert.setPositiveButton("Finish Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();

        }
    }




    private void checkAnswer(boolean userChosenCorrect){
        boolean answerIsTrue=questionBank[currentQuestionIndex].isAnswerTrue();
        int toastMessageId=0;
        if(userChosenCorrect==answerIsTrue){
            toastMessageId=R.string.correct_answer;
            userScore=userScore+1;
        }
        else{
            toastMessageId=R.string.incorrect_answer;
        }
        Toast.makeText(MainActivity2.this,toastMessageId,Toast.LENGTH_SHORT).show();
    }
    }


