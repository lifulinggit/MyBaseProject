package baseproject.com.mybaseproject.mvp.contract


/**
 * Created by wm on 2018/9/8.
 *
 * 文件描述：
 *
 * 修改原因：
 *
 */
interface LoginContract {

    interface LoginView : IContract.IBaseView {

        fun loginSucess(msg: String)

        fun loginFailed(errorMsg: String)

    }

    interface LoginPresenter : IContract.IBasePresenter<LoginView> {
        /**
         * 登陆方法
         */
        fun login()
    }
}