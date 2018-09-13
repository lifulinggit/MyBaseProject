package baseproject.com.mybaseproject.net


import baseproject.com.mybaseproject.utils.scheduler.RxUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

object FileUpOrDownloadUtils {
    /**
     * 单文件上传
     *
     * @param path
     */
    fun uploadFile(path: String, params: Map<String, String>): Observable<BaseResponse<String>> {
        val file = File(path)
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val filePart = MultipartBody.Part.createFormData("headimg", file.name, requestFile)
        return RetrofitManager.service.uploadFile(filePart, params)

    }

    /**
     * 多文件上传
     *
     * @param media
     */
    fun uploadMutFile(media: ArrayList<String>, params: Map<String, String>): Observable<BaseResponse<String>> {
        val parts = arrayOfNulls<MultipartBody.Part>(media.size)
        var cnt = 0
        for (m in media) {
            val file = File(m)
            val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val filePart = MultipartBody.Part.createFormData("headimg[]", file.name, requestFile)
            parts[cnt] = filePart
            cnt++
        }
        return RetrofitManager.service.uploadMutFiles(parts, params)
    }

    /**
     * 文件下载
     */
    fun downloadFile(url: String, target: String,
                     fileDownLoadObserver: FileDownLoadObserver<File>): FileDownLoadObserver<File> {

        return RetrofitManager.service.downloadFile(url)
                .compose(RxUtils.downloadFile())
                .map { responseBody -> fileDownLoadObserver.saveFile(responseBody, target) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(fileDownLoadObserver)

    }
}
