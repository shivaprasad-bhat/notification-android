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
import com.svbneelmane.learn.notify.utils.Constants.Companion.CHANNEL_DESCRIPTION
import com.svbneelmane.learn.notify.utils.Constants.Companion.CHANNEL_ID
import com.svbneelmane.learn.notify.utils.Constants.Companion.CHANNEL_NAME
import com.svbneelmane.learn.notify.utils.Constants.Companion.NOTIFICATION_DESCRIPTION
import com.svbneelmane.learn.notify.utils.Constants.Companion.NOTIFICATION_ID
import com.svbneelmane.learn.notify.utils.Constants.Companion.NOTIFICATION_TITLE
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Timber initialization
        Timber.plant(Timber.DebugTree())

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
        Timber.d("Creating Notification Channel")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_NAME
            val descriptionText = CHANNEL_DESCRIPTION
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager? =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
            notificationManager?.createNotificationChannel(channel)
            Timber.d("Notification channel created..")
        }

    }

    /**
     * Function that sends a notification to user on called
     * @param {}
     *
     */
    private fun sendNotification() {
        Timber.d("Sending notification..")
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
            .setContentTitle(NOTIFICATION_TITLE)
            .setLargeIcon(bitmapLarge)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmapLarge))
            .setContentText(NOTIFICATION_DESCRIPTION)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
            Timber.d("Notified..")
        }
    }
}