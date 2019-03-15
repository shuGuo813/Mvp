package com.bw.android.mvpdemo.view;

import com.bw.android.mvpdemo.entity.getDataBean;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitService {
    @GET("book/search")
    Observable<getDataBean> getSearchData(@Query("q") String name,
                                          @Query("tag") String tag,
                                          @Query("start") int start,
                                          @Query("count") int count);

}
