package baseproject.com.mybaseproject.view

import baseproject.com.mybaseproject.utils.ToastUtils

/**
 * View层的父接口
 */
interface IView {
    //显示吐司
    fun showToast(msg : String){
        ToastUtils.showToast(msg)
    }
    //显示加载条
    fun showProgress(msg: String)
    //隐藏加载条
    fun hideProgress()

}