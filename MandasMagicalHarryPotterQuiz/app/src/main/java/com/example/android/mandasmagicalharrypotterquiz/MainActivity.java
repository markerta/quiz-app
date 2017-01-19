package com.example.android.mandasmagicalharrypotterquiz;

import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.R.attr.id;
import static android.R.id.message;
import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.example.android.mandasmagicalharrypotterquiz.R.string.first_q;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method to parse the answers submitted and determine the quiz-takers score.
     * @param v
     */
    public void submitAnswers(View v) {

        /*
         * Output for Question One Result based on Correctness
         */
        RadioButton questionOneResult = (RadioButton) findViewById(R.id.first_q_correct);
        TextView questionOneResultText = (TextView) findViewById(R.id.first_q_text);
        // Show user correct answer
        questionOneResult.setBackgroundColor(Color.parseColor("#58CCA5"));
        questionOneResult.setTextColor(BLACK);

        ArrayList<CompoundButton> idsOne = new ArrayList<>();
        idsOne.add((RadioButton) findViewById(R.id.first_q_wrong_one));
        idsOne.add((RadioButton) findViewById(R.id.first_q_wrong_two));
        idsOne.add((RadioButton) findViewById(R.id.first_q_wrong_three));
        Boolean oneCorrect = findIncorrectAnswer(idsOne, questionOneResultText);

        /*
         * Output for Question Two Result based on Correctness
         */
        CheckBox questionTwoResultOne = (CheckBox) findViewById(R.id.second_q_correct_one);
        CheckBox questionTwoResultTwo = (CheckBox) findViewById(R.id.second_q_correct_two);
        TextView questionTwoResultText = (TextView) findViewById(R.id.second_q_text);
        // Show user correct answers
        questionTwoResultOne.setBackgroundColor(Color.parseColor("#58CCA5"));
        questionTwoResultOne.setTextColor(BLACK);
        questionTwoResultTwo.setBackgroundColor(Color.parseColor("#58CCA5"));
        questionTwoResultTwo.setTextColor(BLACK);

        ArrayList<CompoundButton> idsTwo = new ArrayList<>();
        idsTwo.add((CheckBox) findViewById(R.id.second_q_wrong_one));
        idsTwo.add((CheckBox) findViewById(R.id.second_q_wrong_two));
        idsTwo.add((CheckBox) findViewById(R.id.second_q_wrong_three));
        idsTwo.add((CheckBox) findViewById(R.id.second_q_wrong_four));
        Boolean twoCorrect = findIncorrectAnswer(idsTwo, questionTwoResultText);

        /*
         * Output for Question Three Result based on Correctness
         */
        EditText questionThreeResult = (EditText) findViewById(R.id.third_q_correct);
        questionThreeResult.setFocusable(false);
        TextView questionThreeResultText = (TextView) findViewById(R.id.third_q_text);
        Boolean threeCorrect=false;
        if(questionThreeResult.getText().toString().equals("Pigwidgeon")) {
            threeCorrect=true;
            questionThreeResultText.setText(R.string.correct);
        }
        else if(questionThreeResult.getText().toString().equals("Pig")) {
            questionThreeResultText.setText(R.string.close_incorrect);
            questionThreeResultText.setTextColor(Color.parseColor("#BA2123"));
        }
        else {
            questionThreeResultText.setText(R.string.incorrect);
            questionThreeResultText.setTextColor(Color.parseColor("#BA2123"));
        }

        /*
         * Output for Question Four Result based on Correctness
         */
        CheckBox questionFourResultOne = (CheckBox) findViewById(R.id.fourth_q_correct_one);
        CheckBox questionFourResultTwo = (CheckBox) findViewById(R.id.fourth_q_correct_two);
        CheckBox questionFourResultThree = (CheckBox) findViewById(R.id.fourth_q_correct_three);
        CheckBox questionFourResultFour = (CheckBox) findViewById(R.id.fourth_q_correct_four);
        TextView questionFourResultText = (TextView) findViewById(R.id.fourth_q_text);
        // Show user correct answers
        questionFourResultOne.setBackgroundColor(Color.parseColor("#58CCA5"));
        questionFourResultOne.setTextColor(BLACK);
        questionFourResultTwo.setBackgroundColor(Color.parseColor("#58CCA5"));
        questionFourResultTwo.setTextColor(BLACK);
        questionFourResultThree.setBackgroundColor(Color.parseColor("#58CCA5"));
        questionFourResultThree.setTextColor(BLACK);
        questionFourResultFour.setBackgroundColor(Color.parseColor("#58CCA5"));
        questionFourResultFour.setTextColor(BLACK);

        Boolean fourCorrect=false;
        if(questionFourResultOne.isChecked() && questionFourResultTwo.isChecked()
                && questionFourResultThree.isChecked() && questionFourResultFour.isChecked()) {
            fourCorrect=true;
            questionFourResultText.setText(R.string.correct);
        }
        else {
            questionFourResultText.setText(R.string.incorrect);
            questionFourResultText.setTextColor(Color.parseColor("#BA2123"));
        }

        /*
         * Output for Question Five Result based on Correctness
         */
        RadioButton questionFiveResult = (RadioButton) findViewById(R.id.fifth_q_correct);
        TextView questionFiveResultText = (TextView) findViewById(R.id.fifth_q_text);
        // Show user correct answer
        questionFiveResult.setBackgroundColor(Color.parseColor("#58CCA5"));
        questionFiveResult.setTextColor(BLACK);

        ArrayList<CompoundButton> idsFive = new ArrayList<>();
        idsFive.add((RadioButton) findViewById(R.id.fifth_q_wrong_one));
        idsFive.add((RadioButton) findViewById(R.id.fifth_q_wrong_two));
        idsFive.add((RadioButton) findViewById(R.id.fifth_q_wrong_three));
        Boolean fiveCorrect=findIncorrectAnswer(idsFive, questionFiveResultText);

        /*
         * Check to see if all answers are correct.
         */
        if(oneCorrect && twoCorrect && threeCorrect && fourCorrect && fiveCorrect) {
            ImageView image = (ImageView) findViewById(R.id.header_image);
            image.setImageResource(R.drawable.congrats);
        }

        /*
         * Scroll back to top of page
         */
        ScrollView sv = (ScrollView) findViewById(R.id.scroll_view);
                sv.fullScroll(ScrollView.FOCUS_UP);

    } // End SubmitAnswers()

    private Boolean findIncorrectAnswer(ArrayList<CompoundButton> ids, TextView text) {
        Boolean correct=true;
        for(int i =0; i<ids.size(); i++) {
            CompoundButton choice = ids.get(i);
            if(choice.isChecked()) {
                choice.setBackgroundColor(Color.parseColor("#BA2123"));
                choice.setTextColor(BLACK);
                correct = false;
            }
        }
        if (!correct) {// Set text and text color to output that the answer was incorrect
            text.setText(R.string.incorrect);
            text.setTextColor(Color.parseColor("#BA2123"));
        }
        else
            text.setText(R.string.correct);
        return correct;
    }

    /*
         * Original code (in submitAnswers) to determine the statement to output after calculating
         * the user's quiz score.
         *
            String results = "";
            if(qOne)
            results+="\nWoohoo! You answered Question One correctly!\n";
            if(qTwo)
                results+="\nWoohoo! You answered Question Two correctly!\n";
            if(qThree)
                results+="\nWoohoo! You answered Question Three correctly!\n";
            if(qFour)
                results+="\nWoohoo! You answered Question Four correctly!\n";
            if(qFive)
                results+="\nWoohoo! You answered Question Five correctly!\n";
            if(qOne && qTwo && qThree && qFour && qFive)
                results+="\nHurray! You've answered all of the questions correctly!" + "\nGive yourself a pat on the back!";
            else
                results+="\nUnfortunately, you did not answer all five questions correctly. But feel free to keep trying!";
         */
    /**
     * Code intended to start new Activity and output the results of the last Activity
     *
     Intent intent = new Intent(this, MainActivity2.class);
     intent.putExtra(EXTRA_MESSAGE, results);
     startActivity(intent);
     *
     */

}
