package baseproject.com.mybaseproject.db.dao;

import baseproject.com.mybaseproject.db.DBCipherHelper;

/**
 * dao的基类  用于为子类统一的提供 DBCipherHelper 对象
 */
class BaseDao {
    protected DBCipherHelper dbHelper;

    public BaseDao() {
        this.dbHelper = DBCipherHelper.getDbHelpers();
    }
}
