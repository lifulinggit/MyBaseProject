package baseproject.com.mybaseproject.ui.adapter


import baseproject.com.mybaseproject.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class CloudFragmentAdapter : BaseQuickAdapter<String> {
    constructor(layoutResId: Int, data: List<String>) : super(layoutResId, data) {}

    override fun convert(baseViewHolder: BaseViewHolder, s: String) {
            baseViewHolder.setText(R.id.tvContent , s)
    }
}