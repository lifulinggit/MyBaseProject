/*******************************************************************************
 * Copyright 2017 Yuran Zhang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package baseproject.com.mybaseproject.net

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class ResultObserver<T> : BaseObserver<BaseResponse<T>>() {
    abstract fun success(data: T)
    abstract fun failure(statusCode: Int, apiErrorModel: ApiErrorModel)

    // 业务状态
    private object Status {
        val SUCCESS = 1 // 正常
    }


    override fun onNext(t: BaseResponse<T>) {
        if (t.resultCode == Status.SUCCESS) {
            success(t.data)
            return
        }
        val apiErrorModel: ApiErrorModel = when (t.resultCode) {
            ApiErrorType.INTERNAL_SERVER_ERROR.code ->
                ApiErrorType.INTERNAL_SERVER_ERROR.getApiErrorModel()
            ApiErrorType.BAD_GATEWAY.code ->
                ApiErrorType.BAD_GATEWAY.getApiErrorModel()
            ApiErrorType.NOT_FOUND.code ->
                ApiErrorType.NOT_FOUND.getApiErrorModel()
            else -> ApiErrorModel(t.resultCode, t.resultMsg)
        }
        failure(t.resultCode, apiErrorModel)
    }

    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
        val apiErrorType: ApiErrorType = when (e) {
            is UnknownHostException -> ApiErrorType.NETWORK_NOT_CONNECT
            is ConnectException -> ApiErrorType.NETWORK_NOT_CONNECT
            is SocketTimeoutException -> ApiErrorType.CONNECTION_TIMEOUT
            else -> ApiErrorType.UNEXPECTED_ERROR
        }
        failure(apiErrorType.code, apiErrorType.getApiErrorModel())
    }
}
