package com.yohan.dummyapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.yohan.dummyapp.R;
import utils.CommonUtils;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton ib_close;
    private EditText et_name;
    private EditText et_email;
    private EditText et_password;
    private EditText et_password_repeat;
    private EditText et_phone;
    private Spinner sp_gender;
    private Button btn_signup;

    private String[] genders;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        ib_close = (ImageButton) findViewById(R.id.imageButton_close);
        et_name = (EditText) findViewById(R.id.editText_name);
        et_email = (EditText) findViewById(R.id.editText_email);
        et_password = (EditText) findViewById(R.id.editText_password);
        et_password_repeat = (EditText) findViewById(R.id.editText_password_repeat);
        et_phone = (EditText) findViewById(R.id.editText_phone);
        sp_gender = (Spinner) findViewById(R.id.spinner_gender);
        btn_signup = (Button) findViewById(R.id.button_signup);
        genders = getResources().getStringArray(R.array.genders);

        btn_signup.setOnClickListener(this);
        ib_close.setOnClickListener(this);

      /*  adapter = new ArrayAdapter<CharSequence>(SignupActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.genders)){
            @Override
            public boolean isEnabled(int position) {
                if(position==0)
                    return false;

                return true;
            }

        };*/
        adapter = ArrayAdapter.createFromResource(SignupActivity.this, R.array.genders, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_gender.setAdapter(adapter);
        sp_gender.setPrompt(getString(R.string.gender));

    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.imageButton_close)
            finish();

        if(view.getId()==R.id.spinner_gender)
        {

        }

        if(view.getId()==R.id.button_signup)
        {
            String str_name =  CommonUtils.trimExtraSpace(et_name.getText().toString());
            String str_email = et_email.getText().toString();
            String str_password = et_password.getText().toString();
            String str_password_repeat = et_password_repeat.getText().toString();
            String str_phone = et_phone.getText().toString();

            // Empty Validation checks
            if(CommonUtils.isStringEmpty(str_name))
            {
                CommonUtils.showErrorDialog(SignupActivity.this, getString(R.string.empty_name));
                et_name.requestFocus();
                return;
            }
            if(CommonUtils.isStringEmpty(str_email))
            {
                CommonUtils.showErrorDialog(SignupActivity.this, getString(R.string.empty_email));
                et_email.requestFocus();
                return;
            }
            //Name validation check
            if(!CommonUtils.isValidName(str_name))
            {
                CommonUtils.showErrorDialog(SignupActivity.this, getString(R.string.invalid_name));
                et_name.requestFocus();
                return;
            }
            //Email validation check
            if(!CommonUtils.isValidEmail(str_email))
            {
                CommonUtils.showErrorDialog(SignupActivity.this, getString(R.string.invalid_email));
                et_email.requestFocus();
                return;
            }

            //Password pattern check
            if(!CommonUtils.isValidPassword(str_password))
            {
                CommonUtils.showErrorDialog(SignupActivity.this, getString(R.string.invalid_password));
                et_name.requestFocus();
                return;
            }

            //Password matching check
            if(!str_password.equals(str_password_repeat))
            {
                CommonUtils.showErrorDialog(SignupActivity.this, getString(R.string.no_match_passwords));
                et_password_repeat.requestFocus();
                return;
            }

            //Phone num length check
            if(!CommonUtils.isStringEmpty(str_phone))
            {
                if(!(str_phone.length()==10))
                {
                    CommonUtils.showErrorDialog(SignupActivity.this, getString(R.string.no_match_passwords));
                    return;
                }
            }

            //Gender no select check
            if(sp_gender.getSelectedItemPosition()==0)
            {
                CommonUtils.showErrorDialog(SignupActivity.this, getString(R.string.select_gender));
                return;
            }

            Intent intent = new Intent(SignupActivity.this, NavigationActivityMain.class);
            startActivity(intent);
        }
    }
}
