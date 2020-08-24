package com.jizhi.optheme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jizhi.optheme.sdk.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        button.setOnClickListener {
//            val intent = Intent(this, AodImageActivity::class.java)
//            startActivity(intent)
//        }
        button2.setOnClickListener {
            val intent = Intent(this, AodNotificationActivity::class.java)
            startActivity(intent)
        }
        button5.setOnClickListener {
            Utils.findProcessAndKill(this)
        }
    }
}