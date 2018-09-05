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

import android.app.Dialog
import android.content.Context
import baseproject.com.mybaseproject.R

object LoadingDialog {
    private var dialog: Dialog? = null

    fun show(context: Context) {
        if (dialog == null){
            dialog = Dialog(context)
            dialog = Dialog(context, R.style.LoadingDialog)
            dialog?.setContentView(R.layout.dialog_loading)
            dialog?.setCancelable(false)
            dialog?.setCanceledOnTouchOutside(false)
        }
        if (dialog!!.isShowing){
            cancel()
        }else{
            dialog?.show()
        }


    }

    fun cancel() {
        dialog?.dismiss()
    }
}
