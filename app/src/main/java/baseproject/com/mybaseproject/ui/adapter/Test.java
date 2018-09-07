package baseproject.com.mybaseproject.ui.adapter;

import android.support.v7.widget.RecyclerView;

import baseproject.com.mybaseproject.ui.activity.base.BaseApplication;

public class Test {
    void ff(){
        RecyclerView view = new RecyclerView(BaseApplication.Companion.getInstance());
        view.addItemDecoration(new RecyclerView.ItemDecoration() {
        });
    }
}
