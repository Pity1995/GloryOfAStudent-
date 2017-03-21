package com.example.pity.gloryofastudent;

import android.app.Activity;
import android.os.Bundle;

import cn.bmob.v3.Bmob;

/**
 * Created by Pity on 12/03/2017.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Bomb初始化
        Bmob.initialize(this,"f0061d0e83a7cd00aa0092fb081b72ae");
    }
}
                                                                                                                                                     