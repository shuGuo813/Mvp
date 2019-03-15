package com.bw.android.mvpdemo.presenter;

import android.content.Intent;
import android.view.View;

import com.bw.android.mvpdemo.view.BookView;

/**
 *  Created by lsg on 2019/3/14
 */
public interface Presenter {
    void onCreate();

    void onStart();

    void onStop();

    void pause();

    void attachView(BookView view);

    void attachIncommingIntent(Intent intent);
}
