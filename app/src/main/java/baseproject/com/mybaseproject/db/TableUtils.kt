package baseproject.com.mybaseproject.db


import android.content.ContentValues
import android.util.Log

import net.sqlcipher.Cursor
import net.sqlcipher.database.SQLiteDatabase

object TableUtils {

    var TAG = "TableUtils "
    //游标
    lateinit var mCursor: Cursor

    //user表的表名
    const val TABLE_USER = "user"

    /**
     * 创建表
     * @param db
     */
    fun createTables(db: SQLiteDatabase) {
        //创建用户表
        createUserTable(db)
    }

    /**
     * 创建用户表
     * @param db
     */
    fun createUserTable(db: SQLiteDatabase) {
        val createSql = TABLE_USER + "(" +
                "userId text primary key," +
                "userName text," +
                "access_token text)"
        createTable(db, createSql)
    }
    /**
     * 创建数据库表
     * @param db
     */
    fun createTable(db: SQLiteDatabase, sql: String) {
        db.execSQL("create table if not exists$sql")
        closeAll()
    }

    /**
     * 删除表
     * @param db
     * @param tableName
     */
    fun dropTable(db: SQLiteDatabase, tableName: String) {
        db.execSQL("drop table if exists $tableName")
        closeAll()
    }
    /**
     * 清空表数据
     * @param db
     * @param tableName
     */
    fun deleteTable(db: SQLiteDatabase, tableName: String) {
        db.execSQL("delete from $tableName")
        closeAll()
    }

    /**
     * 插入数据
     * @param tableName
     * @param values
     */
    fun insertData(tableName: String, values: ContentValues) {
        //获取写数据库
        val db = DBCipherHelper.getDbHelpers().writableDatabase
        //replace操作  在数据库中会要求插入时，某条记录不存在则插入，存在则更新。或更新时，某条记录存在则更新，不存在则插入
        db.replace(tableName, null, values)
        // insert 操作
        //        db.insert(tableName, null, values);
        //关闭数据库
        closeAll()
    }

    /**
     * 未开启事务批量插入
     * @param tableName
     * @param contentValues
     */
    fun insertDatasByNomarl(tableName: String, contentValues: List<ContentValues>?) {
        if (contentValues == null) {
            return
        }
        //获取写数据库
        val db = DBCipherHelper.getDbHelpers().writableDatabase
        for (i in contentValues.indices) {
            // replace 操作
            db.replace(tableName, null, contentValues[i])
        }
        //关闭数据库
        closeAll()
    }

    /**
     * 开启事务批量插入
     * @param tableName
     * @param contentValues
     */
    fun insertDatasByTransaction(tableName: String, contentValues: List<ContentValues>) {
        //获取写数据库
        val db = DBCipherHelper.getDbHelpers().writableDatabase
        db.beginTransaction()  //手动设置开始事务
        try {
            //批量处理操作
            for (i in contentValues.indices) {
                // insert 操作
                db.replace(tableName, null, contentValues[i])
            }
            db.setTransactionSuccessful() //设置事务处理成功，不设置会自动回滚不提交
        } catch (e: Exception) {

        } finally {
            //关闭数据库
            closeAll()
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
    fun mQuery(tableName: String, columns: Array<String>, selection: String,
               selectionArgs: Array<String>, groupBy: String, having: String, orderBy: String): Cursor {
        val db = DBCipherHelper.getDbHelpers().writableDatabase
        return db.query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    /**
     * 查询
     * @param sql
     * @param strings
     * @return
     */
    fun rawQuery(sql: String, strings: Array<String>): Cursor {
        val db = DBCipherHelper.getDbHelpers().writableDatabase
        return db.rawQuery(sql, strings)
    }

    /**
     * 查询全部(查询后需要在调用的类中手动调用closeAll()方法来关闭全部函数)
     *
     * @param tableName 表名
     * @return mCursor 游标
     */
    fun mQueryAll(tableName: String): Cursor {
        val db = DBCipherHelper.getDbHelpers().writableDatabase
        return db.query(tableName, null, null, null, null, null, null)
    }

    /**
     * 更新一个字段
     * @param tableName
     * @param whereClause
     * @param whereArgs
     */
    fun update(tableName: String, contentValue: ContentValues, whereClause: String, whereArgs: Array<String>) {
        val db = DBCipherHelper.getDbHelpers().writableDatabase
        db.update(tableName, contentValue, whereClause, whereArgs)
        //关闭数据库
        closeAll()
    }

    /**
     * 删除数据
     */
    fun deleteData(tableName: String, values: Array<String>, vararg names: String) {
        //拼接条件参数
        val stringBuffer = StringBuffer("")
        if (names.size == 1) {
            stringBuffer.append(names[0] + "=?")
        } else {
            for (name in names) {
                stringBuffer.append("$name=?and ")
            }
            //去除最后的一个 "and "
            stringBuffer.delete(stringBuffer.length - "and ".length, stringBuffer.length - 1)
        }
        //获取写数据库
        val db = DBCipherHelper.getDbHelpers().writableDatabase
        // delete 操作
        db.delete(tableName, stringBuffer.toString(), values)
        //关闭数据库
        closeAll()
    }

    /**
     * 判断某张表是否存在
     *
     * @param tableName 表名
     * @return true 存在
     */
    fun isTableExist(tableName: String?): Boolean {
        var result = false
        if (tableName == null) {
            return false
        }
        try {
            val db = DBCipherHelper.getDbHelpers().writableDatabase
            val sql = "select count(*) as c from sqlite_master where type ='table' and name ='" + tableName.trim { it <= ' ' } + "' "
            mCursor = db.rawQuery(sql, null)
            if (mCursor.moveToNext()) {
                val count = mCursor.getInt(0)
                if (count > 0) {
                    result = true
                }
            }
            //关闭数据库
            closeAll()
        } catch (e: Exception) {
            Log.e(TAG, tableName + "表不存在")
        }

        return result
    }

    /**
     * 获取表中有多少条数据
     *
     * @param tableName
     * @return
     */
    fun getDataNum(tableName: String): Int {
        val db = DBCipherHelper.getDbHelpers().writableDatabase
        mCursor = db.query(tableName, null, null, null, null, null, null)
        val num = mCursor.count
        closeAll()
        return num
    }

    /**
     * 关闭数据库
     */
    fun closeAll() {
        DBCipherHelper.getDbHelpers().colseAll(mCursor)
    }
}
