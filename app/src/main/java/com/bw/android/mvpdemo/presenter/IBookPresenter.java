package com.bw.android.mvpdemo.presenter;

public interface IBookPresenter extends Presenter{
    void getSearchBooks(String name,String tag,int start,int count);
}
