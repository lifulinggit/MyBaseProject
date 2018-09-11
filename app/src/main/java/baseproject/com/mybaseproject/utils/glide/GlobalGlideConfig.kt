package baseproject.com.mybaseproject.utils.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule


/**
 *
 * Glide全局配置，使用GlideModule注解执行自动代码生成，生成GlideApp，后续的Glide
 * 调用都需要替换为GlideApp.with(context).load(url).into(imageView) 的方式
 *
 */
@GlideModule
class GlobalGlideConfig : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        //默认内存缓存大小
//        var calculator =  MemorySizeCalculator.Builder(context)
//                .setMemoryCacheScreens(2.0f)
//                .build()
//        builder.setMemoryCache(LruResourceCache(calculator.memoryCacheSize.toLong()))
        //自定义内存缓存大小
//        val memoryCacheSizeBytes = 1024 * 1024 * 20 // 20mb
//        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))

        //自定义内置磁盘缓存大小
        var diskCacheSizeBytes = 1024 * 1024 * 100 // 100 MB
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes.toLong()))


    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
    }
}