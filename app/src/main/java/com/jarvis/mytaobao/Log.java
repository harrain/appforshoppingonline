package com.jarvis.mytaobao;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jarvis.mytaobao.Data.DatabaseHelper;
import com.jarvis.mytaobao.home.Main_FA;
import com.jarvis.mytaobaotest.R;

import java.util.HashMap;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class Log extends Activity {
    private EditText username;
    private EditText passwd;

    Button mBtnBindPhone;
    String APPKEY="152e99c5b690c";
    String APPSECRETE="9a089f305b99b66deddb33c5a1062d5d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        username= (EditText) findViewById(R.id.username);
            passwd=(EditText)findViewById(R.id.password);

            findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Log.this, Regist.class);
                    startActivityForResult(i, 1);
                }
        });
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(username.getText().toString().trim())) {
                    Toast.makeText(Log.this, "请输入登录账号", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseHelper dbHelper = new DatabaseHelper(Log.this, "user_db");
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String usernamestr = username.getText().toString().trim();
                Cursor cursor = db.query("user", new String[]{"passwd"},
                        "username" + "=" + usernamestr, null, null, null, null);
                while (cursor.moveToNext()) {
                    String passwdstr = cursor.getString(cursor.getColumnIndex("passwd"));
                    if (passwdstr.equals(passwd.getText().toString())) {
                        startActivity(new Intent(Log.this, Main_FA.class));
                        Toast.makeText(Log.this,"恭喜您,登录成功",Toast.LENGTH_LONG).show();
                    } else {
//                        Toast.makeText(LoginActivity.this, "密码不正确请重新输入", Toast.LENGTH_SHORT).show();
                        passwd.setText("");
                        return;
                    }
                }
            }
        });

        //初始化SDK
        SMSSDK.initSDK(this,APPKEY,APPSECRETE,true);
        //配置信息
        mBtnBindPhone=(Button) this.findViewById(R.id.btn_bind_phone);
        //设置点击事件
        mBtnBindPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //注册手机号
                RegisterPage registerPage=new RegisterPage();
                //注册回调函数
                registerPage.setRegisterCallback(new EventHandler(){
                    //事件完成后调用
                    @Override
                    public void afterEvent(int event, int result, Object data) {
                        //判断结果是否已经完成
                        if(result==SMSSDK.RESULT_COMPLETE){
                            //获取数据data
                            HashMap<String,Object> maps= (HashMap<String, Object>) data;
                            //国家
                            String country=(String) maps.get("country");
                            //手机号
                            String phone= (String) maps.get("phone");

                            submitUserInfo(country,phone);
                            Intent intent =new Intent();
                            intent.setClass(Log.this,hello.class);
                            Log.this.startActivity(intent);

                        }


                    }
                });
                //显示注册页面
                registerPage.show(Log.this);
            }
        });
    }

    /**
     *提交用户信息
     * @param country
     * @param phone
     */
    public void submitUserInfo(String country,String phone){
        Random r=new Random();

        String uid=Math.abs(r.nextInt())+"";
        String nicName="F";
        SMSSDK.submitUserInfo(uid, nicName, null, country, phone);
    }
}
