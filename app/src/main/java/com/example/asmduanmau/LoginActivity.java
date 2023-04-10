package com.example.asmduanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asmduanmau.DAO.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {
    EditText mEtPwd;
    CheckBox mCbShowPwd, luuMatKhau;
    ThuThuDAO dao;
    EditText edtUser, edtPassword;
    Button btnHuy,btnXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //anh xa show mat khau
        mEtPwd = (EditText) findViewById(R.id.edtPassword);
        mCbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);

        //xu ly doc ghi tk mk:
        dao = new ThuThuDAO(this);
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
        luuMatKhau = (CheckBox) findViewById(R.id.luuMatKhau);
        btnHuy = findViewById(R.id.btnHuy);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        //doc tk mk
        SharedPreferences pref =getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edtUser.setText(pref.getString("USERNAME",""));
        edtUser.setText(pref.getString("PASSWORD",""));
        luuMatKhau.setChecked(pref.getBoolean("REMEMBER",false));
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtUser.setText("");
                edtPassword.setText("");
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

        //Show mat khau
        mCbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    mEtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    mEtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
    public void checkLogin(){
        String user = edtUser.getText().toString();
        String pass = edtPassword.getText().toString();
        if (user.isEmpty()||pass.isEmpty()){
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        }else {
            if (dao.login(user,pass)){
                SharedPreferences preferences =getSharedPreferences("USER",MODE_PRIVATE);
                SharedPreferences.Editor editor =preferences.edit();
                editor.putString("maTT",user);
                editor.commit();
                Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(user,pass,luuMatKhau.isChecked());
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("user",user);
                startActivity(i);
                finish();

            }else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void rememberUser(String u, String p , boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!status){
            editor.clear();
        }else {
            editor.putString("USERNAME",p);
            editor.putString("PASSWORD",u);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }
}