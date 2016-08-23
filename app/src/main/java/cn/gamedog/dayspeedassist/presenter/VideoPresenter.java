package cn.gamedog.dayspeedassist.presenter;

import android.os.Bundle;

import cn.gamedog.dayspeedassist.bean.BaseData;
import cn.gamedog.dayspeedassist.fragment.TjFragment;
import cn.gamedog.dayspeedassist.fragment.VideoFragment;
import cn.gamedog.dayspeedassist.http.api.HttpUtils;
import nucleus.presenter.RxPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by Tech on 2016/8/16.
 */
public class VideoPresenter  extends RxPresenter<VideoFragment> {
    private static final int REQUEST_ITEMS = 1;
    public static final String DEFAULT_NAME = "Tjfragment";
    private String name = DEFAULT_NAME;
    private static final String NAME_KEY = TJFragmentPresenter.class.getName() + "#name";
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);


    }

    @Override
    protected void onTakeView(VideoFragment tjFragment) {
        super.onTakeView(tjFragment);


        tjFragment.setRefush();




    }

    public void loadrefush(final  String type, final  String sid){
        restartableLatestCache(0, new Func0<Observable<BaseData>>() {
            @Override
            public Observable<BaseData> call() {
                return HttpUtils.getKapai().getKapai(0,type,sid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        }, new Action2<VideoFragment, BaseData>() {
            @Override
            public void call(VideoFragment tjFragment, BaseData baseData) {

                tjFragment.onRefreshsetList(baseData.getData());
                tjFragment.clearState();


            }
        }, new Action2<VideoFragment, Throwable>() {
            @Override
            public void call(VideoFragment tjFragment, Throwable throwable) {

                tjFragment.clearState();
            }
        });

        start(0);

//        Subscriber<BaseData> susber=new Subscriber<BaseData>() {
//            @Override
//            public void onCompleted() {
//                getView().clearState();
//                getView().setPage_cancle();
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//                getView().clearState();
//                getView().setPage_cancle();
//            }
//
//            @Override
//            public void onNext(BaseData baseData) {
//
//                getView().onRefreshsetList(baseData.getData());
//                getView().clearState();
//            }
//        };
//        add(HttpUtils.getKapai().getKapai(0,type,sid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(susber));
    }



    public void loadmore(int page, String typeid,String sid){
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
                getView().clearState();
            }
        };
        add( HttpUtils.getKapai().getKapai(page,typeid,sid).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(susber));

    }
}
