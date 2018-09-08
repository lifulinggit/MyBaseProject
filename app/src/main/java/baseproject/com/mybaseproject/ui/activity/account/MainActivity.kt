package baseproject.com.mybaseproject.ui.activity.account

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.ui.base.BaseActivity
import baseproject.com.mybaseproject.ui.fragment.CloudFragment
import baseproject.com.mybaseproject.ui.fragment.MusicFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var mMusicFragment: MusicFragment? = null
    private var mCloudFragment: CloudFragment? = null
    private var mFriendFragment: MusicFragment? = null
    private var mVisibleFragment: MusicFragment? = null

    //默认为0
    private var mIndex = 0
    private var currTabIndex: String = "currTabIndex"

    override fun getLayoutId(): Int? {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView(savedInstanceState)
    }


    fun initView(savedInstanceState: Bundle?) {
        mIndex = savedInstanceState?.getInt(currTabIndex) ?: R.id.i_music
        //隐藏toolbar
        hideToolBar()
        //文字大小动画
//        mBottomView.enableAnimation(false)
        //左右移动动画
        mBottomView.enableShiftingMode(false)
        //未选中条目是否显示文字
        mBottomView.enableItemShiftingMode(false)

        //添加tab条目的点击事件
        mBottomView.setOnNavigationItemSelectedListener {
            switchFragment(it.itemId)
            true
        }
        mBottomView.selectedItemId = mIndex
    }



    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (mBottomView != null) {
            outState?.putInt(currTabIndex, mIndex)
        }
    }

    /**
     * 切换fragment
     */
    private fun switchFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (index) {
            //音乐
            R.id.i_music -> {
                mMusicFragment?.let { transaction.show(it) }
                        ?: MusicFragment.getInstance("音乐")?.let {
                            mMusicFragment = it
                            transaction.add(R.id.mFragmentCotent, it, "MUSIC")
                        }
            }
            //云端
            R.id.i_backup -> {
                mCloudFragment?.let { transaction.show(it) }
                        ?: CloudFragment.getInstance("云端")?.let {
                            mCloudFragment = it
                            transaction.add(R.id.mFragmentCotent, it, "CLOUD")
                        }
            }
            //朋友圈
            R.id.i_friends -> {
                mFriendFragment?.let { transaction.show(it) }
                        ?: MusicFragment.getInstance("朋友圈")?.let {
                            mFriendFragment = it
                            transaction.add(R.id.mFragmentCotent, it, "FRIEND")
                        }
            }
            //发现
            R.id.i_visibility -> {
                mVisibleFragment?.let { transaction.show(it) }
                        ?: MusicFragment.getInstance("发现")?.let {
                            mVisibleFragment = it
                            transaction.add(R.id.mFragmentCotent, it, "VISIBLE")
                        }
            }
            else ->{}
        }
        mIndex = index
        transaction.commitAllowingStateLoss()
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mMusicFragment?.let { transaction.hide(it) }
        mCloudFragment?.let { transaction.hide(it) }
        mFriendFragment?.let { transaction.hide(it) }
        mVisibleFragment?.let { transaction.hide(it) }
    }
}