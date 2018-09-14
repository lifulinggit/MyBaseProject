package baseproject.com.mybaseproject.utils

import android.content.Context
import android.widget.ImageView
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.app.BaseApplication
import baseproject.com.mybaseproject.utils.glide.GlideApp
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

object ImageUtils {
    var requestOptions = RequestOptions().placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .priority(Priority.NORMAL)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()

    /**
     * 加载圆角图片
     */
    fun loadRadius(context: Context , imageView: ImageView , url : String , radius : Int){
        GlideApp.with(context)
                .load(url).apply(requestOptions)
                .transform(RoundedCorners(radius))
                .into(imageView)
    }

    /**
     * 加载圆形图片
     */
    fun  loadRound(context: Context , imageView: ImageView , url : String){
        GlideApp.with(context)
                .load(url).apply(requestOptions)
                .transform(CircleCrop())
                .into(imageView)
    }

    /**
     * 默认加载图片
     */
    fun loadDefault(context: Context , imageView: ImageView , url : Any){
        GlideApp.with(context)
                .load(url).apply(requestOptions)
                .into(imageView)
    }


    /**
     * 清理缓存
     */
    fun clearDiskCache(){
        GlideApp.get(BaseApplication.getInstance()).clearDiskCache()
    }
}