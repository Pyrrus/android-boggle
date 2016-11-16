package com.example.guest.boggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.randomText) TextView randomText;
//    @Bind(R.id.listWord) TextView listWord;
//    @Bind(R.id.wordView) ScrollView wordView;
    @Bind(R.id.listView) ListView listView;
    @Bind(R.id.editText) EditText editText;
    @Bind(R.id.wordButton) Button wordButton;
    ArrayList<String> words = new ArrayList<String>();
    String randomLatters = wordRandom();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        randomText.setText(randomLatters);

        wordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = editText.getText().toString();

                if (word.length() < 3) {
                    Toast.makeText(MainActivity.this, "word is to short need to be more then 2", Toast.LENGTH_SHORT).show();
                } else {
                    if (containsWord(randomLatters, word)) {
                        words.add(word);
                        Toast.makeText(MainActivity.this, "It much with the random letters", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "It not much with any letters", Toast.LENGTH_SHORT).show();
                    }

                }
                editText.setText("");
                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, words);
                listView.setAdapter(adapter);
            }
        });


    }

    private String wordRandom() {
        Random rand = new Random();
        final char[] alphabet = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        final Set<Character> vowels = new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        char[] randomLetters = new char[8];
        int vowelCount;

        do {
            for (int i = 0; i < randomLetters.length; i++) {
                int r = rand.nextInt(alphabet.length);
                randomLetters[i] = alphabet[r];
            }
            vowelCount = 0;
            for (char actualChar: randomLetters) {
                if (vowels.contains(actualChar))
                    vowelCount++;
            }
        } while (vowelCount < 2);

        String s = new StringBuilder().append("").append(randomLetters).toString();

        return s;
    }

    boolean containsWord(String random, String word) {
        List<Character> list = new LinkedList<Character>();
        for (char latter : random.toCharArray()) {
            list.add(latter);
        }
        for (Character latter : word.toCharArray()) {
            if (!list.remove(latter)) {
                return false;
            }
        }
        return true;
    }
}
