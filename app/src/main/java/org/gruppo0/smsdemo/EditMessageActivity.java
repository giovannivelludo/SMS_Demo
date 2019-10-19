package org.gruppo0.smsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditMessageActivity extends AppCompatActivity {

    //public static final String EXTRA_MESSAGE = "org.gruppo0.smsdemo.MESSAGE";
    //public static final String EXTRA_NUMBER = "org.gruppo0.smsdemo.NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_message);
    }

    /** Called when the user taps the SEND button */
    public void sendMessage(View view) {
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String message = editText2.getText().toString();
        EditText editText = (EditText) findViewById(R.id.editText);
        String number = editText.getText().toString();
    }
}
