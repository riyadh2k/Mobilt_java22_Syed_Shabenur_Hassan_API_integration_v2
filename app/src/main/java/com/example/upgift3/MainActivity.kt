package com.example.upgift3

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.nextPageButton).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, FirstFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    // Handling the back button
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
