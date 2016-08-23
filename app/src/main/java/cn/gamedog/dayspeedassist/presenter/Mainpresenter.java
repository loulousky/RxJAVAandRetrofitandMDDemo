package cn.gamedog.dayspeedassist.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.widget.Toast;


import cn.gamedog.dayspeedassist.MainActivity;
import cn.gamedog.dayspeedassist.bean.BaseData;
import cn.gamedog.dayspeedassist.http.api.HttpUtils;
import nucleus.factory.RequiresPresenter;
import nucleus.presenter.Presenter;
import nucleus.presenter.RxPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HTTP;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by Tech on 2016/7/29.
 */


public class Mainpresenter extends RxPresenter<MainActivity> {
    private static final int REQUEST_ITEMS = 1;
    public static final String DEFAULT_NAME = "Main";
    private String name = DEFAULT_NAME;
    private static final String NAME_KEY = Mainpresenter.class.getName() + "#name";
    @Override
    protected void onCreate(@Nullable Bundle savedState) {
        super.onCreate(savedState);

        if (savedState != null)
            name = savedState.getString(NAME_KEY);


        restartableLatestCache(REQUEST_ITEMS, new Func0<Observable<BaseData>>() {
            @Override
            public Observable<BaseData> call() {
                return HttpUtils.getMainPageBannerList().getBannerList("2940").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        }, new Action2<MainActivity, BaseData>() {
            @Override
            public void call(MainActivity mainActivity, BaseData baseData) {
                mainActivity.setBannerData(baseData.getData());
            }
        }, new Action2<MainActivity, Throwable>() {
            @Override
            public void call(MainActivity mainActivity, Throwable throwable) {

                Toast.makeText(mainActivity,throwable.toString(),1).show();
            }
        });
        if (savedState == null)
            start(REQUEST_ITEMS);

    }



    @Override
    public void onSave(@NonNull Bundle state) {
        super.onSave(state);
        state.putString(NAME_KEY, name);
    }



    public void request(String name) {
        this.name = name;
        start(REQUEST_ITEMS);
    }






}
