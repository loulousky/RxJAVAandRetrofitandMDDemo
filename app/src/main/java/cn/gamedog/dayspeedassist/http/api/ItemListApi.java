package cn.gamedog.dayspeedassist.http.api;

import cn.gamedog.dayspeedassist.bean.BaseData;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Tech on 2016/8/13.
 */
public interface ItemListApi {

    @GET("index.php?m=Article&a=lists&pageSize=20")
    Observable<BaseData> getList(@Query("page") int page,@Query("typeid") String type,@Query("sid") String sid);


}
