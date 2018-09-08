//package baseproject.com.mybaseproject.presenter
//
//import baseproject.com.mybaseproject.model.bean.User
//import baseproject.com.mybaseproject.net.ApiErrorModel
//import baseproject.com.mybaseproject.net.ResultObserver
//import baseproject.com.mybaseproject.net.RetrofitManager
//import baseproject.com.mybaseproject.utils.ToastUtils
//import baseproject.com.mybaseproject.utils.scheduler.RxUtils
//import baseproject.com.mybaseproject.view.LoginView
//
//
//class LoginPresenter(view : LoginView) : BasePresenter<LoginView>() {
//
//
//    fun login(){
//        var iview = getView()
//        var username = iview?.getUserName()
//        var password = iview?.getPassWord()
//
//        var params = HashMap<String , String>()
//        params.put("telephone" , "A005954")
//        params.put("password" , "1aabac6d068eef6a7bad3fdf50a05cc8")
//        iview?.showProgress("")
//
//        /*获取订阅者*/
//        var callback: ResultObserver<User> = object : ResultObserver<User>() {
//            override fun success(data: User) {
//                iview?.hideProgress()
//                iview?.loginSuccess(data.toString())
//            }
//
//            override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {
//                ToastUtils.showToast(apiErrorModel.toString())
//                iview?.hideProgress()
//                iview?.loginFailed(apiErrorModel.message)
//            }
//        }
//        /*调用接口获取事件源*/
//        RetrofitManager.service.login(params)
//                .compose(RxUtils.io2main())
//                .subscribe(callback)
//
//        /*添加观察者*/
//        addDispose(callback)
//    }
//
//
//}