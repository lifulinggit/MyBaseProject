package baseproject.com.mybaseproject.app

import android.app.Application
import android.support.multidex.MultiDexApplication
import baseproject.com.mybaseproject.utils.glide.GlideImageLoader
import com.lzy.imagepicker.ImagePicker
import com.lzy.imagepicker.view.CropImageView
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

class BaseApplication : MultiDexApplication() {

    companion object {
        private var instance: BaseApplication? = null

        fun getInstance() : Application{
            return this!!.instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initConfig()
        initImagePicker()
    }

    /**
     * 初始化配置
     */
    private fun initConfig() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // 隐藏线程信息 默认：显示
                .methodCount(0)         // 决定打印多少行（每一行代表一个方法）默认：2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("hao_zz")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return true
            }
        })
    }
    /**
     * 初始化照片选择器
     */
    private fun initImagePicker() {
        var imagePicker = ImagePicker.getInstance()
        imagePicker.imageLoader = GlideImageLoader()//设置图片加载器
        imagePicker.isShowCamera = true //显示拍照按钮
        imagePicker.isCrop = true //允许裁剪  （单选才有效）
        imagePicker.isSaveRectangle = true //是否按照矩形区域保存
        imagePicker.selectLimit = 3 //选中数量限制
        imagePicker.isMultiMode = true //多选
        imagePicker.style = CropImageView.Style.RECTANGLE //裁剪框的形状
        imagePicker.focusHeight = 800  //裁剪框的高度
        imagePicker.focusWidth = 800  //裁剪框的宽度
        imagePicker.outPutX = 1000 //保存文件的宽度
        imagePicker.outPutY = 1000 //保存文件的高度
    }

}
