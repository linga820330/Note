package com.example.note;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class dialog_insert extends Dialog {
    private static int default_width = 300; //默認寬度
    private static int default_height = 120;//默認高度

    public dialog_insert(Context context, View layout, int style) {
        this(context, default_width, default_height, layout, style);
    }

    public dialog_insert(Context context, int width, int height, View layout, int style) {
        super(context, style);
        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }
}
