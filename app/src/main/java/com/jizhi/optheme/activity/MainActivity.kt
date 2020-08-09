package com.jizhi.optheme.activity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.jizhi.optheme.R
import com.jizhi.optheme.adapter.MainVpAdapter
import com.jizhi.optheme.fragment.AODFragment
import com.jizhi.optheme.fragment.FPAFragment
import com.jizhi.optheme.fragment.SettingsFragment
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.tab.QMUITab
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder
import com.qmuiteam.qmui.widget.tab.QMUITabIndicator
import com.qmuiteam.qmui.widget.tab.QMUITabSegment
import kotlinx.android.synthetic.main.activity_main.*

public class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val fragmentList = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        QMUIDisplayHelper.setFullScreen(this)

        initView()
        addFragment()
        addTab()
        initViewPager()
        initTab()

    }

    private fun initView() {
        btn_atyMain_login.setOnClickListener(this)
    }

    private fun addTab() {

        val tabBuilder: QMUITabBuilder = tab_atyMain.tabBuilder().setGravity(Gravity.CENTER)
        tabBuilder.setTextSize(
            QMUIDisplayHelper.dp2px(this, 25),
            QMUIDisplayHelper.dp2px(this, 28)
        )

        val FPA: QMUITab = tabBuilder
            .setText("FPA")
            .setColorAttr(
                R.attr.qmui_config_color_gray_7,
                R.attr.qmui_config_color_blue
            )
            .build(this)

        val AOD: QMUITab = tabBuilder
            .setText("AOD")
            .setColorAttr(
                R.attr.qmui_config_color_gray_7,
                R.attr.qmui_config_color_blue
            )
            .build(this)

        val Settings: QMUITab = tabBuilder
            .setText("Settings")
            .setColorAttr(
                R.attr.qmui_config_color_gray_7,
                R.attr.qmui_config_color_blue
            )
            .build(this)

        tab_atyMain.addTab(FPA)
        tab_atyMain.addTab(AOD)
        tab_atyMain.addTab(Settings)

    }

    private fun addFragment() {
        fragmentList.add(FPAFragment())
        fragmentList.add(AODFragment())
        fragmentList.add(SettingsFragment())
    }

    private fun initTab() {
        val indicatorHeight = QMUIDisplayHelper.dp2px(this, 5)
        tab_atyMain.setupWithViewPager(vp_atyMain)
        tab_atyMain.mode = QMUITabSegment.MODE_FIXED
        tab_atyMain.setIndicator(
            QMUITabIndicator(
                indicatorHeight, false, false
            )
        )

    }

    private fun initViewPager() {
        val mainVpAdapter: MainVpAdapter = MainVpAdapter(this, fragmentList)
        vp_atyMain.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        vp_atyMain.adapter = mainVpAdapter

    }

    override fun onClick(p0: View?) {
        var id = p0?.id
        when (id) {
            R.id.btn_atyMain_login -> {
                val intent = Intent()
                intent.setClass(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.bottom_in, R.anim.bottom_silent);
            }
        }
    }

}