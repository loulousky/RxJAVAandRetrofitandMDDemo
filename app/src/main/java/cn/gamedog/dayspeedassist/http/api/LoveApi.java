package cn.gamedog.dayspeedassist.http.api;

import cn.gamedog.dayspeedassist.bean.BaseLoveData;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Tech on 2016/8/16.
 */
public interface LoveApi {
    @GET("index.php?m=love&a=index&pageSize=20&did=200641")
    Observable<BaseLoveData> getLoveData(@Query("page") int page);

}
