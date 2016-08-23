package cn.gamedog.dayspeedassist.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import cn.gamedog.dayspeedassist.LoveActivity;
import cn.gamedog.dayspeedassist.bean.BaseLoveData;
import cn.gamedog.dayspeedassist.http.api.HttpUtils;
import nucleus.presenter.RxPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Tech on 2016/8/16.
 */
public class LovePresenter extends RxPresenter<LoveActivity>{
    private static final int REQUEST_ITEMS = 1;
    public static final String DEFAULT_NAME = "NEWLIST";
    private String name = DEFAULT_NAME;
    private static final String NAME_KEY = NewsListPresenter.class.getName() + "#name";


    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        if(savedState != null)
            name = savedState.getString(NAME_KEY);



    }






    public void loadrefush(){


        Subscriber<BaseLoveData> susber=new Subscriber<BaseLoveData>() {
            @Override
            public void onCompleted() {

                getView().clearState();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseLoveData baseData) {

                getView().onRefreshsetList(baseData.getData());
            }
        };



        add(HttpUtils.getLove().getLoveData(0).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(susber));






    }



    public void loadmore(final int page){




        Subscriber<BaseLoveData> susber=new Subscriber<BaseLoveData>() {
            @Override
            public void onCompleted() {


                getView().clearState();
            }

            @Override
            public void onError(Throwable e) {
                getView().clearState();
                getView().setPage_cancle();
            }

            @Override
            public void onNext(BaseLoveData baseData) {

                getView().onLoadMoresetList(baseData.getData());
            }
        };



        add(HttpUtils.getLove().getLoveData(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(susber));









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


    public Observable<BaseLoveData> refush(){


        return null;
    }








}
