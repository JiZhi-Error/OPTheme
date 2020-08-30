package com.jizhi.optheme

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jeremyliao.liveeventbus.LiveEventBus
import com.jizhi.optheme.sdk.Config
import com.jizhi.optheme.sdk.aod.entity.AodConfig
import com.jizhi.optheme.sdk.xp.tstorage.hok.XposedDataStorageClient
import dev.utils.app.VibrationUtils
import kotlinx.android.synthetic.main.activity_aod_notification.*

class AodNotificationActivity : AppCompatActivity() {

    private val TAG = this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aod_notification)

        val client = try {
            XposedDataStorageClient()
        } catch (e: Exception) {
            return
        } catch (e: ExceptionInInitializerError) {
            return
        }
        if (!client.isServiceAvailable) {
            return
        }
        if (!client.isServiceVersionEquals) {
            return
        }

        textView2.text = "已激活"

        val methodsInterface = client.methodsInterface
        val aodConfig = methodsInterface.aodConfig
        Log.i(TAG, "onCreate: $aodConfig")
        if (aodConfig == null) {
            Config.aodConfig = AodConfig()
        } else {
            Config.aodConfig = aodConfig
        }

        switch1.isChecked = Config.aodConfig.enabled
        switch2.isChecked = Config.aodConfig.aodNotificationCountEnabled
        switch3.isChecked = Config.aodConfig.aodCountEnabled
        switch4.isChecked = Config.aodConfig.aodImageEnabled

        editTextTextPersonName.setText("${Config.aodConfig.aodNotificationCount}")
        editTextTextPersonName2.setText("${Config.aodConfig.aodNotificationCountTime}")
        editTextTextPersonName4.setText("${Config.aodConfig.aodCount}")
        editTextTextPersonName3.setText("${Config.aodConfig.aodCountTime}")


        if (switch1.isChecked) {
            switch2.isClickable = true
            switch3.isClickable = true
        } else {
            switch2.isChecked = false
            switch3.isChecked = false
            switch4.isChecked = false
            switch2.isClickable = false
            switch3.isClickable = false
            switch4.isClickable = false
        }

        switch1.setOnCheckedChangeListener { _, b ->
            VibrationUtils.vibrate(2)
            if (b) {
                switch2.isClickable = b
                switch3.isClickable = b
            } else {
                switch2.isChecked = b
                switch3.isChecked = b
                switch4.isChecked = b
                switch2.isClickable = b
                switch3.isClickable = b
                switch4.isClickable = b
            }
        }

        button6.setOnClickListener {
            if (!switch4.isChecked) {
                Toast.makeText(this, "功能未开启", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            VibrationUtils.vibrate(2)
            val intent = Intent(this, AodImageActivity::class.java)
            startActivity(intent)
        }


        button4.setOnClickListener {

            VibrationUtils.vibrate(2)
            Config.aodConfig.enabled = switch1.isChecked
            Config.aodConfig.aodNotificationCountEnabled = switch2.isChecked
            Config.aodConfig.aodCountEnabled = switch3.isChecked
            Config.aodConfig.aodImageEnabled = switch4.isChecked
            methodsInterface.aodConfig = Config.aodConfig
            Log.i("com.jizhi.optheme", "onCreate: ${Config.aodConfig}")
            Toast.makeText(
                applicationContext,
                "开关功能需要重启\n(可以试试点击重启systemUI，不一定生效)\n不是所有情况重启SystemUI就可以解决，还是得重启手机系统\n重启SystemUI可能会遇到bug",
                Toast.LENGTH_SHORT
            ).show()
        }


        button.setOnClickListener {
            VibrationUtils.vibrate(2)
            LiveEventBus.get("com.jizhi.optheme.sdk\$AodNotificationCount", Int::class.java)
                .broadcast(editTextTextPersonName.text.toString().toInt(), true, false)
            Config.aodConfig.aodNotificationCount = editTextTextPersonName.text.toString().toInt()
            methodsInterface.aodConfig = Config.aodConfig
        }
        button2.setOnClickListener {
            VibrationUtils.vibrate(2)
            LiveEventBus.get("com.jizhi.optheme.sdk\$AodNotificationCountTime", Int::class.java)
                .broadcast(editTextTextPersonName2.text.toString().toInt(), true, false)
            Config.aodConfig.aodNotificationCountTime =
                editTextTextPersonName2.text.toString().toInt()
            methodsInterface.aodConfig = Config.aodConfig
        }

        button3.setOnClickListener {
            VibrationUtils.vibrate(2)
            LiveEventBus.get("com.jizhi.optheme.sdk\$AodCount", Int::class.java)
                .broadcast(editTextTextPersonName4.text.toString().toInt(), true, false)

            LiveEventBus.get("com.jizhi.optheme.sdk\$AodCountTime", Long::class.java)
                .broadcast(editTextTextPersonName3.text.toString().toLong(), true, false)
            Config.aodConfig.aodCount = editTextTextPersonName4.text.toString().toInt()
            Config.aodConfig.aodCountTime = editTextTextPersonName3.text.toString().toLong()
            methodsInterface.aodConfig = Config.aodConfig
        }
    }
}