package cn.gamedog.dayspeedassist.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import cn.gamedog.dayspeedassist.Ty3LinePage;
import cn.gamedog.dayspeedassist.bean.BaseData;
import cn.gamedog.dayspeedassist.http.api.HttpUtils;
import nucleus.presenter.RxPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Tech on 2016/8/16.
 */
public class TyPresenter extends RxPresenter<Ty3LinePage>{


    private static final int REQUEST_ITEMS = 1;
    public static final String DEFAULT_NAME = "NEWLIST";
    private String name = DEFAULT_NAME;
    private static final String NAME_KEY = NewsListPresenter.class.getName() + "#name";


    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        if(savedState != null)
            name = savedState.getString(NAME_KEY);
//        restartableLatestCache(REQUEST_ITEMS, new Func0<Observable<BaseData>>() {
//            @Override
//            public Observable<BaseData> call() {
//                return HttpUtils.getNewList().getList(0,getView().type);
//            }
//        }, new Action2<NewsListActivity, BaseData>() {
//            @Override
//            public void call(NewsListActivity newsListActivity, BaseData baseData) {
//
//                newsListActivity.onRefreshsetList(baseData.getData());
//
//            }
//        }, new Action2<NewsListActivity, Throwable>() {
//            @Override
//            public void call(NewsListActivity newsListActivity, Throwable throwable) {
//                Toast.makeText(newsListActivity,throwable.toString(),1).show();
//            }
//        });
//
//        if (savedState == null)
//            start(REQUEST_ITEMS);


    }






    public void loadrefush(final String type, final String sid){


        Subscriber<BaseData> susber=new Subscriber<BaseData>() {
            @Override
            public void onCompleted() {

                getView().clearState();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseData baseData) {

                getView().onRefreshsetList(baseData.getData());
            }
        };



        add(HttpUtils.getNewList().getList(0,type,sid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(susber));





    }



    public void loadmore(final int page, final String typeid, final String sid){




        Subscriber<BaseData> susber=new Subscriber<BaseData>() {
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
            public void onNext(BaseData baseData) {

                getView().onLoadMoresetList(baseData.getData());
            }
        };



        add( HttpUtils.getNewList().getList(page,typeid,sid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(susber));









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


    public Observable<BaseData> refush(){

        return null;
    }



}
