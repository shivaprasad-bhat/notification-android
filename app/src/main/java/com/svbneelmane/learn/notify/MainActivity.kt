package com.svbneelmane.learn.notify

/**
 * Main Activity class that handles the notification
 * Date Created - 01-06-2021
 * @author Shivaprasad Bhat
 */
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.svbneelmane.learn.notify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Create a unique notification channel id and notification id
    private val CHANNEL_ID = "chanel_sample_notification"
    private val notificationId = 337192

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding and set the view
        val mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        createNotificationChannel()

        mainBinding.buttonClickHere.setOnClickListener {
            sendNotification()
        }

    }

    /**
     * Function to create a new notification channel
     * Mandatory to create custom channel after Android O
     * Set - {name, descriptionText, importance}
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Default Channel"
            val descriptionText = "Default Notification Channel Created by Developer"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * Function that sends a notification to user on calle
     * @param {}
     *
     */
    private fun sendNotification() {
        val intent = Intent(this, LandingActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val bitmapLarge =
            BitmapFactory.decodeResource(
                applicationContext.resources,
                R.mipmap.ic_shivaprasad
            )

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_shivaprasad)
            .setContentTitle("Notification From App")
            .setLargeIcon(bitmapLarge)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmapLarge))
            .setContentText("You've got this notification to notify you about the notification")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }
}