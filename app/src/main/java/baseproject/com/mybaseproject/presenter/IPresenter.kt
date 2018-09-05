package baseproject.com.mybaseproject.presenter

/**
 * Presenter的父接口
 */
interface IPresenter<T> {
    //建立关系
    fun attachView(view : T)
    //解除关系
    fun detachView()
    //获取建立的视图对象
    fun getView() : T?
    //是否建立关系
    fun isViewAttached() : Boolean
}