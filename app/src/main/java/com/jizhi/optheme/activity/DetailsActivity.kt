package com.jizhi.optheme.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jizhi.optheme.R
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        QMUIDisplayHelper.setFullScreen(this)

        tv_atyDetails_name.text = "流光溢彩"
        tv_atyDetails_author.text = "无锡二胖"
        tv_atyDetails_size.text = "1.12MB"

        btn_atyDetails_close.setOnClickListener {
            finish()
        }

        val vallue = intent.getStringExtra("value")
        if (vallue.equals("FPA")) {
            et_atyDetails_fps.visibility = View.VISIBLE
            ll_atyDetails_aod.visibility = View.GONE
        } else {
            et_atyDetails_fps.visibility = View.GONE
            ll_atyDetails_aod.visibility = View.VISIBLE
        }

    }


    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.bottom_silent, R.anim.bottom_out);
    }

}