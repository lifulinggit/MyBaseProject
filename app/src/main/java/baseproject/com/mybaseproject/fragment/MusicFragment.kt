package baseproject.com.mybaseproject.fragment

import android.os.Bundle
import android.view.View
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.presenter.BasePresenter
import baseproject.com.mybaseproject.view.IView
import kotlinx.android.synthetic.main.fragment_music.*

class MusicFragment : BaseMvpFragment<BasePresenter<IView>>() {
    private var mTitle: String? = null

    companion object {
        fun getInstance(title : String) : MusicFragment{
            val fragment = MusicFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int? {
        return R.layout.fragment_music
    }

    override fun initView(savedInstanceState: Bundle?) {
        tvTitile.text = mTitle
        mTitle?.let { setTitle(it) }
        setBtnCommiteVisible(View.GONE , "")
    }

    override fun getPresenter(): BasePresenter<IView>? {
        return BasePresenter()
    }
}