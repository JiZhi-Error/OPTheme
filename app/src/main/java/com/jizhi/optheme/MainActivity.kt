package com.jizhi.optheme

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.UriUtils
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
//        button6.setOnClickListener {
//            val intent = Intent(Intent.ACTION_GET_CONTENT)
//            intent.type = "*/*"
//            intent.addCategory(Intent.CATEGORY_OPENABLE)
//            startActivityForResult(intent, 1)
//        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK) {
//            val uri: Uri? = data!!.data
//            val uri2File = UriUtils.uri2File(uri!!)
//            println(uri2File)
//        }

    }

}