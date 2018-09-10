package baseproject.com.mybaseproject.mvp.contract

import android.arch.lifecycle.DefaultLifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import baseproject.com.mybaseproject.utils.LoggerUtils

/**
 * Created by wm on 2018/9/8.
 *
 * 文件描述：
 *
 * 修改原因：
 *
 */
interface IContract {
    /**
     * 顶层View
     */
    interface IBaseView {


        fun showToast(msg: String)

        fun showProgress(msg: String)

        fun hideProgress()

    }

    /**
     * 顶层presenter
     */
    interface IBasePresenter<V> : DefaultLifecycleObserver {

        fun attachView(view: V)
        override fun onCreate(owner: LifecycleOwner) {
            LoggerUtils.showiLogger("IBasePresenter" , "onCreate")
        }


        override fun onResume(owner: LifecycleOwner) {
            LoggerUtils.showiLogger("IBasePresenter" , "onResume")
        }

        override fun onPause(owner: LifecycleOwner) {
            LoggerUtils.showiLogger("IBasePresenter" , "onResume")
        }

        override fun onStop(owner: LifecycleOwner) {
            LoggerUtils.showiLogger("IBasePresenter" , "onStop")
        }
        override fun onDestroy(owner: LifecycleOwner) {
            LoggerUtils.showiLogger("IBasePresenter" , "onDestroy")
            detachView()
        }

        /**
         * 在视图销毁时 释放引用
         */
//        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun detachView()
    }

}