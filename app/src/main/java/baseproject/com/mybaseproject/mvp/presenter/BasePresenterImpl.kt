package baseproject.com.mybaseproject.mvp.presenter

import baseproject.com.mybaseproject.mvp.contract.IContract
import baseproject.com.mybaseproject.net.ResultObserver
import baseproject.com.mybaseproject.utils.scheduler.RxUtils
import io.reactivex.CompletableObserver
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

/**
 * Created by wm on 2018/9/8.
 *
 * 文件描述：
 *
 * 修改原因：
 *
 */
 open class BasePresenterImpl<V : IContract.IBaseView> : IContract.IBasePresenter<V> {

    //用于事件源的统一管理   订阅和取消订阅
    private  var mCompositeDisposable = CompositeDisposable()

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        if (mCompositeDisposable != null){
            mCompositeDisposable.clear()
        }
        mView = null
    }

    /**
     * 获取视图引用
     */
    open fun getView() : V? {
        return mView
    }

    /**
     * 是否已经绑定视图
     */
    open fun isAttached() : Boolean{
        return mView != null
    }

    /**
     * 订阅的统一管理
     */
    open fun <T> addDisposable( disposableObserver: DisposableObserver<T>){
        if (mCompositeDisposable == null){
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable.add(disposableObserver)
    }

    open fun <T> cancelDisposable(disposableObserver: DisposableObserver<T>){
        if (mCompositeDisposable == null){
            return
        }
        mCompositeDisposable.remove(disposableObserver)
    }
}