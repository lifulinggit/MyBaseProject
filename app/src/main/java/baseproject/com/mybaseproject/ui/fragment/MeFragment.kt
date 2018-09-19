package baseproject.com.mybaseproject.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.ui.base.BaseFragment
import com.lzy.imagepicker.ImagePicker
import com.lzy.imagepicker.bean.ImageItem
import com.lzy.imagepicker.ui.ImageGridActivity
import kotlinx.android.synthetic.main.fragment_me.*





class MeFragment : BaseFragment() {
    private var mTitle: String? = null
    private var maxImgCount = 9
    private var selImageList = ArrayList<ImageItem>()

    val IMAGE_ITEM_ADD = -1
    val REQUEST_CODE_SELECT = 100
    val REQUEST_CODE_PREVIEW = 101


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
        mTitle?.let { setTitle(it) }
        setBtnCommiteVisible(View.GONE , "")
        takePoto.setOnClickListener {
            //拍照
            //打开选择,本次允许选择的数量
            ImagePicker.getInstance().selectLimit = maxImgCount - selImageList.size
            val intent = Intent(activity, ImageGridActivity::class.java)
            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true) // 是否是直接打开相机
            startActivityForResult(intent, REQUEST_CODE_SELECT)

        }
        fromXiangCe.setOnClickListener {
            //从相册
            //打开选择,本次允许选择的数量
            ImagePicker.getInstance().selectLimit = maxImgCount - selImageList.size
            val intent1 = Intent(activity, ImageGridActivity::class.java)
            startActivityForResult(intent1, REQUEST_CODE_SELECT)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS){
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT){
                var images =  data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS) as (ArrayList<ImageItem>)
                if (images != null){
                    selImageList.addAll(images)
                }
            }else if (resultCode == ImagePicker.RESULT_CODE_BACK){
                //图片预览返回
                if (data != null && requestCode == REQUEST_CODE_PREVIEW){
                    var images = data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS) as (ArrayList<ImageItem>)
                    if (images != null){
                        selImageList.clear()
                        selImageList.addAll(images)
                    }
                }
            }

        }
    }

}