package baseproject.com.mybaseproject.db;


import android.content.ContentValues;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.List;

public class TableUtils {
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
        String createSql = TABLE_USER+"(" +
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
        db.execSQL("create table " + sql);
    }

    /**
     * 删除表
     * @param db
     * @param tableName
     */
    public static void dropTable(SQLiteDatabase db , String tableName){
        db.execSQL("drop table " +  tableName);
    }

    /**
     * 清空表数据
     * @param db
     * @param tableName
     */
    public static void deleteTable(SQLiteDatabase db , String tableName){
        db.execSQL("delete from "+ tableName);
    }

    /**
     * 插入数据
     * @param tableName
     * @param values
     */
    public static void insertData(String tableName , ContentValues values){
        //获取写数据库
        SQLiteDatabase db = DBCipherHelper.getDbHelpers().getWritableDatabase();
        // insert 操作
        db.insert(tableName, null, values);
        //关闭数据库
        db.close();
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
            // insert 操作
            db.insert(tableName, null, contentValues.get(i));
        }
        //关闭数据库
        db.close();
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
                db.insert(tableName, null, contentValues.get(i));
            }
            db.setTransactionSuccessful(); //设置事务处理成功，不设置会自动回滚不提交
        } catch (Exception e) {

        } finally {
            db.endTransaction(); //处理完成
            //关闭数据库
            db.close();
        }
    }
    /**
     * 删除数据
     */
    public static void deleteData(String tableName , String[] values , String ...names) {
        //拼接条件参数
        StringBuffer stringBuffer = new StringBuffer("");
        if (names.length == 1){
            stringBuffer.append(names[0] + "=?");
        }else {
            for (String name : names){
                stringBuffer.append(name + "=?and ");
            }
            //去除最后的一个 "and "
            stringBuffer.delete(stringBuffer.length() - "and ".length() , stringBuffer.length() - 1);
        }
        //获取写数据库
        SQLiteDatabase db = DBCipherHelper.getDbHelpers().getWritableDatabase();
        // delete 操作
        db.delete(tableName, stringBuffer.toString(), values);
        //关闭数据库
        db.close();
    }

}
