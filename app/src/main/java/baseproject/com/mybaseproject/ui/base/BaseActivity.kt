package baseproject.com.mybaseproject.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.mvp.contract.IContract
import baseproject.com.mybaseproject.utils.ActivityUtils
import baseproject.com.mybaseproject.utils.ToastUtils
import com.noober.background.BackgroundLibrary
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.toolbar_base.*
import kotlinx.android.synthetic.main.toolbar_base.view.*

abstract class BaseActivity : AppCompatActivity(), IContract.IBaseView{


    open lateinit var mPregress : ProgressDialog
    open var mActivity : AppCompatActivity? = null
    open lateinit var rxPermissions : RxPermissions

    override fun onCreate(savedInstanceState: Bundle?) {
        //动态生成shape
        BackgroundLibrary.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        mActivity = this
        //入栈
        ActivityUtils.pushActivity(this)

        rxPermissions = RxPermissions(this)
        mPregress = ProgressDialog(this)

        initToolBar()
    }


    open fun initToolBar() {
        setSupportActionBar(mToolBar)
        //填充子布局
        mContent?.addView(getLayoutId()?.let { View.inflate(this, it, null) })
        //返回按钮点击事件
        mBtnBack.setOnClickListener { finish() }
    }

    /**
     * 获取子布局
     */
    abstract fun getLayoutId(): Int?

    /**
     * 设置新的Toolbar
     */
    open fun setToolBar(toolBar : Toolbar){
        setSupportActionBar(mToolBar)
    }
    /**
     * 隐藏ToolBar
     */
    open fun hideToolBar(){
        mToolBar.visibility = View.GONE
    }

    /**
     * 设置标题
     */
    open fun setTitle(title : String){
        mToolBar.mTvTtile.text = title
    }

    /**
     * 返回按钮设置点击事件
     */
    open fun setBackOnClick(click : View.OnClickListener){
        mBtnBack.setOnClickListener(click)
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

    override fun showToast(msg: String) {
        ToastUtils.showToast(msg)
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

    override fun onDestroy() {
        super.onDestroy()
        //出栈
        ActivityUtils.removeActivity(this)
    }
}