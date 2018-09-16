package com.cmcc.andedu.hdsyn.domain;

import java.util.List;

/**
 * Created by LiYangpan on 2018/8/15  4:45 PM.
 * with INTELLIJ IDEA on rmbp osx 10.11
 * 描述:
 */
public class MsgBean {

    private int allcount = 0;

    private List<?> data;

//    MsgBean(int allcount, List<?> data) {
//        allcount = this.allcount;
//        data = this.data;
//    }


    public int getAllcount() {
        return allcount;
    }

    public void setAllcount(int allcount) {
        this.allcount = allcount;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
