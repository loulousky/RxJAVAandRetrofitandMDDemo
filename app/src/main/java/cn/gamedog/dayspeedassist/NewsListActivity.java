package cn.gamedog.dayspeedassist;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.LayoutRipple;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.gamedog.dayspeedassist.adapter.ItemDataBaseAdapter;
import cn.gamedog.dayspeedassist.bean.BaseData;
import cn.gamedog.dayspeedassist.bean.Itemdata;
import cn.gamedog.dayspeedassist.nucleus.NucleusAppCompatActivity;
import cn.gamedog.dayspeedassist.presenter.NewsListPresenter;
import cn.gamedog.dayspeedassist.views.PullLoadMoreRecyclerView;
import nucleus.factory.RequiresPresenter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@RequiresPresenter(NewsListPresenter.class)
public class NewsListActivity extends NucleusAppCompatActivity<NewsListPresenter>  {


    @Bind(R.id.close_btn)
    ImageView closeBtn;
    @Bind(R.id.mode_btn_layout)
    LayoutRipple modeBtnLayout;
    @Bind(R.id.searched)
    EditText searched;
    @Bind(R.id.cancle)
    ImageView cancle;
    @Bind(R.id.btn_search)
    ButtonFlat btnSearch;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private List<Itemdata> datalist = new ArrayList<>();
    private ItemDataBaseAdapter baseAdapter;

    int page=0;
    public  String type;
    private  String getType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        getType= getIntent().getStringExtra("type");
        type=getIntent().getStringExtra("typeid");

        ButterKnife.bind(this);
        loadlisn();

    }



    public void setPage_plus(){

         page++;
    }

    public void setPage_cancle(){
        if(page>0)
            page--;
    }

    private void loadlisn() {
        pullLoadMoreRecyclerView.setLinearLayout();
        baseAdapter = new ItemDataBaseAdapter(this,datalist);

        pullLoadMoreRecyclerView.setRefreshing(true);
        if(getType.equals("typeid")) {
            getPresenter().loadrefush(type,null);
        }else{
            getPresenter().loadrefush(null,type);

        }
       pullLoadMoreRecyclerView.setColorSchemeResources(android.R.color.holo_red_dark,android.R.color.holo_blue_dark);
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

                if(getType.equals("typeid")) {
                    getPresenter().loadrefush(type,null);
                }else{
                    getPresenter().loadrefush(null,type);
                }

            }
            @Override
            public void onLoadMore() {
                setPage_plus();

                if(getType.equals("typeid")) {
                    getPresenter().loadmore(page,type,null);
                }else{
                    getPresenter().loadmore(page,null,type);
                }

            }
        });
        pullLoadMoreRecyclerView.setAdapter(baseAdapter);
    }
    public  void onLoadMoresetList(List<Itemdata> data){
        datalist.addAll(data);
        baseAdapter.notifyDataSetChanged();

    }
    public  void onRefreshsetList(List<Itemdata> data){
        datalist.clear();
        datalist.addAll(data);
        baseAdapter.notifyDataSetChanged();

    }

    public  void clearState(){
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
