package baseproject.com.mybaseproject.mvp.contract

import android.arch.lifecycle.DefaultLifecycleObserver
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent

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

        /**
         * 在视图销毁时 释放引用
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun detachView()
    }

}