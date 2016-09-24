package com.jarvis.mytaobao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jarvis.mytaobaotest.R;

public class hello extends Activity {
    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        edit= (Button) findViewById(R.id.edit);

        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(hello.this, Log.class);
                startActivityForResult(i, 1);
            }
        });
    }
}
