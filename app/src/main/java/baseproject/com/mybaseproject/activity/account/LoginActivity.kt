package baseproject.com.mybaseproject.activity.account

import android.content.Intent
import android.os.Bundle
import android.view.View
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.activity.base.BaseMVPActivity
import baseproject.com.mybaseproject.presenter.LoginPresenter
import baseproject.com.mybaseproject.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseMVPActivity<LoginView , LoginPresenter>() , LoginView{


    override fun initView(savedInstanceState: Bundle?) {
        btnLogin.setOnClickListener(View.OnClickListener {
            mPresenter?.login()
        })
    }
    override fun getLayoutId(): Int? {
        return R.layout.activity_login
    }

    override fun getPresenter(): LoginPresenter {
        return LoginPresenter(this)
    }
    override fun getUserName(): String {
       return mEtUserName.text.toString().trim()
    }

    override fun getPassWord(): String {
        return mEtPassword.text.toString().trim()
    }

    override fun loginSuccess(msg: String) {
        showToast(msg)
        var intent : Intent = Intent()
        intent.setClass(this , MainActivity::class.java)
        startActivity(intent)
    }

    override fun loginFailed(msg: String) {
    }

}