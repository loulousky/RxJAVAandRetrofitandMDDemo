package cn.gamedog.dayspeedassist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.LayoutRipple;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.gamedog.dayspeedassist.bean.Itemdata;
import cn.gamedog.dayspeedassist.bean.LoveData;
import cn.gamedog.dayspeedassist.views.PullLoadMoreRecyclerView;

public class LoveActivity extends AppCompatActivity {

    @Bind(R.id.mode_btn)
    ImageView modeBtn;
    @Bind(R.id.mode_btn_layout)
    LayoutRipple modeBtnLayout;
    @Bind(R.id.btn_search)
    ButtonFlat btnSearch;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.listview)
    PullLoadMoreRecyclerView listview;
    @Bind(R.id.iv_addtiezi)
    ImageView ivAddtiezi;
    @Bind(R.id.layout_liuyan)
    LayoutRipple layoutLiuyan;
    @Bind(R.id.activity_love)
    RelativeLayout activityLove;
    List<LoveData> datalist=new ArrayList<>();
    int page=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        ButterKnife.bind(this);
    }

    private void loadview(){
        listview.setLinearLayout();
        listview.setRefreshing(true);
    }


    public void setPage_plus(){
        page++;
    }

    public void setPage_cancle(){
        if(page>0)
            page--;
    }


    public  void onLoadMoresetList(List<LoveData> data){
        datalist.addAll(data);
      //  baseAdapter.notifyDataSetChanged();
    }
    public  void onRefreshsetList(List<LoveData> data){
        datalist.clear();
        datalist.addAll(data);
     //   baseAdapter.notifyDataSetChanged();
    }

    public  void clearState(){
        listview.setPullLoadMoreCompleted();
    }

}
