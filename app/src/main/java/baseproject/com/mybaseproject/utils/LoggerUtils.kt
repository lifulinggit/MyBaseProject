package baseproject.com.mybaseproject.utils

import com.orhanobut.logger.Logger

/**
 * 日志工具类
 */
object LoggerUtils {
    fun showILogger(TAG: String, msg: String) {
        Logger.i(TAG, msg)
    }

    fun showDLogger(TAG: String, msg: String) {
        Logger.d(TAG, msg)
    }

    fun showELogger(TAG: String, msg: String) {
        Logger.e(TAG, msg)
    }

    fun showJsonString(msg: String) {
        Logger.json(msg)
    }

    fun showVLogger(TAG: String, msg: String) {
        Logger.v(TAG, msg)
    }
}
