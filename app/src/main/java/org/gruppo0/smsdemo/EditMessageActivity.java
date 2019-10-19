package org.gruppo0.smsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditMessageActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "org.gruppo0.smsdemo.MESSAGE";
    public static final String EXTRA_NUMBER = "org.gruppo0.smsdemo.NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_message);
    }

    /** Called when the user taps the SEND button */
    public void sendMessage() {
        // Do something
    }
}
