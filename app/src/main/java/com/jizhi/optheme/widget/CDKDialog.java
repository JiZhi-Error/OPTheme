package com.jizhi.optheme.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jizhi.optheme.R;

/**
 * @author: YunYan
 * @description:  CDK 弹出框
 */
public class CDKDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private String title = "";
    private TextView tv_cdkDialog_title;
    private EditText et_cdkDialog_cdk;
    private Button btn_cdkDialog_done;

    public CDKDialog(@NonNull Context context, String title) {
        super(context, R.style.DialogTheme);
        this.context = context;
        this.title = title;
    }

    protected CDKDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_cdk, null);
        setContentView(view);

        tv_cdkDialog_title = (TextView) view.findViewById(R.id.tv_cdkDialog_title);
        et_cdkDialog_cdk = (EditText) view.findViewById(R.id.et_cdkDialog_cdk);
        btn_cdkDialog_done = (Button) view.findViewById(R.id.btn_cdkDialog_done);
        btn_cdkDialog_done.setOnClickListener(this);
        tv_cdkDialog_title.setText(title);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = null;
        if (dialogWindow != null) {
            lp = dialogWindow.getAttributes();
            DisplayMetrics d = context.getResources().getDisplayMetrics();
            lp.width = (int) (d.widthPixels * 0.8);
            dialogWindow.setAttributes(lp);
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_cdkDialog_done:
                et_cdkDialog_cdk.getText();
                break;
            default:
        }
    }
}
