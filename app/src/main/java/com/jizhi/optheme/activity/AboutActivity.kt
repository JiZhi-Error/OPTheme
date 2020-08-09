package com.jizhi.optheme.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jizhi.optheme.R
import com.jizhi.optheme.adapter.AboutAdapter
import com.jizhi.optheme.data.AboutData
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    private var poweredByList = mutableListOf<AboutData>()
    private var thanksList = mutableListOf<AboutData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        QMUIDisplayHelper.setFullScreen(this)

        tv_atyAbout_users.text = "6.66k+"
        tv_atyAbout_like.text = "2.33k+"
        addItem()
        initRvPoweredBy()
        initRvThanks()
    }

    private fun initRvPoweredBy() {

        val poweredByAdapter = AboutAdapter(R.layout.item_about, poweredByList, true)
        rv_atyAbout_poweredBy.adapter = poweredByAdapter
        rv_atyAbout_poweredBy.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }

    private fun initRvThanks() {
        val thanksAdapter = AboutAdapter(R.layout.item_about, thanksList, false)
        rv_atyAbout_thanks.adapter = thanksAdapter
//        rv_atyAbout_thanks.layoutManager =
//            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_atyAbout_thanks.layoutManager =
            GridLayoutManager(this, 2, RecyclerView.HORIZONTAL, false)
    }

    private fun addItem() {
        poweredByList.add(AboutData(R.drawable.ic_launcher_background, "天狼", "创建者"))
        poweredByList.add(AboutData(R.drawable.ic_launcher_background, "极致", "代码贡献"))
        poweredByList.add(AboutData(R.drawable.ic_launcher_background, "云烟", "UI编写"))
        thanksList.add(AboutData(R.drawable.ic_launcher_background, "绑兔", ""))
        thanksList.add(AboutData(R.drawable.ic_launcher_background, "天伞桜", ""))
        thanksList.add(AboutData(R.drawable.ic_launcher_background, "不遥远", ""))
        thanksList.add(AboutData(R.drawable.ic_launcher_background, "水位清澈", ""))
        thanksList.add(AboutData(R.drawable.ic_launcher_background, "毛仔", ""))
        thanksList.add(AboutData(R.drawable.ic_launcher_background, "无锡二胖", ""))
        thanksList.add(AboutData(R.drawable.ic_launcher_background, "测试", ""))
        thanksList.add(AboutData(R.drawable.ic_launcher_background, "测试", ""))
        thanksList.add(AboutData(R.drawable.ic_launcher_background, "测试", ""))
        thanksList.add(AboutData(R.drawable.ic_launcher_background, "测试", ""))
        thanksList.add(AboutData(R.drawable.ic_launcher_background, "测试", ""))
        thanksList.add(AboutData(R.drawable.ic_launcher_background, "测试", ""))
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.bottom_silent, R.anim.bottom_out);
    }

}