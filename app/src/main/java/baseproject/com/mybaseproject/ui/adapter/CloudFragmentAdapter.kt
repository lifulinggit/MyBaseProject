package baseproject.com.mybaseproject.ui.adapter

import android.widget.ImageView
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.app.BaseApplication
import baseproject.com.mybaseproject.utils.ImageUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


class CloudFragmentAdapter : BaseQuickAdapter<String, BaseViewHolder> {
    constructor(layoutResId: Int, data: List<String>) : super(layoutResId, data) {}

    override fun convert(baseViewHolder: BaseViewHolder, s: String) {
        baseViewHolder.setText(R.id.tvContent, s)
        ImageUtils.loadRound(BaseApplication.getInstance()
                , baseViewHolder.getView<ImageView>(R.id.mImageView)
                , "http://t2.hddhhn.com/uploads/tu/201809/9999/c42eafc576.jpg")
    }

}