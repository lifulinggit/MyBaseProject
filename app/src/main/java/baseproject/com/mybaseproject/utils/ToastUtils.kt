package baseproject.com.mybaseproject.utils

import android.widget.Toast

import baseproject.com.mybaseproject.app.BaseApplication

class ToastUtils {

    companion object {
        fun showToast(msg: String) {
            Toast.makeText(BaseApplication.getInstance(), msg, Toast.LENGTH_SHORT).show()
        }

        fun showToast(resId: Int) {
            Toast.makeText(BaseApplication.getInstance(), resId, Toast.LENGTH_SHORT).show()
        }
    }

}
