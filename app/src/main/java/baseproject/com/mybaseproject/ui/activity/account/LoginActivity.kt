package baseproject.com.mybaseproject.ui.activity.account

import android.os.Bundle
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.mvp.contract.LoginContract
import baseproject.com.mybaseproject.mvp.presenter.LoginPresenterImpl
import baseproject.com.mybaseproject.ui.base.BaseMvpActivity
import baseproject.com.mybaseproject.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_login.*


/**
 * Created by wm on 2018/9/8.
 *
 * 文件描述：
 *
 * 修改原因：
 *
 */
class LoginActivity : BaseMvpActivity<LoginContract.LoginView, LoginPresenterImpl>(),LoginContract.LoginView{

    override var mPresenter = LoginPresenterImpl()

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnLogin.setOnClickListener { mPresenter.login() }
    }
    override fun loginSucess(msg: String) {
        showToast(msg)
        //跳转到MainActivity
        ActivityUtils.startActivityAndFinish(this , MainActivity::class.java)
    }

    override fun loginFailed(errorMsg: String) {
        showToast(errorMsg)
    }

}