package com.yikuan.androidcommon.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.yikuan.androidcommon.ui.CustomDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomDialog dialog = new CustomDialog(this);
        dialog.setLayoutRes(R.layout.activity_main);
        dialog.show();
    }
}
