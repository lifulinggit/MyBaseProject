package baseproject.com.mybaseproject.mvp.presenter

import baseproject.com.mybaseproject.mvp.contract.IContract

/**
 * Created by wm on 2018/9/8.
 *
 * 文件描述：
 *
 * 修改原因：
 *
 */
 open class BasePresenterImpl<V : IContract.IBaseView> : IContract.IBasePresenter<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }


}