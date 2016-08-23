package cn.gamedog.dayspeedassist.bean;

import java.util.List;

/**
 * Created by Tech on 2016/8/16.
 */
public class BaseLoveData {
    boolean next;
    List<LoveData> data;

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public List<LoveData> getData() {
        return data;
    }

    public void setData(List<LoveData> data) {
        this.data = data;
    }
}
