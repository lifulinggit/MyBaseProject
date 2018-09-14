package baseproject.com.mybaseproject.utils.glide

import android.app.Activity
import android.widget.ImageView
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.utils.ImageUtils
import com.lzy.imagepicker.loader.ImageLoader

class GlideImageLoader : ImageLoader {
    override fun clearMemoryCache() {
        ImageUtils.clearDiskCache()
    }

    override fun displayImagePreview(activity: Activity?, path: String?, imageView: ImageView?, width: Int, height: Int) {
        ImageUtils.loadDefault(activity!! , imageView!! , R.mipmap.ic_launcher_round)
    }

    override fun displayImage(activity: Activity?, path: String?, imageView: ImageView?, width: Int, height: Int) {
        ImageUtils.loadDefault(activity!! , imageView!! , path!!)
    }
}