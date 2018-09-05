package baseproject.com.mybaseproject.activity.account

import android.content.Intent
import android.os.Bundle
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.activity.base.BaseMVPActivity
import baseproject.com.mybaseproject.presenter.BasePresenter
import baseproject.com.mybaseproject.view.IView
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import java.util.concurrent.TimeUnit

class SplashActivity : BaseMVPActivity<IView , BasePresenter<IView>>() {
    override fun getPresenter(): BasePresenter<IView> {
        return BasePresenter()
    }

    override fun initView(savedInstanceState: Bundle?) {
        hideToolBar()
        Observable.just("跳转到主页").delay(2 , TimeUnit.SECONDS)
                .subscribe(Consumer {
                    jumpToMainActivity()
                })
    }
    private fun jumpToMainActivity() {
        var intent : Intent = Intent()
        intent.setClass(this , LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun getLayoutId(): Int? {
        return R.layout.activity_splash
    }


}