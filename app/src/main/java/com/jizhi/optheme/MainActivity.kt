package com.jizhi.optheme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var i = 0;
        button.setOnClickListener {
            LiveEventBus.get("com.jizhi.optheme.sdk\$AodNotification", Int::class.java)
                .broadcast(editTextTextPersonName.text.toString().toInt(),true,false)
        }
        button2.setOnClickListener {
            LiveEventBus.get("com.jizhi.optheme.sdk\$AodTime", Int::class.java)
                .broadcast(editTextTextPersonName2.text.toString().toInt(),true,false)
        }
    }
}