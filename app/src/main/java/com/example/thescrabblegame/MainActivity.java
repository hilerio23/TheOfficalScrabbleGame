/**
 *
 * @author Tamsen Dean
 * @author Anabel Hilerio
 * @author Alec Uyematsu
 * @author Samone Watkins
 *
 *
 * ---------EXTERNAL CITATION--------------
 * Date: March 10, 2021
 * Problem: Anabel was having an issue with how to properly use edit text
 *          NOTE: This citation does belong in activity_main.xml, but won't let me
 *          comment accordingly.
 * Source: https://developer.android.com/reference/android/widget/EditText
 * Solution: Use the Android SDK to figure out how to implement it in the xml file
 *
 * -------EXTERNAL CITATION-----------------
 * Date: March 10, 2021
 * Problem: Anabel didn't remeber how to append text to the edit text
 * Source: https://stackoverflow.com/questions/9981334/how-to-append-text-into-an-edit
 *         text-in-android#:~:text=1%20Answer&text=Just%20use%20append()%20of,the%20en
 *         d%20of%20the%20Editable.
 * Solution: use EditText.append() to append the text on screen.
 *
 */
package com.example.thescrabblegame;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thescrabblegame.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button runTest = (Button)findViewById(R.id.button);
        EditText info = (EditText)findViewById(R.id.editTextTextMultiLine);

        runTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info.getText().clear();

                ScrabbleState firstInstance = new ScrabbleState();
                ScrabbleState secondInstance = new ScrabbleState(firstInstance);
                firstInstance.playWord(firstInstance);
                info.append("The player is writing down the word frog\n");

                firstInstance.isLegal(firstInstance);
                info.append("The move the player made was legal\n");

                firstInstance.exchange(firstInstance);
                info.append("The player did a letter exchange\n");

                firstInstance.pass(firstInstance);
                info.append("The player passed on their turn\n");

                ScrabbleState thirdInstance = new ScrabbleState();
                ScrabbleState fourthInstance = new ScrabbleState(thirdInstance);

                info.append(secondInstance.toString());
                info.append(fourthInstance.toString());
            }
        });
    }
}