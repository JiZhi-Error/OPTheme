package com.jizhi.optheme.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jizhi.optheme.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author: YunYan
 * @description:  设置页 recycleView 适配器
 */
public class SettingsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public SettingsAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.tv_itemSettings_name,s);
    }
}
