package com.bw.android.mvpdemo.model;

import android.content.Context;

import com.bw.android.mvpdemo.view.RetrofitHelper;
import com.bw.android.mvpdemo.view.RetrofitService;
import com.bw.android.mvpdemo.entity.getDataBean;

import rx.Observable;


/**
 *  Created by lsg on 2019/3/14
 */
public class DataManager {
    private RetrofitService retrofitService;

    public DataManager(Context context,String url){
        this.retrofitService = RetrofitHelper.getInstance(context,url).getService();
    }

    public Observable<getDataBean> getSearchBook(String name, String tag, int start, int count){
        return retrofitService.getSearchData(name,tag,start,count);
    }
}
