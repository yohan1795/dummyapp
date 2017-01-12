package com.yohan.dummyapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.yohan.dummyapp.R;

import utils.CommonUtils;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ib_close;
    private EditText et_reset_email;
    private Button btn_reset;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgot_password);

        ib_close = (ImageButton) findViewById(R.id.imageButton_close);
        et_reset_email = (EditText) findViewById(R.id.editText_password_reset_email);
        btn_reset = (Button) findViewById(R.id.button_reset_password);

        ib_close.setOnClickListener(this);
        btn_reset.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.imageButton_close)
            finish();


        if(view.getId()==R.id.button_reset_password)
        {
            String str_email = et_reset_email.getText().toString();

            //Empty validation on Email
            if(CommonUtils.isStringEmpty(str_email))
            {
                CommonUtils.showErrorDialog(ForgotPasswordActivity.this, getString(R.string.empty_email));
                return;
            }

            //Regex validation on Email
            if(!CommonUtils.isValidEmail(str_email))
            {
                CommonUtils.showErrorDialog(ForgotPasswordActivity.this, getString(R.string.invalid_email));
                return;
            }

            Toast.makeText(ForgotPasswordActivity.this, "Password reset mail has been sent to your Email", Toast.LENGTH_SHORT).show();

            finish();
        }
    }
}
