package baseproject.com.mybaseproject.db.dao

import android.content.ContentValues
import baseproject.com.mybaseproject.db.TableUtils
import baseproject.com.mybaseproject.model.bean.User
import net.sqlcipher.Cursor
import java.util.*

class UserDao private constructor() : BaseDao() {

    /**
     * 插入一条数据
     *
     * @param user
     */
    fun insert(user: User) {
        val values = ContentValues()
        values.put("userName", user.userName)
        values.put("userId", user.userId)
        values.put("access_token", user.access_token)
        TableUtils.insertData(TableUtils.TABLE_USER, values)
    }

    /**
     * 通过 userId 删除数据
     */
    fun deleteByUserId(userId: String) {
        TableUtils.deleteData(TableUtils.TABLE_USER, arrayOf(userId), "userId")
    }

    /**
     * 更新
     */
    fun updateUserNameByUserId(userId: String, newName: String) {
        val values = ContentValues()
        values.put("userName", newName)
        TableUtils.update(TableUtils.TABLE_USER, values, "userId=?", arrayOf(userId))
    }

    /**
     * 查询本地数据库的全部数据
     *
     * @return
     */
    fun findAllData(): ArrayList<User> {
        val temp = ArrayList<User>()
        val cursor = TableUtils.mQueryAll(TableUtils.TABLE_USER)
        while (cursor.moveToNext()) {
            temp.add(parseToUser(cursor))
        }
        TableUtils.closeAll()
        return temp
    }

    /**
     * 通过userId查询
     * @param userId
     * @return
     */
    fun queryByUserId(userId: String): User? {
        val cursor = TableUtils.mQuery(TableUtils.TABLE_USER, emptyArray(),
                "userId=?", arrayOf(userId), null.toString(), null.toString(), null.toString())
        var user: User? = null
        while (cursor.moveToNext()) {
            user = parseToUser(cursor)
            break
        }
        return user
    }

    /**
     * 通过游标生成 User对象
     * @param cursor
     * @return
     */
    private fun parseToUser(cursor: Cursor): User {
        val userId = cursor.getString(cursor.getColumnIndex("userId"))
        val userName = cursor.getString(cursor.getColumnIndex("userName"))
        val access_token = cursor.getString(cursor.getColumnIndex("access_token"))
        return User(userName, userId, access_token)
    }

    companion object {
        private var dao: UserDao? = null

        fun getDao(): UserDao? {
            if (dao == null) {
                synchronized(UserDao::class.java) {
                    if (dao == null) {
                        dao = UserDao()
                    }
                }
            }
            return dao
        }
    }
}
