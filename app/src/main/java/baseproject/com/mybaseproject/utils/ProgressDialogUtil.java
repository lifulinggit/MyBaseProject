package baseproject.com.mybaseproject.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import baseproject.com.mybaseproject.R;

public class ProgressDialogUtil {


	public static void showProgress(Context context , String msg){
		if (mDialog == null){
			mDialog = createLoadingDialog(context , msg);
		}
		mDialog.show();
	}

	public static void dissmiss(){
		if (mDialog != null && mDialog.isShowing()){
			mDialog.dismiss();
		}
	}
	private static Dialog mDialog;// 圆形
	/**
	 * 得到自定义的progressDialog
	 *
	 * @param context
	 * @param msg
	 * @return
	 */
	public static Dialog createLoadingDialog(Context context, String msg) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		// main.xml中的ImageView
		// ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
		TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
		// 加载动画
		// Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
		// context, R.anim.loading_animation);
		// 使用ImageView显示动画
		// spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		tipTextView.setText(msg);// 设置加载信息

		if (mDialog == null){
			mDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
			mDialog.setCanceledOnTouchOutside(false);
			// loadingDialog.setCancelable(false);// 不可以用“返回键”取消
			mDialog.setContentView(layout, new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
		}
		return mDialog;

	}

}
