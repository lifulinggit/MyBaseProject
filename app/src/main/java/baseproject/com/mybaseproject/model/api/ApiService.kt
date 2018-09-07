package com.hazz.kotlinmvp.api

import baseproject.com.mybaseproject.model.bean.User
import baseproject.com.mybaseproject.net.BaseResponse
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import retrofit2.http.POST
import retrofit2.http.QueryMap


/**
 * Created by xuhao on 2017/11/16.
 * Api 接口
 */

interface ApiService{

    /**
     * 登录
     */
    @POST("/user/login")
    fun login(@QueryMap map :  Map<String,String>): Observable<BaseResponse<User>>

    fun me(){
        Observable.just("").subscribe(Consumer {  })
    }


}