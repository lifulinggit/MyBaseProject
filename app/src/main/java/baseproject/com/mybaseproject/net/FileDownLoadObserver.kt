package baseproject.com.mybaseproject.net

import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

abstract class FileDownLoadObserver<T> : DisposableObserver<T>() {

    override fun onNext(t: T) {
        onDownLoadSuccess(t)
    }

    override fun onError(e: Throwable) {
        onDownLoadFail(e)
    }

    //可以重写，具体可由子类实现
    override fun onComplete() {}

    //下载成功的回调
    abstract fun onDownLoadSuccess(t: T)

    //下载失败回调
    abstract fun onDownLoadFail(throwable: Throwable)

    //下载进度监听
    abstract fun onProgress(progress: Int, total: Long)

    /**
     * 将文件写入本地
     * @param responseBody 请求结果全体
     * @param destFileDir 目标文件夹
     * @param destFileName 目标文件名
     * @return 写入完成的文件
     * @throws IOException IO异常
     */
    @Throws(IOException::class)
    fun saveFile(responseBody: ResponseBody, destFilePath: String): File? {
        val inputStream = responseBody.byteStream()
        val buffer = ByteArray(1024*4)
        var fos : FileOutputStream?= null
        val total = responseBody.contentLength()
        var sum: Long = 0
        var len = 0
        val off = 0
        try{
            var file = File(destFilePath)
            fos = FileOutputStream(file)
            while (inputStream.read(buffer).apply { len = this } >0){
                fos?.write(buffer , off, len)
                sum += len.toLong()
                val progress =(sum * 1.0f / total * 100).toInt()
                onProgress(progress , total)
            }
            fos?.flush()
            fos?.close()
            return file
        }catch (e : IOException){
            onDownLoadFail(e)
            return null
        }
    }
}