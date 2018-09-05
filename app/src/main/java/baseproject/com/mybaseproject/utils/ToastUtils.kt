package baseproject.com.mybaseproject.utils

import android.widget.Toast

import baseproject.com.mybaseproject.activity.base.BaseApplication

object ToastUtils {

    fun showToast(msg: String) {
        Toast.makeText(BaseApplication.instance, msg, Toast.LENGTH_SHORT).show()
    }

    fun showToast(resId: Int) {
        Toast.makeText(BaseApplication.instance, resId, Toast.LENGTH_SHORT).show()
    }
}
