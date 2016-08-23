package cn.gamedog.dayspeedassist.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.materialdesign.views.LayoutRipple;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.gamedog.dayspeedassist.NewsListActivity;
import cn.gamedog.dayspeedassist.R;

/**
 * 首页导航 2
 *
 * @author mengfanlu/liuhai/zhangyan
 */
public class GudgeFragmentwo extends Fragment {

    @Bind(R.id.iv_1)
    ImageView iv1;
    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.layout_xwzx)
    LayoutRipple layoutXwzx;
    @Bind(R.id.iv_2)
    ImageView iv2;
    @Bind(R.id.layout_xszn)
    LayoutRipple layoutXszn;
    @Bind(R.id.iv_3)
    ImageView iv3;
    @Bind(R.id.layout_sm)
    LayoutRipple layoutSm;
    @Bind(R.id.iv_4)
    ImageView iv4;
    @Bind(R.id.layout_kztj)
    LayoutRipple layoutKztj;
    @Bind(R.id.iv_5)
    ImageView iv5;
    @Bind(R.id.layout_mxms)
    LayoutRipple layoutMxms;
    @Bind(R.id.iv_6)
    ImageView iv6;
    @Bind(R.id.layout_ttgl)
    LayoutRipple layoutTtgl;
    @Bind(R.id.igv_yxtj)
    ImageView igvYxtj;
    @Bind(R.id.tv_yxtj)
    TextView tvYxtj;
    @Bind(R.id.layout_zhsp)
    LayoutRipple layoutZhsp;
    @Bind(R.id.iv_8)
    ImageView iv8;
    @Bind(R.id.layout_dzsp)
    LayoutRipple layoutDzsp;
    @Bind(R.id.iv_9)
    ImageView iv9;
    @Bind(R.id.layout_lslb)
    LayoutRipple layoutLslb;
    private View secondView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        secondView = View.inflate(getActivity(), R.layout.second_menu, null);
        ButterKnife.bind(this, secondView);

        loadclick();
        return secondView;
    }


    private void loadclick(){
        layoutXwzx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),NewsListActivity.class);
                intent.putExtra("typeid", 2941+"");
                intent.putExtra("type", "typeid");
                startActivity(intent);
            }
        });

        layoutXszn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),NewsListActivity.class);
                intent.putExtra("typeid", 3465+"");
                intent.putExtra("type", "typeid");
                startActivity(intent);
            }
        });



    }



    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
