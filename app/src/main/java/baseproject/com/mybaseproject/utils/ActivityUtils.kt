package baseproject.com.mybaseproject.utils

import android.app.Activity
import android.content.Intent
import java.io.Serializable

/**
 * Activity相关的工具类
 */
object ActivityUtils {
    //activity的集合
    private var activitys : HashSet<Activity> ?= null

    init {
        activitys = HashSet()
    }

    /**
     * 添加一个activity到集合
     */
    fun pushActivity(activity: Activity){
        activitys?.add(activity)
    }

    /**
     * 移除一个activity
     */
    fun removeActivity(activity: Activity){
        activitys?.remove(activity)
    }

    /**
     * 移除所有的activity 退出程序时候使用
     */
    fun removeAllActivitys(){
        for (activity in this!!.activitys!!){
            activity?.finish()
            activitys?.remove(activity)
        }
    }
    /**
     * 打开一个新页面
     */
    fun startActivity(activity: Activity, clazz: Class<*>) {
        val intent = Intent(activity, clazz)
        activity.startActivity(intent)
    }

    /**
     * 打开一个新页面 并且关闭上一个
     */
    fun startActivityAndFinish(activity: Activity, clazz: Class<*>) {
        val intent = Intent(activity, clazz)
        activity.startActivity(intent)
        activity.finish()
    }

    /**
     * 打开一个新界面 带参数
     */
    fun startActivityWithPrams(activity: Activity, clazz: Class<*>, params: Map<String, Any>) {
        val intent = Intent(activity, clazz)
        for (key in params.keys) {
            if (params[key] is String)
                intent.putExtra(key, params[key] as String)
            if (params[key] is Int)
                intent.putExtra(key, params[key] as Int)
            if (params[key] is Boolean)
                intent.putExtra(key, params[key] as Boolean)
            if (params[key] is Serializable)
                intent.putExtra(key , params[key] as Serializable)
        }
        activity.startActivity(intent)
    }

    /**
     * 打开一个新页面
     */
    fun startActivityForResult(activity: Activity, clazz: Class<*>, requestCode: Int) {
        val intent = Intent(activity, clazz)
        activity.startActivityForResult(intent, requestCode)
    }

    /**
     * 打开一个新界面 带参数
     */
    fun startActivityWithPramsForResult(activity: Activity, clazz: Class<*>, params: Map<String, Any> , requestCode : Int) {
        val intent = Intent(activity, clazz)
        for (key in params.keys) {
            if (params[key] is String)
                intent.putExtra(key, params[key] as String)
            if (params[key] is Int)
                intent.putExtra(key, params[key] as Int)
            if (params[key] is Boolean)
                intent.putExtra(key, params[key] as Boolean)
            if (params[key] is Serializable)
                intent.putExtra(key , params[key] as Serializable)
        }
        activity.startActivityForResult(intent , requestCode)
    }
}