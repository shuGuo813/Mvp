package com.bw.android.mvpdemo.view;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  Created by lsg on 2019/3/14
 */
public class RetrofitHelper {
    private Context context;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(new GsonBuilder().create());
    @SuppressLint("StaticFieldLeak")
    private static RetrofitHelper instance = null;
    private Retrofit retrofit = null;
    private  String url;

    private RetrofitHelper(Context context,String baseurl){
        this.context = context;
        this.url = baseurl;
        initdata();
    }

    private void initdata(){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public RetrofitService getService(){
        return retrofit.create(RetrofitService.class);
    }

    public static synchronized RetrofitHelper getInstance(Context context,String baseurl){
        if(instance == null){
            instance = new RetrofitHelper(context,baseurl);
        }
        return instance;
    }
}
