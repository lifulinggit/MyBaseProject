package baseproject.com.mybaseproject.mvp.presenter

import baseproject.com.mybaseproject.model.bean.User
import baseproject.com.mybaseproject.mvp.contract.LoginContract
import baseproject.com.mybaseproject.net.ApiErrorModel
import baseproject.com.mybaseproject.net.ResultObserver
import baseproject.com.mybaseproject.net.RetrofitManager
import baseproject.com.mybaseproject.utils.ToastUtils
import baseproject.com.mybaseproject.utils.scheduler.RxUtils

class LoginPresenterImpl : BasePresenterImpl<LoginContract.LoginView>() , LoginContract.LoginPresenter{

    override fun login() {
        mView?.loginSucess("登录成功")

        var params = HashMap<String , String>()

        var callback: ResultObserver<User> = object : ResultObserver<User>() {
            override fun success(data: User) {
                mView?.hideProgress()
                mView?.loginSucess(data.toString())
            }

            override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {
                ToastUtils.showToast(apiErrorModel.toString())
                mView?.hideProgress()
                mView?.loginFailed(apiErrorModel.message)
            }
        }

        RetrofitManager.service.login(params)
                .compose(RxUtils.io2main())
                .subscribe(callback)

        addDisposable(callback)

    }



}