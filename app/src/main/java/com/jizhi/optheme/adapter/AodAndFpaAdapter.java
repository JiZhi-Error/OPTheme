package com.jizhi.optheme.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jizhi.optheme.data.MainData;
import com.jizhi.optheme.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author: YunYan
 * @description:  aod 和 fpa 适配器
 */
public class AodAndFpaAdapter extends BaseQuickAdapter<MainData, BaseViewHolder> {

    public AodAndFpaAdapter(int layoutResId, @Nullable List<MainData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MainData mainData) {
//        baseViewHolder.setBackgroundResource(R.id.img_itemFpaAod_bg, mainData.getBackgroungd());
        baseViewHolder.setText(R.id.tv_itemFpaAod_name, mainData.getName());
        baseViewHolder.setText(R.id.tv_itemFpaAod_money, mainData.getMoney());

    }

}
