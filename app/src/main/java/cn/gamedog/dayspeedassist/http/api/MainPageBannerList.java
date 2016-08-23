package cn.gamedog.dayspeedassist.http.api;

import cn.gamedog.dayspeedassist.bean.BaseData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Tech on 2016/8/11.
 */
public interface MainPageBannerList {

    @GET("index.php?m=Article&a=lists&page=1&flag=p&locations=market&pageSize=5")
    Observable<BaseData> getBannerList(@Query("typeid") String typeid);












}
