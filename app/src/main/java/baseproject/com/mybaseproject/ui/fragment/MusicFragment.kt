package baseproject.com.mybaseproject.ui.fragment

import android.os.Bundle
import android.view.View
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.ui.base.BaseFragment
import baseproject.com.mybaseproject.view.IView
import kotlinx.android.synthetic.main.fragment_music.*

class MusicFragment : BaseFragment() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    fun initView() {
        tvTitile.text = mTitle
        mTitle?.let { setTitle(it) }
        setBtnCommiteVisible(View.GONE , "")
    }
}