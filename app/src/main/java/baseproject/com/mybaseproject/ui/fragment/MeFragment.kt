package baseproject.com.mybaseproject.ui.fragment

import android.os.Bundle
import android.view.View
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.ui.base.BaseFragment
import baseproject.com.mybaseproject.utils.glide.GlideImageLoader
import com.lzy.imagepicker.ImagePicker
import com.lzy.imagepicker.view.CropImageView
import kotlinx.android.synthetic.main.fragment_me.*
import kotlinx.android.synthetic.main.fragment_music.*

class MeFragment : BaseFragment() {
    private var mTitle: String? = null

    companion object {
        fun getInstance(title : String) : MeFragment{
            val fragment = MeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int? {
        return R.layout.fragment_me
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView() {
        tvTitile.text = mTitle
        mTitle?.let { setTitle(it) }
        setBtnCommiteVisible(View.GONE , "")
        takePoto.setOnClickListener {
            //拍照
        }
        fromXiangCe.setOnClickListener {
            //从相册
        }
    }


}