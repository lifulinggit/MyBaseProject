package baseproject.com.mybaseproject.db;

import android.content.Context;
import android.util.Log;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import baseproject.com.mybaseproject.db.dao.UserDao;
import baseproject.com.mybaseproject.ui.activity.base.BaseApplication;


public class DBCipherHelper extends SQLiteOpenHelper {
    public static String TAG = "DBCipherHelper";
    //数据库名称
    public static String DB_NAME = "base.db";
    //数据库版本
    public static int DB_VERSION = 1;
    //数据库密码
    public static final String DB_PASSWORD = "lalala";

    private static DBCipherHelper dbHelper;

    /**
     * 构造方法
     *
     * @param context
     */
    private DBCipherHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }

    public DBCipherHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //不可忽略的  进行so库加载
        SQLiteDatabase.loadLibs(context);
    }

    /**
     * 单利模式
     */
    public static DBCipherHelper getDbHelpers(){
        if (dbHelper == null){
            synchronized (UserDao.class){
                if(dbHelper == null){
                    dbHelper = new DBCipherHelper(BaseApplication.Companion.getInstance());
                }
            }
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //首次安装时候调用  在这里进行创建表的操作
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //当数据库版本号升级的时候调用该方法
        updateTables(db , oldVersion , newVersion);
    }

    public SQLiteDatabase mDataBase;
    /**
     * 获取加密后的写数据库对象
     * @return
     */
    public SQLiteDatabase getWritableDatabase(){
        this.mDataBase = getWritableDatabase(DB_PASSWORD);
        return mDataBase;
    }

    /**
     * 获取加密后的读数据库对象
     * @return
     */
    public SQLiteDatabase getReadableDatabase(){
        this.mDataBase = getReadableDatabase(DB_PASSWORD);
        return mDataBase;
    }
    /**
     * 数据库创建
     * @param db
     */
    private void createTables(SQLiteDatabase db) {
        TableUtils.createTables(db);
    }

    /**
     * 数据库更新
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    private void updateTables(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * 关闭数据库
     * 关闭所有
     * @param cursor
     */
    public void colseAll(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        } else {
            Log.e(TAG, "closeAll: mCursor已关闭");
        }
        if (mDataBase != null && mDataBase.isOpen()) {
            if(mDataBase.inTransaction()){
                //如果开启了事物
                mDataBase.endTransaction();
            }
            mDataBase.close();
        } else {
            Log.e(TAG, "closeAll: mSQLiteDatabase已关闭");
        }
    }

}
