package com.jizhi.optheme.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jizhi.optheme.widget.CDKDialog
import com.jizhi.optheme.R
import com.jizhi.optheme.activity.AboutActivity
import com.jizhi.optheme.adapter.SettingsAdapter

class SettingsFragment : Fragment() {

    private var nameList = mutableListOf<String>("订单管理", "激活码", "关于")
    private var rv_includeMain: RecyclerView? = null
    private val mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        rv_includeMain = view.findViewById<RecyclerView>(R.id.rv_includeMain)
        initRv()
        return view
    }

    private fun initRv() {
        val settingsAdapter = SettingsAdapter(R.layout.item_settings, nameList)
        rv_includeMain?.layoutManager = LinearLayoutManager(context)
        rv_includeMain?.adapter = settingsAdapter

        settingsAdapter.setOnItemClickListener { adapter, view, position ->
            when (position) {
                0 -> {

                }
                1 -> {
                    showDialog()
                }
                2 -> {
                    val intent = Intent()
                    intent.setClass(context!!, AboutActivity::class.java)
                    startActivity(intent)
                    activity!!.overridePendingTransition(R.anim.right_in, R.anim.bottom_silent);
                }
            }

        }


    }

    private fun showDialog() {
        val cdkDialog = CDKDialog(context!!, "CDK")
        cdkDialog.show()
    }


}