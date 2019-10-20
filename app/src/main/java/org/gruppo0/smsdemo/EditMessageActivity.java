package org.gruppo0.smsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditMessageActivity extends AppCompatActivity {

    //public static final String EXTRA_MESSAGE = "org.gruppo0.smsdemo.MESSAGE";
    //public static final String EXTRA_NUMBER = "org.gruppo0.smsdemo.NUMBER";
    public static final int SEND_SMS_FROM_EDITOR = 1;
    private BroadcastReceiver onSend = null;
    private BroadcastReceiver onDeliver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_message);
    }

    // called when the user taps the SEND button
    public void sendMessage(View view) {
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String message = editText2.getText().toString();
        EditText editText = (EditText) findViewById(R.id.editText);
        String number = editText.getText().toString();

        // check if SEND_SMS permission was granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // if not
            ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_FROM_EDITOR);
        }
        // yet to implement: ActivityCompat.OnRequestPermissionsResultCallback

        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent sent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent("SMS_SENT"), 0);
        PendingIntent delivered = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent("SMS_DELIVERED"), 0);
        onSend = new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                if (getResultCode()== Activity.RESULT_OK)
                    Toast.makeText(arg0, "SMS sent", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(arg0, "Error while sending", Toast.LENGTH_LONG).show();
            }
        };
        onDeliver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                if (getResultCode()==Activity.RESULT_OK)
                    Toast.makeText(arg0, "SMS delivered", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(arg0, "Error while delivering", Toast.LENGTH_LONG).show();
            }
        };
        registerReceiver(onSend, new IntentFilter("SMS_SENT"));
        registerReceiver(onDeliver, new IntentFilter("SMS_DELIVERED"));
        smsManager.sendTextMessage(number, null, message, sent, delivered);
    }
}
