package com.yohan.dummyapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yohan.dummyapp.R;
import utils.CommonUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;
    private TextView tv_signup;
    private TextView tv_forgot_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        et_username = (EditText) findViewById(R.id.editText_username);
        et_password = (EditText) findViewById(R.id.editText_password);
        tv_signup = (TextView) findViewById(R.id.textView_signup);
        tv_forgot_password = (TextView) findViewById(R.id.textView_forgot_password);
        btn_login = (Button) findViewById(R.id.button_login_login);

        et_username.setText("admin@appirio.com");
        tv_signup.setOnClickListener(this);
        tv_forgot_password.setOnClickListener(this);
        btn_login.setOnClickListener(this);

    }

    private boolean validate()
    {
        boolean valid = false;
        String str_username = et_username.getText().toString();
        String str_password = et_password.getText().toString();

        if(CommonUtils.isStringEmpty(str_username))
        {
            CommonUtils.showErrorDialog(LoginActivity.this, getString(R.string.empty_username_msg));
        }
        else if(CommonUtils.isStringEmpty(str_password))
        {
            CommonUtils.showErrorDialog(LoginActivity.this, getString(R.string.empty_password_msg));
        }
        else
        {
            valid = true;
        }

        return valid;
    }

    private boolean authorize()
    {
        boolean flag = false;
        String username = "admin@appirio.com";
        String password = "q";
        if(et_username.getText().toString().equalsIgnoreCase(username) && et_password.getText().toString().equals(password))
        {
            flag = true;
        }
        else
        {
            CommonUtils.showErrorDialog(LoginActivity.this, getString(R.string.invalid_username_password_msg));
        }

        return flag;
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.button_login_login)
        {
            if(validate())
            if(authorize()) {
                Intent intent = new Intent(LoginActivity.this, NavigationActivityMain.class);
                startActivity(intent);
            }

        }

        if(view.getId()==R.id.textView_signup)
        {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        }

        if(view.getId()==R.id.textView_forgot_password)
        {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        }
    }
}
