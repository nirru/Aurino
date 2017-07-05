package com.oxilo.aurion.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.oxilo.aurion.AppConstant;
import com.oxilo.aurion.R;
import com.oxilo.aurion.services.ServiceFactory;
import com.oxilo.aurion.services.WebService;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private AppCompatEditText user_name;
    private AppCompatEditText user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
    }

    private void initUI(){
        user_name = (AppCompatEditText)findViewById(R.id.user_name);
        user_password = (AppCompatEditText)findViewById(R.id.user_password);




//        final String mName = "rajesh";
//        final String mPassword = "admin123";

        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String mName = user_name.getText().toString();
                 String mPassword = user_password.getText().toString();
                if (mName.equals("")){
                    user_name.setError("Username can't be blank");
                    return;
                }

                if (mPassword.equals("")){
                    user_password.setError("Password can't be blank");
                    return;
                }

                else {
                    loginMe(mName,mPassword);
                }


            }
        });


    }

    private void loginMe(String mName,String mPassword){
        try {
            SharedPreferences prefs = getSharedPreferences("AURION", MODE_PRIVATE);
            String token = prefs.getString("token", null);
            WebService webService = ServiceFactory.createService(WebService.class, AppConstant.BASE_URL);
            webService.login(mName,
                    mPassword,
                    token,"android")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {

                            try {
                                String sd = new String(responseBodyResponse.body().bytes());
                                JSONObject mapping = new JSONObject(sd);
                                if (mapping.getString("status").toString().equals("success")){
                                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    finish();
                                }else{
                                    Toast.makeText(LoginActivity.this,"Invalid Credential",Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
