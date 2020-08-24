package com.jizhi.optheme

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jeremyliao.liveeventbus.LiveEventBus
import com.jizhi.optheme.sdk.aod.entity.AodImageConfig
import kotlinx.android.synthetic.main.activity_aod_image.*

class AodImageActivity : AppCompatActivity() {

    val TAG = this::class.java.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aod_image)
        val aodImageConfig = AodImageConfig()
        but1.setOnClickListener {
            et_atyMoreSettings_marginLeft.visibility = View.GONE
            et_atyMoreSettings_marginRight.visibility = View.GONE
            et_atyMoreSettings_marginTop.visibility = View.VISIBLE
            et_atyMoreSettings_marginBottom.visibility = View.VISIBLE
            et_atyMoreSettings_marginLeft.text = null
            et_atyMoreSettings_marginRight.text = null
            aodImageConfig.aodImageGravity = Gravity.CENTER_HORIZONTAL
            Toast.makeText(this, "水平居中", Toast.LENGTH_SHORT).show()
        }
        but2.setOnClickListener {
            et_atyMoreSettings_marginLeft.visibility = View.VISIBLE
            et_atyMoreSettings_marginRight.visibility = View.VISIBLE
            et_atyMoreSettings_marginTop.visibility = View.GONE
            et_atyMoreSettings_marginBottom.visibility = View.GONE
            et_atyMoreSettings_marginTop.text = null
            et_atyMoreSettings_marginBottom.text = null
            aodImageConfig.aodImageGravity = Gravity.CENTER_VERTICAL
            Toast.makeText(this, "垂直居中", Toast.LENGTH_SHORT).show()
        }
        but3.setOnClickListener {
            et_atyMoreSettings_marginLeft.visibility = View.GONE
            et_atyMoreSettings_marginRight.visibility = View.GONE
            et_atyMoreSettings_marginTop.visibility = View.GONE
            et_atyMoreSettings_marginBottom.visibility = View.GONE
            et_atyMoreSettings_marginTop.text = null
            et_atyMoreSettings_marginBottom.text = null
            et_atyMoreSettings_marginLeft.text = null
            et_atyMoreSettings_marginRight.text = null
            aodImageConfig.aodImageGravity = Gravity.CENTER
            Toast.makeText(this, "居中", Toast.LENGTH_SHORT).show()
        }
        but4.setOnClickListener {
            et_atyMoreSettings_marginLeft.visibility = View.GONE
            et_atyMoreSettings_marginRight.visibility = View.GONE
            et_atyMoreSettings_marginTop.visibility = View.GONE
            et_atyMoreSettings_marginBottom.visibility = View.GONE
            et_atyMoreSettings_marginTop.text = null
            et_atyMoreSettings_marginBottom.text = null
            et_atyMoreSettings_marginLeft.text = null
            et_atyMoreSettings_marginRight.text = null
            aodImageConfig.aodImageGravity = Gravity.NO_GRAVITY
            Toast.makeText(this, "无", Toast.LENGTH_SHORT).show()
        }

        but5.setOnClickListener {
            aodImageConfig.aodImageWidth = et_atyMoreSettings_width.text.toString().toInt()
            aodImageConfig.aodImageHeight = et_atyMoreSettings_height.text.toString().toInt()
            val marginLeft = et_atyMoreSettings_marginLeft.text.toString()
            val marginTop = et_atyMoreSettings_marginTop.text.toString()
            val marginRight = et_atyMoreSettings_marginRight.text.toString()
            val marginBottom = et_atyMoreSettings_marginBottom.text.toString()
            Log.i(TAG, "onCreate: marginLeft ：$marginLeft")
            Log.i(TAG, "onCreate: marginTop ：$marginTop")
            Log.i(TAG, "onCreate: marginRight ：$marginRight")
            Log.i(TAG, "onCreate: marginBottom ：$marginBottom")
            if (marginLeft.isEmpty()) {
                aodImageConfig.aodImageMarginsLeft = 0
            } else {
                aodImageConfig.aodImageMarginsLeft = marginLeft.toInt()
            }
            if (marginTop.isEmpty()) {
                aodImageConfig.aodImageMarginsTop = 0
            } else {
                aodImageConfig.aodImageMarginsTop = marginTop.toInt()
            }
            if (marginRight.isEmpty()) {
                aodImageConfig.aodImageMarginsRight = 0
            } else {
                aodImageConfig.aodImageMarginsRight = marginRight.toInt()
            }
            if (marginBottom.isEmpty()) {
                aodImageConfig.aodImageMarginsBottom = 0
            } else {
                aodImageConfig.aodImageMarginsBottom = marginBottom.toInt()
            }
            aodImageConfig.aodViewDrawable = R.drawable.diewu
            Log.i(TAG, "onCreate: $aodImageConfig")
            LiveEventBus.get(
                "com.jizhi.optheme.sdk\$AodImageConfig",
                AodImageConfig::class.java
            )
                .broadcast(aodImageConfig, true, false)
            Toast.makeText(this, "确定", Toast.LENGTH_SHORT).show()

        }

    }
}