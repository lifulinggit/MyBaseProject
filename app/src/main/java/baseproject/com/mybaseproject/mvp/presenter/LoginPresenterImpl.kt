package baseproject.com.mybaseproject.mvp.presenter

import android.os.Environment
import baseproject.com.mybaseproject.net.FileUpOrDownloadUtils
import baseproject.com.mybaseproject.model.bean.User
import baseproject.com.mybaseproject.mvp.contract.LoginContract
import baseproject.com.mybaseproject.net.ApiErrorModel
import baseproject.com.mybaseproject.net.FileDownLoadObserver
import baseproject.com.mybaseproject.net.ResultObserver
import baseproject.com.mybaseproject.net.RetrofitManager
import baseproject.com.mybaseproject.utils.scheduler.RxUtils
import java.io.File

class LoginPresenterImpl : BasePresenterImpl<LoginContract.LoginView>() , LoginContract.LoginPresenter{

    override fun login() {
        var params = HashMap<String , String>()
        params.put("telephone" , "A005954")
        params.put("password"  , "哈哈哈")

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

    fun download(){
        var url = "http://download.sdk.mob.com/apkbus.apk"
        var filePath = Environment.getExternalStorageDirectory().toString() + "/ceshi/hahah.apk"

        var observer = FileUpOrDownloadUtils.downloadFile(url , filePath , object  : FileDownLoadObserver<File>(){
            override fun onDownLoadSuccess(t: File) {
                mView?.loginSucess("下载成功")
            }

            override fun onDownLoadFail(throwable: Throwable) {
                mView?.loginFailed("下载失败")
            }

            override fun onProgress(progress: Int, total: Long) {
                println("------------下载进度: $progress")
            }
        })
        addDisposable(observer)

    }



}