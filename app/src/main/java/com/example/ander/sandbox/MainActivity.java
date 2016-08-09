package com.example.ander.sandbox;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NotificationCompat.Builder mNotificationBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, RedirectActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_ONE_SHOT);

        mNotificationBuilder = new NotificationCompat.Builder(this);
        mNotificationBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        mNotificationBuilder.setContentTitle("This is a title");
        mNotificationBuilder.setContentText("Herp derpsum tee le sherp perper berp herpsuNer serp le tee sherp mer. Perp serp berps me.");
        mNotificationBuilder.setPriority(Notification.PRIORITY_MAX);
        mNotificationBuilder.setContentIntent(pendingIntent);
        mNotificationBuilder.addAction(android.R.drawable.btn_plus, "ahah!", pendingIntent);


        ConnectivityManager conMag = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMag.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            mNotificationBuilder.setAutoCancel(true);

            NotificationCompat.BigPictureStyle pictureStyle = new NotificationCompat.BigPictureStyle();
            pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ic_stat_name));

            mNotificationBuilder.setStyle(pictureStyle);
            NotificationManagerCompat.from(MainActivity.this).notify(1, mNotificationBuilder.build());



        } else {
            Toast.makeText(MainActivity.this, "Please check your internet connection!", Toast.LENGTH_LONG).show();
            mNotificationBuilder.setAutoCancel(false);

            NotificationCompat.BigPictureStyle pictureStyle = new NotificationCompat.BigPictureStyle();
            pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), android.R.drawable.stat_sys_warning));

            mNotificationBuilder.setStyle(pictureStyle);
            NotificationManagerCompat.from(MainActivity.this).notify(1, mNotificationBuilder.build());
        }

    }
}
