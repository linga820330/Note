package com.example.note;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NoteFragment noteFragment;
    private SecretFragment secretFragment;
    private MoneyDBHelper moneyDBHelper;
    private Long ExitTime;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId())
            {
                case R.id.na_Note:
                    fragment = new NoteFragment();
                    loadFragment(fragment);
                    break;
                case R.id.na_Money:
                    fragment = new MoneyFragment();
                    loadFragment(fragment);
                    break;
                case R.id.na_Secret:
                    fragment = new SecretFragment();
                    loadFragment(fragment);
                    break;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        //transaction.addToBackStack(null); //按返回鍵回到上一個Fragment
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (moneyDBHelper==null){
            moneyDBHelper = new MoneyDBHelper(this);
            moneyDBHelper.getWritableDatabase();
        }

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.BottomNa);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        loadFragment(new NoteFragment());
    }

    //連續點擊兩次返回鍵退出APP
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //判断用户是否點擊了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            //與上次點擊返回鍵時做時間差
            if ((System.currentTimeMillis() - ExitTime) > 2000) { //MainFragment不使用此功能 因為有webview
                //大於2000ms則認為是不小心按到，使用Toast進行提示
                Toast.makeText(this, "再按一次退出APP", Toast.LENGTH_SHORT).show();
                //並記錄本次點擊“返回鍵”的時刻，以便下次進行判断
                ExitTime = System.currentTimeMillis();
            } else {
                //小於2000m則認為是用户確實希望退出程序-調用System.exit()方法進行退出
                System.exit(0);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }
}
