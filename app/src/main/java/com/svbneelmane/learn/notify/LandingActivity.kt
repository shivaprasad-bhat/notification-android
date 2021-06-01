package com.svbneelmane.learn.notify

/**
 * This is the activity which will be launched after touching the notification from notification bar
 * Date: 01-06-2021
 * @author Shivaprasad Bhat
 */
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.svbneelmane.learn.notify.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val landingBinding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(landingBinding.root)

        Snackbar.make(landingBinding.constraintLayoutLanding, "Welcome to Landing Page", Snackbar.LENGTH_LONG).show()
    }
}