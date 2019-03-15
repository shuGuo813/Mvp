package com.bw.android.mvpdemo.view;
import com.bw.android.mvpdemo.entity.getDataBean;
/**
 *  Created by lsg on 2019/3/14
 */
public interface BookView extends View{
    void onSuccess(getDataBean getDataBean);

    void onError(String error);
}
