package com.jarvis.mytaobao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jarvis.mytaobao.Data.DatabaseHelper;
import com.jarvis.mytaobaotest.R;

public class Regist extends Activity {

    EditText username;
    EditText passwd;
    EditText confim;
    private Button btn_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        username = (EditText) findViewById(R.id.person_et_name);
        passwd = (EditText) findViewById(R.id.person_et_pwd);
        confim = (EditText) findViewById(R.id.person_et_depwd);
        //两次密码对比
        confim.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    if(!confim.getText().toString().trim().equals(passwd.getText().toString().trim()))
                    {
                        Toast.makeText(Regist.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_regist = (Button) findViewById(R.id.person_btn_register);
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断账户密码
                if (TextUtils.isEmpty(username.getText().toString()) ||
                        TextUtils.isEmpty(passwd.getText().toString())) {
                    Toast.makeText(Regist.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!confim.getText().toString().trim().equals(passwd.getText().toString().trim()))
                {
                    Toast.makeText(Regist.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                }

                //{这里写数据库处理}
                DatabaseHelper dbHelper = new DatabaseHelper(Regist.this,"user_db");

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("username", username.getText().toString());
                values.put("passwd", passwd.getText().toString());
                db.insert("user", null, values);

                Bundle data = new Bundle();
                Intent i = getIntent();
                data.putString("username", username.getText().toString());
                i.putExtras(data);
                setResult(1, i);


                finish();
                Toast.makeText(Regist.this, "恭喜您注册成功", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Regist.this,Log.class));
            }
        });


    }
}
