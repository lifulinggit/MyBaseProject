package com.hazz.kotlinmvp.api

import baseproject.com.mybaseproject.model.bean.User
import baseproject.com.mybaseproject.net.BaseResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*


/**
 * Created by xuhao on 2017/11/16.
 * Api 接口
 */

interface ApiService{

    /**
     * 登录
     */

    @Headers("url_name:user")
    @POST("/user/login")
    fun login(@QueryMap map :  Map<String,String>): Observable<BaseResponse<User>>
    /**
     * 单文件上传
     */
    @POST("file/upload")
    @Multipart
    fun uploadFile(@Part parts : MultipartBody.Part, @QueryMap map :  Map<String,String>): Observable<BaseResponse<String>>
    /**
     * 多文件上传
     */
    @POST("file/upload")
    @Multipart
    fun uploadMutFiles(@Part parts: Array<MultipartBody.Part?>, @QueryMap map:  Map<String,String> ): Observable<BaseResponse<String>>
    /**
     * 文件下载
     */
    @Streaming
    @GET
    fun downloadFile(@Url fileUrl : String): Observable<ResponseBody>

}