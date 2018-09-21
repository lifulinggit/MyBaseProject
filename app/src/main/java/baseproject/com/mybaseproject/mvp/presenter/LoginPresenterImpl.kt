package baseproject.com.mybaseproject.mvp.presenter

import baseproject.com.mybaseproject.model.bean.User
import baseproject.com.mybaseproject.mvp.contract.LoginContract
import baseproject.com.mybaseproject.net.ApiErrorModel
import baseproject.com.mybaseproject.net.ResultObserver
import baseproject.com.mybaseproject.net.RetrofitManager
import baseproject.com.mybaseproject.utils.scheduler.RxUtils

class LoginPresenterImpl : BasePresenterImpl<LoginContract.LoginView>() , LoginContract.LoginPresenter{

    override fun login() {
        var params = HashMap<String , String>()
        params["telephone"] = "A005954"
        params["password"] = "哈哈哈"
        mView?.showProgress("正在加载...")
        addDisposable( RetrofitManager.service.login(params)
                .compose(RxUtils.io2main())
                .subscribeWith(object : ResultObserver<User>(){
                    override fun success(data: User) {
                        mView?.hideProgress()
                        mView?.loginSucess(data.toString())
                    }

                    override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {
                        mView?.hideProgress()
                        mView?.loginFailed(apiErrorModel.message)
                    }
                }))
    }
}