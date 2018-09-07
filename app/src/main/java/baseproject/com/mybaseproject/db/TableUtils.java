package baseproject.com.mybaseproject.db;


import android.content.ContentValues;
import android.util.Log;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.util.List;

public class TableUtils {

    public static String TAG = "TableUtils ";
    //游标
    public static Cursor mCursor;

    //user表的表名
    public static String TABLE_USER = "user";


    /**
     * 创建表
     * @param db
     */
    public static void createTables(SQLiteDatabase db) {
        //创建用户表
        createUserTable(db);
    }

    /**
     * 创建用户表
     * @param db
     */
    public static void createUserTable(SQLiteDatabase db){
        String createSql = TABLE_USER +"(" +
                "userId text primary key," +
                "userName text," +
                "access_token text)";
        createTable(db , createSql);
    }



    /**
     * 创建数据库表
     * @param db
     */
    public static void createTable(SQLiteDatabase db , String sql) {
        db.execSQL("create table if not exists" + sql);
        closeAll();
    }

    /**
     * 删除表
     * @param db
     * @param tableName
     */
    public static void dropTable(SQLiteDatabase db , String tableName){
        db.execSQL("drop table if exists " +  tableName);
       closeAll();
    }



    /**
     * 清空表数据
     * @param db
     * @param tableName
     */
    public static void deleteTable(SQLiteDatabase db , String tableName){
        db.execSQL("delete from "+ tableName);
        closeAll();
    }

    /**
     * 插入数据
     * @param tableName
     * @param values
     */
    public static void insertData(String tableName , ContentValues values){
        //获取写数据库
        SQLiteDatabase db = DBCipherHelper.getDbHelpers().getWritableDatabase();

        //replace操作  在数据库中会要求插入时，某条记录不存在则插入，存在则更新。或更新时，某条记录存在则更新，不存在则插入
        db.replace(tableName , null , values);
        // insert 操作
//        db.insert(tableName, null, values);
        //关闭数据库
        closeAll();
    }

    /**
     * 未开启事务批量插入
     * @param tableName
     * @param contentValues
     */
    public static void insertDatasByNomarl(String tableName , List<ContentValues> contentValues) {
        if (contentValues == null){
            return;
        }
        //获取写数据库
        SQLiteDatabase db = DBCipherHelper.getDbHelpers().getWritableDatabase();
        for (int i = 0; i < contentValues.size(); i++) {
            // replace 操作
            db.replace(tableName, null, contentValues.get(i));
        }
        //关闭数据库
        closeAll();
    }
    /**
     * 开启事务批量插入
     * @param tableName
     * @param contentValues
     */
    public static void insertDatasByTransaction(String tableName , List<ContentValues> contentValues) {
        //获取写数据库
        SQLiteDatabase db = DBCipherHelper.getDbHelpers().getWritableDatabase();
        db.beginTransaction();  //手动设置开始事务
        try {
            //批量处理操作
            for (int i = 0; i < contentValues.size(); i++) {
                // insert 操作
                db.replace(tableName, null, contentValues.get(i));
            }
            db.setTransactionSuccessful(); //设置事务处理成功，不设置会自动回滚不提交
        } catch (Exception e) {

        } finally {
            //关闭数据库
            closeAll();
        }
    }

    /**
     * 查询
     *
     * @param tableName
     * @param columns
     * @param selection
     * @param selectionArgs
     * @return mCursor 游标
     */
    public static Cursor mQuery(String tableName, String[] columns, String selection,
                         String[] selectionArgs , String groupBy , String having , String orderBy) {
        SQLiteDatabase db = DBCipherHelper.getDbHelpers().getWritableDatabase();
        Cursor cursor = db.query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy);
        return cursor;
    }

    /**
     * 查询
     * @param sql
     * @param strings
     * @return
     */
    public static Cursor rawQuery(String sql , String[] strings){
        SQLiteDatabase db = DBCipherHelper.getDbHelpers().getWritableDatabase();
        return db.rawQuery(sql , strings);
    }
    /**
     * 查询全部(查询后需要在调用的类中手动调用closeAll()方法来关闭全部函数)
     *
     * @param tableName 表名
     * @return mCursor 游标
     */
    public static Cursor mQueryAll(String tableName) {
        SQLiteDatabase db = DBCipherHelper.getDbHelpers().getWritableDatabase();
        Cursor cursor = db.query(tableName, null, null, null, null, null, null);
        return cursor;
    }

    /**
     * 更新一个字段
     * @param tableName
     * @param whereClause
     * @param whereArgs
     */
    public static void update(String tableName , ContentValues contentValue ,  String whereClause, String[] whereArgs){
        SQLiteDatabase db = DBCipherHelper.getDbHelpers().getWritableDatabase();
        db.update(tableName, contentValue, whereClause, whereArgs);
        //关闭数据库
        closeAll();
    }
    /**
     * 删除数据
     */
    public static void deleteData(String tableName ,String whereClause, String[] whereArgs) {

        //获取写数据库
        SQLiteDatabase db = DBCipherHelper.getDbHelpers().getWritableDatabase();
        // delete 操作
        db.delete(tableName , whereClause , whereArgs);
        //关闭数据库
        closeAll();
    }
    /**
     * 判断某张表是否存在
     *
     * @param tableName 表名
     * @return true 存在
     */
    public static boolean isTableExist(String tableName) {
        boolean result = false;
        if (tableName == null) {
            return false;
        }
        try {
            SQLiteDatabase db = DBCipherHelper.getDbHelpers().getWritableDatabase();
            String sql = "select count(*) as c from sqlite_master where type ='table' and name ='" + tableName.trim() + "' ";
            mCursor = db.rawQuery(sql, null);
            if (mCursor.moveToNext()) {
                int count = mCursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }
            //关闭数据库
            closeAll();
        } catch (Exception e) {
            Log.e(TAG, tableName + "表不存在");
        }
        return result;
    }
    /**
     * 获取表中有多少条数据
     *
     * @param tableName
     * @return
     */
    public static int getDataNum(String tableName) {
        SQLiteDatabase db = DBCipherHelper.getDbHelpers().getWritableDatabase();
        mCursor = db.query(tableName, null, null, null, null, null, null);
        int num = mCursor.getCount();
        closeAll();
        return num;
    }
    /**
     * 关闭数据库
     */
    public static void closeAll(){
        DBCipherHelper.getDbHelpers().colseAll(mCursor);
    }
}
