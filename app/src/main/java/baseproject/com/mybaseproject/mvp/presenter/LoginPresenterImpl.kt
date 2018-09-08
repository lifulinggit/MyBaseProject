package baseproject.com.mybaseproject.mvp.presenter

import baseproject.com.mybaseproject.mvp.contract.LoginContract

class LoginPresenterImpl : BasePresenterImpl<LoginContract.LoginView>() , LoginContract.LoginPresenter{

    override fun login() {
        mView?.loginSucess("登录成功")
    }

}