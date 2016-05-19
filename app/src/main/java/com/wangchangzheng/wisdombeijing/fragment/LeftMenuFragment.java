package com.wangchangzheng.wisdombeijing.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wangchangzheng.wisdombeijing.Activitys.MainActivity;
import com.wangchangzheng.wisdombeijing.R;
import com.wangchangzheng.wisdombeijing.base.impl.NewsCenterPager;
import com.wangchangzheng.wisdombeijing.domain.NewsMenu;

import java.util.ArrayList;

/**
 * Created by wangchangzheng on 16/5/19.
 */
public class LeftMenuFragment extends BaseFragment {
    @ViewInject(R.id.lv_list)
    ListView lvList;

    private ArrayList<NewsMenu.NewsMenuData> mNewsMenuData;//侧边栏网络数据对象

    private int mCurrentPos;//当前被选中的item位置

    private LeftMenuAdapter mAdapter;

    @Override
    public void initData() {

    }

    @Override
    public View initView() {
        View view=View.inflate(mActivity,R.layout.fragment_left_menu,null);
        ViewUtils.inject(this,view);
        return null;
    }

    public void setMenuData(ArrayList<NewsMenu.NewsMenuData> data){
        mCurrentPos=0;

        mNewsMenuData=data;

        mAdapter=new LeftMenuAdapter();

        lvList.setAdapter(mAdapter);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPos=position;
                mAdapter.notifyDataSetChanged();

                toggle();//收起侧边栏

                setCurrentDetaildPager(position);
            }
        });
    }

    private void setCurrentDetaildPager(int position) {
        MainActivity mainUI = (MainActivity) mActivity;
        ContentFragement fragement=mainUI.getContentFragment();

        NewsCenterPager newsCenterPager=fragement.getNewsCenterPager();

        newsCenterPager.setCurrentDetailPager(position);
    }

    private void toggle() {
        MainActivity mainUI= (MainActivity) mActivity;
        SlidingMenu slidingMenu=mainUI.getSlidingMenu();
        slidingMenu.toggle();
    }


    private class LeftMenuAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mNewsMenuData.size();
        }

        @Override
        public Object getItem(int position) {
            return mNewsMenuData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(mActivity,R.layout.left_meu_item_list,null);

            TextView tvMenu= (TextView) view.findViewById(R.id.tv_menu);
            NewsMenu.NewsMenuData item= (NewsMenu.NewsMenuData) getItem(position);
            tvMenu.setText(item.title);

            if (position==mCurrentPos){
                tvMenu.setEnabled(true);
            }else {
                tvMenu.setEnabled(false);
            }
            return view;
        }
    }
}
