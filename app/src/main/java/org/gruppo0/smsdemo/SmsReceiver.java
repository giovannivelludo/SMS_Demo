package org.gruppo0.smsdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    public static final String EXTRA_MESSAGE = "org.gruppo0.smsdemo.MESSAGE";
    private String TAG = SmsReceiver.class.getSimpleName();

    public SmsReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the data (SMS data) bound to intent
        Bundle bundle = intent.getExtras();

        SmsMessage[] msgs = null;

        String str = "";

        if (bundle != null) {
            // Retrieve the SMS Messages received
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            // For every SMS message received
            for (int i=0; i < msgs.length; i++) {
                // Convert Object array
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                // Sender's phone number
                str += "SMS from " + msgs[i].getOriginatingAddress() + " : ";
                // Fetch the text message
                str += msgs[i].getMessageBody().toString();
                // Newline :-)
                str += "\n";
            }

            // Display the entire SMS Message
            Log.d(TAG, str);

            // send intent with message to main activity

            /** Intent smsIntent = new Intent("SmsReceiver");
            smsIntent.putExtra(EXTRA_MESSAGE, str);
            context.sendBroadcast(smsIntent); */

            Intent smsIntent = new Intent(context, MainActivity.class);
            smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            smsIntent.putExtra(EXTRA_MESSAGE, str);
            context.startActivity(smsIntent);
        }
    }
}