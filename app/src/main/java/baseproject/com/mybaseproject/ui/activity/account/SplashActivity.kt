package baseproject.com.mybaseproject.ui.activity.account

import android.Manifest
import android.content.Intent
import android.os.Bundle
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.presenter.BasePresenter
import baseproject.com.mybaseproject.ui.activity.base.BaseMVPActivity
import baseproject.com.mybaseproject.utils.ToastUtils
import baseproject.com.mybaseproject.view.IView


class SplashActivity : BaseMVPActivity<IView , BasePresenter<IView>>() {
    override fun getPresenter(): BasePresenter<IView> {
        return BasePresenter()
    }

    override fun initView(savedInstanceState: Bundle?) {

        hideToolBar()

        jumpToMainActivity()
        //申请权限
        rxPermissions.requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS)
                .subscribe { permission ->
                    if (permission.granted) {
                        // 用户已经同意该权限
                        ToastUtils.showToast(permission.name + " is granted.")
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        ToastUtils.showToast(permission.name + " is denied. More info should be provided.")
                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』
                        ToastUtils.showToast(permission.name + " is denied.")
                    }
                }

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