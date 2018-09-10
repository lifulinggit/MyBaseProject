package baseproject.com.mybaseproject.utils;

import com.orhanobut.logger.Logger;

public class LoggerUtils {
    public static void showiLogger(String TAG , String msg){
        Logger.i("---------"+ TAG , msg);
    }
}
