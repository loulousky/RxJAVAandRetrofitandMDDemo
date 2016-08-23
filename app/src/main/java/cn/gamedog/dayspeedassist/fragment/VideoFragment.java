package cn.gamedog.dayspeedassist.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.gamedog.dayspeedassist.R;
import cn.gamedog.dayspeedassist.adapter.KapaiDataAdapter;
import cn.gamedog.dayspeedassist.adapter.VideoDateAdapter;
import cn.gamedog.dayspeedassist.bean.Itemdata;
import cn.gamedog.dayspeedassist.nucleus.NucleusSupportFragment;
import cn.gamedog.dayspeedassist.presenter.TJFragmentPresenter;
import cn.gamedog.dayspeedassist.presenter.VideoPresenter;
import cn.gamedog.dayspeedassist.views.PullLoadMoreRecyclerView;
import nucleus.factory.RequiresPresenter;


@RequiresPresenter(VideoPresenter.class)
public class VideoFragment extends NucleusSupportFragment<VideoPresenter> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;

    // TODO: Rename and change types of parameters
    private String typeid;
    private String sid;
    private View view;
    private int page=0;
    private boolean isfrise=true;
    private List<Itemdata> datalist = new ArrayList<>();

    private VideoDateAdapter baseAdapter;
    public VideoFragment(String typeid, String sid) {
        this.typeid = typeid;
        this.sid = sid;
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_video, null);
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
        baseAdapter = new VideoDateAdapter(getActivity(),datalist);
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
