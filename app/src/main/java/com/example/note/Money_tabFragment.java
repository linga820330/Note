package com.example.note;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Money_tabFragment extends Fragment {

    public static String TABLAYOUT_FRAGMENT = "tab_fragment";
    private TextView txt;
    private int type;
    private View view;
    private Button bt_insert;
    private TextView tv_insert_price;
    private String insertCount = "";
    private MoneyDBHelper moneyDBHelper;
    private SQLiteDatabase db;
    private RecyclerView rv_Money;
    private List<money_list> money_lists = new ArrayList<>();

    public static Money_tabFragment newInstance(int type) {
        Money_tabFragment fragment = new Money_tabFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TABLAYOUT_FRAGMENT, type);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (int) getArguments().getSerializable(TABLAYOUT_FRAGMENT);
        }
        // 建立SQLiteOpenHelper物件
        if (moneyDBHelper==null) {
            moneyDBHelper = new MoneyDBHelper(getContext());
            moneyDBHelper.getWritableDatabase();
        }

    }

    public Money_tabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_money_tab, container, false);
        bt_insert = (Button)view.findViewById(R.id.bt_insert);
        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDialog();
            }
        });
        rv_Money = (RecyclerView) view.findViewById(R.id.rv_Money);
        rv_Money.setHasFixedSize(true);
        rv_Money.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        rv_Money.setAdapter(new adapter_Money(getContext(),money_lists));





        txt = (TextView) view.findViewById(R.id.txt);

        switch (type) {
            case 1:
                txt.setText("这是综艺Fragment");
                break;
            case 2:
                txt.setText("这是体育Fragment");
                break;
            case 3:
                txt.setText("这是新闻Fragment");
                break;
            case 4:
                txt.setText("这是热点Fragment");
                break;
            case 5:
                txt.setText("这是头条Fragment");
                break;
            case 6:
                txt.setText("这是军事Fragment");
                break;
            case 7:
                txt.setText("这是历史Fragment");
                break;
            case 8:
                txt.setText("这是科技Fragment");
                break;
            case 9:
                txt.setText("这是人文Fragment");
                break;
            case 10:
                txt.setText("这是地理Fragment");
                break;
            case 11:
                txt.setText("这是地理Fragment");
                break;
            case 12:
                txt.setText("这是地理Fragment");
                break;
        }


        return view;
    }

    private void insertDialog() {
       // AlertDialog.Builder logoutDialog = new AlertDialog.Builder(getContext());
        final View custom_dialog = getLayoutInflater().inflate(R.layout.custom_dialog_money_insert,null);
        dialog_insert dialog_insert = new dialog_insert(getContext(),250,0,custom_dialog,R.style.dialog);
        tv_insert_price = (TextView) custom_dialog.findViewById(R.id.tv_insert_price);
        insertCount = "";//初始化
        Button bt_Money_1 = (Button)custom_dialog.findViewById(R.id.bt_Money_1);
        bt_Money_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCount("1");
            }
        });
        Button bt_Money_2 = (Button)custom_dialog.findViewById(R.id.bt_Money_2);
        bt_Money_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCount("2");
            }
        });
        Button bt_Money_3 = (Button)custom_dialog.findViewById(R.id.bt_Money_3);
        bt_Money_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCount("3");
            }
        });
        Button bt_Money_4 = (Button)custom_dialog.findViewById(R.id.bt_Money_4);
        bt_Money_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCount("4");
            }
        });
        Button bt_Money_5 = (Button)custom_dialog.findViewById(R.id.bt_Money_5);
        bt_Money_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCount("5");
            }
        });
        Button bt_Money_6 = (Button)custom_dialog.findViewById(R.id.bt_Money_6);
        bt_Money_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCount("6");
            }
        });
        Button bt_Money_7 = (Button)custom_dialog.findViewById(R.id.bt_Money_7);
        bt_Money_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCount("7");
            }
        });
        Button bt_Money_8 = (Button)custom_dialog.findViewById(R.id.bt_Money_8);
        bt_Money_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCount("8");
            }
        });
        Button bt_Money_9 = (Button)custom_dialog.findViewById(R.id.bt_Money_9);
        bt_Money_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCount("9");
            }
        });
        Button bt_Money_0 = (Button)custom_dialog.findViewById(R.id.bt_Money_0);
        bt_Money_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCount("0");
            }
        });
        Button bt_Money_back = (Button)custom_dialog.findViewById(R.id.bt_Money_back);
        bt_Money_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //刪除字串最後一個字
                if (!insertCount.equals("")) {
                    insertCount = insertCount.substring(0, insertCount.length() - 1);
                    tv_insert_price.setText(insertCount);
                }
            }
        });
        Button bt_Money_red = (Button)custom_dialog.findViewById(R.id.bt_Money_red);
        bt_Money_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources resource = (Resources) getContext().getResources();
                ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.red);
                if (csl != null) {
                    tv_insert_price.setTextColor(csl);
                }

            }
        });
        Button bt_Money_green = (Button)custom_dialog.findViewById(R.id.bt_Money_green);
        bt_Money_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources resource = (Resources) getContext().getResources();
                ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.green);
                if (csl != null) {
                    tv_insert_price.setTextColor(csl);
                }
            }
        });

        Button bt_money_plus = (Button)custom_dialog.findViewById(R.id.bt_money_plus);
        bt_money_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_money_item = (EditText) custom_dialog.findViewById(R.id.et_money_item);
                String s_money_item = et_money_item.getText().toString();
                EditText et_money_content = (EditText) custom_dialog.findViewById(R.id.et_money_content);
                String s_money_content = et_money_content.getText().toString();
                String s_money_price = tv_insert_price.getText().toString();

                int i_money_price = Integer.valueOf(s_money_price);
                int i_money_type = tv_insert_price.getCurrentTextColor();
                TextView tv_money_date = (TextView)custom_dialog.findViewById(R.id.tv_money_date);
                String s_money_date = tv_money_date.getText().toString();
                money_list moneyList = new money_list(s_money_date,s_money_item,s_money_content,i_money_price,1);

                long rowid = moneyDBHelper.insert(moneyList);
                if (rowid==-1) {
                    Toast.makeText(getContext(), "失敗", Toast.LENGTH_LONG).show();
                }
            }
        });

       // logoutDialog.setView(custom_dialog);
       // final AlertDialog dialog = logoutDialog.create();
       // dialog.show();
        dialog_insert.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(moneyDBHelper!=null)
            moneyDBHelper.close();

    }

    private void addCount(String count) {
        insertCount = insertCount + count;
        tv_insert_price.setText(insertCount);
    }

    private class adapter_Money extends RecyclerView.Adapter<adapter_Money.ViewHolder>{
        private Context context;
        private List<money_list> money_list;
        public adapter_Money(Context context, List<money_list> money_list) {
            this.context = context;
            this.money_list = money_list;
        }

        @NonNull
        @Override
        public adapter_Money.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.cardview_money,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull adapter_Money.ViewHolder viewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 3;
        }
        class ViewHolder extends RecyclerView.ViewHolder{

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

            }
        }
    }
}
