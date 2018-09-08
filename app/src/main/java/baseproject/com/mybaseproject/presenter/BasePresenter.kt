//package baseproject.com.mybaseproject.presenter
//
//import io.reactivex.observers.DisposableObserver
//import java.lang.ref.WeakReference
//
//open class BasePresenter<T>{
//
//    /*弱引用*/
//    var weakReference: WeakReference<T>? = null
//
//    fun attachView(t: T){
//        weakReference = WeakReference(t)
//    }
//
//    open fun detachView(){
//        //取消视图的引用
//        if (weakReference != null){
//            weakReference!!.clear()
//            weakReference = null
//        }
//        //遍历取消订阅
//        val it = mObservers.iterator()
//        while (it.hasNext())
//        {
//            it.next().dispose()
//            it.remove()
//        }
//    }
//
//    fun getView(): T?{
//        return weakReference!!.get()
//    }
//    /**
//     * 观察者的集合
//     */
//    var mObservers = HashSet<DisposableObserver<*>>()
//
//    /**
//     * 添加观察者
//     */
//    protected fun addDispose(observer: DisposableObserver<*>) {
//        mObservers.add(observer)
//    }
//    /**
//     * 取消订阅
//     */
//    protected fun unSubscribe(observer: DisposableObserver<*>){
//        if (!observer.isDisposed){
//            observer.dispose()
//            if (mObservers.contains(observer)) mObservers.remove(observer)
//        }
//    }
//}