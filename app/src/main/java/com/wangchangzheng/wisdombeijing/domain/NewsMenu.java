package com.wangchangzheng.wisdombeijing.domain;

import java.util.ArrayList;

/**
 * Created by wangchangzheng on 16/5/19.
 */
public class NewsMenu {

    public int retcode;
    public ArrayList<Integer> extend;
    public ArrayList<NewsMenuData> data;



    public class NewsMenuData {
        public int id;
        public String title;
        public int type;
        public ArrayList<NewsTabData> children;
        @Override
        public String toString() {
            return "NewsMenuData [title=" + title + ",children=" + children + "]";
        }
    }


    public class NewsTabData {
        public int id;
        public String title;
        public int type;
        public String url;

        @Override
        public String toString() {
            return "NewsTableData [title=" + title + "]";
        }
    }

    @Override
    public String toString() {
        return "NewsMneu [data=" + data  + "]";
    }
}
