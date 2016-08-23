package cn.gamedog.dayspeedassist.bean;

import java.util.List;

/**
 * Created by Tech on 2016/8/11.
 */
public class BaseData {
    String count;
    boolean next;
    List<Itemdata> data;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public List<Itemdata> getData() {
        return data;
    }

    public void setData(List<Itemdata> data) {
        this.data = data;
    }


}
