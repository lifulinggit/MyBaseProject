package baseproject.com.mybaseproject.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import baseproject.com.mybaseproject.R
import baseproject.com.mybaseproject.ui.adapter.CloudFragmentAdapter
import baseproject.com.mybaseproject.ui.base.BaseFragment
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_cloud.*

class CloudFragment : BaseFragment() {
    private var mTitle: String? = null
    private lateinit var mList : ArrayList<String>

    companion object {
        fun getInstance(title : String) : CloudFragment{
            val fragment = CloudFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int? {
        return R.layout.fragment_cloud
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initView()
    }

     fun initData() {
        mList = ArrayList<String>()
        for (i in 1..30){
            mList.add(i.toString())
        }
    }

     fun initView() {
        mTitle?.let { setTitle(it) }
        setBtnCommiteVisible(View.GONE , "")
        initRecyclerView()
    }

    private fun initRecyclerView() {
        var layoutManager = LinearLayoutManager(activity)
        //设置为纵向排列
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView.layoutManager = layoutManager
        //初始化adapter
        var mAdapter = CloudFragmentAdapter(R.layout.item_cloud , mList)
        //条目的点击事件
        mAdapter.setOnRecyclerViewItemClickListener(BaseQuickAdapter.OnRecyclerViewItemClickListener { view, i ->
            showToast(mList.get(i))
        })
        //加载更多
        mAdapter.setOnLoadMoreListener{
            showToast("加载更多")
        }
        //添加头布局
        var tv = TextView(activity)
        tv.text = "这是头布局"
        mAdapter.addHeaderView(tv)

        mRecyclerView.adapter = mAdapter
    }
}