package com.oxilo.aurion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.oxilo.aurion.activity.LoginActivity;
import com.oxilo.aurion.activity.MainActivity;
import com.oxilo.aurion.activity.SplashActivity;
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

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by nikk on 29/4/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        SharedPreferences.Editor editor = getSharedPreferences("AURION", MODE_PRIVATE).edit();

        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        editor.putString("token", refreshedToken);
        editor.apply();

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken,"Android");
    }

    private void sendRegistrationToServer(String token,String deviceType){
        WebService webService = ServiceFactory.createService(WebService.class, AppConstant.BASE_URL);
        webService.deviceDetail(token,
                deviceType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {

                        try {String sd = new String(responseBodyResponse.body().bytes());
                            JSONObject mapping = new JSONObject(sd);
                            if (mapping.getString("status").toString().equals("success")){
                                Log.e("SUCESS","" + "DONE");
                            }else{
                                Log.e("SUCESS","" + "FAIL");
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
    }


}
