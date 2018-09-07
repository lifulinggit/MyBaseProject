package baseproject.com.mybaseproject.db.dao;

import android.content.ContentValues;
import android.util.Log;

import net.sqlcipher.Cursor;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;

import baseproject.com.mybaseproject.db.TableUtils;
import baseproject.com.mybaseproject.model.bean.User;
import baseproject.com.mybaseproject.utils.ToastUtils;

public class UserDao extends BaseDao {
    private static UserDao dao;

    private UserDao() {
    }

    public static UserDao getDao() {
        if (dao == null) {
            synchronized (UserDao.class) {
                if (dao == null) {
                    dao = new UserDao();
                }
            }
        }
        return dao;
    }

    /**
     * 插入一条数据
     *
     * @param user
     */
    public void insert(User user) {
        ContentValues values = new ContentValues();
        values.put("userName", user.getUserName());
        values.put("userId", user.getUserId());
        TableUtils.insertData(TableUtils.TABLE_USER, values);
    }

    /**
     * 通过 userId 删除数据
     */
    public void deleteByUserId(String userId) {
        TableUtils.deleteData(TableUtils.TABLE_USER, new String[]{userId}, "userId");
    }

    public User queryByUserId(String userId) {
        //获取可读数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //查询数据库
        Cursor cursor = null;
        try {
             cursor = db.rawQuery("select * from user", null);
            while (cursor.moveToNext()) {
                int count = cursor.getColumnCount();
                String columName = cursor.getColumnName(0);
                String tname = cursor.getString(0);
                Log.e("huangxiaoguo", "count = " + count + " columName = " + columName + "  name =  " + tname);
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException e) {
            ToastUtils.INSTANCE.showToast(e.getMessage());
        }
        //关闭数据库
        db.close();
        return null;
    }
}
