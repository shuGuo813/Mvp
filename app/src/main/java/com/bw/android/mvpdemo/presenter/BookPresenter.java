package com.bw.android.mvpdemo.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bw.android.mvpdemo.model.DataManager;
import com.bw.android.mvpdemo.view.BookView;
import com.bw.android.mvpdemo.entity.getDataBean;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BookPresenter implements IBookPresenter{
    private DataManager dataManager;
    private Context context;
    private BookView bookView;
    private getDataBean getDataBean;
    private String url;
    private CompositeSubscription compositeSubscription;//观察者的注册器

    public BookPresenter(Context context,String url){
        this.context = context;
        this.url = url;
    }
    @Override
    public void getSearchBooks(String name, String tag, int start, int count) {
        compositeSubscription.add(dataManager.getSearchBook(name,tag,start,count)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<com.bw.android.mvpdemo.entity.getDataBean>() {
            @Override
            public void onCompleted() {
                if(getDataBean != null){
                    bookView.onSuccess(getDataBean);
                }
            }

            @Override
            public void onError(Throwable e) {
                bookView.onError("获取失败!");
            }

            @Override
            public void onNext(com.bw.android.mvpdemo.entity.getDataBean getDataBean) {
                BookPresenter.this.getDataBean = getDataBean;
            }
        }));
    }

    @Override
    public void onCreate() {
        dataManager = new DataManager(context,url);
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if(compositeSubscription.hasSubscriptions()){
            compositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(BookView view) {
        bookView = view;
    }

    @Override
    public void attachIncommingIntent(Intent intent) {

    }
}
