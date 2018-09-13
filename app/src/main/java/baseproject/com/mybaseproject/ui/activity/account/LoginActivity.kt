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
class LoginActivity : BaseMvpActivity<LoginContract.LoginView, LoginPresenterImpl>(), LoginContract.LoginView {

    override var mPresenter = LoginPresenterImpl()

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnLogin.setOnClickListener {
            //文件下载
            //判断权限
//            rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                    .subscribe { permission ->
//                        if (permission.granted) {
//                            //同意了
//                            ToastUtils.showToast("同意")
//                            mPresenter.download()
//                        } else if (permission.shouldShowRequestPermissionRationale) {
//                            //拒绝
//                            ToastUtils.showToast("拒绝")
//                        } else {
//                            //拒绝&不在询问
//                            ToastUtils.showToast("拒绝不在询问")
//                        }
//                    }
            //登录接口
            mPresenter.login()

        }
    }

    override fun loginSucess(msg: String) {
        showToast(msg)
        //跳转到MainActivity
        ActivityUtils.startActivityAndFinish(this, MainActivity::class.java)
    }

    override fun loginFailed(errorMsg: String) {
        showToast(errorMsg)
    }

}