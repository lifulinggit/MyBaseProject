package baseproject.com.mybaseproject.net

/**
 * Created by xuhao on 2017/11/16.
 * 封装返回的数据
 */
class BaseResponse<T>(val resultCode :Int,
                      val resultMsg:String,
                      val time : String,
                      val data:T)