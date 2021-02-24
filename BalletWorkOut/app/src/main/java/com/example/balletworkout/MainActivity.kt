package com.example.balletworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Click event for start Button which we have created in XML.
        llStart.setOnClickListener {
            val intent = Intent(this, BalletActivity::class.java)
            startActivity(intent)
            //END
        }
        llBMI.setOnClickListener {
            // Launching the BMI Activity
            val intent = Intent(this, BMIBalletActivity::class.java)
            startActivity(intent)
        }

        llHistory.setOnClickListener {
            // Launching the History Activity
            val intent = Intent(this, HistoryBalletActivity::class.java)
            startActivity(intent)
        }
    }
}