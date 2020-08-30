package com.jizhi.optheme

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jizhi.optheme.sdk.BuildConfig
import com.jizhi.optheme.sdk.utils.Utils
import dev.DevUtils
import dev.utils.app.VibrationUtils
import dev.utils.app.logger.DevLogger
import dev.utils.app.logger.LogConfig
import dev.utils.app.logger.LogLevel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (BuildConfig.DEBUG){
            val logConfig: LogConfig = LogConfig()
            logConfig.logLevel = LogLevel.DEBUG
            logConfig.sortLog = true // 美化日志, 边框包围

            logConfig.methodCount = 0
            DevLogger.init(logConfig)
            // 打开 lib 内部日志 - 线上环境, 不调用方法就行
            DevUtils.openLog()
            DevUtils.openDebug()
        }
//        button.setOnClickListener {
//            VibrationUtils.vibrate(2)
//            val intent = Intent(this, AodImageActivity::class.java)
//            startActivity(intent)
//        }
        button2.setOnClickListener {
            VibrationUtils.vibrate(2)
            val intent = Intent(this, AodNotificationActivity::class.java)
            startActivity(intent)
        }
        button5.setOnClickListener {
            VibrationUtils.vibrate(2)
            Utils.findProcessAndKill(this)
        }

        textView4.setOnClickListener {
            val uri: Uri = Uri.parse("https://github.com/JiZhi-Error/OPTheme")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

    }


}