package com.example.pity.gloryofastudent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Pity on 11/03/2017.
 */
public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        //Bomb初始化
        Bmob.initialize(this,"f0061d0e83a7cd00aa0092fb081b72ae");

        //控件初始化
        final EditText editTextUserName = (EditText) findViewById(R.id.welcomeEditTextUserName);
        final EditText editTextPassword = (EditText) findViewById(R.id.welcomeEditTextPassword);
        Button buttonLogin = (Button) findViewById(R.id.welcomeButtonLogin);
        Button buttonRegister = (Button) findViewById(R.id.welcomeButtonRegister);

        //注册功能
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建用户信息实例
                final UserInformation userInformation = new UserInformation();
                //获取用户账号
                userInformation.setUserID(editTextUserName.getText().toString());
                //验证用户账号是否已被注册
                BmobQuery<UserInformation> query = new BmobQuery<>();
                query.addWhereEqualTo("userID",editTextUserName.getText().toString());
                query.findObjects(new FindListener<UserInformation>() {
                    @Override
                    public void done(List<UserInformation> list, BmobException e) {
                        //如果没有查询错误时
                        if(e==null){
                            //如果数据库里没有重复用户名时
                            if(list.size()==0){
                                //获取用户密码
                                userInformation.setUserPassword(editTextPassword.getText().toString());
                                //上传至Bmob云
                                userInformation.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        //如果没有上传错误时
                                        if (e==null){
                                            //注册成功信息反馈
                                            Toast.makeText(getApplicationContext(),"注册成功！",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent();
                                            //界面跳转
                                            intent.setClass(WelcomeActivity.this,MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        //如果有上传错误时
                                        else{
                                            //注册失败信息反馈
                                            Toast.makeText(getApplicationContext(),"注册失败，再试一次！",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            //如果数据库有重复用户名时
                            else {
                                Toast.makeText(getApplicationContext(),"您的账号已经被人注册啦，换一个试试吧～",Toast.LENGTH_SHORT).show();
                            }
                        }
                        //如果有查询错误时
                        else {
                            Toast.makeText(getApplicationContext(),"注册失败，再来一次！",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //登入功能
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //用户信息实例化
                UserInformation userInformation = new UserInformation();
                //获取用户账号
                userInformation.setUserID(editTextUserName.getText().toString());
                //验证用户是否存在
                final BmobQuery<UserInformation> query = new BmobQuery<>();
                //在数据库里找用户账号
                query.addWhereEqualTo("userID",editTextUserName.getText().toString());
                query.findObjects(new FindListener<UserInformation>() {
                    @Override
                    public void done(List<UserInformation> list, BmobException e) {
                        //如果用户账号不存在
                        if(list.size()==0){
                            Toast.makeText(getApplicationContext(),"找不到你的账号诶，再试试看嘛～",Toast.LENGTH_SHORT).show();
                        }
                        //如果用户账号存在，进行密码核对
                        else{
                            //密码正确
                            if(Objects.equals(list.get(0).getUserPassword(), editTextPassword.getText().toString())){
                                Toast.makeText(getApplicationContext(),"欢迎回来，我的王！",Toast.LENGTH_SHORT).show();
                                //页面跳转
                                Intent intent = new Intent();
                                intent.setClass(WelcomeActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            //密码错误
                            else{
                                Toast.makeText(getApplicationContext(),"学霸学霸，密码去哪里呀？",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
}

