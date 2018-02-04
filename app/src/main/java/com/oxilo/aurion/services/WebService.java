package com.oxilo.aurion.services;


import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ericbasendra on 10/06/16.
 */
public interface WebService {

    @FormUrlEncoded
    @POST("mobapp/api/api.php/Login")
    io.reactivex.Observable<Response<ResponseBody>> login(@Field("username") String name,
                                                          @Field("password") String username,
                                                          @Field("DeviceToken") String deviceToken,
                                                          @Field("deviceType") String deviceType);

    @FormUrlEncoded
    @POST("mobapp/api/api.php/deviceDetail")
    io.reactivex.Observable<Response<ResponseBody>> deviceDetail(
                                                          @Field("DeviceToken") String deviceToken,
                                                          @Field("deviceType") String deviceType);

//    @FormUrlEncoded
//    @POST("projects/gargash/aurion/api/api.php/Login")
//    io.reactivex.Observable<Response<ResponseBody>> login(@Field("username") String name,
//                                                          @Field("password") String username,
//                                                          @Field("DeviceToken") String deviceToken,
//                                                          @Field("deviceType") String deviceType);

}
