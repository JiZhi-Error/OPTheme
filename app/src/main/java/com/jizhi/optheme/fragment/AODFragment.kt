package com.jizhi.optheme.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jizhi.optheme.data.MainData
import com.jizhi.optheme.R
import com.jizhi.optheme.activity.DetailsActivity
import com.jizhi.optheme.adapter.AodAndFpaAdapter

class AODFragment : Fragment() {

    private var mainDataList = mutableListOf<MainData>()
    private var rv_includeMain: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_aod, container, false)
        rv_includeMain = view.findViewById<RecyclerView>(R.id.rv_includeMain)

        addItem(10)

        initRv()
        return view
    }

    private fun addItem(count: Int) {
        for (i in 0 until count) {
            mainDataList.add(
                MainData(
                    0,
                    "Name" + i,
                    "$0." + i
                )
            )
        }
    }

    private fun initRv() {
        val aodAndFpaAdapter = AodAndFpaAdapter(
            R.layout.item_fpa_aod,
            mainDataList
        )
        rv_includeMain?.layoutManager = GridLayoutManager(context, 2)
        rv_includeMain?.adapter = aodAndFpaAdapter

        aodAndFpaAdapter.addChildClickViewIds((R.id.img_itemFpaAod_bg))
        aodAndFpaAdapter.setOnItemChildClickListener { adapter, view, position ->

            val intent = Intent()

            intent.setClass(context!!, DetailsActivity::class.java)
            intent.putExtra("value", "AOD")
            startActivity(intent)
            activity!!.overridePendingTransition(R.anim.bottom_in, R.anim.bottom_silent);
        }

    }

    companion object {
        private const val TAG = "AODFragment";
    }


}