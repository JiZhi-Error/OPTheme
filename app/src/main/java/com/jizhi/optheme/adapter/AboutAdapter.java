package com.jizhi.optheme.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jizhi.optheme.R;
import com.jizhi.optheme.data.AboutData;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author: YunYan
 * @description: aod 和 fpa 适配器
 */
public class AboutAdapter extends BaseQuickAdapter<AboutData, BaseViewHolder> {

    private boolean isPoweredBy = true;

    public AboutAdapter(int layoutResId, @Nullable List<AboutData> data, boolean isPoweredBy) {
        super(layoutResId, data);
        this.isPoweredBy = isPoweredBy;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AboutData aboutData) {

        TextView position = baseViewHolder.getView(R.id.tv_itemAbout_position);

        baseViewHolder.setText(R.id.tv_itemAbout_name, aboutData.getName());
        baseViewHolder.setBackgroundResource(R.id.img_itemAbout_photo, aboutData.getPhoto());
        if (isPoweredBy) {
            CardView cardView = baseViewHolder.getView(R.id.cv_itemAbout_cardView);
            position.setVisibility(View.VISIBLE);
            position.setText(aboutData.getPosition());
            cardView.setRadius(QMUIDisplayHelper.dp2px(position.getContext(),40));
        } else {
            CardView cardView = baseViewHolder.getView(R.id.cv_itemAbout_cardView);
            position.setVisibility(View.GONE);
            cardView.setRadius(QMUIDisplayHelper.dp2px(position.getContext(),15));
        }


    }
}
