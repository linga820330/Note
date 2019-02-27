package com.example.note;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends Fragment {

    private EditText et_Note;
    private View view;
    private Button bt_left,bt_right,bt_plus,bt_delet;
    private TextView tv_page;
    private int PageCount = 1;
    private SharedPreferences sharedPreferences;
    private AlertDialog alertDialog ;

    public NoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_note, container, false);
        setView();
        sharedPreferences = getContext().getSharedPreferences("NOTE", Context.MODE_PRIVATE);

        //讀取總頁數
        PageCount = sharedPreferences.getInt("PageCount",1);

        bt_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取得當前page
                int page = Integer.valueOf(tv_page.getText().toString());
                if (page<PageCount) {
                    //儲存當前頁面內容
                    String s_pageLast = String.valueOf(page);
                    sharedPreferences.edit()
                            .putString(s_pageLast, String.valueOf(et_Note.getText()))
                            .commit();

                    //讀取下一頁內容
                    page += 1;
                    String s_pageNew = String.valueOf(page);
                    tv_page.setText(s_pageNew);
                    String s_content = sharedPreferences.getString(s_pageNew, "");
                    et_Note.setText(s_content);
                }
            }
        });

        bt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = Integer.valueOf(tv_page.getText().toString());
                if(page>1) {
                    //儲存當前頁面內容
                    String s_pageLast = String.valueOf(page);
                    sharedPreferences.edit()
                            .putString(s_pageLast, String.valueOf(et_Note.getText()))
                            .commit();

                    //讀取上一頁內容
                    page -= 1;
                    String s_pageNew = String.valueOf(page);
                    tv_page.setText(s_pageNew);
                    String s_content = sharedPreferences.getString(s_pageNew,"");
                    et_Note.setText(s_content);
                }

            }
        });

        //新增頁面
        bt_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //頁面總數+1
                PageCount+=1;
                sharedPreferences.edit()
                        .putInt("PageCount",PageCount)
                        .commit();

                //儲存當前頁面
                int page = Integer.valueOf(tv_page.getText().toString());
                String s_pageLast = String.valueOf(page);
                sharedPreferences.edit()
                        .putString(s_pageLast, String.valueOf(et_Note.getText()))
                        .commit();

                //跳到新增的頁面page和內容
                String s_PageCount = String.valueOf(PageCount);
                tv_page.setText(s_PageCount);
                String s_content = sharedPreferences.getString(s_PageCount,"");
                et_Note.setText(s_content);
            }
        });

        //刪除當前頁面
        bt_delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取得當前頁數
                final int page = Integer.valueOf(tv_page.getText().toString());

                //page只有1頁時不能刪除
                if(PageCount>1) {
//客製化Dialog
                    AlertDialog.Builder logoutDialog = new AlertDialog.Builder(getContext());
                    View custom_dialog = getLayoutInflater().inflate(R.layout.costom_dialog_note,null);
                    Button dialog_bt_p =(Button) custom_dialog.findViewById(R.id.dialog_bt_p);
                    Button dialog_bt_n =(Button) custom_dialog.findViewById(R.id.dialog_bt_n);
                    TextView tv_cusDialog_Message = (TextView) custom_dialog.findViewById(R.id.tv_cusDialog_Message);
                    tv_cusDialog_Message.setText("確定要刪除第"+page+"頁 全部內容?");
                    logoutDialog.setView(custom_dialog);
                    final AlertDialog dialog = logoutDialog.create();
                    dialog_bt_p.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DeletPage(page);
                            dialog.cancel();
                        }
                    });
                    dialog_bt_n.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();

                    //alertDialog = new AlertDialog.Builder(getContext())
                    //        .setTitle("刪除此頁")
                    //        .setMessage("確定要刪除第"+page+"頁全部內容?")
                    //        .setPositiveButton("刪除", new DialogInterface.OnClickListener() {
                    //            @Override
                    //            public void onClick(DialogInterface dialog, int which) {
                    //                DeletPage(page);
                    //            }
                    //        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    //            @Override
                    //            public void onClick(DialogInterface dialog, int which) {
//
                    //            }
                    //        }).show();

                    }
                }

        });
        return view;
    }

    private void DeletPage(int page) {
        //若刪除的是最後一頁，當前的page要-1
        if (page == PageCount) {
            tv_page.setText(String.valueOf(page - 1));
        }

        //若刪除的是最後一頁以外的頁面，當前的page不變，後面頁面的內容依序覆蓋前面頁面的內容
        for (int i = page; i <= PageCount; i++) {
            //取得後一頁內容
            String s_pageNext = String.valueOf(i + 1);
            String s_contentNext = sharedPreferences.getString(s_pageNext, " ");
            //覆蓋當前頁面內容
            String s_page = String.valueOf(i);
            sharedPreferences.edit()
                    .putString(s_page, s_contentNext)
                    .commit();
        }
        //總頁數-1
        PageCount -= 1;
        sharedPreferences.edit()
                .putInt("PageCount",PageCount)
                .commit();

        //取得當前page儲存的頁面內容顯示
        int pageNow = Integer.valueOf(tv_page.getText().toString());
        String s_pageNew = String.valueOf(pageNow);
        String s_content = sharedPreferences.getString(s_pageNew,"");
        et_Note.setText(s_content);
    }
    private void setView() {
        tv_page = (TextView)view.findViewById(R.id.tv_page);
        et_Note = (EditText)view.findViewById(R.id.et_Note);
        bt_left = (Button)view.findViewById(R.id.bt_left);
        bt_right = (Button)view.findViewById(R.id.bt_right);
        bt_plus = (Button)view.findViewById(R.id.bt_plus);
        bt_delet = (Button)view.findViewById(R.id.bt_delet);
    }

    @Override
    public void onResume() {
        super.onResume();
        //取得上次離開時的頁面page
        int LastPage = sharedPreferences.getInt("LastPage",1);
        String s_LastPage = String.valueOf(LastPage);
        tv_page.setText(s_LastPage);
        //載入上次離開時的頁面內容
        String s = sharedPreferences.getString(s_LastPage,"");
        et_Note.setText(s);

    }

    @Override
    public void onPause() {
        super.onPause();
        //取得當前頁面page並儲存
        int page = Integer.valueOf(tv_page.getText().toString());
        sharedPreferences.edit()
                .putInt("LastPage",page)
                .commit();
        String s_page = String.valueOf(page);
        //儲存當前頁面內容
        sharedPreferences.edit()
                .putString(s_page, String.valueOf(et_Note.getText()))
                .commit();
    }
}
