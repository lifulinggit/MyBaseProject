package baseproject.com.mybaseproject.ui.base

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import baseproject.com.mybaseproject.mvp.contract.IContract

/**
 * Created by wm on 2018/9/8.
 *
 * 文件描述：
 *
 * 修改原因：
 *
 */
abstract class BaseMvpActivity<V : IContract.IBaseView , P : IContract.IBasePresenter<V>>
    : BaseActivity(), LifecycleOwner {

    abstract open  var mPresenter : P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mPresenter.attachView(this as V)
        //注册生命周期的监听在此处调用
        lifecycle.addObserver(mPresenter)
    }
}