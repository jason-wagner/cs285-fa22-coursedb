package edu.wilkes.mathcs.wagner.coursedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewCourseActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "edu.wilkes.mathcs.wagner.coursedatabase.EXTRA_TITLE";
    public static final String EXTRA_SUBJECT = "edu.wilkes.mathcs.wagner.coursedatabase.EXTRA_SUBJECT";
    public static final String EXTRA_NUMBER = "edu.wilkes.mathcs.wagner.coursedatabase.EXTRA_NUMBER";
    public static final String EXTRA_PROGLANG = "edu.wilkes.mathcs.wagner.coursedatabase.EXTRA_PROGLANG";

    private EditText editTitle, editSubject, editNumber, editProgLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        editTitle = findViewById(R.id.editTitle);
        editSubject = findViewById(R.id.editSubject);
        editNumber = findViewById(R.id.editNumber);
        editProgLang = findViewById(R.id.editProgLang);

        final Button button = findViewById(R.id.button_save);

        button.setOnClickListener(view -> {
            Intent i = new Intent();

            if (TextUtils.isEmpty(editTitle.getText()) ||
                    TextUtils.isEmpty(editSubject.getText()) ||
                    TextUtils.isEmpty(editNumber.getText()) ||
                    TextUtils.isEmpty(editProgLang.getText())) {
                setResult(RESULT_CANCELED, i);
            } else {
                String title = editTitle.getText().toString();
                String subject = editSubject.getText().toString();
                String number = editNumber.getText().toString();
                String progLang = editProgLang.getText().toString();

                i.putExtra(EXTRA_TITLE, title);
                i.putExtra(EXTRA_SUBJECT, subject);
                i.putExtra(EXTRA_NUMBER, number);
                i.putExtra(EXTRA_PROGLANG, progLang);
                setResult(RESULT_OK, i);
            }

            finish();
        });
    }
}