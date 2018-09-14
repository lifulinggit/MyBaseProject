package baseproject.com.mybaseproject.net

import baseproject.com.mybaseproject.app.BaseApplication
import com.hazz.kotlinmvp.api.ApiService
import com.hazz.kotlinmvp.api.UrlConstant
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * Created by xuhao on 2017/11/16.
 *
 */

object RetrofitManager{

    val service: ApiService by lazy (LazyThreadSafetyMode.SYNCHRONIZED){
        getRetrofit().create(ApiService::class.java)
    }


    private fun getRetrofit(): Retrofit {
        // 获取retrofit的实例
        return Retrofit.Builder()
                .baseUrl(UrlConstant.COMM_API_URL)  //自己配置
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    private fun getOkHttpClient(): OkHttpClient {
        //添加一个log拦截器,打印所有的log
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        //可以设置请求过滤的水平,body,basic,headers
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        //设置 请求的缓存的大小跟位置
        val cacheFile = File(BaseApplication.instance?.cacheDir, "cache")
        val cache = Cache(cacheFile, 1024 * 1024 * 50) //50Mb 缓存的大小

        return OkHttpClient.Builder()
                .addInterceptor(MoreBaseUrlInterceptor())
//              .addInterceptor(addCacheInterceptor())
                .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应度看到
                .cache(cache)  //添加缓存
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
                .build()
    }

    /**
     *动态适配多个BaseUrl拦截器
     */
    private fun MoreBaseUrlInterceptor(): Interceptor {
        return Interceptor { chain ->
            //获取请求的request
            var originalRequest = chain.request()
            //获取老的url
            var oldUrl = originalRequest.url()
            //获取请求request的创建者的builder实例
            var builder = originalRequest.newBuilder()
            //从request中回去headers 通过给定键的URL_NAME
            val headers = originalRequest.headers(UrlConstant.URL_NAME)
            if (headers != null && headers.size > 0){
                //如果有这个header 先将配置的header删除 因此header仅作用在 app 和OkHttp之间
                builder.removeHeader(UrlConstant.URL_NAME)
                //获取头部信息中配置的value
                var urlName = headers[0]
                //根据头部信息中的value 来匹配新的baseUrl
                var baseUrl  = when(urlName){
                    UrlConstant.COMM_API -> HttpUrl.parse(UrlConstant.COMM_API_URL)
                    else -> {
                        HttpUrl.parse(UrlConstant.COMM_API_URL)
                    }
                }
                //重建新的HttpUrl， 需要重新设置的URL部分
                var newHttpUrl = oldUrl.newBuilder()
                        .scheme(baseUrl?.scheme()) //http协议 或者 https
                        .host(baseUrl?.host()) // 主机地址
                        .port(baseUrl?.port()!!)//端口号
                        .build()
                //获取处理后新的newRequest
                var newRequest = builder.url(newHttpUrl).build()
                return@Interceptor chain.proceed(newRequest)
            }
            chain.proceed(originalRequest)
        }
    }


}
