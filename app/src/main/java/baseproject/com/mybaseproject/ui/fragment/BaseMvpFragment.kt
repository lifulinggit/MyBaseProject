package baseproject.com.mybaseproject.ui.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.presenter.BasePresenter
import baseproject.com.mybaseproject.view.IView
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.toolbar_base.*
import kotlinx.android.synthetic.main.toolbar_base.view.*

abstract  class BaseMvpFragment<P : BasePresenter<IView>> : Fragment() , IView{

     open lateinit var mPregress : ProgressDialog
     var mPresenter : BasePresenter<IView>? = null

     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         mPregress = ProgressDialog(activity)
        return inflater.inflate(R.layout.fragment_base , null)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar()
        mPresenter = getPresenter()
        //关联代理
        if (mPresenter != null){
            mPresenter!!.attachView(this)
        }
        initData()
        initView(savedInstanceState)
    }



    /**
     * 初始化toobar
     */
    private fun initToolBar() {
        (activity as AppCompatActivity).setSupportActionBar(mfragmentToolBar)
        //填充子布局
        mFragmentContent?.addView(getLayoutId()?.let { View.inflate(activity, it, null) })
        //隐藏返回按钮
        mBtnBack.visibility = View.GONE
    }
    /**
     * 设置标题
     */
    open fun setTitle(title : String){
        if (title == null)
            return
        mfragmentToolBar.mTvTtile.text = title
    }

    /**
     * 提交按钮点击事件
     */
    open fun setCommiteClick(click : View.OnClickListener){
        mBtnCommite.setOnClickListener(click)
    }
    /**
     * 设置提交按钮的显示与隐藏
     */
    open fun setBtnCommiteVisible(v : Int , text : String){
        mBtnCommite.visibility = v
        mBtnCommite.text = text
    }
     /**
      * 显示加载条
      */
     override fun showProgress(msg: String) {
         mPregress.show()
     }

    /**
     * 隐藏加载条
     */
     override fun hideProgress() {
         if (mPregress.isShowing){
             mPregress.dismiss()
         }
     }



    override fun onDestroyView() {
        super.onDestroyView()
        if (mPresenter != null){
            mPresenter?.detachView()
        }
    }

     /**
     * 获取子布局
     */
    abstract fun getLayoutId(): Int?

    /**
     * 初始化界面
     */
     abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 初始化数据
     */
     open fun initData(){}

    /**
     * 获取代理
     */
    abstract fun getPresenter(): P?
}