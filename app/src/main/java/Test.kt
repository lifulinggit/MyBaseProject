

import android.app.Activity
import android.support.design.widget.BottomNavigationView
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.utils.ToastUtils
import baseproject.com.mybaseproject.wiget.BottomNavigationViewEx

class Test {

    internal fun func(context: Activity) {
        val viewEx = BottomNavigationViewEx(context)
        viewEx.onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.i_music -> ToastUtils.showToast("音乐")
                R.id.i_friends -> ToastUtils.showToast("朋友圈")
            }
            false
        }
    }
}
