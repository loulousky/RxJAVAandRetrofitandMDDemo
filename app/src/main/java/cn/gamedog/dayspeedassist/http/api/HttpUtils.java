package cn.gamedog.dayspeedassist.http.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tech on 2016/8/11.
 */
public class HttpUtils {


    /**
     * 获得首页广告
     * @return
     */


    private static String BaseUrl="http://zhushouapi.gamedog.cn/";

   private static  MainPageBannerList mainPageBannerList;
    private static ItemListApi itemListApi;
    private static KapaiApi kapaiApi;
    private static LoveApi loveapi;


    private static OkHttpClient okHttpClient = getNewClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    //添加打印日志的调试工具
    private static OkHttpClient getNewClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    public static MainPageBannerList getMainPageBannerList(){

        if (mainPageBannerList == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BaseUrl)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            mainPageBannerList = retrofit.create(MainPageBannerList.class);
        }
        return mainPageBannerList;
    }


    /**
     *
     * 新闻页面List
     */

    public static  ItemListApi getNewList(){

        if (itemListApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BaseUrl)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            itemListApi = retrofit.create(ItemListApi.class);
        }
        return itemListApi;


    }


    /**
     *
     * 卡牌类展示
     */

    public  static KapaiApi getKapai(){

        if (kapaiApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BaseUrl)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            kapaiApi = retrofit.create(KapaiApi.class);
        }
        return kapaiApi;

    }

    /**
     * 得到送爱心数据
     */

    public static LoveApi getLove(){
        if (loveapi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BaseUrl)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            loveapi = retrofit.create(LoveApi.class);
        }
        return loveapi;

    }




}
