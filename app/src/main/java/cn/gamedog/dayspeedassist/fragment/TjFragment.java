package cn.gamedog.dayspeedassist.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.gamedog.dayspeedassist.R;
import cn.gamedog.dayspeedassist.adapter.ItemDataBaseAdapter;
import cn.gamedog.dayspeedassist.adapter.KapaiDataAdapter;
import cn.gamedog.dayspeedassist.bean.Itemdata;
import cn.gamedog.dayspeedassist.nucleus.NucleusSupportFragment;
import cn.gamedog.dayspeedassist.presenter.TJFragmentPresenter;
import cn.gamedog.dayspeedassist.views.PullLoadMoreRecyclerView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by Tech on 2016/8/15.
 */

@RequiresPresenter(TJFragmentPresenter.class)
public class TjFragment extends NucleusSupportFragment<TJFragmentPresenter> {

    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private List<Itemdata> datalist = new ArrayList<>();

    private KapaiDataAdapter baseAdapter;
    private String typeid;
    private String sid;
    private int page=0;
    private boolean isfrise=true;
    public TjFragment(String typeid,String sid) {
        super();
        this.typeid=typeid;
        this.sid=sid;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.tj_fragment, null);
        ButterKnife.bind(this, view);
        loadView();
        return view;
    }
    public void setPage_plus(){
        page++;
    }

    public void setPage_cancle(){
        if(page>0)
            page--;
    }

    private void loadView(){


     //   pullLoadMoreRecyclerView.ad
        baseAdapter = new KapaiDataAdapter(getActivity(),datalist);
        getPresenter().loadrefush(typeid,sid);
        pullLoadMoreRecyclerView.setGridLayout(2);//参数为列数
        pullLoadMoreRecyclerView.setColorSchemeResources(android.R.color.holo_red_dark,android.R.color.holo_blue_dark);
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {



                    getPresenter().loadrefush(typeid,sid);



            }
            @Override
            public void onLoadMore() {
                setPage_plus();


                    getPresenter().loadmore(page,typeid,sid);


            }
        });
        pullLoadMoreRecyclerView.setAdapter(baseAdapter);
    }


    public void setRefush(){
        if (!pullLoadMoreRecyclerView.isRefresh()&&isfrise) {
            pullLoadMoreRecyclerView.setRefreshing(true);
            isfrise=false;
        }
    }

    public  void onLoadMoresetList(List<Itemdata> data){
        datalist.addAll(data);
        baseAdapter.notifyDataSetChanged();

    }
    public  void onRefreshsetList(List<Itemdata> data){
        datalist.clear();
        datalist.addAll(data);
        baseAdapter.notifyDataSetChanged();
        clearState();

    }

    public  void clearState(){
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



}
